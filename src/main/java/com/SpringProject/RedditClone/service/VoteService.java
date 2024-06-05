package com.SpringProject.RedditClone.service;

import com.SpringProject.RedditClone.dto.VoteDto;
import com.SpringProject.RedditClone.exceptions.PostNotFoundException;
import com.SpringProject.RedditClone.exceptions.SpringRedditException;
import com.SpringProject.RedditClone.model.Post;
import com.SpringProject.RedditClone.model.Vote;
import com.SpringProject.RedditClone.repository.PostRepository;
import com.SpringProject.RedditClone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.SpringProject.RedditClone.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
//        if (UPVOTE.equals(voteDto.getVoteType())) {
//            post.setVoteCount(post.getVoteCount() + 1);
//        } else {
//
//            post.setVoteCount(post.getVoteCount() - 1);
//        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() != null ? post.getVoteCount() + 1 : 1);
        } else {
            post.setVoteCount(post.getVoteCount() != null ? post.getVoteCount() - 1 : -1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
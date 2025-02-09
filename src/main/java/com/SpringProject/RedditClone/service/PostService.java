package com.SpringProject.RedditClone.service;

import com.SpringProject.RedditClone.dto.PostRequest;
import com.SpringProject.RedditClone.dto.PostResponse;
import com.SpringProject.RedditClone.exceptions.PostNotFoundException;
import com.SpringProject.RedditClone.exceptions.SubredditNotFoundException;
import com.SpringProject.RedditClone.mapper.PostMapper;
import com.SpringProject.RedditClone.model.Post;
import com.SpringProject.RedditClone.model.Subreddit;
import com.SpringProject.RedditClone.model.User;
import com.SpringProject.RedditClone.repository.PostRepository;
import com.SpringProject.RedditClone.repository.SubredditRepository;
import com.SpringProject.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

//@Service
//@AllArgsConstructor
//@Slf4j
//@Transactional
//public class PostService {
//
//    private final PostRepository postRepository;
//    private final SubredditRepository subredditRepository;
//    private final UserRepository userRepository;
//    private final AuthService authService;
//    private final PostMapper postMapper;
//
//    public void save(PostRequest postRequest) {
//        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
//                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
//        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
//    }
//
//    @Transactional(readOnly = true)
//    public PostResponse getPost(Long id) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new PostNotFoundException(id.toString()));
//        return postMapper.mapToDto(post);
//    }
//
//    @Transactional(readOnly = true)
//    public List<PostResponse> getAllPosts() {
//        return postRepository.findAll()
//                .stream()
//                .map(postMapper::mapToDto)
//                .collect(toList());
//    }
//
//    @Transactional(readOnly = true)
//    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
//        Subreddit subreddit = subredditRepository.findById(subredditId)
//                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
//        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
//        return posts.stream().map(postMapper::mapToDto).collect(toList());
//    }
//
//    @Transactional(readOnly = true)
//    public List<PostResponse> getPostsByUsername(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//        return postRepository.findByUser(user)
//                .stream()
//                .map(postMapper::mapToDto)
//                .collect(toList());
//    }

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
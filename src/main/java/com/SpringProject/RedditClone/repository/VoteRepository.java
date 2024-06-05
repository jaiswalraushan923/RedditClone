package com.SpringProject.RedditClone.repository;

import com.SpringProject.RedditClone.model.Post;
import com.SpringProject.RedditClone.model.User;
import com.SpringProject.RedditClone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
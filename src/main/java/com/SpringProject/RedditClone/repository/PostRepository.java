package com.SpringProject.RedditClone.repository;

import com.SpringProject.RedditClone.model.Post;
import com.SpringProject.RedditClone.model.Subreddit;
import com.SpringProject.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
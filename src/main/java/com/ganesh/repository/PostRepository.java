package com.ganesh.repository;

import com.ganesh.model.Comment;
import com.ganesh.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Comment> findByPostId(long postId);
}

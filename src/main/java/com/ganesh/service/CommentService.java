package com.ganesh.service;

import com.ganesh.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto newComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateComment(Long postId, long commentId,CommentDto commentDto);
    void deleteComment(Long postId, Long commentId);
}

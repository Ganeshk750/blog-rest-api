package com.ganesh.service;

import com.ganesh.dto.PostDto;
import com.ganesh.dto.PostResponseDto;

public interface PostService {

    PostDto newPost(PostDto postDto);
    PostResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(long id);
}

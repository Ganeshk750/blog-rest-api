package com.ganesh.controller;

import com.ganesh.dto.PostDto;
import com.ganesh.dto.PostResponseDto;
import com.ganesh.service.PostService;
import com.ganesh.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value="CURD Rest APIs for Post resource")
@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation(value="Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.newPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation(value="Get All Post REST API")
    @GetMapping
    public PostResponseDto getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value= "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

    }

    @ApiOperation(value="Get Post By Id REST API")
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name="postId")long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @ApiOperation(value="Update Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name="postId") long postId) {
        PostDto response = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name="postId") long postId){
        postService.deletePostById(postId);
        return new ResponseEntity<>("Post deleted successfully.", HttpStatus.OK);
    }


}

package com.dhanesh.social.controllers;

import com.dhanesh.social.models.Post;
import com.dhanesh.social.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<Post>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestPart("post") Post post,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Post createdPost = postService.createPost(post, file);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id,
            @RequestPart("post") Post post,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return postService.getPostById(id)
                .map(existingPost -> {
                    existingPost.setDescription(post.getDescription());
                    existingPost.setLikes(post.getLikes());
                    existingPost.setComments(post.getComments());
                    try {
                        Post updatedPost = postService.updatePost(existingPost, file);
                        return ResponseEntity.ok(updatedPost);
                    } catch (IOException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(new ResponseEntity<Post>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postService.getPostById(id).isPresent()) {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
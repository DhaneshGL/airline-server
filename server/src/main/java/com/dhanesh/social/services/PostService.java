package com.dhanesh.social.services;

import com.dhanesh.social.models.FileAttachment;
import com.dhanesh.social.models.Post;
import com.dhanesh.social.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileUrl = fileStorageService.storeFile(file);
            String fileType = file.getContentType() != null ? file.getContentType().split("/")[0] : "unknown";
            post.setFile(new FileAttachment(fileUrl, fileType));
        }
        return postRepository.save(post);
    }

    public Post updatePost(Post post, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileUrl = fileStorageService.storeFile(file);
            String fileType = file.getContentType() != null ? file.getContentType().split("/")[0] : "unknown";
            post.setFile(new FileAttachment(fileUrl, fileType));
        }
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
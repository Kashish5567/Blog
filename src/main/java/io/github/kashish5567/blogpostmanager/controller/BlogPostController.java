package io.github.kashish5567.blogpostmanager.controller;

import io.github.kashish5567.blogpostmanager.model.BlogPost;
import io.github.kashish5567.blogpostmanager.service.BlogPostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogposts")
@Validated
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(@Qualifier("databaseBlogPostService") BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        BlogPost savedPost = blogPostService.createBlogPost(blogPost);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
        List<BlogPost> posts = blogPostService.getAllBlogPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable @Positive long id) {
        BlogPost post = blogPostService.getBlogPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/byTitle")
    public ResponseEntity<BlogPost> getBlogPostByTitle(@RequestParam String title) {
        BlogPost post = blogPostService.getBlogPostByTitle(title);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}/content")
    public ResponseEntity<BlogPost> updateContent(
            @PathVariable @Positive long id,
            @RequestParam @Size(min = 10, max = 10000) String newContent
    ) {
        BlogPost post = blogPostService.updateBlogPostContent(id, newContent);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}/title")
    public ResponseEntity<BlogPost> updateTitle(
            @PathVariable @Positive long id,
            @RequestParam @Size(min = 5, max = 100) String newTitle
    ) {
        BlogPost post = blogPostService.updateBlogPostTitle(id, newTitle);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}/author")
    public ResponseEntity<BlogPost> updateAuthor(
            @PathVariable @Positive long id,
            @RequestParam @Size(min = 3, max = 50) String newAuthor
    ) {
        BlogPost post = blogPostService.updateBlogPostAuthor(id, newAuthor);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}/coverImage")
    public ResponseEntity<BlogPost> updateCoverImage(
            @PathVariable @Positive long id,
            @RequestParam @URL String newCoverImageUrl
    ) {
        BlogPost post = blogPostService.updateBlogPostCoverImage(id, newCoverImageUrl);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}/togglePublish")
    public ResponseEntity<BlogPost> togglePublishStatus(@PathVariable @Positive long id) {
        BlogPost post = blogPostService.togglePublishStatus(id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable @Positive long id) {
        boolean deleted = blogPostService.deleteBlogPost(id);
        if (deleted) {
            return ResponseEntity.ok("Blog post deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog post not found");
    }
}


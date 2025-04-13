package io.github.kashish5567.blogpostmanager.service;

import io.github.kashish5567.blogpostmanager.blog.exceptions.BlogPostNotFoundException;
import io.github.kashish5567.blogpostmanager.blog.exceptions.InvalidBlogPostException;
import io.github.kashish5567.blogpostmanager.blog.model.BlogPost;
import io.github.kashish5567.blogpostmanager.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseBlogPostService implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public DatabaseBlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public BlogPost createPost(BlogPost blogPost) {
        if (blogPost == null || blogPost.getTitle() == null || blogPost.getTitle().isEmpty()) {
            throw new InvalidBlogPostException("Blog title must not be null or empty");
        }
        return blogPostRepository.save(blogPost);
    }

    @Override
    public List<BlogPost> getAllPosts() {
        List<BlogPost> posts = blogPostRepository.findAll();
        if (posts.isEmpty()) {
            throw new BlogPostNotFoundException("No blog posts found");
        }
        return posts;
    }

    @Override
    public BlogPost getPostById(long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new BlogPostNotFoundException("Blog post with ID " + id + " not found"));
    }

    @Override
    public BlogPost getPostByTitle(String title) {
        return blogPostRepository.findByTitle(title)
                .orElseThrow(() -> new BlogPostNotFoundException("Blog post with title '" + title + "' not found"));
    }

    @Override
    public BlogPost updatePostContent(long id, String newContent) {
        if (newContent == null || newContent.isEmpty()) {
            throw new InvalidBlogPostException("Content must not be null or empty");
        }
        BlogPost blogPost = getPostById(id);
        blogPost.setContent(newContent);
        return blogPostRepository.save(blogPost);
    }
    @Override
    public BlogPost updatePostTitle(long id, String newTitle) {
        if (newTitle == null || newTitle.isEmpty()) {
            throw new InvalidBlogPostException("Title must not be null or empty");
        }
        BlogPost blogPost = getPostById(id);
        blogPost.setTitle(newTitle);
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updatePostAuthor(long id, String newAuthor) {
        if (newAuthor == null || newAuthor.isEmpty()) {
            throw new InvalidBlogPostException("Author must not be null or empty");
        }
        BlogPost blogPost = getPostById(id);
        blogPost.setAuthor(newAuthor);
        return blogPostRepository.save(blogPost);
    }

    @Override
    public boolean deletePost(long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return true;
        } else {
            throw new BlogPostNotFoundException("Blog post with ID " + id + " not found");
        }
    }
}

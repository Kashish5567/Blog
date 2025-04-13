package io.github.kashish5567.blogpostmanager.service;

import io.github.kashish5567.blogpostmanager.model.BlogPost;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing BlogPost operations.
 * This class handles business logic related to BlogPosts.
 */
@Service
public class InMemoryBlogPostService implements BlogPostService {

    private final List<BlogPost> blogPosts;

    public InMemoryBlogPostService() {
        this.blogPosts = new ArrayList<>();
    }

    /**
     * Creates and stores a new blog post.
     *
     * @param blogPost The blog post to be created
     * @return The newly created blog post
     */
    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        blogPosts.add(blogPost);
        return blogPost;
    }

    /**
     * Retrieves all blog posts.
     *
     * @return List of all blog posts
     */
    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPosts;
    }

    /**
     * Finds a blog post by its ID.
     *
     * @param id The ID of the blog post
     * @return The found blog post or null
     */
    @Override
    public BlogPost getBlogPostById(long id) {
        return blogPosts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the title of a blog post.
     *
     * @param id The ID of the blog post
     * @param newTitle The new title
     * @return The updated blog post or null
     */
    @Override
    public BlogPost updateBlogPostTitle(long id, String newTitle) {
        BlogPost post = getBlogPostById(id);
        if (post != null) {
            post.setTitle(newTitle);
        }
        return post;
    }

    /**
     * Updates the content of a blog post.
     *
     * @param id The ID of the blog post
     * @param newContent The new content
     * @return The updated blog post or null
     */
    @Override
    public BlogPost updateBlogPostContent(long id, String newContent) {
        BlogPost post = getBlogPostById(id);
        if (post != null) {
            post.setContent(newContent);
        }
        return post;
    }

    /**
     * Updates the author of a blog post.
     *
     * @param id The ID of the blog post
     * @param newAuthor The new author name
     * @return The updated blog post or null
     */
    @Override
    public BlogPost updateBlogPostAuthor(long id, String newAuthor) {
        BlogPost post = getBlogPostById(id);
        if (post != null) {
            post.setAuthor(newAuthor);
        }
        return post;
    }

    /**
     * Deletes a blog post by ID.
     *
     * @param id The ID of the blog post
     * @return true if deleted, false otherwise
     */
    @Override
    public boolean deleteBlogPost(long id) {
        BlogPost post = getBlogPostById(id);
        if (post != null) {
            blogPosts.remove(post);
            return true;
        }
        return false;
    }
}

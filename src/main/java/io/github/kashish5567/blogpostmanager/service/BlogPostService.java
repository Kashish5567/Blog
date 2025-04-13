package io.github.kashish5567.blogpostmanager.service;

import io.github.kashish5567.blogpostmanager.exceptions.BlogPostNotFoundException;
import io.github.kashish5567.blogpostmanager.exceptions.BlogPostValidationException;
import io.github.kashish5567.blogpostmanager.model.BlogPost;

import java.util.List;

public interface BlogPostService {

    BlogPost createBlogPost(BlogPost blogPost) throws BlogPostValidationException;

    List<BlogPost> getAllBlogPosts() throws BlogPostNotFoundException;

    BlogPost getBlogPostById(long id) throws BlogPostNotFoundException;

    BlogPost getBlogPostByTitle(String title) throws BlogPostNotFoundException;

    BlogPost updateBlogPostContent(long id, String newContent) throws BlogPostValidationException;

    BlogPost updateBlogPostTitle(long id, String newTitle) throws BlogPostValidationException;

    BlogPost updateBlogPostAuthor(long id, String newAuthor) throws BlogPostValidationException;

    BlogPost updateBlogPostCoverImage(long id, String newCoverImageUrl) throws BlogPostValidationException;

    BlogPost togglePublishStatus(long id) throws BlogPostNotFoundException;

    boolean deleteBlogPost(long id) throws BlogPostNotFoundException;
}


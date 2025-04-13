package io.github.kashish5567.blogpostmanager.exception;

/**
 * Exception thrown when a blog post is not found in the database.
 * Used when searching for a blog post by ID or title.
 */
public class BlogPostNotFoundException extends RuntimeException {

    /**
     * Creates a new BlogPostNotFoundException with the specified message.
     * @param message The error message explaining why the blog post was not found.
     */
    public BlogPostNotFoundException(String message) {
        super(message);
    }
}

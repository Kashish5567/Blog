package io.github.kashish5567.blogpostmanager.exception;

/**
 * Exception thrown when a blog post fails validation.
 * Used when input data is missing required fields or contains invalid values.
 */
public class InvalidBlogPostException extends RuntimeException {

    /**
     * Creates a new InvalidBlogPostException with the specified message.
     * @param message The error message explaining why the blog post is invalid.
     */
    public InvalidBlogPostException(String message) {
        super(message);
    }
}

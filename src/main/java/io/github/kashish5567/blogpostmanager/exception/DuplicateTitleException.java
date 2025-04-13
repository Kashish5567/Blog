package io.github.kashish5567.blogpostmanager.exception;

/**
 * Exception thrown when attempting to create or update a blog post
 * with a title that already exists.
 */
public class DuplicateTitleException extends RuntimeException {

  /**
   * Creates a new DuplicateTitleException with the specified message.
   * @param message The error message explaining the duplication issue.
   */
  public DuplicateTitleException(String message) {
    super(message);
  }
}

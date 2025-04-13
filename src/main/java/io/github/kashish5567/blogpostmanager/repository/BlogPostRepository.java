package io.github.kashish5567.blogpostmanager.repository;

import io.github.kashish5567.blogpostmanager.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link BlogPost} entities in the database.
 * <p>
 * This interface extends {@link JpaRepository} which provides standard CRUD operations:
 * <ul>
 *     <li>{@code save(BlogPost entity)}: Saves a blog post entity to the database</li>
 *     <li>{@code findById(Long id)}: Finds a blog post by its ID</li>
 *     <li>{@code findAll()}: Retrieves all blog posts</li>
 *     <li>{@code deleteById(Long id)}: Deletes a blog post by its ID</li>
 *     <li>{@code delete(BlogPost entity)}: Deletes a specific blog post entity</li>
 *     <li>{@code count()}: Returns the total number of blog posts</li>
 *     <li>{@code existsById(Long id)}: Checks if a blog post with given ID exists</li>
 * </ul>
 * <p>
 * The {@code @Repository} annotation marks this interface as a Spring Data repository,
 * which allows Spring to find and configure it automatically.
 */
@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    /**
     * Finds a blog post by its title.
     *
     * @param title the title of the blog post to search for
     * @return an Optional containing the found blog post, or an empty Optional if not found
     */
    Optional<BlogPost> findByTitle(String title);

    /**
     * Finds all blog posts authored by a specific person.
     *
     * @param author the author's name
     * @return a list of blog posts by that author
     */
    List<BlogPost> findByAuthor(String author);

    /**
     * Finds blog posts whose author name contains the specified keyword (case insensitive).
     *
     * @param author the keyword in the author's name
     * @return a list of matching blog posts
     */
    List<BlogPost> findByAuthorContainingIgnoreCase(String author);

    /**
     * Finds all published blog posts.
     *
     * @return a list of published blog posts
     */
    List<BlogPost> findByPublishedTrue();

    /**
     * Finds blog posts created between a date range, ordered by creation time.
     *
     * @param startDate start date (inclusive)
     * @param endDate end date (inclusive)
     * @return a list of blog posts created within the range
     */
    @Query("SELECT b FROM BlogPost b WHERE b.createdAt BETWEEN ?1 AND ?2 ORDER BY b.createdAt ASC")
    List<BlogPost> findPostsInDateRange(LocalDateTime startDate, LocalDateTime endDate);
}


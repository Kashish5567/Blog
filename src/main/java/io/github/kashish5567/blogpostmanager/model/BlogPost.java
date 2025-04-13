package io.github.kashish5567.blogpostmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /**
     * The title of the blog post.
     * Cannot be blank and must be between 5 and 100 characters.
     */
    @NotBlank(message = "Blog post title is required.")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters.")
    @Column(nullable = false)
    private String title;

    /**
     * The content of the blog post.
     * Cannot be blank and must be at least 20 characters.
     */
    @NotBlank(message = "Content is required.")
    @Size(min = 20, message = "Content must be at least 20 characters long.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * The author's name.
     */
    @NotBlank(message = "Author name is required.")
    @Size(min = 3, max = 50, message = "Author name must be between 3 and 50 characters.")
    @Column(nullable = false)
    private String author;

    /**
     * Optional cover image for the blog post.
     */
    @URL(message = "Cover image must be a valid URL.")
    private String coverImage;

    /**
     * Whether the blog post is published.
     */
    @NotNull
    @Column(nullable = false)
    private boolean published = false;

    /**
     * Timestamp for when the post was created.
     */
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public BlogPost() {}

    public BlogPost(String title, String content, String author, String coverImage, boolean published, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.coverImage = coverImage;
        this.published = published;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // equals, hashCode, and toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlogPost blogPost)) return false;
        return id == blogPost.id &&
                published == blogPost.published &&
                Objects.equals(title, blogPost.title) &&
                Objects.equals(content, blogPost.content) &&
                Objects.equals(author, blogPost.author) &&
                Objects.equals(coverImage, blogPost.coverImage) &&
                Objects.equals(createdAt, blogPost.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author, coverImage, published, createdAt);
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", published=" + published +
                ", createdAt=" + createdAt +
                '}';
    }
}



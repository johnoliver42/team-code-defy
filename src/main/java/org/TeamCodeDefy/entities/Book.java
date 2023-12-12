package org.TeamCodeDefy.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Hibernate entity for Book table.
 * Auto generated using JPA Buddy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Books")
public class Book {

    @JsonProperty("id")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @JsonProperty("readingList")
    @JsonBackReference
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ReadingList_id", nullable = false)
    private ReadingList readingList;

    @JsonProperty("isbn")
    @Column(name = "isbn")
    private String isbn;

    @JsonProperty("isRead")
    @NotNull
    @Column(name = "isRead", nullable = false)
    private Boolean isRead = false;

    @JsonProperty("lastPageRead")
    @NotNull
    @Column(name = "lastPageRead", nullable = false)
    private Integer lastPageRead = 0;

    @JsonProperty("readingListSequenceNumber")
    @Column(name = "readingListSequenceNumber")
    private Integer readingListSequenceNumber = null;

    @JsonProperty("publisher")
    @Size(max = 100)
    @Column(name = "publisher", length = 100)
    private String publisher;

    @JsonProperty("language")
    @Size(max = 50)
    @Column(name = "language", length = 50)
    private String language;

    @JsonProperty("author")
    @Size(max = 100)
    @Column(name = "author", length = 100)
    private String author;

    @JsonProperty("title")
    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @JsonProperty("pageCount")
    @Column(name = "pageCount")
    private Integer pageCount;

    @JsonProperty("averageRating")
    @Size(max = 4)
    @Column(name = "averageRating", length = 4)
    private String averageRating;

    @JsonProperty("description")
    @Lob
    @Column(name = "description")
    private String description;

    @JsonProperty("thumbnailLink")
    @Lob
    @Column(name = "thumbnailLink")
    private String thumbnailLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReadingList getReadingList() {
        return readingList;
    }

    public void setReadingList(ReadingList readingList) {
        this.readingList = readingList;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Integer getLastPageRead() {
        return lastPageRead;
    }

    public void setLastPageRead(Integer lastPageRead) {
        this.lastPageRead = lastPageRead;
    }

    public Integer getReadingListSequenceNumber() {
        return readingListSequenceNumber;
    }

    public void setReadingListSequenceNumber(Integer readingListSequenceNumber) {
        this.readingListSequenceNumber = readingListSequenceNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
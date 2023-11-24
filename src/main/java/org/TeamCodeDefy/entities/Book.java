package org.TeamCodeDefy.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Hibernate entity for Book table.
 * Auto generated using JPA Buddy
 */
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ReadingList_id", nullable = false)
    private ReadingList readingList;

    @Column(name = "isbn")
    private String isbn;

    @NotNull
    @Column(name = "isRead", nullable = false)
    private Boolean isRead = false;

    @NotNull
    @Column(name = "lastPageRead", nullable = false)
    private Integer lastPageRead;

    @NotNull
    @Column(name = "readingListSequenceNumber", nullable = false)
    private Integer readingListSequenceNumber;

    @Size(max = 100)
    @Column(name = "publisher", length = 100)
    private String publisher;

    @Size(max = 50)
    @Column(name = "language", length = 50)
    private String language;

    @Size(max = 100)
    @Column(name = "author", length = 100)
    private String author;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "pageCount")
    private Integer pageCount;

    @Size(max = 4)
    @Column(name = "averageRating", length = 4)
    private String averageRating;

    @Lob
    @Column(name = "description")
    private String description;

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

}
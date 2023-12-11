package org.TeamCodeDefy.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.TeamCodeDefy.persistance.ReadingListIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Hibernate entity for ReadingList table.
 * Auto generated using JPA Buddy
 */
@Entity
public class ReadingList {

    @JsonProperty("id")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = ReadingListIdGenerator.generatorName)
    @GenericGenerator(name = ReadingListIdGenerator.generatorName, strategy = "org.TeamCodeDefy.persistance.ReadingListIdGenerator")
    private Integer id;

    @JsonProperty("listName")
    @Size(max = 100)
    @NotNull
    @Column(name = "listName", nullable = false, length = 100)
    private String listName;

    @JsonProperty("createDate")
    @NotNull
    @CreationTimestamp
    @Column(name = "createDate", nullable = false)
    private Timestamp createDate;

    @JsonProperty("books")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "readingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}
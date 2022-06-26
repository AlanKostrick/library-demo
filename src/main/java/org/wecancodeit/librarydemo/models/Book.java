package org.wecancodeit.librarydemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    String title;
    private String description;
    @ManyToOne
    private Campus campus;
    @ManyToMany
    private Collection<Author> authors;
    @ManyToMany
    private Collection<HashTag> hashTags;

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Campus getCampus() {
        return campus;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }


    public Collection<HashTag> getHashTags(){
        return hashTags;
    }


    public Book(){

    }

    public Book(String title, String description, Campus campus, List<Author> authors, HashTag...hashTags) {
        this.title=title;
        this.description=description;
        this.campus=campus;
        this.authors= authors;
        this.hashTags = new ArrayList<>(Arrays.asList(hashTags));
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

    public void addHashTag(HashTag hashTagToAdd) {
        hashTags.add(hashTagToAdd);
    }

    public void deleteHashTag(HashTag hashTagToRemove) {
        hashTags.remove(hashTagToRemove);
    }
}

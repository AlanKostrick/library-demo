package org.wecancodeit.librarydemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wecancodeit.librarydemo.models.Author;
import org.wecancodeit.librarydemo.models.Book;
import org.wecancodeit.librarydemo.models.Campus;
import org.wecancodeit.librarydemo.models.HashTag;
import org.wecancodeit.librarydemo.repositories.AuthorRepository;
import org.wecancodeit.librarydemo.repositories.BookRepository;
import org.wecancodeit.librarydemo.repositories.CampusRepository;
import org.wecancodeit.librarydemo.repositories.HashTagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JPAWiringTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CampusRepository campusRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private HashTagRepository hashTagRepo;

    @Test
    public void campusShouldHaveAListOfBooks(){
        Campus testCampus = new Campus("Test Location");
        Campus testCampus2 = new Campus("Test Location2");
        Author testAuthor1 = new Author("Test firstName", "Test lastName");
        List<Author> authors = new ArrayList<>();
        authors.add(testAuthor1);
        Book testBook = new Book("Title", "Description", testCampus, authors);
        Book testBook2 = new Book("Title", "Description", testCampus2, authors);

        campusRepo.save(testCampus);
        campusRepo.save(testCampus2);
        authorRepo.save(testAuthor1);
        bookRepo.save(testBook);
        bookRepo.save(testBook2);

        entityManager.flush();
        entityManager.clear();

        Optional<Campus> retrievedCampusOpt = campusRepo.findById(testCampus.getId());
        Campus retrievedCampus = retrievedCampusOpt.get();
        assertThat(retrievedCampus.getBooks()).contains(testBook);
    }

    @Test
    public void booksShouldBeAbleToHaveMultipleAuthors(){
        Campus testCampus = new Campus("Test Location");
        Author testAuthor1 = new Author("Test firstName", "Test lastName");
        Author testAuthor2 = new Author("Test firstName2", "Test lastName2");
        List<Author> authors = new ArrayList<>();
        authors.add(testAuthor1);
        authors.add(testAuthor2);
        Book testBook1 = new Book("Title", "Description", testCampus, authors);
        Book testBook2 = new Book("Title", "Description", testCampus, authors);
        Book testBook3 = new Book("Title", "Description", testCampus, authors);
        campusRepo.save(testCampus);
        authorRepo.save(testAuthor1);
        authorRepo.save(testAuthor2);
        bookRepo.save(testBook1);
        bookRepo.save(testBook2);
        bookRepo.save(testBook3);

        entityManager.flush();
        entityManager.clear();

        Book retrievedBook = bookRepo.findById(testBook1.getId()).get();
        Author retrievedAuthor1 = authorRepo.findById(testAuthor1.getId()).get();
        Author retrievedAuthor2 = authorRepo.findById(testAuthor2.getId()).get();
        assertThat(retrievedBook.getAuthors()).contains(testAuthor1,testAuthor2);
    }

    @Test
    public void booksShouldHaveHashTags(){
        Campus testCampus = new Campus("Test Location");
        Author testAuthor1 = new Author("Test firstName", "Test lastName");
        Author testAuthor2 = new Author("Test firstName2", "Test lastName2");
        List<Author> authors = new ArrayList<>();
        authors.add(testAuthor1);
        authors.add(testAuthor2);
        Book testBook1 = new Book("Title", "Description", testCampus, authors);
        HashTag hashTag1 = new HashTag("HashTag1");
        HashTag hashTag2 = new HashTag("HashTag2");
        campusRepo.save(testCampus);
        authorRepo.save(testAuthor1);
        authorRepo.save(testAuthor2);
        bookRepo.save(testBook1);
        hashTagRepo.save(hashTag1);
        hashTagRepo.save(hashTag2);
        testBook1.addHashTag(hashTag1);
        testBook1.addHashTag(hashTag2);
        bookRepo.save(testBook1);

        entityManager.flush();
        entityManager.clear();

        Book retrievedBook = bookRepo.findById(testBook1.getId()).get();
        assertThat(retrievedBook.getHashTags()).contains(hashTag1, hashTag2);
    }
}

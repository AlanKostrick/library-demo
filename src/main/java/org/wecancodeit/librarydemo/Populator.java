package org.wecancodeit.librarydemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.librarydemo.models.Author;
import org.wecancodeit.librarydemo.models.Book;
import org.wecancodeit.librarydemo.models.Campus;
import org.wecancodeit.librarydemo.models.HashTag;
import org.wecancodeit.librarydemo.repositories.AuthorRepository;
import org.wecancodeit.librarydemo.repositories.BookRepository;
import org.wecancodeit.librarydemo.repositories.CampusRepository;
import org.wecancodeit.librarydemo.repositories.HashTagRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class Populator implements CommandLineRunner {

    @Resource
    private CampusRepository campusRepo;
    @Resource
    private AuthorRepository authorRepo;
    @Resource
    private BookRepository bookRepo;
    @Resource
    private HashTagRepository hashTagRepo;

    @Override
    public void run(String... args) throws Exception {

        HashTag java = new HashTag("Java");
        HashTag programming = new HashTag("Programming");
        hashTagRepo.save(java);
        hashTagRepo.save(programming);

        Campus columbus = new Campus("Columbus");
        Campus cleveland = new Campus("Cleveland");
        campusRepo.save(columbus);
        campusRepo.save(cleveland);

        Author sierra = new Author("Kathy","Sierra");
        Author bates = new Author("Burt", "Bates");
        Author beck = new Author("Kent", "Beck");
        Author fowler = new Author("Martin", "Fowler");
        Author martin = new Author("Micah", "Martin");
        authorRepo.save(sierra);
        authorRepo.save(bates);
        authorRepo.save(beck);
        authorRepo.save(fowler);
        authorRepo.save(martin);

        List<Author> headFirstAuthors = new ArrayList<>();
        headFirstAuthors.add(sierra);
        headFirstAuthors.add(bates);

        List<Author> tddByExampleAuthors = new ArrayList<>();
        tddByExampleAuthors.add(beck);

        List<Author> refactoringAuthors = new ArrayList<>();
        refactoringAuthors.add(fowler);

        List<Author> agileCSharpAuthors = new ArrayList<>();
        agileCSharpAuthors.add(martin);


        Book headfirstJava = new Book("Head First Java", "A great book to learn Java with", columbus, headFirstAuthors, java);
        Book tddByExample = new Book("TDD By Example", "The first book on TDD and still one of the best", columbus, tddByExampleAuthors);
        Book refactoring = new Book("Refactoring", "The first book to catalog the many refactorings available to address code smells", columbus, refactoringAuthors);
        Book agileCSharp = new Book("Agile Principles, Patterns and Practices in C#", "A classic book refactored for C#", cleveland, agileCSharpAuthors);
        bookRepo.save(headfirstJava);
        bookRepo.save(tddByExample);
        bookRepo.save(refactoring);
        bookRepo.save(agileCSharp);

    }
}
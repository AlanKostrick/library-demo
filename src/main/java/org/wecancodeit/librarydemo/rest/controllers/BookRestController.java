package org.wecancodeit.librarydemo.rest.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.librarydemo.models.Book;
import org.wecancodeit.librarydemo.models.HashTag;
import org.wecancodeit.librarydemo.repositories.BookRepository;
import org.wecancodeit.librarydemo.repositories.HashTagRepository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
public class BookRestController {

    @Resource
    private BookRepository bookRepo;

    @Resource
    private HashTagRepository hashTagRepo;

    @GetMapping("/api/books")
    public Collection<Book> getBooks() {
        return (Collection<Book>) bookRepo.findAll();
    }

    @GetMapping("/api/books/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return bookRepo.findById(id);
    }

    @PostMapping("/api/books/{id}/add-hashtag")
    public Optional<Book> addHashTagToBook(@RequestBody String body, @PathVariable Long id) throws JSONException {
        JSONObject newHashTag = new JSONObject(body);
        String hashTagName = newHashTag.getString("hashTagName");
        Optional<HashTag> hashTagToAddOpt = hashTagRepo.findByName(hashTagName);
        //if the hashtag exists in the database it can be included on the particular book
        if (hashTagToAddOpt.isPresent()) {
            Optional<Book> bookToAddHashTagToOpt = bookRepo.findById(id);
            Book bookToAddHashTagTo = bookToAddHashTagToOpt.get();
            bookToAddHashTagTo.addHashTag(hashTagToAddOpt.get());
            bookRepo.save(bookToAddHashTagTo);
        }
        return bookRepo.findById(id);
    }

}
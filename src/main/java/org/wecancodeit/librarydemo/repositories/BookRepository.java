package org.wecancodeit.librarydemo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.librarydemo.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}

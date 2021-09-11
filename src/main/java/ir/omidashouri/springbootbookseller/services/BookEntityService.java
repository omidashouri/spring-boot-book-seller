package ir.omidashouri.springbootbookseller.services;

import ir.omidashouri.springbootbookseller.model.BookEntity;

import java.util.List;

public interface BookEntityService {
    BookEntity saveBook(BookEntity newBook);

    void deleteBook(Long id);

    List<BookEntity> findAllBooks();
}

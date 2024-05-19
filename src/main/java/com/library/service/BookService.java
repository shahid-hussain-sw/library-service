package com.library.service;

import com.library.dto.BookDto;
import java.util.List;

public interface BookService {

    BookDto createBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto borrowedBook(Long bookId, Long borrowerId);

    BookDto returnBook(Long bookId, Long borrowerId);

}

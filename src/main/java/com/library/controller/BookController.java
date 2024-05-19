package com.library.controller;

import com.library.dto.BookDto;
import com.library.service.BookService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/library/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooks(){
       return bookService.getAllBooks();
    }


    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@Valid @RequestBody BookDto bookDto){
        return bookService.createBook(bookDto);
    }

    @PatchMapping(value = "/borrow/{bookId}/book/{borrowerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId){
        return bookService.borrowedBook(bookId, borrowerId);
    }

    @PatchMapping(value = "/return/{bookId}/book/{borrowerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto returnBook(@PathVariable Long bookId, @PathVariable Long borrowerId){
        return bookService.returnBook(bookId, borrowerId);
    }
}

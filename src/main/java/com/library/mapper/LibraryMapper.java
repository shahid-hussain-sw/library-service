package com.library.mapper;

import com.library.dto.BookDto;
import com.library.dto.BorrowerDto;
import com.library.model.Book;
import com.library.model.Borrower;
import org.springframework.stereotype.Component;

@Component
public class LibraryMapper {

    public Book buildBookFromDto(BookDto bookDto){
        return Book.builder().title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .author(bookDto.getAuthor())
                .build();
    }

    public BookDto buildBookDtoFromEntity(Book book){
        return BookDto.builder().title(book.getTitle())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .id(book.getId())
                .build();
    }

    public Borrower buildBorrowerFromDto(BorrowerDto borrowerDto){
        return Borrower.builder().email(borrowerDto.getEmail())
                .name(borrowerDto.getName())
                .build();
    }

    public BorrowerDto buildBorrowerDtoFromEntity(Borrower borrower){
        return BorrowerDto.builder().email(borrower.getEmail())
                .name(borrower.getName())
                .id(borrower.getId())
                .build();
    }
}

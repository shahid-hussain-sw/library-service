package com.library.service.impl;

import com.library.dto.BookDto;
import com.library.exception.AlreadyExistException;
import com.library.exception.NotFoundException;
import com.library.mapper.LibraryMapper;
import com.library.model.Book;
import com.library.model.Borrower;
import com.library.repository.BookRepository;
import com.library.repository.BorrowerRepository;
import com.library.service.BookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BorrowerRepository borrowerRepository;
    private final LibraryMapper mapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BorrowerRepository borrowerRepository, LibraryMapper mapper, BookRepository bookRepository) {
        this.borrowerRepository = borrowerRepository;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        validateIsbn(bookDto);
        Book book = bookRepository.save(mapper.buildBookFromDto(bookDto));
        return mapper.buildBookDtoFromEntity(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(mapper::buildBookDtoFromEntity).collect(Collectors.toList());
    }

    @Override
    public BookDto borrowedBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->new NotFoundException("Book does not exist"));
        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(()->new NotFoundException("Borrower does not exist"));
        if(borrower.getBooks().contains(book)){
            log.warn("Book is already borrowed by the given borrower, bookId:{}, borrowerId:{}", bookId, borrowerId);
            throw new AlreadyExistException("Book is already borrowed by the borrower");
        }
        book.setBorrowedBy(borrower);
        borrower.getBooks().add(book);
        borrowerRepository.save(borrower);
        book = bookRepository.save(book);
        return mapper.buildBookDtoFromEntity(book);
    }

    @Override
    public BookDto returnBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->new NotFoundException("Book does not exist"));
        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(()->new NotFoundException("Borrower does not borrow this book"));
        if(!borrower.getBooks().contains(book)){
            log.warn("Book is not borrowed by the given borrower, bookId:{}, borrowerId:{}", bookId, borrowerId);
            throw new AlreadyExistException("BBook is not borrowed by the given borrower");
        }
        book.setBorrowedBy(null);
        borrower.getBooks().remove(book);
        borrowerRepository.save(borrower);
        book = bookRepository.save(book);
        return mapper.buildBookDtoFromEntity(book);
    }

    private void validateIsbn(BookDto bookDto){

        List<Book> books = bookRepository.findByIsbn(bookDto.getIsbn());
        books.forEach(book -> {
            if(!book.getAuthor().equals(bookDto.getAuthor()) || !book.getTitle().equals(bookDto.getTitle())){
               log.warn("Book is already exist with the same isbn:{}, author:{} and title:{}",bookDto.getIsbn(), bookDto.getAuthor(), bookDto.getTitle());
                throw new AlreadyExistException("Book already exist with the same title and author");
            }
        });
    }
}

package com.library.service.impl;

import com.library.dto.BorrowerDto;
import com.library.mapper.LibraryMapper;
import com.library.model.Borrower;
import com.library.repository.BorrowerRepository;
import com.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final LibraryMapper mapper;

    @Autowired
    public BorrowerServiceImpl(BorrowerRepository borrowerRepository, LibraryMapper mapper) {
        this.borrowerRepository = borrowerRepository;
        this.mapper = mapper;
    }

    @Override
    public BorrowerDto createBorrower(BorrowerDto borrowerDto) {
        Borrower borrower = borrowerRepository.save(mapper.buildBorrowerFromDto(borrowerDto));
        return mapper.buildBorrowerDtoFromEntity(borrower);
    }
}

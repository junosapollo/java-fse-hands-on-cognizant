package com.cognizant.librarycore;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository repository;

    public BookService() { }
    public BookService(BookRepository repository) { this.repository = repository; }

    @Autowired
    public void setRepository(BookRepository repository) { this.repository = repository; }

    public List<String> findAll() {
        if (repository == null) throw new IllegalStateException("BookRepository has not been injected");
        return repository.findAll();
    }

    public void add(String title) { repository.add(title); }
}

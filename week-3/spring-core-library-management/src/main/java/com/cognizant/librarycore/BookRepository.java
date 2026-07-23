package com.cognizant.librarycore;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    private final List<String> books = new ArrayList<>(List.of("Clean Code", "Effective Java"));

    public List<String> findAll() { return List.copyOf(books); }
    public void add(String title) { books.add(title); }
}

package com.cognizant.libraryboot;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository repository;
    public BookController(BookRepository repository) { this.repository = repository; }

    @GetMapping public List<Book> all() { return repository.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Book> one(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping public ResponseEntity<Book> create(@RequestBody Book book) { return ResponseEntity.ok(repository.save(book)); }
    @PutMapping("/{id}") public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book update) {
        return repository.findById(id).map(book -> { book.setTitle(update.getTitle()); book.setAuthor(update.getAuthor()); return ResponseEntity.ok(repository.save(book)); }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id); return ResponseEntity.noContent().build();
    }
}

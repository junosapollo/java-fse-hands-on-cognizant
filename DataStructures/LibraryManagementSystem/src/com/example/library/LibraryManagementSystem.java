package com.example.library;

import java.util.Arrays;
import java.util.Comparator;

class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "Book [ID=" + bookId + ", Title=" + title + ", Author=" + author + "]";
    }
}

public class LibraryManagementSystem {

    // linear search
    public static Book linearSearch(Book[] books, String targetTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null;
    }

    // binary search requires sorted array
    public static Book binarySearch(Book[] books, String targetTitle) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] library = {
            new Book("B1", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B2", "1984", "George Orwell"),
            new Book("B3", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B4", "Pride and Prejudice", "Jane Austen"),
            new Book("B5", "Moby Dick", "Herman Melville")
        };

        // linear search test
        System.out.println("Linear Search for '1984':");
        Book resultLinear = linearSearch(library, "1984");
        System.out.println(resultLinear != null ? "Found: " + resultLinear : "Not Found");

        // binary search test requires sorted array
        Arrays.sort(library, Comparator.comparing(Book::getTitle));
        
        System.out.println("\nSorted Library (by Title) for Binary Search:");
        for (Book b : library) System.out.println(b.getTitle());

        System.out.println("\nBinary Search for 'Moby Dick':");
        Book resultBinary = binarySearch(library, "Moby Dick");
        System.out.println(resultBinary != null ? "Found: " + resultBinary : "Not Found");
        
        // linear search is simpler but has complexity useful when library is unsorted or small
        // binary search has complexity which is much faster for a large library but requires
        // the list of books to be sorted beforehand which takes
    }
}

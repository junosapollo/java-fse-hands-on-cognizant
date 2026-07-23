package com.cognizant.librarycore;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            context.getBean(BookService.class).findAll().forEach(System.out::println);
        }
    }
}

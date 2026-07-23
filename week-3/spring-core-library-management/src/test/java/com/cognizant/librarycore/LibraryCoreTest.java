package com.cognizant.librarycore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class LibraryCoreTest {
    @Test
    void loadsSetterConfiguredXmlContext() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            assertEquals(2, context.getBean(BookService.class).findAll().size());
        }
    }

    @Test
    void loadsConstructorConfiguredContext() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("constructor-context.xml")) {
            assertEquals("Clean Code", context.getBean(BookService.class).findAll().get(0));
        }
    }

    @Test
    void scansAnnotationBeans() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotation-context.xml")) {
            assertNotNull(context.getBean(BookService.class));
            context.getBean(BookService.class).add("Spring in Action");
            assertEquals(3, context.getBean(BookService.class).findAll().size());
        }
    }
}

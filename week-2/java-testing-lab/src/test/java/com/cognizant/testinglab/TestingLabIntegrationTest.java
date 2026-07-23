package com.cognizant.testinglab;

import static org.junit.jupiter.api.Assertions.*;
import com.cognizant.testinglab.calculator.CalculatorService;
import com.cognizant.testinglab.users.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestingLabIntegrationTest {
    @Autowired CalculatorService calculator;
    @Autowired UserRepository users;

    @Test
    void loadsSpringAndPersistsUser() {
        assertEquals(7, calculator.add(3, 4));
        users.save(new User(10L, "Test User"));
        assertEquals("Test User", users.findById(10L).orElseThrow().getName());
    }
}

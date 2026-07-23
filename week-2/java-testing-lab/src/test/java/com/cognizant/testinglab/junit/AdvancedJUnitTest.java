package com.cognizant.testinglab.junit;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AdvancedJUnitTest {
    private EvenChecker checker;

    @BeforeEach
    void setUp() { checker = new EvenChecker(); }

    @AfterEach
    void tearDown() { checker = null; }

    @ParameterizedTest
    @MethodSource("numbers")
    void checksSeveralEvenNumbers(int number, boolean expected) {
        assertEquals(expected, checker.isEven(number));
    }

    static Stream<Arguments> numbers() {
        return Stream.of(Arguments.of(2, true), Arguments.of(3, false), Arguments.of(0, true));
    }

    @Test
    void verifiesArrangeActAssertAndException() {
        assertThrows(IllegalStateException.class, () -> new ExceptionThrower().throwException());
    }

    @Test
    void verifiesPerformanceTimeout() {
        assertTimeout(Duration.ofMillis(250), () -> new PerformanceTester().performTask(10_000));
    }

    @Test
    @Order(1)
    void orderedTestExample() { assertEquals(0, 0); }
}

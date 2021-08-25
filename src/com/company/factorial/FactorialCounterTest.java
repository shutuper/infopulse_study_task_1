package com.company.factorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialCounterTest {

    @Test
    public void FactorialCounterTest() {
        Assertions.assertEquals(120, FactorialCounter.countFactRecursion(5));
        Assertions.assertEquals(120, FactorialCounter.countFactIteration(5));
        try {
            FactorialCounter.countFactRecursion(-1);
            fail();
        } catch (Exception thrown) {
            assertEquals("Number should be >= 0", thrown.getMessage());
        }
        Assertions.assertEquals(479001600, FactorialCounter.countFactIteration(12));
        Assertions.assertEquals(479001600, FactorialCounter.countFactRecursion(12));
        System.out.println("Successfully!");
    }

}
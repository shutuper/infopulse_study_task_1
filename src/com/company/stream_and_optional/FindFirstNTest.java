package com.company.stream_and_optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;


class FindFirstNTest {

    @Test
    public void testFindFirstN() {
        String[] exampleStr = new String[]{"abc", "abb", "ggg", "lala", "all", "nice", "awful", "hi"};
        Integer[] exampleInt = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Object[] exampleObj = new Object[]{1, "lol", 'c'};

        FindFirstN findFirstN = new FindFirstN();

        // первые 2 строки, что начинаються на "а":
        Assertions.assertEquals(Arrays.asList("abc", "abb"), findFirstN.findFirstN(2, exampleStr).collect(Collectors.toList()));
        // первые 2 елемента кратные 2-ум:
        Assertions.assertEquals(Arrays.asList(2, 4), findFirstN.findFirstN(2, exampleInt).collect(Collectors.toList()));
        // первые 3 строки, что начинаються на "а":
        Assertions.assertEquals(Arrays.asList("abc", "abb", "all"), findFirstN.findFirstN(3, Arrays.stream(exampleStr).collect(Collectors.toList())));
        // первые 3 елемента кратные 2-ум:
        Assertions.assertEquals(Arrays.asList(2, 4, 6), findFirstN.findFirstN(3, Arrays.stream(exampleInt).collect(Collectors.toList())));

    }

}
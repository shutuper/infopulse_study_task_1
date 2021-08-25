package com.company.stream_and_optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindFirstN<T> {

    private final Predicate<T> forInt = x ->  (Integer) x % 2 == 0;
    private final Predicate<T> forString = x -> String.valueOf(x).startsWith("a");
    private final Predicate<T> forElse = x -> x.hashCode() % 2 == 0;

    public Stream<T> findFirstN(int n, T... elements) {
        Optional<T> element = Arrays.stream(elements).findAny();
        if (element.isEmpty()) return Stream.of();
        else {
            Class<?> type = element.get().getClass();
            if (type.equals(String.class))
                return Arrays.stream(elements).filter(forString).limit(n);
            else if (type.equals(Integer.class))
                return Arrays.stream(elements).filter(forInt).limit(n);
            else return Arrays.stream(elements).filter(forElse).limit(n);
        }
    }

    public List<T> findFirstN(int n, List<T> list) {
        Optional<T> element = list.stream().findAny();
        if (element.isEmpty()) return new ArrayList<T>();
        else {
            Class<?> type = element.get().getClass();
            if (type.equals(String.class))
                return list.stream().filter(forString).limit(n).collect(Collectors.toList());
            else if (type.equals(Integer.class))
                return list.stream().filter(forInt).limit(n).collect(Collectors.toList());
            else return list.stream().filter(forElse).limit(n).collect(Collectors.toList());
        }
    }


}

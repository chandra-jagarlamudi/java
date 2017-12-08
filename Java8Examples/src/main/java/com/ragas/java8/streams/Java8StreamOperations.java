package com.ragas.java8.streams;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StreamOperations {
    /**
     * STREAM OPERATIONS - To perform a sequence of operations over elements of data source and aggregate results,
     * we need
     * 1. Source (Arrays, Collections, Files)
     * 2. Intermediate operations (return Stream<T>) intermediate operations allow chaining and can be many
     * 3. Terminal operations (return a result of definite type), only one terminal operation can be used per stream.
     *
     * It’s also worth noting that operations on streams don’t change the source.
     */
    long count = getStringList().stream().distinct().count();

    /**
     * 1. Iterating
     * Stream API helps to substitute for, for-each and while loops. It allows concentrating on operation’s logic,
     * but not on the iteration over the sequence of elements.
     */
    boolean isExist = getStringList().stream().anyMatch(element -> element.contains("c"));

    /**
     * 2. Filtering
     * The filter() method allows us to pick stream of elements which satisfy a predicate.
     */
    private static void filtering(){
        Stream<String> filteredStream = getStringList().stream().filter(element -> element.contains("L"));
        System.out.println("Stream Filtering");
        filteredStream.forEach(System.out::println);
    }

    /**
     * 3. Mapping
     * To convert elements of a Stream by applying a special function to them and to collect these new elements
     * into a Stream. The method below converts Stream<String> to the Stream<Path> by applying a specific lambda
     * expression to every element of the initial Stream.
     */
    private static void mapping(){
        List<String> uris = new ArrayList<>();
        uris.add("C:\\My.txt");
        Stream<Path> mappedStream = uris.stream().map(uri -> Paths.get(uri));
        System.out.println("\nStream Mapping");
        mappedStream.forEach(System.out::println);
    }

    /**
     * 4. Matching
     *  Stream API allows to validate elements of a sequence according to predicate. To do this one of the following
     *  methods can be used: anyMatch(), allMatch(), noneMatch(). These are terminal operations returning a boolean.
     */
    private void matching(){
        boolean isValid = getStringList().stream().anyMatch(element -> element.contains("h")); // true
        boolean isValidOne = getStringList().stream().allMatch(element -> element.contains("h")); // false
        boolean isValidTwo = getStringList().stream().noneMatch(element -> element.contains("h")); // false
    }

    /**
     * 5. Reduction
     * Stream API allows reducing a sequence of elements to some value according to a specified function with the
     * help of the reduce() method of the type Stream. This method takes two parameters: first – start value,
     * second – an accumulator function.
     */
    private void reducing(){
        List<Integer> integers = Arrays.asList(1, 1, 1);
        Integer reduced = integers.stream().reduce(23, (a, b) -> a + b);
    }

    /**
     * 6. Collecting
     * The reduction can also be provided by the collect() method of type Stream. This operation is very handy in
     * case of converting a stream to a Collection or a Map and representing a stream in form of a single string.
     * There is a utility class Collectors which provide a solution for almost all typical collecting operations.
     * For some, not trivial tasks, a custom Collector can be created
     *
     * This method uses the terminal operation collect() to reduce a Stream<String> to the List<String>.
     */
    private static void collecting(){
        List<String> collectionList
                = getStringList().stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("\nStream Collecting");
        collectionList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        filtering();
        mapping();
        collecting();
    }

    private static List<String> getStringList(){
        List<String> list = new ArrayList<>();
        list.add("Chandra");
        list.add("Lasya");
        list.add("Lavanya");
        list.add("Lakshmi");
        list.add("Abhiram");
        list.add("");
        list.add("");
        return list;
    }
}

package com.ragas.java8.streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class Java8Streams {
    public static void main(String[] args) {
        /**
         * STREAM CREATION - There are many ways to create a stream instance of different sources. Once created, the
         * instance will not modify its source, therefore allowing the creation of multiple instances from a
         * single source.
         *
         * 1. Empty Stream
         */
        Stream<String> emptyStream = Stream.empty();

        /**
         * 2. Stream of Collection - A default method stream() is added to the Collection interface that allows
         * creating a Stream<T> using any collection as an element source
         */
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        List<String> list = new ArrayList<>();
        Stream<String> collectionStream = list.stream();
        System.out.println("Streams from empty collections have " + collectionStream.count() + " elements");

        /**
         * 3. Stream of Array
         */
        Stream<String> streamOfArray = Stream.of("a", "b", "c");
        // Can also be created out of an existing array or of a part of an array:
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        /**
         * 4. Stream Builder - When builder is used the desired type should be additionally specified in the right
         * part of the statement, otherwise the build() method will create an instance of the Stream<Object>
         */
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

        /**
         * 5. Stream Generate - The generate() method accepts a Supplier<T> for element generation. The resulting
         * stream is infinite, specify the desired size or the generate() method will work until it reaches the
         * memory limit
         */
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

        /**
         * 6. Stream of Primitives - We can create streams out of three primitive types: int, long and double.
         */
        IntStream intStream = IntStream.range(1, 3);
        LongStream longStream = LongStream.rangeClosed(1, 3);
        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3);

        /**
         * 7. Stream of String
         */
        IntStream streamOfChars = "abc".chars();
        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

        /**
         * 8. Stream of File - Java NIO class Files allows to generate a Stream<String> of a text file through the
         * lines() method. Every line of the text becomes an element of the stream:
         */
        Path path = Paths.get("C:\\work\\aa\\info.txt");
        try {
            Stream<String> streamOfStrings = Files.lines(path);
            Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         *  REFERENCING STREAMS - We can instantiate stream and have accessible reference to it as long as
         *  intermediate operations(filter, map, flatMap, peek, distinct, sort, limit) are performed. Executing a
         *  terminal operation(forEach, toArray, reduce, collect, min, max, count, anyMatch, allMatch,
         *  noneMatch, findFirst, findAny) makes a stream inaccessible.
         */

        Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> anyElement = stream.findAny();
        System.out.println("" + anyElement.orElse("Empty String"));
        // Trying to reuse the same reference from above after calling the terminal operation
        // throws IllegalStateException at runtime, it is not checked at compile time
        //Optional<String> firstElement = stream.findFirst();

        // This can be rewritten as
        List<String> elements = Stream.of("a", "b", "c").filter(element -> element.contains("b"))
                        .collect(Collectors.toList());
        Optional<String> anyElementAfterCollection = elements.stream().findAny();
        System.out.println("After calling findAny(): " + anyElementAfterCollection.orElse("Empty String"));
        Optional<String> firstElementfromCollectionStream = elements.stream().findFirst();
        System.out.println("After calling findFirst(): " + firstElementfromCollectionStream.orElse("Empty String"));
    }
}

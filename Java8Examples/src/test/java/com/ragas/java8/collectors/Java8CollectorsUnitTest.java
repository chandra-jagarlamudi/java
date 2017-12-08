package com.ragas.java8.collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Java8CollectorsUnitTest {

    private final List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");

    @Test
    public void whenCollectingToList_shouldCollectToList() throws Exception {
        List<String> result = givenList.stream().collect(toList());

        assertThat(result).containsAll(givenList);
    }

    @Test
    public void whenCollectingToList_shouldCollectToSet() throws Exception {
        Set<String> result = givenList.stream().collect(toSet());

        assertThat(result).containsAll(givenList);
    }

    @Test
    public void whenCollectingToCollection_shouldCollectToCollection() throws Exception {
        List<String> result = givenList.stream().collect(toCollection(LinkedList::new));

        assertThat(result).containsAll(givenList).isInstanceOf(LinkedList.class);
    }

    @Test
    public void whenCollectingToImmutableCollection_shouldThrowException() throws Exception {
        assertThatThrownBy(() -> {
            givenList.stream().collect(toCollection(ImmutableList::of));
        }).isInstanceOf(UnsupportedOperationException.class);

    }

    @Test
    public void whenCollectingToMap_shouldCollectToMap() throws Exception {
        Map<String, Integer> result = givenList.stream().collect(toMap(Function.identity(), String::length));

        assertThat(result).containsEntry("a", 1).containsEntry("bb", 2).containsEntry("ccc", 3).containsEntry("dd", 2);
    }

    @Test
    public void whenCollectingToMap_shouldCollectToMapMerging() throws Exception {
        Map<String, Integer> result = givenList.stream().collect(toMap(Function.identity(), String::length, (i1, i2) -> i1));

        assertThat(result).containsEntry("a", 1).containsEntry("bb", 2).containsEntry("ccc", 3).containsEntry("dd", 2);
    }

    @Test
    public void whenCollectingAndThen_shouldCollect() throws Exception {
        List<String> result = givenList.stream().collect(collectingAndThen(toList(), ImmutableList::copyOf));

        assertThat(result).containsAll(givenList).isInstanceOf(ImmutableList.class);
    }

    @Test
    public void whenJoining_shouldJoin() throws Exception {
        String result = givenList.stream().collect(joining());

        assertThat(result).isEqualTo("abbcccdd");
    }

    @Test
    public void whenJoiningWithSeparator_shouldJoinWithSeparator() throws Exception {
        String result = givenList.stream().collect(joining(" "));

        assertThat(result).isEqualTo("a bb ccc dd");
    }

    @Test
    public void whenJoiningWithSeparatorAndPrefixAndPostfix_shouldJoinWithSeparatorPrePost() throws Exception {
        String result = givenList.stream().collect(joining(" ", "PRE-", "-POST"));

        assertThat(result).isEqualTo("PRE-a bb ccc dd-POST");
    }

    @Test
    public void whenPartitioningBy_shouldPartition() throws Exception {
        Map<Boolean, List<String>> result = givenList.stream().collect(partitioningBy(s -> s.length() > 2));

        assertThat(result).containsKeys(true, false).satisfies(booleanListMap -> {
            assertThat(booleanListMap.get(true)).contains("ccc");

            assertThat(booleanListMap.get(false)).contains("a", "bb", "dd");
        });
    }

    @Test
    public void whenCounting_shouldCount() throws Exception {
        Long result = givenList.stream().collect(counting());

        assertThat(result).isEqualTo(4);
    }

    @Test
    public void whenSummarizing_shouldSummarize() throws Exception {
        DoubleSummaryStatistics result = givenList.stream().collect(summarizingDouble(String::length));

        assertThat(result.getAverage()).isEqualTo(2);
        assertThat(result.getCount()).isEqualTo(4);
        assertThat(result.getMax()).isEqualTo(3);
        assertThat(result.getMin()).isEqualTo(1);
        assertThat(result.getSum()).isEqualTo(8);
    }

    @Test
    public void whenAveraging_shouldAverage() throws Exception {
        Double result = givenList.stream().collect(averagingDouble(String::length));

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void whenSumming_shouldSum() throws Exception {
        Double result = givenList.stream().filter(i -> true).collect(summingDouble(String::length));

        assertThat(result).isEqualTo(8);
    }

    @Test
    public void whenMaxingBy_shouldMaxBy() throws Exception {
        Optional<String> result = givenList.stream().collect(maxBy(Comparator.naturalOrder()));

        assertThat(result).isPresent().hasValue("dd");
    }

    @Test
    public void whenGroupingBy_shouldGroupBy() throws Exception {
        Map<Integer, Set<String>> result = givenList.stream().collect(groupingBy(String::length, toSet()));

        assertThat(result).containsEntry(1, newHashSet("a")).containsEntry(2, newHashSet("bb", "dd")).containsEntry(3, newHashSet("ccc"));
    }

    @Test
    public void whenCreatingCustomCollector_shouldCollect() throws Exception {
        ImmutableSet<String> result = givenList.stream().collect(toImmutableSet());

        assertThat(result).isInstanceOf(ImmutableSet.class).contains("a", "bb", "ccc", "dd");

    }

    private static <T> ImmutableSetCollector<T> toImmutableSet() {
        return new ImmutableSetCollector<>();
    }

    private static class ImmutableSetCollector<T> implements Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> {

        @Override
        public Supplier<ImmutableSet.Builder<T>> supplier() {
            return ImmutableSet::builder;
        }

        @Override
        public BiConsumer<ImmutableSet.Builder<T>, T> accumulator() {
            return ImmutableSet.Builder::add;
        }

        @Override
        public BinaryOperator<ImmutableSet.Builder<T>> combiner() {
            return (left, right) -> left.addAll(right.build());
        }

        @Override
        public Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher() {
            return ImmutableSet.Builder::build;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Sets.immutableEnumSet(Characteristics.UNORDERED);
        }
    }
}
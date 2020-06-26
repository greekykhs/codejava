package com.greekykhs.java8;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Java8Test1 {
	public static void main(String[] args) {
		//Create empty Stream
		Stream<String> streamEmpty = Stream.empty();
		
		//Create Stream of Array
		Stream<String> streamOfArray = Stream.of("a", "b", "c");

		//Stream of Primitives
		//range(int startInclusive, int endExclusive) 
		IntStream intStream = IntStream.range(1, 3);
		//rangeClosed(int startInclusive, int endInclusive) 
		LongStream longStream = LongStream.rangeClosed(1, 3);
		
		//Stream of dates with 1 day difference
		Stream.iterate(LocalDate.now(), 
				date -> date.plusDays(1))
		.limit(10)
		.collect(Collectors.toList());
		
		//Create a list of numbers starting from 1 till 10
		List<Integer> intList = Stream.iterate(1, i -> i + 1)
				.limit(10).collect(Collectors.toList());
		
		//Reusing Stream
		Supplier<Stream<String>> streamSupplier= () -> 
				Stream.of("d2", "a2", "b1", "b3", "a1")
				.filter(s -> s.startsWith("a"));

		streamSupplier.get().anyMatch(s -> true); 
		streamSupplier.get().noneMatch(s -> true);
		
		/*
		 identity: the initial value for an accumulator or a default value 
		 if a stream is empty and there is nothing to accumulate;

         accumulator: a function which specifies a logic of aggregation 
         of elements. As accumulator creates a new value for every 
         step of reducing, the quantity of new values equals to the 
         stream's size and only the last value is useful.

		 combiner: a function which aggregates results of the accumulator. 
		 Combiner is called only in a parallel mode to reduce results 
		 of accumulators from different threads.
		 */
		//Reduce example
		//value of reducedParams will be 16
		int reducedParams = Stream.of(1, 2, 3)
				  .reduce(10, //identity
					(a, b) -> a + b, //accumulator
					(a, b) -> {return a + b;});//combiner

	}
}
/*
//Using Java 9's takeWhile and dropWhile
//We have a list of chars from 'a' to 'i'. 
List<String> alphabets = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");

//Return list all chars which may appear before char 'g' in iteration.
List<String> alphabetsTillg = alphabets.stream().
.takeWhile(s -> !s.equals("g"))
.collect(Collectors.toList());

//Return list all chars which may appear after char 'g' in iteration.
List<String> alphabetsTillg = alphabets.stream()
.dropWhile(s -> !s.equals("g"))
 .collect(Collectors.toList());
 */

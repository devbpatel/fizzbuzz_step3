package main.java.com.tf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {

	private static final String DELIMETER = " ";
	private Map<String, AtomicInteger> stastics = new HashMap<String, AtomicInteger>();

	public static void main(String[] args) {
		FizzBuzz fizzBuzz = new FizzBuzz();
		System.out.println(fizzBuzz.buildOutput(1, 20));
		System.out.println(fizzBuzz.buildStatistics());
	}
	
	public FizzBuzz() {
		initializeStatisticsMap();
	}

	private void initializeStatisticsMap() {
		// initialize stasticsMap with zero value entries and avoid null checking
        stastics.clear();
        stastics.put(FizzBuzzEnum.fizz.toString(), new AtomicInteger());
        stastics.put(FizzBuzzEnum.buzz.toString(), new AtomicInteger());
        stastics.put(FizzBuzzEnum.fizzbuzz.toString(), new AtomicInteger());
        stastics.put(FizzBuzzEnum.lucky.toString(), new AtomicInteger());
        stastics.put(FizzBuzzEnum.number.toString(), new AtomicInteger());
	}

	public String getOutput(int number) {
		String output = null;
		if (Integer.toString(number).contains("3")) {
			output = FizzBuzzEnum.lucky.toString();
			stastics.get(FizzBuzzEnum.lucky.toString()).incrementAndGet();
		} else if (number % 15 == 0) {
			output =  FizzBuzzEnum.fizzbuzz.toString();
			stastics.get(FizzBuzzEnum.fizzbuzz.toString()).incrementAndGet();
		} else if (number % 3 == 0) {
			output = FizzBuzzEnum.fizz.toString();
			stastics.get(FizzBuzzEnum.fizz.toString()).incrementAndGet();
		} else if (number % 5 == 0) {
			output = FizzBuzzEnum.buzz.toString();
			stastics.get(FizzBuzzEnum.buzz.toString()).incrementAndGet();
		} else {
			output = Integer.toString(number);
			stastics.get(FizzBuzzEnum.number.toString()).incrementAndGet();
		}
		return output;
	}

	public String buildOutput(int from, int to) {
		validateRange(from, to);
		StringBuilder result = new StringBuilder();
		for (int i = from; i <= to; i++) {
			result.append(getOutput(i));
			if (i < to) {
				result.append(DELIMETER);
			}
		}
		return result.toString();
	}
	
	private void validateRange(int from, int to) {
		//TODO: Confirm with product team about Zero and Negatives
		if (from <= 0 || to <= 0) {
			throw new IllegalArgumentException(
					"Range cannot have zero or negative number");
		} else if (from >= to) {
			throw new IllegalArgumentException(
					"from range must be less than to range");
		}
	}
	
	private String buildStatistics() {
		StringBuilder output = new StringBuilder();
		Set<String> keySet = stastics.keySet();
		Iterator<String> keySetIterator = keySet.iterator();
		while (keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			output.append(key + ": " + stastics.get(key));
			
			//add line break if it's not last element
			if (keySetIterator.hasNext()) {
				output.append("\n");
			}
		}
		return output.toString();
	}
	
	public Map<String, AtomicInteger> getStatistics() {
		return stastics;
	}

}

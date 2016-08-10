package test.java.com.tf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import main.java.com.tf.FizzBuzz;

import org.junit.Test;

public class FizzBuzzTest {
	private FizzBuzz fizzBuzz = new FizzBuzz();
	
	@Test
	public void testNumberDivisibleBy3() {
		 assertEquals("fizz", fizzBuzz.getOutput(6));
	}

	@Test
	public void testNumberDivisibleBy5() {
		 assertEquals("buzz", fizzBuzz.getOutput(5));
	}
	
	@Test
	public void testNumberDivisibleBy3And5() {
		 assertEquals("fizzbuzz", fizzBuzz.getOutput(15));
	}
	
	@Test
	public void testNumberNotDivisibleBy3Or5() {
		assertEquals("4", fizzBuzz.getOutput(4));
	}
	
	@Test
	public void testNumberContains3() {
		assertEquals("lucky", fizzBuzz.getOutput(3));
	}
	
	@Test
    public void testBuildOutputIsNotNullAndNotEmpty() {
        String out = fizzBuzz.buildOutput(1,16);
        assertNotNull(out);
        assertNotSame(0, out.length());
    }
	
	@Test(expected = RuntimeException.class)
    public final void testBuildOutputCannotAcceptZero() {
		fizzBuzz.buildOutput(0,16);
    }
	
	@Test(expected = RuntimeException.class)
    public final void testBuildOutputCannotAcceptNegative() {
		fizzBuzz.buildOutput(-1,15);
    }
	
	@Test(expected = RuntimeException.class)
    public final void testBuildOutputWithInvalidRange() {
		fizzBuzz.buildOutput(16,0);
    }
	
	@Test
	public void testBuildOutputWithFullResult() {
		assertEquals(fizzBuzz.buildOutput(1, 16), "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16");
	}
	
	@Test
	public void testBuildOutputWithStatistics() {
		fizzBuzz.buildOutput(1, 20);
		Map<String, AtomicInteger> statistics = fizzBuzz.getStatistics();
		assertEquals(statistics.get("fizz").get(), 4);
		assertEquals(statistics.get("buzz").get(), 3);
		assertEquals(statistics.get("fizzbuzz").get(), 1);
		assertEquals(statistics.get("lucky").get(), 2);
		assertEquals(statistics.get("number").get(), 10);
	}
}

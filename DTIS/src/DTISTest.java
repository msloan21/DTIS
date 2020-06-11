import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DTISTest {
	public static final List<String> openingChars = new ArrayList(
			Arrays.asList("{", "[", "("));
	public static final List<String> closingChars = new ArrayList(
			Arrays.asList("}", "]", ")"));
	
	DTIS dtis;
	
	@Before
	public void setup() {
		dtis = new DTIS();
	}
	
	@After
	public void teardown() {
		dtis = null;
	}
	
	
	@Test
	public void testRunValid() {
		String input = "{}";
		
		boolean result = dtis.run(input);
		
		assertTrue(result);
		
		input = "([{}])";
		assertTrue(dtis.run(input));
	}
	
	@Test
	public void testRunInValid() {
		String input = "{";
		
		assertFalse(dtis.run(input));
		
		input = "{}]4";
		assertFalse(dtis.run(input));
		
		input = "";
		assertFalse(dtis.run(input));
		
		input = "   ";
		assertFalse(dtis.run(input));
	}
	
	@Test
	public void testIfValidTrue() {
		for(String index : openingChars) {
			assertTrue(dtis.checkIfInCharLists(index));
		}
		
		for(String index : closingChars) {
			assertTrue(dtis.checkIfInCharLists(index));
		}
	}
	
	@Test
	public void testIfValidFalse() {
		String index = "X";
		System.out.println(dtis.checkIfInCharLists(index));
		assertFalse(dtis.checkIfInCharLists(index));
		
		index = "1";
		assertFalse(dtis.checkIfInCharLists(index));
	}
}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DTIS {

	public static final List<String> openingChars = new ArrayList(
			Arrays.asList("{", "[", "("));
	public static final List<String> closingChars = new ArrayList(
			Arrays.asList("}", "]", ")"));
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("false");
			System.exit(3);
		}
		DTIS dtis = new DTIS();
		boolean isValid = dtis.run(args[0]);
		System.out.println(String.valueOf(isValid));
	}
	
	public boolean run(String input) {
		//has to be at least 2 chars and even number
		if((input.trim().length() == 0 || 
				input.trim().length() == 1) || 
				((input.trim().length()%2) > 0)) {
			return false;
		}
		String currentChar = "";
		//check each char if is in Chars list
		//this could be done with regex but 
		//that makes the code harder to follow.
		if(!checkIfInCharLists(input)) {
			return false;
		}
			
		//now split the list and work out
		//to see if opposites match
		String openings = input.substring(0, input.length()/2);
		String closings = input.substring((input.length()/2), input.length());
		
		return compare(openings, closings);
	}
	
	public boolean checkIfInCharLists(String input) {
		boolean allMatch = false;
		List inputList = Arrays.asList(input.split(""));
		//filter out non matching input from openings
		List leftOvers = (List) inputList.stream().filter(i -> !openingChars.contains(i)).collect(Collectors.toList());
		//filter out non match leftovers from closings
		leftOvers = (List) leftOvers.stream().filter(i -> !closingChars.contains(i)).collect(Collectors.toList());
		//if final result is empty, all inputs are valid
		return leftOvers.size() <= 0;
	}
	
	public boolean compare(String openings, String closeings) {
		for(int i = 0; i < openings.length(); i++) {
			String currOpening = String.valueOf(openings.charAt(i));
			int currOpeningIndex = openings.indexOf(currOpening);
			//now find what the closing char should be and see if it matches
			String closingChar = closingChars.get(currOpeningIndex);
			if(!closingChar.equals(String.valueOf(closeings.charAt(i)))) {
				return false;
			}
		}
		
		return true;
	}
}

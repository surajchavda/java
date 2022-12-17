package businesslayer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class NameGuesserTest {

	@Test
	void testSearch() {
		NameGuesser sol = new NameGuesser("Indian_Names_Test.csv", 1, false);
		Set<String> actualOutput = sol.search("aa****");
		Set<String> expectedOutput = new HashSet<>(
				Arrays.asList("aabida", "aachal", "aabida", "aaenab", "aadesh", "aadish"));
		assertEquals(expectedOutput, actualOutput);
		actualOutput = sol.search("z*h*i*");
		expectedOutput = new HashSet<>(Arrays.asList("zuhaib"));
		assertEquals(expectedOutput, actualOutput);
	}

}

package businesslayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import domain.Trie;
import utilities.FileReader;

/**
 * This class defines API that can be used to search names from a dataset by
 * passing a pattern in the form of a*c*e where * can be any letter between a to
 * z.
 * 
 * This class also declares a main function which provides a console interface
 * for the Name Guesser API
 * 
 * @author suraj
 *
 */
public class NameGuesser {
	Trie trie = null;

	/**
	 * Parameterized Constructor for NameGuesser
	 * 
	 * @param csvFile      CSV file for the name dataset
	 * @param columnPos    Column position for the name column
	 * @param ignoreHeader True if header row needs to be ignored
	 */
	public NameGuesser(String csvFile, int columnPos, boolean ignoreHeader) {
		Set<String> names = FileReader.readNamesFromCSV(csvFile, columnPos, ignoreHeader);
		trie = new Trie(names);
	}

	/**
	 * Default constructor for NameGuesser. Uses default file for reading name
	 * records.
	 */
	public NameGuesser() {
		System.out.println(" Reading names from file...");
		Set<String> names = FileReader.readNamesFromCSV();
		System.out.println(" " + names.size() + " names found in file.");
		System.out.println(" Generating Trie for the given names. Please wait this may take several minutes.");
		long start = System.nanoTime();
		trie = new Trie(names);
		long finish = System.nanoTime();
		long timeElapsedInSecs = TimeUnit.NANOSECONDS.toSeconds(finish - start);
		System.out.println(" Trie generated in " + timeElapsedInSecs + " seconds.");
	}

	/**
	 * This methods returns set of names matching the passed pattern
	 * 
	 * @param patter A string havign format like a*c*e where * can be any alphabets
	 *               between a to z
	 * @return Set of names matching the pattern
	 */
	public Set<String> search(String patter) {
		Set<String> result = new HashSet<>();
		trie.search(patter, null, 0, result);
		return result;
	}

	/**
	 * Console application/client which used NameGuesser search API
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		NameGuesser sol = new NameGuesser();
		while (true) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("===========================================================================");
				System.out.println("                              Name Guesser                              ");
				System.out.println("===========================================================================");
				System.out.println(" Enter a name pattern in the format a*b**e, where * symbolises possibility");
				System.out.println(" of any character from a-z.                                               ");
				System.out.println(" The program will return all names matching the given pattern from dataset.");
				System.out.println("===========================================================================");
				System.out.print(" Enter patter (press q/Q to quit) : ");
				String input = null;
				input = br.readLine().trim();
				while (input == null || input.isEmpty()) {
					System.out.print(System.lineSeparator() + " Enter valid input (press q/Q to quit) : ");
					input = br.readLine().trim();
				}
				if (input.equalsIgnoreCase("q")) {
					System.out.println(" Quitting program.");
					break;
				}
				long start = System.nanoTime();
				Set<String> names = sol.search(input.toLowerCase());
				long finish = System.nanoTime();
				long timeElapsedInMillis = TimeUnit.NANOSECONDS.toMillis(finish - start);
				System.out.println(" Search completed in " + timeElapsedInMillis + " milliseconds.");

				if (names.isEmpty()) {
					System.out.println(" No names found matching the pattern " + input);
				} else {
					System.out.println(" Names matching the pattern " + input + " are : ");
					int id = 1;
					for (String name : names) {
						System.out.println(id + " " + name);
						id++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

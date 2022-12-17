package businesslayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import domain.Trie;
import utilities.CSVReader;

public class NameGuesser {
	Trie trie = null;

	public NameGuesser(String csvFile, int columnPos, boolean ignoreHeader) {
		Set<String> names = CSVReader.readNamesFromCSV(csvFile, columnPos, ignoreHeader);
		trie = new Trie(names);
	}

	public NameGuesser() {
		Set<String> names = CSVReader.readNamesFromCSV();
		trie = new Trie(names);
	}

	public Set<String> search(String patter) {
		Set<String> result = new HashSet<>();
		trie.search(patter, null, 0, result);
		return result;
	}

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
				Set<String> names = sol.search(input);
				System.out.println(" Names matching the pattern " + input + " are : ");
				int id = 1;
				for (String name : names) {
					System.out.println(id + " " + name);
					id++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

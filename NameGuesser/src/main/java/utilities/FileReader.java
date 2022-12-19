package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import common.Constants;

public class FileReader {
	private FileReader() {
	}
	/*
	 * public static void main(String[] args) { try { BufferedWriter bw = new
	 * BufferedWriter( new OutputStreamWriter(new
	 * FileOutputStream("c:/opt/names_sorted.txt"))); Set<String> names =
	 * readNamesFromFile("ap-names-grouped.txt", "\\s+", 4, true); TreeSet<String>
	 * names_sorted = new TreeSet<>(names); for (String name : names_sorted) {
	 * bw.write(name); bw.newLine(); } bw.close(); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */

	private static FileReader fileReader = null;

	public static FileReader getInstance() {
		if (fileReader == null) {
			fileReader = new FileReader();
		}
		return fileReader;
	}

	public static Set<String> readNamesFromCSV(String csvFile, int columnPos, boolean ignoreHeader) {
		String splitBy = Constants.COMMA;
		return readNamesFromFile(csvFile, splitBy, columnPos, ignoreHeader);
	}

	public static Set<String> readNamesFromFile(String file, String splitBy, int columnPos, boolean ignoreHeader) {
		String line = null;
		if (splitBy == null || splitBy.isEmpty()) {
			splitBy = Constants.VARIABLE_SPACES;
		}
		Set<String> output = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(FileReader.class.getClassLoader().getResourceAsStream(file)));
			if (ignoreHeader) {
				br.readLine();
			}
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				String[] names = line.split(splitBy); // use comma as separator
				if (names[columnPos] != null && !names[columnPos].trim().isEmpty()) {
					output.add(names[columnPos].trim());
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static Set<String> readNamesFromCSV() {
		return readNamesFromCSV(Constants.NAMES_SORTED_FILE, Constants.COLUMN_POS, Constants.IGNORE_COLUMN_ROW);
	}

}

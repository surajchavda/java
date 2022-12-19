package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import common.Constants;

/**
 * This class is used to read name data from a file.
 * 
 * @author suraj
 *
 */
public class FileReader {
	private FileReader() {
	}

	private static FileReader fileReader = null;

	/**
	 * Singleton getInstance() implementation
	 * 
	 * @return instance of FileReader
	 */
	public static FileReader getInstance() {
		if (fileReader == null) {
			fileReader = new FileReader();
		}
		return fileReader;
	}

	/**
	 * Returns Set of names from the given file
	 * 
	 * @param csvFile      CSV file from which the name data needs to be fetched
	 * @param columnPos    Position of the column which contains name data
	 * @param ignoreHeader True if header row needs to be ignored else False
	 * @return Set of names fetched from the file
	 */
	public static Set<String> readNamesFromCSV(String csvFile, int columnPos, boolean ignoreHeader) {
		String splitBy = Constants.COMMA;
		return readNamesFromFile(csvFile, splitBy, columnPos, ignoreHeader);
	}

	/**
	 * Returns Set of names from the given file
	 * 
	 * @param file         File from which the name data needs to be fetched
	 * @param splitBy      Delimiter to be used for separating the columns
	 * @param columnPos    Position of the column which contains name data
	 * @param ignoreHeader True if header row needs to be ignored else False
	 * @return Set of names fetched from the file
	 */
	public static Set<String> readNamesFromFile(String file, String splitBy, int columnPos, boolean ignoreHeader) {
		String line = null;
		if (splitBy == null || splitBy.isEmpty()) {
			splitBy = Constants.VARIABLE_SPACES;
		}
		Set<String> output = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(FileReader.class.getClassLoader().getResourceAsStream(file)));
			// If ignoreHeader is true, skip the first row
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

	/**
	 * Returns Set of names from the default file embedded in code
	 * 
	 * @return Set of names fetched from the default file embedded in code
	 */
	public static Set<String> readNamesFromCSV() {
		return readNamesFromCSV(Constants.NAMES_SORTED_FILE, Constants.COLUMN_POS, Constants.IGNORE_COLUMN_ROW);
	}

}

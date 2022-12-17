package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import common.Constants;

public class CSVReader {
	private CSVReader() {
	}

	private static CSVReader csvReader = null;

	public static CSVReader getInstance() {
		if (csvReader == null) {
			csvReader = new CSVReader();
		}
		return csvReader;
	}

	public static Set<String> readNamesFromCSV(String csvFile, int columnPos, boolean ignoreHeader) {
		String line = "";
		String splitBy = ",";
		Set<String> output = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(CSVReader.class.getClassLoader().getResourceAsStream(csvFile)));
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
		return readNamesFromCSV(Constants.NAMES_CSV_FILE, Constants.COLUMN_POS, Constants.IGNORE_COLUMN_ROW);
	}

}

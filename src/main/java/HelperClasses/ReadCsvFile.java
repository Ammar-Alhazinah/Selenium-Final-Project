package HelperClasses;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class ReadCsvFile {
private static int numberOfItems = 0;

    public static int getNumberOfItems() {
        return numberOfItems;
    }

    public static List<String[]> readAllLines(String filePath) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(filePath), ',');

        List<String[]> data = new ArrayList<String[]>();

        // read line by line
        String[] record = null;

        while ((record = reader.readNext()) != null) {
            data.add(record);
numberOfItems++;
        }
        reader.close();
        return data;
    }
}

package DataProvider;

import HelperClasses.ReadCsvFile;
import org.testng.annotations.DataProvider;

import java.util.List;
import HelperClasses.Constants;
public class DataProviderClass {


    @DataProvider(name = "Data")
    public static Object[][] getData() throws Exception {

        List<String[]> lines = ReadCsvFile.readAllLines(Constants.DATA_FILE);
        lines.remove(0);
        Object[][] data = new Object[lines.size()][lines.get(0).length];
        int index = 0;
        for (String[] line : lines) {
            data[index] = line;
            index++;
        }
        return data;
    }
}

package File_format;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {   // Step 1: CsvReader-->List
							//Step 2: List2Layer --> Layer
							//Step 3: list2kml --> turn the layer into kml
	/**
	 * class which suppose to analize data from csv files
	 * @param csvFile 
	 * @return ArrayList representing a file 
	 */
    public static ArrayList Csvreader(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";

    	ArrayList<ArrayList<String>> mat = new ArrayList<ArrayList<String>>();

        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {
            while ((line = br.readLine()) != null) 
            {
            	ArrayList<String> row = new ArrayList<String>();
                String[] userInfo = line.split(cvsSplitBy);
                for(int i=0;i<userInfo.length;i++) {
                	row.add(userInfo[i]);
                }
                mat.add(row);
            }

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return mat;
    }

}
package rohith;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class diff extends BaseClass{
	public void diff(String file1, String file2){

		final String FILENAME1 = file1; //"/Users/rohith/Documents/rohith/lastSuccessDom.txt";
		final String FILENAME2 = file2; //"/Users/rohith/Documents/rohith/changeInDom.txt";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(FILENAME1));
			BufferedReader br2 = new BufferedReader(new FileReader(FILENAME2));
			String line1 = br1.readLine();
			String line2 = br2.readLine();
			boolean areEqual = true;
			int lineNum = 1;
			while (line1 != null || line2 != null) {
				if (!line1.contentEquals(line2)) {
					areEqual = false;
					map.put(line1, line2);
				}
				line1 = br1.readLine();
				line2 = br2.readLine();
				lineNum++;
			}
			if (areEqual) {
				System.out.println("File1 and File2 contents are same.");
			}
			br1.close();
			br2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

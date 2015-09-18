import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

public class SearchTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() throws IOException {
		
		//new file
		File testFile = new File("test.txt");
		TextBuddy2.setFileName("test.txt");
		//new arraylist
		ArrayList<String> contentArray = new ArrayList<String>();
		ArrayList<String> checkerArray = new ArrayList<String>();
		
		TextBuddy2.addToArrayList("mango is fresh", contentArray);
		TextBuddy2.addToArrayList("mango is sweet", contentArray);
		TextBuddy2.addToArrayList("strawberry is red", contentArray);
		TextBuddy2.addToArrayList("i love red", contentArray);
		TextBuddy2.addToTxtFile(testFile,contentArray);
		
		TextBuddy2 testSearch = new TextBuddy2();
		
		//searchFile method returns an arraylist that contains all the lines that contains the 
		//search word
		checkerArray = testSearch.searchFile(contentArray,testFile, "mango");
		
		
		Object[] resultArray = new String[checkerArray.size()];
		resultArray = checkerArray.toArray();
		
		String[] expectedArray = {"mango is fresh", "mango is sweet"};
		assertArrayEquals(expectedArray, resultArray);
		
	}

}

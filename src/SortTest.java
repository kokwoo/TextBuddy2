import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

public class SortTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() throws IOException {
		 	//new file
			File testFile = new File("test.txt");
			TextBuddy2.clearTxtFile(testFile);
			TextBuddy2.setFileName("test.txt");
			//new arraylist
			ArrayList<String> contentArray = new ArrayList<String>();
			ArrayList<String> checkerArray = new ArrayList<String>();
			
			TextBuddy2.addToArrayList("d", contentArray);
			TextBuddy2.addToArrayList("c", contentArray);
			TextBuddy2.addToArrayList("b", contentArray);
			TextBuddy2.addToArrayList("a", contentArray);
			
			TextBuddy2.addToTxtFile(testFile,contentArray);
			
			TextBuddy2 testSort = new TextBuddy2();
			testSort.sortFile(contentArray,testFile);
			
			TextBuddy2.fileLinesToArrayList(checkerArray, testFile);
			
			Object[] resultArray = new String[checkerArray.size()];
			resultArray = checkerArray.toArray();
			
			String[] expectedArray = {"a", "b", "c", "d"};
			assertArrayEquals(expectedArray, resultArray);
			
			
		
	}
	@Test
	public final void test1() throws IOException {
			File testFile1 = new File("test1.txt");
			TextBuddy2.clearTxtFile(testFile1);
 			TextBuddy2.setFileName("test1.txt");
    		ArrayList<String> contentArray = new ArrayList<String>();
			ArrayList<String> checkerArray = new ArrayList<String>();
			TextBuddy2.addToArrayList("4", contentArray);
			TextBuddy2.addToArrayList("3", contentArray);
			TextBuddy2.addToArrayList("2", contentArray);
			TextBuddy2.addToArrayList("1", contentArray);
			TextBuddy2.addToTxtFile(testFile1,contentArray);		
			
			TextBuddy2 testSort = new TextBuddy2();
			testSort.sortFile(contentArray,testFile1);
			
			//copy my sorted testFile to my checkerArray 
			TextBuddy2.fileLinesToArrayList(checkerArray, testFile1);
			Object[] resultArray = new String[checkerArray.size()];
			resultArray = checkerArray.toArray();
			String[] expectedArray = {"1", "2", "3", "4"};
			
			
			assertArrayEquals(expectedArray, resultArray);
		
	}
}
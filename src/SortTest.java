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
			//new arraylist
			ArrayList<String> contentArray = new ArrayList<String>();
			contentArray.add("d");
			contentArray.add("c");
			contentArray.add("b");
			contentArray.add("a");
			
			TextBuddy2.addToTxtFile(testFile,contentArray);		
			//textbuddy call testSort
			TextBuddy2 testSort = new TextBuddy2();
			testSort.sortFile(contentArray,testFile);
			
			Object[] resultArray = new String[contentArray.size()];
			resultArray = contentArray.toArray();
			String[] expectedArray = {"a", "b", "c", "d"};
			
			assertArrayEquals(expectedArray, resultArray);
		
	}
	
}
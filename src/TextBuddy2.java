
//Name:Wong Kok Woo
//Matrix No.: A0125303X
//CE2
//Tutorial ID: F10

/* 
 * This program manipulates the text in a text file
 * It saves the text file after each operation
 * It consists of 5 different types of command 
 * - "add" the line to the text file
 * - "display" all the line stored in the text file
 * - "delete" a line in the text file
 * - "clear" the file
 * - "exit" the program
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextBuddy2 {
	
	//variables
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<String> arrayOfText = new ArrayList<String>();
	private static String fileName;
	private static File file = new File(fileName);
	private static String userInput;
	private static String userCommand;
	private static FileWriter fileWriter;
	private static BufferedWriter bufferedWriter;
	
	
	// commands
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_EXIT = "exit";
	
	//messages
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. ";
	private static final String MESSAGE_WELCOME_1 = "is ready for use";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_TEXT_ADDED = "add to " +fileName+ ": ";
	
	static void main(String[] args) throws IOException{
		fileName = args[0];
		System.out.println(MESSAGE_WELCOME +fileName+ MESSAGE_WELCOME_1);
		
		if(!file.exists()){
			createNewTxtFile();
		}
		else{
			fileLinesToArray();
		}
		
		requestCommand();
		userInput = readUserInput();
		
		while(!userInput.equals(COMMAND_EXIT)){
			readUserCommand(userInput);
			
			if(userCommand.equals(COMMAND_ADD)){
				String content = findContent(userInput);
				addToArrayList(content);
				addToTxtFile(content);
				showAddedMsg(content);		
			}
			
		}
	}

	private static void showAddedMsg(String content) {
		System.out.print(MESSAGE_TEXT_ADDED + '"' + content + '"');
	}

	private static void addToArrayList(String content) {
		arrayOfText.add(content);
	}
	
	private static void readUserCommand(String text) {
		if(text.contains(" ")){
			userCommand = text.substring(0, text.indexOf(" "));
		}
		else{
			userCommand = text;
		}
	}
	
	private static String findContent(String userInput) {
		String content = userInput.substring(4);
		return content;
	}
	
	private static void addToTxtFile(String content) throws IOException {
		
		fileWriter = new FileWriter(file);
		bufferedWriter = new BufferedWriter(fileWriter);
		int numOfLines = arrayOfText.size();
		rewriteTextFile(numOfLines);
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	
	private static void rewriteTextFile(int numOfLines) throws IOException {
		for(int i=0; i<numOfLines; i++){
			bufferedWriter.write(arrayOfText.get(i));
			bufferedWriter.newLine();
		}
	}
	
	
	//other methods
	private static String readUserInput() {
		return scanner.nextLine();
	}

	//private static void fileLinesToArray() {
		//restoreLines(file, arrayOfText);
	//}

	private static void createNewTxtFile() throws IOException {
		file.createNewFile();
	}
	
	private static void requestCommand() {
		System.out.print(MESSAGE_COMMAND);
	}

}



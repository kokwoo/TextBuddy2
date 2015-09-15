
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
	private static ArrayList<String> arrayOfText;
	private static File file;
	private static String fileName;
	private static String userInput;
	private static String userCommand;
	private static String content;
	private static FileWriter fileWriter;
	private static BufferedWriter bufferedWriter;
	private static FileReader fileReader;
	private static BufferedReader bufferedReader;
	
	
	// commands
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_EXIT = "exit";
	
	//messages
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. ";
	private static final String MESSAGE_WELCOME_1 = " is ready for use";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_TEXT_ADDED = "added to ";
	
	public static void main(String[] args) throws IOException{
		initiateProgram(args);
	
		
		if(!file.exists()){
			createNewTxtFile();
		}
		else{
			fileLinesToArrayList();
		}
		
		requestCommand();
		readUserInput();
		
		while(!userInput.equals(COMMAND_EXIT)){
			readUserCommand();
			
			if(userCommand.equals(COMMAND_ADD)){
				findContent();
				addToArrayList(content);
				addToTxtFile(content);
				showAddedMsg(content);	
			}
			
			printNewLine();
			requestCommand();
			readUserInput();	
		}
	}
	
	

	private static void printNewLine() {
		System.out.println();
	}

	private static void showAddedMsg(String content) {
		System.out.print(MESSAGE_TEXT_ADDED +fileName+ ": " + '"' + content + '"');
	}

	private static void addToTxtFile(String content) throws IOException {
		
		createNewWriter();
		int numOfLines = arrayOfText.size();
		rewriteTextFile(numOfLines);
		bufferedWriter.flush();
		bufferedWriter.close();
	}



	private static void createNewWriter() throws IOException {
		fileWriter = new FileWriter(file);
		bufferedWriter = new BufferedWriter(fileWriter);
	}

	private static void rewriteTextFile(int numOfLines) throws IOException {
		for(int i=0; i<numOfLines; i++){
			bufferedWriter.write(arrayOfText.get(i));
			bufferedWriter.newLine();
		}
	}

	private static void addToArrayList(String content) {
		arrayOfText.add(content);
	}

	private static void findContent() {
		content = userInput.substring(4);
	}

	private static void readUserCommand() {
		if(userInput.contains(" ")){
			userCommand = userInput.substring(0, userInput.indexOf(" "));
		}
		else{
			userCommand = userInput;
		}
	}

	private static void readUserInput() {
		userInput = scanner.nextLine();
	}

	private static void requestCommand() {
		System.out.print(MESSAGE_COMMAND);
	}

	private static void fileLinesToArrayList() throws IOException {
		int lineCount = countLinesInFile();
		createNewReader();
		//restore the lines from file to array
		for(int i=0; i<lineCount; i++){
			arrayOfText.add(bufferedReader.readLine());
		}
		bufferedReader.close();	
	}

	private static int countLinesInFile() throws IOException {		
		createNewReader();
		int lineCounter = 0;
		//counts the number of line in the text file
		while(bufferedReader.readLine() != null){
			lineCounter++;	
		}
		bufferedReader.close();
		return lineCounter;
	
	}

	private static void createNewReader() throws FileNotFoundException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
	}

	private static void createNewTxtFile() throws IOException {
		file.createNewFile();
	}

	private static void initiateProgram(String[] args) {
		arrayOfText = new ArrayList<String>();
		fileName = args[0];
		file = new File(fileName);
		System.out.println(MESSAGE_WELCOME +fileName+ MESSAGE_WELCOME_1);
	}

}


//for testing
//for(int i=0; i<arrayOfText.size(); i++){
//System.out.println(arrayOfText.get(i));

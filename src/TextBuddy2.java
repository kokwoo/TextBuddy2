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
//import java.util.Collections;
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
	private static int lineToDelete;
	private static int linesInFile;
	
	//TextBuddy2 as an object
	private static TextBuddy2 textBuddy2;
	
	// commands
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_EXIT = "exit";
	//private static final String COMMAND_SORT = "sort";
	//private static final String COMMAND_SEARCH = "search";
	
	//messages
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. ";
	private static final String MESSAGE_WELCOME_1 = " is ready for use";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_TEXT_ADDED = "added to ";
	private static final String MESSAGE_TEXT_DELETE_ERROR = "text does not exits, unable to delete from ";
	private static final String MESSAGE_TEXT_DELETED = "deleted from ";
	private static final String MESSAGE_TEXTFILE_CLEARED = "all content deleted from ";
	private static final String MESSAGE_FILE_EMPTY = " is empty";
	//private static final String MESSAGE_FILE_SORTED = " is sorted";
	
	public static void main(String[] args) throws IOException{
		
		//construct a new TextBuddy2 
		textBuddy2 = new TextBuddy2(args);	
		textBuddy2.runProgram();
	}
	
	//read command, read content, execute command
	private void runProgram() throws IOException, FileNotFoundException {
		while(!userInput.equals(COMMAND_EXIT)){
			readUserCommand();	
			executeCommand();
			printNewLine();
			requestCommand();
			readUserInput();	
		}
	}
	
	//constructor
	public TextBuddy2(String[] args) throws IOException {
		initiateProgram(args);
		//create new file if file does not exist, else write to old file
		if(!file.exists()){
			createNewTxtFile();
		}
		else{
			fileLinesToArrayList();
		}
		requestCommand();
		readUserInput();
	}
	
	private static void executeCommand() throws IOException, FileNotFoundException {
		if(userCommand.equals(COMMAND_ADD)){		
			textBuddy2.addMethod();
		}
		
		else if(userCommand.equals(COMMAND_DELETE)){
			textBuddy2.deleteMethod();
		}
		
		else if(userCommand.equals(COMMAND_CLEAR)){
			textBuddy2.clearMethod();
		}
		
		else if(userCommand.equals(COMMAND_DISPLAY)){
			textBuddy2.displayTxtFile();
		}
		
	
	}
	
	//Public methods for unit testing
	public void clearMethod() throws FileNotFoundException {
		clearTxtFileAndArrayList();
		showClearedMsg();
	}

	public void deleteMethod() throws IOException {
		stringToInt();
		if(lineToDelete> sizeOfArray()){
			printErrorMsg();
		}
		else{
			deleteLineInTxtFile();
			showDeletedMsg();
		}
	}

	public void addMethod() throws IOException {
		findContent();
		addToArrayList(content);
		addToTxtFile(content);
		showAddedMsg(content);
	}
	
	public void displayTxtFile() throws IOException{
		countLinesInFile();
		createNewReader();
		content = bufferedReader.readLine();
		if(content == null){
			showFileEmpty();
		}
		else{
			printTextInFile();
		}		
		bufferedReader.close();
	}
	
	//Private methods 
	private static void printTextInFile() throws IOException {
		for(int i=1; i<=linesInFile; i++){
			printLineTextWithNum(i);
			if(i!=linesInFile){
				printNewLine();
			}
			content = bufferedReader.readLine();
		}
	}

	private static void printLineTextWithNum(int i) {
		System.out.print(i + ". " + content );
	}

	private static void showFileEmpty() {
		System.out.print(fileName + MESSAGE_FILE_EMPTY);
	}
	
	
	private static void showClearedMsg() {
		System.out.print( MESSAGE_TEXTFILE_CLEARED+ fileName);
	}
	
	private static void clearTxtFileAndArrayList() throws FileNotFoundException{
		arrayOfText.clear();
		clearTxtFile();	
	}

	private static void showDeletedMsg() {
		System.out.print(MESSAGE_TEXT_DELETED +fileName+ ": " + '"'+content+'"');
	}

	private static void deleteLineInTxtFile() throws IOException {
		createNewWriter();
		content = arrayOfText.get(lineToDelete-1);
		arrayOfText.remove(lineToDelete-1);
		clearTxtFile();
		rewriteTextFile(sizeOfArray());
		bufferedWriter.close();
	}
	
	private static void clearTxtFile() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();	
	}
	
	private static void printErrorMsg() {
		System.out.print(MESSAGE_TEXT_DELETE_ERROR +fileName);
	}
	
	private static void stringToInt() {
		findContent();
		lineToDelete = Integer.parseInt(content);
	}
	
	private static void printNewLine() {
		System.out.println();
	}

	private static void showAddedMsg(String content) {
		System.out.print(MESSAGE_TEXT_ADDED +fileName+ ": " + '"' + content + '"');
	}

	private static void addToTxtFile(String content) throws IOException {
		createNewWriter();
		rewriteTextFile(sizeOfArray());
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	private static int sizeOfArray() {
		return arrayOfText.size();
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
		content = userInput.replace(userCommand, "").trim();
	}

	private static void readUserCommand() {
		userCommand = userInput.trim().split("\\s+")[0];
	}

	private static void readUserInput() {
		userInput = scanner.nextLine();
	}

	private static void requestCommand() {
		System.out.print(MESSAGE_COMMAND);
	}

	private static void fileLinesToArrayList() throws IOException {
		countLinesInFile();
		createNewReader();
		//restore the lines from file to array
		for(int i=0; i<linesInFile; i++){
			arrayOfText.add(bufferedReader.readLine());
		}
		bufferedReader.close();	
	}

	private static void countLinesInFile() throws IOException {		
		createNewReader();
		int lineCounter = 0;
		//counts the number of line in the text file
		while(bufferedReader.readLine() != null){
			lineCounter++;	
		}
		bufferedReader.close();
		linesInFile = lineCounter;	
	}

	private static void createNewReader() throws FileNotFoundException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
	}

	private static void createNewTxtFile() throws IOException {
		file.createNewFile();
	}

	private static String initiateProgram(String[] args) {
		arrayOfText = new ArrayList<String>();
		fileName = args[0];
		file = new File(fileName);
		System.out.println(MESSAGE_WELCOME +fileName+ MESSAGE_WELCOME_1);
		return fileName;
	}
}
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
import java.util.Collections;
import java.util.Scanner;

public class TextBuddy2 {

	// variables
	private static Scanner scanner = new Scanner(System.in);
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

	// TextBuddy2 as an object
	private static TextBuddy2 textBuddy2;

	// commands
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_SORT = "sort";
	private static final String COMMAND_SEARCH = "search";

	// messages
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. ";
	private static final String MESSAGE_WELCOME_1 = " is ready for use";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_TEXT_ADDED = "added to ";
	private static final String MESSAGE_TEXT_DELETE_ERROR = "text does not exits, unable to delete from ";
	private static final String MESSAGE_TEXT_DELETED = "deleted from ";
	private static final String MESSAGE_TEXTFILE_CLEARED = "all content deleted from ";
	private static final String MESSAGE_FILE_EMPTY = " is empty";
	private static final String MESSAGE_INVALID_COMMAND = "invalid command";
	private static final String MESSAGE_FILE_SORTED = " is sorted";

	public static void setFileName(String newFileName){
		fileName = newFileName;
	}
	
	// default constructor
	public TextBuddy2() {
	}

	// constructor
	public TextBuddy2(String fileName, File file, ArrayList<String> arrayOfText) throws IOException {
		// create new file if file does not exist, else write to old file
		if (!file.exists()) {
			createNewTxtFile(file);
		} else {
			//restore the information form file to arraylist
			fileContentToArrayList(arrayOfText, file);
		}		
	}

	private void printWelcomeMsg(String fileName) {
		System.out.println(MESSAGE_WELCOME + fileName + MESSAGE_WELCOME_1);
	}
	
	public static void main(String[] args) throws IOException {
		//filename as first argument
		fileName = args[0];
		ArrayList<String> arrayOfText = instantiateArrayList();
		File file = instantiateFile();
		instantiateTestBuddy2(arrayOfText, file);
		textBuddy2.runProgram(arrayOfText, file);
	}
	
	//instantiate file,arraylist, textbuddy2
	private static File instantiateFile() {
		File file = new File(fileName);
		return file;
	}
	private static ArrayList<String> instantiateArrayList() {
		ArrayList<String> arrayOfText = new ArrayList<String>();
		return arrayOfText;
	}
	private static void instantiateTestBuddy2(ArrayList<String> arrayOfText, File file) throws IOException {
		textBuddy2 = new TextBuddy2(fileName, file, arrayOfText);
	}

	// show welcome message, read command, read content, execute command
	public void runProgram(ArrayList<String> arrayOfText, File file) throws IOException, FileNotFoundException {
		printWelcomeMsg(fileName);
		requestCommand();
		readUserInput();
		while (!userInput.equals(COMMAND_EXIT)) {
			readUserCommand();
			executeCommand(arrayOfText, file);
			printNewLine();
			requestCommand();
			readUserInput();
		}
	}

	public static void executeCommand(ArrayList<String> arrayOfText, File file)
			throws IOException, FileNotFoundException {
		if (userCommand.equals(COMMAND_ADD)) {
			textBuddy2.addMethod(file, arrayOfText);
		}

		else if (userCommand.equals(COMMAND_DELETE)) {
			textBuddy2.deleteMethod(arrayOfText, file);
		}

		else if (userCommand.equals(COMMAND_CLEAR)) {
			textBuddy2.clearMethod(arrayOfText, file);
		}

		else if (userCommand.equals(COMMAND_DISPLAY)) {
			textBuddy2.displayTxtFile(file);
		}
		
		else if (userCommand.equals(COMMAND_SORT)) {
			if (arrayOfText.isEmpty()) {
				printFileEmptyMsg();
			} else {
				textBuddy2.sortFile(arrayOfText, file);
			}
		}
		
		else if(userCommand.equals(COMMAND_SEARCH)){
			content = findContent();
			if(arrayOfText.isEmpty()){
				printFileEmptyMsg();
			}
			else{
				//wordFoundLines stores a list of lines that contain the word the user is searching
				ArrayList<String> wordFoundLines = textBuddy2.searchFile(arrayOfText, file, content);
				printLinesWithSearchWord(wordFoundLines);
			}
		}
		
		else {
			printInvalidCommandMsg();
		}
	}

	public ArrayList<String> searchFile(ArrayList<String> arrayOfText, File file, String content) {
		ArrayList<String> wordFoundLines = new ArrayList<String>();
		for(int i=0; i<arrayOfText.size();i++){
			if(containWord(arrayOfText.get(i), content)){
				wordFoundLines.add(arrayOfText.get(i));
			}
		}
		return wordFoundLines;
	}

	private static void printLinesWithSearchWord(ArrayList<String> wordFoundLines) {
		if(wordFoundLines.isEmpty()){
			printWordNotFoundMsg();
		}
		else{
			printSearchResults(wordFoundLines);
		}
	}

	private static void printSearchResults(ArrayList<String> wordFoundLines) {
		System.out.println("Here are your search results:");
		for(int j=1;j<=wordFoundLines.size();j++){
			content = wordFoundLines.get(j-1);
			printLineTextWithNum(j);
			if(j!=wordFoundLines.size()){
				printNewLine();
			}
		}
	}

	private static void printWordNotFoundMsg() {
		System.out.print(fileName + " does not contain "+ content );
	}
	
	private static boolean containWord(String text, String content){
		String[] splited = text.split("\\s+");
		for(int i=0; i<splited.length;i++){
			if(splited[i].equalsIgnoreCase(content)){
				return true;
			}
		}
		return false;
	}
	
	public void sortFile(ArrayList<String> arrayOfText, File file) throws IOException, FileNotFoundException {
		Collections.sort(arrayOfText);
		createNewWriter(file);
		clearTxtFile(file);
		rewriteTextFile(arrayOfText.size(), arrayOfText);
		bufferedWriter.flush();
		bufferedWriter.close();
		printFileSortedMsg();
	}

	private static void printFileSortedMsg() {
		System.out.print(fileName + MESSAGE_FILE_SORTED);
	}
	
	private static void printInvalidCommandMsg() {
		System.out.print(MESSAGE_INVALID_COMMAND);
	}

	// Public non-static methods 
	public void clearMethod(ArrayList<String> arrayOfText, File file) throws FileNotFoundException {
		clearTxtFileAndArrayList(arrayOfText, file);
		printClearedMsg();
	}

	public void deleteMethod(ArrayList<String> arrayOfText, File file) throws IOException {
		findContent();
		stringToInt();
		if (lineToDelete > sizeOfArray(arrayOfText)) {
			printErrorMsg();
		} else {
			deleteLineInTxtFile(arrayOfText, file);
			printDeletedMsg();
		}
	}

	public void addMethod(File file, ArrayList<String> arrayOfText) throws IOException {
		findContent();
		addToArrayList(content, arrayOfText);
		addToTxtFile(file, arrayOfText);
		printAddedMsg(content);
	}

	public void displayTxtFile(File file) throws IOException {
		countLinesInFile(file);
		createNewReader(file);
		content = bufferedReader.readLine();
		if (content == null) {
			printFileEmptyMsg();
		} else {
			printTextInFile();
		}
		bufferedReader.close();
	}

	private static void printTextInFile() throws IOException {
		for (int i = 1; i <= linesInFile; i++) {
			printLineTextWithNum(i);
			if (i != linesInFile) {
				printNewLine();
			}
			content = bufferedReader.readLine();
		}
	}

	private static void printLineTextWithNum(int i) {
		System.out.print(i + ". " + content);
	}

	private static void printFileEmptyMsg() {
		System.out.print(fileName + MESSAGE_FILE_EMPTY);
	}

	private static void printClearedMsg() {
		System.out.print(MESSAGE_TEXTFILE_CLEARED + fileName);
	}

	private static void clearTxtFileAndArrayList(ArrayList<String> arrayOfText, File file)
			throws FileNotFoundException {
		arrayOfText.clear();
		clearTxtFile(file);
	}

	private static void printDeletedMsg() {
		System.out.print(MESSAGE_TEXT_DELETED + fileName + ": " + '"' + content + '"');
	}

	private static void deleteLineInTxtFile(ArrayList<String> arrayOfText, File file) throws IOException {
		createNewWriter(file);
		getContentToDelete(arrayOfText);
		removeContentInArrayList(arrayOfText);
		clearTxtFile(file);
		rewriteTextFile(sizeOfArray(arrayOfText), arrayOfText);
		bufferedWriter.close();
	}

	private static void removeContentInArrayList(ArrayList<String> arrayOfText) {
		arrayOfText.remove(lineToDelete - 1);
	}

	private static void getContentToDelete(ArrayList<String> arrayOfText) {
		content = arrayOfText.get(lineToDelete - 1);
	}

	public static void clearTxtFile(File file) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}

	private static void printErrorMsg() {
		System.out.print(MESSAGE_TEXT_DELETE_ERROR + fileName);
	}

	private static void stringToInt() {
		lineToDelete = Integer.parseInt(content);
	}

	private static void printNewLine() {
		System.out.println();
	}

	private static void printAddedMsg(String content) {
		System.out.print(MESSAGE_TEXT_ADDED + fileName + ": " + '"' + content + '"');
	}

	public static void addToTxtFile(File file, ArrayList<String> arrayOfText) throws IOException {
		createNewWriter(file);
		rewriteTextFile(sizeOfArray(arrayOfText), arrayOfText);
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	private static int sizeOfArray(ArrayList<String> arrayOfText) {
		return arrayOfText.size();
	}

	private static void createNewWriter(File file) throws IOException {
		fileWriter = new FileWriter(file);
		bufferedWriter = new BufferedWriter(fileWriter);
	}

	private static void rewriteTextFile(int numOfLines, ArrayList<String> arrayOfText) throws IOException {
		for (int i = 0; i < numOfLines; i++) {
			bufferedWriter.write(arrayOfText.get(i));
			bufferedWriter.newLine();
		}
	}

	public static void addToArrayList(String content, ArrayList<String> arrayOfText) {
		arrayOfText.add(content);
	}

	private static String findContent() {
		content = userInput.replace(userCommand, "").trim();
		return content;
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

	public static void fileContentToArrayList(ArrayList<String> arrayOfText, File file) throws IOException {
		countLinesInFile(file);
		createNewReader(file);
		// restore the lines from file to array
		for (int i = 0; i < linesInFile; i++) {
			arrayOfText.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		
	}

	private static void countLinesInFile(File file) throws IOException {
		createNewReader(file);
		int lineCounter = 0;
		// counts the number of line in the text file
		while (bufferedReader.readLine() != null) {
			lineCounter++;
		}
		bufferedReader.close();
		linesInFile = lineCounter;
	}

	private static void createNewReader(File file) throws FileNotFoundException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
	}

	private static void createNewTxtFile(File file) throws IOException {
		file.createNewFile();
	}

}

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

	// commands
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_EXIT = "exit";
	// private static final String COMMAND_SORT = "sort";
	// private static final String COMMAND_SEARCH = "search";

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
	// private static final String MESSAGE_FILE_SORTED = " is sorted";
	
	
	//constructor for TextBuddy2
	public TextBuddy2() {
	}
	
	public static void main(String[] args) throws IOException {
		// construct a new TextBuddy2
		TextBuddy2 textBuddy2 = new TextBuddy2();
		textBuddy2.runProgram();
	}
}
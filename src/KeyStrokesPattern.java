// Karl Blankenship
// COSC 1437
// Project I - Key Strokes Pattern
// June 2018
// Filename: KeyStrokesPattern.java


import java.io.*;		//Required for file Input and Output.
import java.util.*;		//Required for reading text with Scanner object.



/**
 	This class creates a main method program to analyze keystroke frequency in a text file.
 	This class will read in a text file, define all the different keystrokes, display a
 	count of all keystrokes and write this analysis to an output file.
 	@author Karl Blankenship
*/

public class KeyStrokesPattern 
{

	
	
	/**
	 	Entry point for program execution.
	 	This method will prompt user for a file name
	 	and check that the file exists. The text file
	 	will be read once character at a time and each
	 	character will be counted. A tally will then be
	 	displayed to the program output and written to
	 	an output file.  
	*/
	
	public static void main(String[] args)  throws IOException
	{
		//Create a KeyStrokesPattern object for invoking local methods.
		KeyStrokesPattern keyStrokes = new KeyStrokesPattern();
		
		String inputFileName;		//Reference variable to hold the input file name.
		
		//Get file name from user.
		inputFileName = keyStrokes.getFileName();		
	
		//Create a File object using the file name entered by the user.
		File inputFile = new File(inputFileName);
		
		//Stop program with message if user-entered file cannot be located.
		if (!inputFile.exists())
		{
			System.out.println(inputFile + " was not found. Program Stopped.");
			System.exit(0);
		}
		
		//Create a new ArrayList object named keyStrokeList to accumulate KeyStroke objects.
		//KeyStroke.java is a user created class for creating objects that each contain a
		//keyboard character and running count for that character.
		ArrayList<KeyStroke> keyStrokeList = new ArrayList<KeyStroke>(0);
		
		//Analyze the input file and return an ArrayList containing KeyStroke objects.
		keyStrokeList = keyStrokes.analyzeFile(inputFile);
		
		//Display the ArrayList of KeyStroke objects to the console window.
		keyStrokes.displayOutput(keyStrokeList);
		
		//Write the ArrayList of KeyStroke objects to output.txt file.
		keyStrokes.writeOutputFile(keyStrokeList);
	}	
		
	
	
	/**
		The getFile method prompts the user for a text file and returns the 
		file name.
		@return the file name entered by the user as a String.
	*/
	
	public String getFileName()
	{
		String fileNameEntry;		//Create a reference variable to hold input file name.
		
		//Create a new Scanner object for reading keyboard inputs.
		Scanner keyboardInput = new Scanner(System.in);
				
		//Obtain text file name from the user.
		System.out.print("Enter the name of the text file to be analyzed (*.txt): ");
		fileNameEntry = keyboardInput.nextLine();
				
		//Close keyboardInput object to clear warning in eclipse editor.
		keyboardInput.close();
		
		//Return the proposed file to the main method.
		return fileNameEntry;	
	}
	
	
	
	/**
		The analyzeFile method will read the file one character at a time and will
		add new KeyStroke objects along with a running sum to the ArrayList object.
		@param file object that is to be analyzed.
		@return an ArrayList of KeyStroke objects with sums.
	*/
		
	public ArrayList<KeyStroke> analyzeFile(File input) throws IOException
	{
		//Create a new Scanner object textFromFile to read the input file.
		Scanner textFromFile = new Scanner(input);
		
		//Scan file one character at a time.
		textFromFile.useDelimiter("");
		
		String characterFromFile;	//Holds a single key stroke text object.
		boolean characterExists;	//Flag for determining if characterFromFile is already
									//a KeyStroke object.
		
		//Create an ArrayList object to hold KeyStroke objects.
		ArrayList<KeyStroke> keyStrokeObjectsList = new ArrayList<KeyStroke>(0);
		
		//Continue reading from the file while there are still remaining characters.
		while (textFromFile.hasNext()) 
		{
			characterFromFile = textFromFile.next();	//Set to the next character. 	
			characterExists = false; 	//Set flag to false.
			
			//Loop through existing keyStrokeList objects and compare each existing
			//objects character value to the current characterFromFile to see if the 
			//current character already exists as an object in the ArrayList. If it 
			//already exists, increment the object counter for that object in the 
			//ArrayList (keyStrokeList). 
			for (KeyStroke item : keyStrokeObjectsList)
			{
				if (characterFromFile.equals(item.returnKeyStrokeCharacter()))
				{
					item.incrementCount();
					characterExists = true;
					break;
				}
			}
			
			//If there is no matching object, create a new KeyStroke object containing
			//the current characterFromFile, increment the count to 1 and add the
			//new object to the keyStrokeList.
			if (!characterExists)
			{
				keyStrokeObjectsList.add(new KeyStroke(characterFromFile));
			}	
		}
		
		//Close the scanner object.
		textFromFile.close();
		
		//Return the keyStrokeObjectList ArrayList object.
		return keyStrokeObjectsList;
	}	
		
	
	
	/**
	 	The displayOutput method will display the Unicode, Character and count for
	 	each keystroke present in the KeyStroke ArrayList object. Counted keystrokes
	 	include all visible keyboard characters	as well as spaces, tabs (Unicode value 9),
	 	carriage returns (Unicode value 13), new lines (Unicode value 10) and
	 	space (Unicode 32).
		@param characterObjectArrayList
	*/
	
	public void displayOutput(ArrayList<KeyStroke> characterObjectArrayList)
	{
		//Loop though all keyStrokeObjects in the characterObjectArrayList and display the 
		//keystroke characters and total occurrences for each character.
		for (KeyStroke keyStrokeObject : characterObjectArrayList)
		{	
			System.out.println("Unicode: " + keyStrokeObject.returnKeyStrokeCharacter().codePointAt(0) +
							   " Character: " + keyStrokeObject.returnKeyStrokeCharacter() +
							   "  Count: " + keyStrokeObject.returnCount());
		}	
	}
	
	
	
	/**
		The writeOutputFile method will write the Unicode, Character and count for
	 	each keystroke present in the KeyStroke ArrayList object to a text file. 
	 	Counted keystrokes include all visible keyboard characters	as well as spaces,
	 	tabs (Unicode value 9), carriage returns (Unicode value 13) new lines
	 	(Unicode value 10) and space (Unicode 32). The output file is text file 
	 	named output.txt.
		@param characterObjectArrayList
	*/
	
	public void writeOutputFile(ArrayList<KeyStroke> characterObjectArrayList) throws IOException
	{
		//Open the output file.
		PrintWriter outputFile = new PrintWriter("output.txt");
		
		//Loop though all keyStrokeObjects in the characterObjectArrayList and write the 
		//keystroke Unicode, characters and total occurrences for each character to the 
		//outputFile.
		for (KeyStroke keyStrokeObject : characterObjectArrayList)
		{				
			//Write properties of each object to a new line in outputFile.
			outputFile.println("Unicode: " + keyStrokeObject.returnKeyStrokeCharacter().codePointAt(0) +
								" Character: " + keyStrokeObject.returnKeyStrokeCharacter() +
								"  Count: " + keyStrokeObject.returnCount());
		}
		
		//Close the output file.
		outputFile.close();
		
		//Notify user that file is closed.
		System.out.println("Data written to output.txt");
	}
}


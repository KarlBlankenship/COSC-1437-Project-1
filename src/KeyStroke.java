// Karl Blankenship
// COSC 1437
// Project I - Key Strokes Pattern
// June 2018
// Filename: KeyStroke.java


/**
 	This is a user defined class describing KeyStroke objects that can hold a 
 	string of a single character and a count of how many times that character 
 	has been seen. 
 	This class will be capable initializing with keystroke character and an 
 	initial count of 1.
 	This class will contain methods increment the count, return the count and
 	return the string character.
 	@author Karl Blankenship
*/

public class KeyStroke 
{
	private int count = 0;	//Define the counter and initialize it at 0.
	private String keyStrokeCharacter = "";	//Holds the KeyStroke String Character.
	
	
	
	/**
		Constructor which will set the count to 1 and will set the keyStrokeCharacter.
		@param keyStroke The string character.
	*/
	
	public KeyStroke(String keyStroke)
	{
		count = 1;
		keyStrokeCharacter = keyStroke;
	}
	
	
	
	/**
		The incrementCount method will increment the counter.
	*/
	
	public void incrementCount()
	{
		count++;
	}
	
	
	
	/**
	 	The returnCount method will return the current count.
		@return The current count stored in the object.
	*/
	
	public int returnCount()
	{
		return count;
	}
	

	
	/**
		The returnKeyStrokeCharacter method will return the objects character.
		@return A string object pointing to a single character.
	*/
	
	public String returnKeyStrokeCharacter()
	{
		return keyStrokeCharacter;
	}
}

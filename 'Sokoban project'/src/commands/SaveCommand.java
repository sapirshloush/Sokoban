package commands;

import java.io.*;
import java.util.*;
import Levels.*;
import exceptions.UnknownCommandException;

/**
 * This class represents the command of saving the game.
 * Uses the provided decoded saving command and with the 
 * appropriate saver class it saves the game in the suitable file type.
 * 
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public class SaveCommand implements Command
{
	private Level levelToWrite;
	private Map<String, LevelSaver> savingCommands;
	private FileOutputStream fos;
	private String fileName;
	private LevelSaver saver;
	
	/**
	 * Constructs a saving command which saves the given level to the given file.
	 * @param fileName - The name of the file to write to.
	 * @param level - The level to write to the file.
	 * @throws UnknownCommandException - in case of inserting an invalid command.
	 * @throws StringIndexOutOfBoundsException - in case of inserting a valid command without the expected file name.
	 */
	public SaveCommand(String fileName, Level level) 
			throws UnknownCommandException, StringIndexOutOfBoundsException
	{	
		if(fileName.equals(""))
			throw new UnknownCommandException();
		
		this.fileName = fileName;
		levelToWrite = level;
		saver = null;
		
		try
		{
			fos = new FileOutputStream(fileName);
		}
		catch(IOException e)
		{
			System.err.println("Cannot write to the specified file.");
		}
		
		savingCommands = new HashMap<String, LevelSaver>();
		savingCommands.put("txt", new MyTextLevelSaver());
		savingCommands.put("xml", new MyXMLLevelSaver());
		savingCommands.put("obj", new MyObjectLevelSaver());
	}
	
	/**
	 * This method is responsible for decoding the file suffix
	 * and with it executes the suitable saving method.
	 */
	public void execute()
	{	
		String suffix = fileName.substring(fileName.length() - 3, fileName.length());
		if(suffix.equals("txt") || suffix.equals("obj") || suffix.equals("xml"))
		{
			saver = savingCommands.get(suffix);
			saver.saveLevel(levelToWrite, fos);
			System.out.println("File was saved successfully!");
		}
		
		else
			System.err.println("Cannot save the file. The specified suffix is invalid.");
	}
}
package commands;

import java.io.*;
import java.util.*;
import Levels.*;
import exceptions.UnknownCommandException;

/**
 * This class represents the command of loading the game.
 * Uses the provided decoded loading command from the User(CLI)
 * and decodes the type of file from which the user wishes
 * to load the file.
 * 
 * 
 * @implements {@linkplain Command}
 * @author Sapir Shloush & Ben Ohayon
 */
public class LoadCommand implements Command
{
	private Level returnedLevel;
	private LevelLoader loader;
	private String fileName;
	private Map<String, LevelLoader> commandDecoder;
	private FileInputStream fis;
	
	/**
	 * Constructs a loading command with the requested file to load from.
	 * 
	 * @param fileName - The specified file's name to load from.
	 * @throws FileNotFoundException - in case of not finding the requested file.
	 * @throws UnknownCommandException - in case of inserting a load command with no following file name.
	 */
	public LoadCommand(String fileName) throws FileNotFoundException, UnknownCommandException
	{
		if(fileName.equals(""))
			throw new UnknownCommandException();
		
		this.fileName = fileName;
		returnedLevel = null;
		loader = null;
		try
		{
			fis = new FileInputStream(fileName);
		}
		catch (FileNotFoundException e)
		{
			throw new FileNotFoundException();
		}
		
		commandDecoder = new HashMap<String, LevelLoader>();
		commandDecoder.put("txt", new MyTextLevelLoader());
		commandDecoder.put("xml", new MyXMLLevelLoader());
		commandDecoder.put("obj", new MyObjectLevelLoader());
	}
	
	/**
	 * @return the loaded level from the suitable loader.
	 */
	public Level getReturnedLevel()
	{
		return returnedLevel;
	}
	
	/**
	 * This method executes the command by calling the suitable loader.
	 */
	public void execute()
	{
		String suffix = fileName.substring(fileName.length() - 3, fileName.length());
		if(suffix.equals("txt") || suffix.equals("obj") || suffix.equals("xml"))
		{
			loader = commandDecoder.get(suffix);
			returnedLevel = loader.loadLevel(fis);
			System.out.println("File was loaded successfully!");
		}
		
		else
		{
			System.err.println("Cannot load the file. The specified suffix is invalid.");
		}
	}
}
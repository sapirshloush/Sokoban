package MainRunning;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import Levels.Level;
import commands.*;
import exceptions.UnknownCommandException;

/** 
 * CLI (Command Line Interface) - 
 * This class represents the invoker which interacts with the user
 * and is responsible for getting the requested commands.
 * Provides decoding of the command and sends it to the 
 * appropriate receiver which finally executes the command.
 * 
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public class CLI
{
	private Level lvl;
	private String inputCommand;
	private Map<String, Command> commands;
	private Command cmd;
	
	/**
	 * Constructs a CLI interface with a given command.
	 * @param inputCommand - The requedted command from the user.
	 */
	public CLI(String inputCommand)
	{
		lvl = new Level();
		this.inputCommand = inputCommand;
	}
	
	/**
	 * This method decodes the given command and sends it to the 
	 * suitable receiver.
	 * @throws UnknownCommandException in case of getting an invalid command.
	 * @throws FileNotFoundException in case of failure in finding the requested file.
	 * @throws StringIndexOutOfBoundsException in case of 
	 */
	public void decodeCommand() 
			throws UnknownCommandException, FileNotFoundException, 
					StringIndexOutOfBoundsException, NullPointerException
	{
		String a = inputCommand.toLowerCase();
		commands = new HashMap<String, Command>();
		
		if(a.startsWith("load"))
		{
			commands.put("Load", new LoadCommand(inputCommand.substring(5)));
			cmd = commands.get("Load");
		}
		
		else if(a.startsWith("save"))
		{
			if(this.getLevel().getBoard() == null)
				throw new NullPointerException();
			
			commands.put("Save", new SaveCommand(inputCommand.substring(5), this.getLevel()));
			cmd = commands.get("Save");
		}
		
		else if(a.startsWith("display"))
		{
			if(this.getLevel().getBoard() == null)
				throw new NullPointerException();
			
			commands.put("Display", new DisplayCommand(this.getLevel()));
			cmd = commands.get("Display");
		}
		
		else if(a.startsWith("move"))
		{
			if(this.getLevel().getBoard() == null)
				throw new NullPointerException();
			
			commands.put("Move", new MoveCommand(inputCommand.substring(5), this.getLevel()));
			cmd = commands.get("Move");
		}
		
		else if(a.startsWith("exit"))
		{
			commands.put("Exit", new ExitCommand());
			cmd = commands.get("Exit");
		}
		
		else
			throw new UnknownCommandException();
	}
	
	/**
	 * This method sets the stored command string with a new command given by the user.
	 * @param input - The new command string from the user.
	 */
	public void setInputCommand(String input)
	{
		this.inputCommand = input;
	}
	
	/**
	 * @return the loaded level from the file(xml, txt, obj).
	 */
	public Level getLevel()
	{
		return this.lvl;
	}
	
	/**
	 * this method executes the suitable receiver 
	 * and by that executes the requested command.
	 */
	public void executeCommand()
	{
		if(cmd instanceof LoadCommand)
		{
			
			LoadCommand ld = (LoadCommand)cmd;
			ld.execute();
			lvl = ld.getReturnedLevel();
		}
		
		else
			cmd.execute();
		
	}
	
}














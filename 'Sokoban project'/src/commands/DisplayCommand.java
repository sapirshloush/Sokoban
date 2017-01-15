package commands;

import Levels.Level;

/**
 * This class represnts the command of displaying the current game state to the user.
 * With the given level to dislay, this command prints it on the screen.
 * 
 * @implements {@linkplain Command}
 * @author Sapir Shloush & Ben Ohayon
 */
public class DisplayCommand implements Command
{
	private Level levelToDisplay;
	
	/**
	 * Constructs a display command with the level to display, "lvl".
	 * @param lvl - The requested level to display on the screen.
	 */
	public DisplayCommand(Level lvl)
	{
		this.levelToDisplay = lvl;
	}
	
	/**
	 * This method displays the requested level to the screen.
	 */
	public void execute()
	{
		System.out.println(levelToDisplay);
	}
}


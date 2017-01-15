package commands;

/**
 * This class represents the command which exits the program 
 * and stops the routine of command input from the user.
 * 
 * @implements {@linkplain Command}
 * @author Sapir Shloush & Ben Ohayon
 */
public class ExitCommand implements Command
{
	/**
	 * This method is responsible for exiting the main program.
	 */
	public void execute()
	{
		System.out.println("Exiting...");
		System.exit(0);
	}
}

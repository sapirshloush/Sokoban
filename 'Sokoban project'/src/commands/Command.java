package commands;

/**
 * A representation of a command behavior.
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public interface Command
{
	/**
	 * This method represents the behavior of all this class's subclasses.
	 */
	public void execute();
}

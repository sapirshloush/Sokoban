package MainRunning;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Levels.*;
import Warehouse.*;
import commands.*;
import exceptions.UnknownCommandException;

public class MainProgram
{
	public static void main(String[] args)
	{
//		Level lvl = new Level();
//		ArrayList<ArrayList<ItemInWarehouse>> board = new ArrayList<ArrayList<ItemInWarehouse>>();
//		ArrayList<ItemInWarehouse> list1 = new ArrayList<ItemInWarehouse>();
//		ArrayList<ItemInWarehouse> list2 = new ArrayList<ItemInWarehouse>();
//		ArrayList<ItemInWarehouse> list3 = new ArrayList<ItemInWarehouse>();
//		ArrayList<ItemInWarehouse> list4 = new ArrayList<ItemInWarehouse>();
//		ArrayList<ItemInWarehouse> list5 = new ArrayList<ItemInWarehouse>();
//		
//		for(int i = 0 ; i < 10 ; i++)
//			list1.add(new Wall(new Position(0,i)));
//		
//		for(int i = 0 ; i < 5 ; i++)
//			list2.add(new Box(new Position(1,i)));
//		
//		for(int i = 0 ; i < 20 ; i++)
//			list3.add(new AnchorPoint(new Position(2,i)));
//		
//		for(int i = 0 ; i < 10 ; i++)
//			list4.add(new Player(new Position(3,i)));
//		
//		for(int i = 0 ; i < 30 ; i++)
//			list5.add(null);
//		
//		board.add(list1);
//		board.add(list2);
//		board.add(list3);
//		board.add(list4);
//		board.add(list5);
//			
//		lvl.setBoard(board);
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("please enter a command: ");
		String command = in.nextLine();
		CLI cli = new CLI(command);
		
		while(true)
		{
			cli.setInputCommand(command);
			try
			{
				cli.decodeCommand();
				cli.executeCommand();
			}
			catch (UnknownCommandException | StringIndexOutOfBoundsException e)
			{
				System.err.println("Invalid command.");
			}
			catch (FileNotFoundException e)
			{
				System.err.println("The file you're looking for does not exist.");
			} 
			catch(NullPointerException e)
			{
				System.err.println("The level is empty. Try to load the level first.");
			}
			
			command = in.nextLine();
		}
		
		
		
		
//		System.out.println("enter a command: ");
//		String cmd = in.nextLine();
//		Level level1 = null;
//		
//		CLI cli = new CLI(cmd);
//		
//		cli = new CLI(cmd);
//		try
//		{
//			cli.decodeCommand();
//			cli.executeCommand();
//			level1 = cli.getLevel();
//		}
//		catch (UnknownCommandException e)
//		{
//			e.printExceptionDescription();
//		} 
//		
//		System.out.println(level1);
	}
}

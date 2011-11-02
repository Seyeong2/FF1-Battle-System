package groups;

/**
 * Formation.java
 * @author Nicholas Hydock 
 * 
 * Description: A special form of ArrayList that keeps track of
 * 				enemy objects.  It can create new enemies using
 * 				just strings as well as keep track of which enemies
 * 				are still alive.  It can also create multiple enemies
 * 				at a time using an array of names.
 */

import java.util.ArrayList;
import java.util.List;

import jobs.Job;
import actors.Actor;
import actors.Enemy;
import actors.Player;

public class Formation extends ArrayList<Enemy>{
	
	/**
	 * Returns the number of players in the party that are alive
	 * @return
	 */
	public int getAlive()
	{
		int counter = 0;
		for (Enemy e: this)
			if (e.getAlive())
				counter++;
		return counter;
	}	

	/**
	 * Returns a list of all members that are alive
	 * @return
	 */
	public Enemy[] getAliveMembers() 
	{
		List<Enemy> alive = new ArrayList<Enemy>();
		for (Enemy e: this)
			if (e.getAlive())
				alive.add(e);
		return (Enemy[]) alive.toArray();
	}
	
	/**
	 * Make new enemies with class name of a
	 */
	public void add(String e)
	{
		this.add(new Enemy(e));
	}
	
	/**
	 * Make multiple new enemies
	 */
	public void add(String[] foes)
	{
		for (String e : foes)
			add(e);
	}
}
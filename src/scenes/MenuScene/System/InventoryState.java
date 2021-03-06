package scenes.MenuScene.System;

import scenes.GameState;
import engine.Input;

/**
 * InventoryState
 * @author nhydock
 *
 *	State that handles managing and viewing the party's
 *	items in possession
 */
public class InventoryState extends GameState
{
	String[] items;				//items to display
	boolean hasItems = false;	//if the party even has items
	
	//item selection
	int row;					//table row
	int col;					//table column
	
	//amount of columns to display
	public static final int COLUMNS = 3;
	
	/**
	 * Constructs the state
	 * @param menuSystem
	 */
	public InventoryState(MenuSystem menuSystem)
	{
		super(menuSystem);
		items = menuSystem.party.getInventory().getItemList();
		if (items.length > 0)
			hasItems = true;
	}

	/**
	 * Defaults the item index to 0
	 */
	@Override
	public void start()
	{
		index = 0;
	}

	/**
	 * Do nothing
	 */
	@Override
	public void handle()
	{
	}

	/**
	 * Return to the menu
	 */
	@Override
	public void finish()
	{
		parent.setNextState();
	}

	/**
	 * Handles input/navigating the list of items
	 */
	@Override
	public void handleKeyInput(int key)
	{
		//if the party has nothing then the menu is not navigable 
		if (!hasItems)
		{
			if (key == Input.KEY_A)
				finish();
		}
		else
		{
			//navigate by rows and columns
			if (key == Input.KEY_DN)
				row++;
			if (key == Input.KEY_UP)
				row--;
			if (key == Input.KEY_RT)
				col++;
			if (key == Input.KEY_LT)
				col--;
		
			//handle bounds to contain the arrow to the list
			if (row < 0)
				row = items.length/COLUMNS - 1;
			if (row >= items.length/COLUMNS)
				row = 0;
			if (col >= COLUMNS)
				col = 0;
			if (col < 0)
				col = COLUMNS-1;
			
			index = row*(int)Math.ceil(items.length/(double)COLUMNS)+col;
		}
		//exit the menu
		if (key == Input.KEY_B)
			finish();
	}

}

package scenes.CreationScene.System;

import scenes.GameSystem;
import actors.Player;
import engine.Engine;
import groups.Party;

/**
 * CreationSystem
 * @author nhydock
 *
 *  Logic system that handles how the creation scene should work
 */
public class CreationSystem extends GameSystem{

	Engine e;
	
	Player activePlayer;	//the current player being worked on
	int index;				//current player index
	Party party;
	
	ChooseJobsState cjs;
	NamingState ns;
	
	/**
	 * Creates the system that handles creating characters and
	 * a party to play with
	 */
	public CreationSystem()
	{
		e = Engine.getInstance();
		party = new Party();
		for (int i = 0; i < 4; i++)
			party.add(new Player("", Player.AVAILABLEJOBS.get(i)));
		
		index = 0;
		activePlayer = party.get(0);
		
		cjs = new ChooseJobsState(this);
		ns = new NamingState(this);
		
		state = cjs;
		state.start();
	}
	
	/**
	 * Gets the current player being worked on
	 * @return
	 */
	public Player getActivePlayer()
	{
		return activePlayer;
	}
	
	/**
	 * Replace the active player with the updated player
	 * Used when setting jobs
	 * @param p
	 */
	public void setActivePlayer(Player p)
	{
		party.set(index, p);
		activePlayer = party.get(index);
	}
	
	/**
	 * Ends creation and moves to world map
	 */
	@Override
	public void finish()
	{
		e.setParty(party);
		e.startGame();
	}

	/**
	 * Gets the current party in development
	 * @return
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * Gets the current character index
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
     * Advances the system into its next state
     */
    @Override
    public void setNextState()
    {
    	try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
           
        if (state == cjs)
            state = ns;
        else if (state == ns)
        {
        	index++;
            if (index >= party.size())
            {
                finish();
                return;
            }
            activePlayer = party.get(index);
            state = cjs;
        }
        state.start();
    }
    
    /**
     * Sets the current active player to the previous one
     */
    public void setPrevious()
    {
    	index = Math.max(0, index-1);
    	activePlayer = party.get(index);
    	state = cjs;
    	state.start();
    }
}

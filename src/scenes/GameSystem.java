package scenes;


/**
 * GameSystem
 * @author nhydock
 *
 *  GameSystems is the base class for scene controlling systems
 *  They house all the logic for the scene
 */
public abstract class GameSystem
{
    protected GameState state;        //current state of the scene
    
    /**
     * Updates the system
     */
    public void update()
    {
    	state.handle();
    }

    /**
     * Advances the system to the next state
     */
    abstract public void setNextState();

    /**
     * Returns the current state that the BattleSystem is in
     * @return
     */
    public GameState getState() {
        return state;
    }   
    
    /**
     * Handles key input
     * @param keyCode	the key code of the key pressed
     */
    public void keyPressed(int keyCode) {
        state.handleKeyInput(keyCode);
    }

	abstract public void finish();

	public void start() {}

}

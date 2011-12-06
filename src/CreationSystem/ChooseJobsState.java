package CreationSystem;

import java.awt.event.KeyEvent;

import engine.Input;
import jobs.*;
import actors.Player;

/**
 * ChooseJobsState
 * @author nhydock
 *
 *	Sets the job of the character
 */
public class ChooseJobsState extends CreationState {

	Player p;
	Job[] jobs;
	Job selectedJob;
	int index;
	
	ChooseJobsState(CreationSystem c) {
		super(c);
	}
	
	@Override
	public void start() {
		p = parent.getActivePlayer();
		jobs = new Job[Job.AVAILABLEJOBS.size()];
		for (int i = 0; i < Job.AVAILABLEJOBS.size(); i++)
			jobs[i] = new Job(p, Job.AVAILABLEJOBS.get(i));
		index = 0;
	}

	@Override
	public void handle() {
		if (index >= jobs.length)
			index = 0;
		parent.setActivePlayer(jobs[index]);
	}

	@Override
	public void finish() {
		parent.next();
	}

	@Override
	public void handleKeyInput(KeyEvent arg0) {
		if (arg0.getKeyCode() == Input.KEY_A)
			finish();
		else if (arg0.getKeyCode() == Input.KEY_DN ||
				 arg0.getKeyCode() == Input.KEY_UP ||
				 arg0.getKeyCode() == Input.KEY_LT ||
				 arg0.getKeyCode() == Input.KEY_RT)
			index++;
	}

}
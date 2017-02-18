package io.github.robotpy.magicbot.sm;

import io.github.robotpy.magicbot.MagicAutonomous;

/**
 * This is a specialized version of the StateMachine that is designed
 * to be used as an autonomous mode. There are a few key differences:
 * 
 * - The engage function is always called, so the state machine
 *   will always run to completion unless done() is called
 * - Messages will always be printed out upon each state transition
 */
public class AutonomousStateMachine extends StateMachine implements MagicAutonomous {

	private boolean m_engaged = false;
	
	@Override
	public void onEnabled() {
		super.onEnabled();
		m_engaged = true;
		m_verboseLogging = true;
	}
	
	public void autonomousPeriodic() {
		
		// Only engage the state machine until its execution finishes, otherwise
        // it will just keep repeating
        //
        // This is because if you keep calling engage(), the state machine will
        // loop. I'm tempted to change that, but I think it would lead to unexpected
        // side effects. Will have to contemplate this...
		
		if (m_engaged) {
			engage();
			execute();
			m_engaged = isExecuting();
		}
	}
}

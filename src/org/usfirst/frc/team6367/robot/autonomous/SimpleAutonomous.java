package org.usfirst.frc.team6367.robot.autonomous;

import io.github.robotpy.magicbot.sm.AutonomousStateMachine;
import io.github.robotpy.magicbot.sm.TimedState;

public class SimpleAutonomous extends AutonomousStateMachine {

	@TimedState(first=true, duration=1.0, nextState="final_state")
	private void first_state(Double tm, boolean initialState) {
		System.out.println("TM " + tm);
	}
	
	@TimedState(duration=2.0)
	private void final_state() {
		
	}
	
}

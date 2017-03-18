package org.usfirst.frc.team6367.robot.autonomous;

import org.usfirst.frc.team6367.robot.components.DriveTrain;

import io.github.robotpy.magicbot.MagicInject;
import io.github.robotpy.magicbot.sm.AutonomousStateMachine;
import io.github.robotpy.magicbot.sm.TimedState;

public class DriveForwardAutonomous extends AutonomousStateMachine {
	
	@MagicInject
	DriveTrain driveTrain;

	@TimedState(first=true, duration=2.5)
	public void drivingForward(){
		driveTrain.move(-0.5, 0);
		
	}
	
}

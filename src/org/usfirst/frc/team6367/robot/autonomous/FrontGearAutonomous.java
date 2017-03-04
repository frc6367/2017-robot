package org.usfirst.frc.team6367.robot.autonomous;

import org.usfirst.frc.team6367.robot.components.DriveTrain;

import io.github.robotpy.magicbot.sm.TimedState;

public class FrontGearAutonomous {

	DriveTrain driveTrain;
	
	@TimedState(first=true, duration=2)
	public void drivingForward(){
			   driveTrain.move(-0.5, 0);
		
	}
	@TimedState( duration=1)
	public void Wait(){
		driveTrain.move(0, 0);
		
	}
	@TimedState(duration=1)
	public void moveBackward(){
		driveTrain.move(0.5, 0);
	}
		
			
    }
			
		
	
	
	
	



package org.usfirst.frc.team6367.robot.autonomous;

import org.usfirst.frc.team6367.robot.components.DriveTrain;

import io.github.robotpy.magicbot.MagicInject;
import io.github.robotpy.magicbot.sm.AutonomousStateMachine;
import io.github.robotpy.magicbot.sm.State;

public class SideGearAutonomous extends AutonomousStateMachine {
	
	@MagicInject
	DriveTrain drivetrain;
	
	@State(first = true)
	public void driveForward(){
	if(drivetrain.moveTo() == true){
	 nextState("rotateTo");
	}
	
	}
	@State
	public void rotateTo(){
		if(drivetrain.rotateTo() == true){
			nextState("driveForwardAgain");
		}
		
	}
		public void driveForwardAgain(){
		if(drivetrain.moveTo() == true){
			
		}
	}
}

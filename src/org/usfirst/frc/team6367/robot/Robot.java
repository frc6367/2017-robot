package org.usfirst.frc.team6367.robot;

import org.usfirst.frc.team6367.robot.autonomous.SimpleAutonomous;
import org.usfirst.frc.team6367.robot.components.DriveTrain;

import io.github.robotpy.magicbot.MagicRobot;

public class Robot extends MagicRobot {

	@Override
	protected void createObjects() {
		
		// add components first
		DriveTrain driveTrain = new DriveTrain();
		//addComponent(driveTrain);
		
		// then add autonomous modes
		addAutonomous("Demo", new SimpleAutonomous());
	}
	
	@Override
	protected void teleopInit() {
		
	}
	
	@Override
	protected void teleopPeriodic() {
		
	}
}

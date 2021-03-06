package org.usfirst.frc.team6367.robot;

import org.usfirst.frc.team6367.robot.autonomous.DriveForwardAutonomous;
import org.usfirst.frc.team6367.robot.components.Climber;
import org.usfirst.frc.team6367.robot.components.DriveTrain;
import org.usfirst.frc.team6367.robot.components.Fuel;
import org.usfirst.frc.team6367.robot.components.Gears;

import edu.wpi.first.wpilibj.Joystick;
import io.github.robotpy.magicbot.MagicRobot;

public class Robot extends MagicRobot {
	
	Joystick leftStick = new Joystick(0);
	
	DriveTrain driveTrain;
	Climber climb;
	Gears gear;
	Fuel fuel;
	
	@Override
	protected void createObjects() {
		
		// enable basic camera support
		// -> TODO: add target tracking
		// Java camera server
		edu.wpi.first.wpilibj.CameraServer.getInstance().startAutomaticCapture(0);
		// Python camera server
		//CameraServer.startPythonVision("/vision.py", "main");

		// add components first
		driveTrain = new DriveTrain();
		addComponent(driveTrain);
		
		 climb = new Climber();
		addComponent(climb);

		 gear = new Gears(driveTrain);
		addComponent(gear);

		 fuel = new Fuel();
		addComponent(fuel);

		// then add autonomous modes
		addAutonomous("Drive Forward", new DriveForwardAutonomous(), true);
	}
		
	@Override
	protected void teleopInit() {

	}
	
	@Override
	protected void teleopPeriodic(){
		// robot should drive
		driveTrain.move(leftStick.getY(),leftStick.getX());
		
		// left trigger is pressed shoot
		if(leftStick.getTrigger()==true){
			fuel.shoot();
		}
		 
		// button two is pressed it intake(both)
		if(leftStick.getRawButton(2)==true){
			fuel.inTake();
		}
		
		// button 11 is pressed  then climb
		if(leftStick.getRawButton(4)==true){
			climb.climb();
		}
		
		if (leftStick.getRawButton(3)) {
			gear.align();
		}
				
	}
}







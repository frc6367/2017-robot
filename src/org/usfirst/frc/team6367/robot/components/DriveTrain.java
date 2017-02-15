package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveTrain {
	
	double m_speed = 0;
	double m_rotation = 0;
	
	boolean m_shouldRotate = false;
	double m_angle = 0;
	
	RobotDrive m_drive; 
	
	public void move(double speed, double rotation){
		m_speed = speed;
		m_rotation = rotation;
	}
	
	public void rotateTo(double angle){
		m_angle = angle;
		m_shouldRotate = true;
	}
	
	public void execute(){
		m_drive.arcadeDrive(m_speed, m_rotation, true);
		
		m_speed = 0;
		m_rotation = 0;
		m_shouldRotate = false;
	}
	

}
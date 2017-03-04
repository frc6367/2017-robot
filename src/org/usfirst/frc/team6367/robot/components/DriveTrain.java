package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import io.github.robotpy.magicbot.MagicComponent;

public class DriveTrain implements MagicComponent {
	
	static final double ENCODER_P = 0.1;
	
	// assumes the toughbox is 10.71:1 gear ratio
	// wheel is 0.5 feet
	// cimcoder is 20 ticks per revolution
	static final double ENCODER_K = 10.71*20.0 / (0.5*Math.PI);
	
	double m_speed = 0;
	double m_rotation = 0;
	
	boolean m_shouldRotate = false;
	double m_angle = 0;
	
	RobotDrive m_drive; 
	Encoder encoder= new Encoder(0,1);
	
	/**
	 * 
	 * @return distance traveled in feet
	 */
	double getDistance(){
		return encoder.get()*ENCODER_K;
	}
	
		
	public void move(double speed, double rotation){
		m_speed = speed;
		m_rotation = rotation;
	}
	
		
	
	
	public boolean moveTo(double dst){
	
		double error = (dst - getDistance());
		if(Math.abs(error)<0.15){
			return true;
			
		}else{
			m_speed=ENCODER_P*error;
			return false;
		}
	}
	
	public void rotateTo(double angle){
		m_angle = angle;
		m_shouldRotate = true;
	}
	
	public boolean rotateTo(){
		return false;
	}
	
	public void execute(){
		m_drive.arcadeDrive(m_speed, m_rotation, true);
		
		// reset the values 
		m_speed = 0;
		m_rotation = 0;
		m_shouldRotate = false;
	}
	

}
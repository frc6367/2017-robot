package org.usfirst.frc.team6367.robot.components;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.robotpy.magicbot.MagicComponent;



public class DriveTrain implements MagicComponent {
	VictorSP frontRightMotor = new VictorSP(1);
	VictorSP frontLeftMotor = new VictorSP(0);
	VictorSP backRightMotor = new VictorSP(3);
	VictorSP backLeftMotor = new VictorSP(2);
	
	double ENCODER_P = 0.1;
	double ANGLE_P = 0.1;

	// assumes the toughbox is 10.71:1 gear ratio
	// wheel is 0.5 feet
	// cimcoder is 20 ticks per revolution
	static final double ENCODER_K = 10.71*20.0 / (0.5*Math.PI);

	double m_speed = 0;
	double m_rotation = 0;

	RobotDrive m_drive = new RobotDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
	Encoder encoder= new Encoder(0,1);
	AHRS navX = new AHRS(SPI.Port.kMXP);
	
	public DriveTrain() {
		SmartDashboard.putNumber("encoder_p", ENCODER_P);
		SmartDashboard.putNumber("angle_p", ANGLE_P);
	}

	@Override
	public void onEnabled() {
		ENCODER_P = SmartDashboard.getNumber("encoder_p", ENCODER_P);
		ANGLE_P = SmartDashboard.getNumber("angle_p", ANGLE_P);
	}


	/**
	 * 
	 * @return distance traveled in feet
	 */
	public double getDistance(){
		return encoder.get()*ENCODER_K;
	}
	
	public double getAngle(){
	 	return navX.getAngle();
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
	
	private double normalizeAngle(double angle) {
		return ((angle + 180.0) % 360.0) - 180.0;
	}
	
	private double computeError(double setpoint, double input) {
		setpoint = normalizeAngle(setpoint);
		
		double error = setpoint - input;
		if (Math.abs(error) > 180.0) {
			if (error > 0) {
				error = error - 360.0;
			} else {
				error = error + 360.0;
			}
		} 
	    
		return error;
	}

	public boolean rotateTo(double angle){
		double error = computeError(angle, getAngle());
		if(Math.abs(error) < 2.0){
			return true;

		}else{
			m_rotation=ANGLE_P*error;
			return false;
		}	
	}

	public void execute(){
		m_drive.arcadeDrive(m_speed, m_rotation, true);

		// reset the values 
		m_speed = 0;
		m_rotation = 0;
	}


}
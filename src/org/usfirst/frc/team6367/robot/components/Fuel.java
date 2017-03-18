package org.usfirst.frc.team6367.robot.components;


import edu.wpi.first.wpilibj.VictorSP;
import io.github.robotpy.magicbot.MagicComponent;
public class Fuel implements MagicComponent {

	
	static final int OFF = 0;
	static final double REAR_UPPER_ON = 1;
	static final double REAR_LOWER_IN = -1;
	static final double REAR_LOWER_OUT = 1;
	
	
	VictorSP upperRearMotor = new VictorSP(5);
	VictorSP rearLowerMotor = new VictorSP(4);
	

	boolean shot = false;
	boolean take = false;
	
	
	public void  shoot() {
		shot = true;
	}

	public void inTake() {
		take = true;
	}
	

	public void execute(){
		if (shot==false && take==false){
			
			upperRearMotor.set(OFF);
			rearLowerMotor.set(OFF);
		}
		else if (shot==true && take==false){
			
			upperRearMotor.set(REAR_UPPER_ON);
			rearLowerMotor.set(REAR_LOWER_OUT);
			
		}
		
		else if (shot==false && take==true){
			
			upperRearMotor.set(REAR_UPPER_ON);
			rearLowerMotor.set(REAR_LOWER_IN);
			
		}
		
		else if (shot==true && take==true){
			
			upperRearMotor.set(REAR_UPPER_ON);
			rearLowerMotor.set(REAR_LOWER_OUT);
		}
		
		shot=false;
		take=false;
		

	}

}

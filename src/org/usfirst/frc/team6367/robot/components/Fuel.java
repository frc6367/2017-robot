package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.Victor;
import io.github.robotpy.magicbot.MagicComponent;

public class Fuel implements MagicComponent {

	static final int FRONT_ON = 1;
	static final int OFF = 0;
	static final int REAR_UPPER_ON = 1;
	static final int REAR_LOWER_IN = 1;
	static final int REAR_LOWER_OUT =-1;
	
	Victor frontIntakeMotor = new Victor(6);
	Victor upperRearMotor = new Victor(5);
	Victor rearLowerMotor = new Victor(4);
	

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
			frontIntakeMotor.set(OFF);
			upperRearMotor.set(OFF);
			rearLowerMotor.set(OFF);
		}
		else if (shot==true && take==false){
			frontIntakeMotor.set(OFF);
			upperRearMotor.set(REAR_UPPER_ON);
			rearLowerMotor.set(REAR_LOWER_OUT);
			
		}
		
		else if (shot==false && take==true){
			frontIntakeMotor.set(FRONT_ON);
			upperRearMotor.set(REAR_UPPER_ON);
			rearLowerMotor.set(REAR_LOWER_IN);
			
		}
		
		else if (shot==true && take==true){
			frontIntakeMotor.set(FRONT_ON);
			upperRearMotor.set(REAR_UPPER_ON);
			rearLowerMotor.set(REAR_LOWER_OUT);
		}
		
		shot=false;
		take=false;
		

	}

}

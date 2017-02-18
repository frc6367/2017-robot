package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.Victor;
import io.github.robotpy.magicbot.MagicComponent;

public class Fuel implements MagicComponent {
	
	Victor motor1 = new Victor(5);
	Victor motor2 = new Victor(6);

	boolean shot = false;
	boolean take=false;
	
	public void shoot() {
		shot = true;

	}

	public void inTake() {
		take =true;
	}

	public void execute(){
		if(shot=true){
			
			motor1.set(1);
		}
		else{
			motor1.set(0);
		}

		if(take==true){
			
			motor2.set(1);
		}
		else{
			
			motor2.set(0);
		}

shot=false;
take=false;

	}

}

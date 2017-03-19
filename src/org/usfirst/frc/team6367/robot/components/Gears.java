package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import io.github.robotpy.magicbot.MagicComponent;

public class Gears implements MagicComponent {

	boolean align = false;
	
	Relay light = new Relay(0);
	
	NetworkTable nt = NetworkTable.getTable("/camera");
	
	static final double[] DEFAULT = new double[]{0, 0, 0};
	
	DriveTrain driveTrain;
	
	double lastTime;
	double lastAngle;
	boolean foundOrNot;
	
	public Gears(DriveTrain d){
		driveTrain = d;
		lastTime = 0.0;
		lastAngle = 0.0;
		foundOrNot = false;
	}
	
	public void align() {
		align = true;
	}
	
	@Override
	public void execute(      ) {
		
		nt.putBoolean("enabled", align);
		
		if (align == true) {
			
			light.set(Value.kOn);
		
			// targeting:
			// target[0] -- is 0 if not found, or 1 if found
			// target[1] -- time
			// target[2] -- offset (in degrees) of the target  
			double[] target = nt.getNumberArray("target", DEFAULT);

			// convert the number to a boolean
			if (target[0] > 0) {
				foundOrNot = true;
			} else {
				foundOrNot = false;
			}
			
			double newTime = target[1];
			double offset = target[2];
			
			//if I found my target
			if (foundOrNot) {
				
				// if the time has changed, update the angle
				if (Math.abs(newTime - lastTime) > 0.001) {
					lastAngle = driveTrain.getAngle()-offset;
					lastTime = newTime;
				}
				
				driveTrain.moveTo(lastAngle);
			}	
		} else{
			light.set(Value.kOff);
		}
		
		align = false;
	}

}

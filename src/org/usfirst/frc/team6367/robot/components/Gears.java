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
	
	double time;
	double lastAngle;
	double foundOrNot;
	
	public Gears(DriveTrain d){
		driveTrain = d;
		time = 0.0;
		lastAngle = 0.0;
		foundOrNot = 0.0;
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

			foundOrNot = target[0];
			double newTime = target[1];
			double offset = target[2];
			// TODO: do stuff here
			
			driveTrain.moveTo(lastAngle);
			
			if(offset == 0.0){
				align = false;
				lastAngle = 0.0;
				time = 0.0;
				foundOrNot = 0.0;
			}
			else if(newTime!=time && foundOrNot==1.0){ //if I found my target
				lastAngle = driveTrain.getAngle()-offset;
				time = newTime;
			}
						
		} else{
			light.set(Value.kOff);
		}
		

		
		align = false;
	}

}

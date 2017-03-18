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
			
			// TODO: do stuff here
			
		} else{
			light.set(Value.kOff);
		}
		
		align = false;
	}

}

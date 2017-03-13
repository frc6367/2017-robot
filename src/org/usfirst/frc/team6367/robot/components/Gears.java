package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import io.github.robotpy.magicbot.MagicComponent;

public class Gears implements MagicComponent {

	boolean gear = false;
	NetworkTable nt = NetworkTable.getTable("/camera");
	
	static final double[] DEFAULT = new double[]{0, 0, 0};
	
	public void align() {
		gear = true;
	}
	
	@Override
	public void execute() {
		if (gear == true) {
			
			// targeting:
			// target[0] -- is 0 if not found, or 1 if found
			// target[1] -- time
			// target[2] -- offset (in degrees) of the target
			double[] target = nt.getNumberArray("target", DEFAULT);
			
			// TODO: do stuff here
			
		}
		
		gear = false;
	}

}

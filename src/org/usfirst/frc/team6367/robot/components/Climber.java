package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.Victor;

public class Climber {
	boolean climb = false;
	Victor motor = new Victor(7);

	public void climb() {
		climb = true;
	}

	public void execute() {
		if (climb == true) {
			motor.set(1);
		} else {
			motor.set(0);
		}

		climb = false;
	}

}

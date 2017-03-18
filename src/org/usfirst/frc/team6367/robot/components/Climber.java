 package org.usfirst.frc.team6367.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import io.github.robotpy.magicbot.MagicComponent;

public class Climber implements MagicComponent {
	boolean climb = false;
	VictorSP motor = new VictorSP(6);

	public void climb() {
		climb = true;
	}

	public void execute() {
		if (climb == true) {
			motor.set(1.0);
		} else {
			motor.set(0.0);
		}

		climb = false;
	}

}

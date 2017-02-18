package io.github.robotpy.magicbot;

import edu.wpi.first.wpilibj.Timer;

public class PreciseDelay {

	private final double m_period;
	private double m_nextDelay;

	/**
	 * @param period Delay time in seconds
	 */
	public PreciseDelay(double period) {
		m_period = period;
		m_nextDelay = Timer.getFPGATimestamp() + m_period;
	}
	
	/**
	 * Delay for a fixed period of time
	 */
	public void delay() {
		
		// we must always yield here, just in case
		Timer.delay(0.0002);
		
		double delayPeriod = m_nextDelay - Timer.getFPGATimestamp();
		if (delayPeriod > 0.0001) {
			Timer.delay(delayPeriod);
		}
		
		m_nextDelay += m_period;
	}
}

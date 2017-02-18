package io.github.robotpy.magicbot;

/**
 * Must be implemented by all autonomous modes used with Magicbot
 */
public interface MagicAutonomous {
	/**
	 * Called when autonomous mode is initially enabled
	 */
	default void onEnabled() {
		// empty
	};
	
	/**
	 * Called when autonomous mode is no longer active
	 */
	default void onDisabled() {
		// empty
	}
	
	/**
	 * This function is called every 20ms during autonomous mode
	 */
	public void autonomousPeriodic();
}

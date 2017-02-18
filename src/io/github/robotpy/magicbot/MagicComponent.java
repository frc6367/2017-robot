package io.github.robotpy.magicbot;

/**
 * Must be implemented by all components used with Magicbot
 */
public interface MagicComponent {

	/**
	 * Called when the robot enters autonomous or teleoperated mode. This
	 * function should initialize your component to a "safe" state so that
	 * unexpected things don't happen when enabling the robot.
	 * 
	 * Note: There isn't a separate initialization function for autonomous
	 *       and teleoperated modes. This is intentional, as they should be the
	 *       same.
	 */
	default void onEnabled() {
		// empty
	};
	
	/**
	 * Called when the robot leaves autonomous or teleoperated mode
	 */
	default void onDisabled() {
		// empty
	}
	
	/**
	 * This function is called at the end of the control loop
	 */
	public void execute();
}

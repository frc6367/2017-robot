package io.github.robotpy.magicbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Robots that use the MagicBot framework should use this as their
 * base robot class. If you use this as your base, you must
 * implement the following methods:
 * 
 * - createObjects
 * - teleopPeriodic
 * 
 * MagicRobot allows you to define multiple autonomous modes and
 * to select one of them via SmartDashboard/SFX.
 * 
 * MagicRobot will set the following NetworkTables variables
 * automatically:
 * 
 * - /robot/mode: one of 'disabled', 'auto', 'teleop', or 'test'
 * - /robot/is_simulation: true/false
 * - /robot/is_ds_attached: true/false
 */
public abstract class MagicRobot extends SampleRobot {

	private List<MagicComponent> m_components = new ArrayList<>();
	private NetworkTable m_nt;
	
	private Map<String, MagicAutonomous> m_autonomous = new HashMap<>();
	private SendableChooser<String> m_autoChooser = new SendableChooser<>();
	
	protected double m_controlLoopWaitTime = 0.020;
	
	@Override
	protected final void robotInit() {
		
		// create user objects
		createObjects();
		
		// TODO: component injection
		
		// perform injection on autonomous modes for now
		for (MagicAutonomous autonomous: m_autonomous.values()) {
			MagicInjector.inject(this, autonomous, null);
		}
		
		SmartDashboard.putData("Autonomous Mode", m_autoChooser);
		
		// compatibility with FRC dashboard
		String [] modes = new String[m_autonomous.size()];
		m_autonomous.keySet().toArray(modes);
		Arrays.sort(modes);
		
		new SmartDashboard().putStringArray("Auto List", modes);
		
		m_nt = NetworkTable.getTable("/robot");
		m_nt.putBoolean("is_simulation", isSimulation());
		m_nt.putBoolean("is_ds_attached", m_ds.isDSAttached());
	}
	
	/**
	 * Add a component to be executed
	 */
	protected void addComponent(MagicComponent component) {
		m_components.add(component);
	}
	
	/**
	 * Add an autonomous mode
	 * 
	 * @param name			Name to be displayed to the user
	 * @param autonomous	Autonomous mode object
	 */
	protected void addAutonomous(String name, MagicAutonomous autonomous) {
		addAutonomous(name, autonomous, false);
	}
	
	/**
	 * Add an autonomous mode
	 * 
	 * @param name			Name to be displayed to the user
	 * @param autonomous	Autonomous mode object
	 * @param default       If true, set to be the default mode
	 */
	protected void addAutonomous(String name, MagicAutonomous autonomous, boolean defaultMode) {
		if (m_autonomous.containsKey(name)) {
			throw new RuntimeException("Duplciate autonomous mode '" + name + "'");
		}
		
		m_autonomous.put(name, autonomous);
		
		if (defaultMode) {
			m_autoChooser.addDefault(name, name);
		} else {
			m_autoChooser.addObject(name, name);	
		}
	}
	
	/**
	 * Implement this and create all of your robot objects here
	 */
	protected abstract void createObjects();
	
	/**
	 * Initialization code for disabled mode should go here.
	 * 
	 * Users may override this method for initialization code which will be
	 * called each time the robot enters disabled mode.
	 * 
	 * Note: the 'on_disable' functions of all components are called
	 *       before this function is called
	 */
	protected void disabledInit() {
		// empty
	}
	
	/**
	 * Periodic code for disabled mode should go here
	 * 
	 * Users should override this method for code which will be called
	 * periodically at a regular rate while the robot is in disabled mode.
	 * 
	 */
	protected void disabledPeriodic() {
		// empty
	}
	
	/**
	 * Initialization code for teleop control code may go here.
	 * 
	 * Users may override this method for initialization code which will be
	 * called each time the robot enters teleop mode.
	 * 
	 * The 'on_enable' functions of all components are called
	 * before this function is called
	 * 
	 */
	protected void teleopInit() {
		// empty
	}
	
	/**
	 * Periodic code for teleop mode should go here.
	 * 
	 * Users should override this method for code which will be called
	 * periodically at a regular rate while the robot is in teleop mode.
	 * 
	 * This code executes before the 'execute' functions of all
	 * components are called
	 */
	protected void teleopPeriodic() {
		// empty
	}
	
	@Override
	protected final void disabled() {
		boolean dsAttached = m_ds.isDSAttached();
		
		m_nt.putString("mode", "disabled");
		m_nt.putBoolean("is_ds_attached", dsAttached);
		
		disableComponents();
		
		while (isDisabled()) {
			if (dsAttached != m_ds.isDSAttached()) {
				dsAttached = !dsAttached;
				m_nt.putBoolean("is_ds_attached", dsAttached);
			}
			
			disabledPeriodic();
			
			Timer.delay(m_controlLoopWaitTime);
		}
	}
	
	@Override
	public final void autonomous() {
		m_nt.putString("mode", "auto");
		m_nt.putBoolean("is_ds_attached", m_ds.isDSAttached());
		
		enableComponents();
		
		MagicAutonomous autoMode;
		
		// FRC Dashboard compatibility
		// -> if you set it here, you're stuck using it. The FRC Dashboard
        //    doesn't seem to have a default (nor will it show a default),
        //    so the key will only get set if you set it.
		String modeName = SmartDashboard.getString("Auto Selector", "");
		if (modeName == null) {
			modeName = m_autoChooser.getSelected();
		}
		
		autoMode = m_autonomous.get(modeName);
		if (autoMode == null) {
			System.err.println("Warning: no autonomous mode selected");
			
			autoMode = new MagicAutonomous() {
				@Override
				public void autonomousPeriodic() {
				}
			};
			
		} else {
			System.out.println("Enabling autonomous mode '" + modeName + "'");
		}
		
		autoMode.onEnabled();
		
		PreciseDelay delay = new PreciseDelay(m_controlLoopWaitTime);
		
		while (isAutonomous() && isEnabled()) {
			
			autoMode.autonomousPeriodic();
			execute();
			delay.delay();
		}
		
		autoMode.onDisabled();
		
		disableComponents();
	}
	
	@Override
	public final void operatorControl() {
		m_nt.putString("mode", "teleop");
		m_nt.putBoolean("is_ds_attached", m_ds.isDSAttached());
		
		// initialize things
		enableComponents();
		
		teleopInit();
		
		PreciseDelay delay = new PreciseDelay(m_controlLoopWaitTime);
		
		while (isOperatorControl() && isEnabled()) {
			teleopPeriodic();
			
			execute();
			delay.delay();
		}
		
		disableComponents();
	}
	
	@Override
	public final void test() {
		m_nt.putString("mode", "teleop");
		m_nt.putBoolean("is_ds_attached", m_ds.isDSAttached());
		
		while (isTest() && isEnabled()) {
			LiveWindow.run();
			Timer.delay(0.01);
		}
	}
	
	private void enableComponents() {
		for (MagicComponent c: m_components) {
			c.onEnabled();
		}
	}
	
	private void disableComponents() {
		for (MagicComponent c: m_components) {
			c.onDisabled();
		}
	}
	
	private void execute() {
		for (MagicComponent c: m_components) {
			c.execute();
		}
	}
}

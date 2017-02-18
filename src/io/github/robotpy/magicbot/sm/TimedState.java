package io.github.robotpy.magicbot.sm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * If this decorator is applied to a function in an object that inherits
 * from :class:`.StateMachine`, it indicates that the function
 * is a state that will run for a set amount of time unless interrupted
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TimedState {
	
	/** The length of time to run the state before progressing
        to the next state */
	double duration() default -1.0;
	
	/** The name of the next state. If not specified, then
	 *  this will be the last state executed if time expires
	 */
    String nextState() default "";

    /** If set, this state function will be ran first */
    boolean first() default false;
    
    /**
     *  If set, this state will continue executing even if engage
     *  is not called. However, if `done` is called, execution will
     *  stop regardless of whether this is set
     */
    boolean mustFinish() default false;
}

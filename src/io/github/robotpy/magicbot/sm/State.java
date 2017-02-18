package io.github.robotpy.magicbot.sm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * If this annotation is applied to a method in an object that inherits
 * from :class:`.StateMachine`, it indicates that the function
 * is a state. The state will continue to be executed until the
 * ``nextState`` function is executed.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
	
	/** If set, this state function will be ran first */
    boolean first() default false;
    
    /**
     *  If set, this state will continue executing even if engage
     *  is not called. However, if `done` is called, execution will
     *  stop regardless of whether this is set
     */
    boolean mustFinish() default false;
}

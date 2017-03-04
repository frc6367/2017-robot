package io.github.robotpy.magicbot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If this annotation is applied to a variable in a class, then the identical
 * object in the Robot class will be copied to this variable after the component
 * constructor is called.
 * 
 * This annotation can be used on anything that inherits from MagicComponent and
 * is added to the robot via addComponent or addAutonomous
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MagicInject {
}

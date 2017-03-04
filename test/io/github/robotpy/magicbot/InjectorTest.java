package io.github.robotpy.magicbot;

import static org.junit.Assert.*;

import org.junit.Test;

public class InjectorTest {
	
	class InjectFrom {
		int theField = 42;
	}
	
	class InjectTo {
		@MagicInject
		int theField = 4;
		
		public int getTheField() {
			return theField;
		}
	}
	
	@Test
	public void testInjection() {
		
		InjectFrom from = new InjectFrom();
		InjectTo to = new InjectTo();
		
		assertEquals(4, to.getTheField());
		
		MagicInjector.inject(from, to, "ignored");
		
		assertEquals(42, to.getTheField());
		
	}

}

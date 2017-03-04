package io.github.robotpy.magicbot;

import java.lang.reflect.Field;

class MagicInjector {

	static public void inject(Object source, Object to, String toName) {
		
		Class<?> toClass = to.getClass();
		Field[] fields = toClass.getDeclaredFields();
		
		for (Field injectField: fields) {
			if (injectField.isAnnotationPresent(MagicInject.class)) {
				
				// try to find the field in the source object
				String injectFieldName = injectField.getName();
				Field sourceField = null;
				
				try {
					sourceField = source.getClass().getDeclaredField(injectFieldName);
				} catch (NoSuchFieldError | NoSuchFieldException | SecurityException ex1) {
					try {
						if (toName != null) {
							sourceField = source.getClass().getDeclaredField(toName + injectFieldName);
						}
					} catch (NoSuchFieldError | NoSuchFieldException | SecurityException ex2) {
						
					}
				}
				
				if (sourceField == null) {
					throw new RuntimeException("Field + " + injectFieldName + " in " + toName + " does not exist in" + toClass);
				}
				
				sourceField.setAccessible(true);
				injectField.setAccessible(true);
				
				try {
					injectField.set(to, sourceField.get(source));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException("Error setting field + " + injectFieldName + " in " + toName, e);
				}
			}
		}	
	}
}

package de.codecentric.centerdevice.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

	public static Method getAccessibleMethod(Object obj, String name, Class<?>... args) throws NoSuchMethodException {
		Method method = obj.getClass().getDeclaredMethod(name, args);
		method.setAccessible(true);
		return method;
	}

	public static Field getAccessibleField(Object obj, String name) throws NoSuchFieldException, SecurityException {
		Field field = obj.getClass().getDeclaredField(name);
		field.setAccessible(true);
		return field;
	}

	public static void invokeQuietly(Object instance, String methodName) {
		try {
			getAccessibleMethod(instance, methodName).invoke(instance);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

package de.codecentric.centerdevice.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
	public static MethodHandle getHandle(Object obj, String name, Class<?>... args)
			throws ReflectiveOperationException {
		return MethodHandles.lookup().unreflect(getAccessibleMethod(obj, name, args));
	}

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

	public static void invokeQuietly(MethodHandle handle, Object instance) {
		try {
			handle.invoke(instance);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

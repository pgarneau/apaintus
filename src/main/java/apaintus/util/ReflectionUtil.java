package apaintus.util;

import java.lang.reflect.Field;

public class ReflectionUtil {
	public static <V> V get(Object object, String fieldName) {
		Class<?> objectClass = object.getClass();

		while (objectClass != null) {
			try {
				Field field = objectClass.getDeclaredField(fieldName);
				field.setAccessible(true);

				return (V) field.get(object);
			} catch (NoSuchFieldException e) {
				objectClass = objectClass.getSuperclass();
			} catch (Exception e) {
			    //TODO: LOG THIS ERROR
			}
		}

		return null;
	}

	public static void set(Object object, String fieldName, Object fieldValue) {
		Class<?> objectClass = object.getClass();

		while (objectClass != null) {
			try {
				Field field = objectClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(object, fieldValue);

				return;
			} catch (NoSuchFieldException e) {
				objectClass = objectClass.getSuperclass();
			} catch (Exception e) {
				//TODO: LOG THIS ERROR
			}
		}
	}
}

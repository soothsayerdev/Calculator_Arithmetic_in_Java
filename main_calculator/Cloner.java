// This class was supposedly ready

import java.lang.reflect.*;

public class Cloner<X> {
    @SuppressWarnings("unchecked")
    public X clone(X x) {
        // Obtain the class of the instance in the object x,
        // which, as we know, is the class X, and store it
        // in the object named "clazz".
        Class<?> clazz = x.getClass();

        // null because we will call a method without parameters
        Class<?>[] paramTypes = null;

        // Obtain the method named "clone" without parameters
        // from the Class<?> stored in the object clazz (we know
        // that the class is class X)
        Method method = null;
        try {
            method = clazz.getMethod("clone", paramTypes);
        } catch (NoSuchMethodException error) {
            // Method not found, continue without action
        }
        Object[] realParams = null;

        X result = null;
        try {
            // Invoke the "clone" method on the object x
            result = (X) method.invoke(x, realParams);
        } catch (InvocationTargetException error) {
            // Exception during method invocation
        } catch (IllegalAccessException error) {
            // Illegal access during method invocation
        }

        // Alternate: result = (X)x.clone();

        return result;
    }
}

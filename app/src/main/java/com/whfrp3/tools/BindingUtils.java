package com.whfrp3.tools;

/**
 * Binding utils.
 */
public class BindingUtils {

    /**
     * Return the string corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return String corresponding to the resource id.
     */
    public static String string(int resourceId) {
        return MyApplication.getAppContext().getString(resourceId);
    }
}

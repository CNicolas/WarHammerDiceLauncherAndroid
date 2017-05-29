package com.whfrp3.tools.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Helper used to convert JSON into Java Object and vice versa.
 */
public final class GsonHelper {

    /**
     * Singleton's instance.
     */
    private static GsonHelper instance = null;

    /**
     * Gson instance.
     */
    private Gson gson;

    /**
     * Private constructor.
     */
    private GsonHelper() {
        gson = new GsonBuilder().create();
    }

    /**
     * Getter of the unique instance of GsonHelper.
     *
     * @return Unique instance of GsonHelper.
     */
    public static GsonHelper getInstance() {
        if (instance == null) {
            instance = new GsonHelper();
        }
        return instance;
    }

    /**
     * Convert JSON string into Java Object.
     *
     * @param json JSON string to convert.
     * @param clazz Class of target java object.
     * @param <T> Type of target java object.
     * @return Java object.
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * Convert Java Object to JSON String.
     *
     * @param object Object to convert.
     * @return JSON String.
     */
    public String toJson(Object object) {
        return gson.toJson(object);
    }
}

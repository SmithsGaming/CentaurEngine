package smithsgaming.centaurengine.util.data;

import java.io.File;

/**
 * Created by Tim on 27/11/2016.
 */
public abstract class ConfigurationManager {

    private Configuration config;

    public ConfigurationManager(File saveFile) {
        this.config = new Configuration(saveFile);
        loadConfig();
        init();
        saveConfig();
    }

    public void loadConfig() {
        config.load();
    }

    public void saveConfig() {
        config.save();
    }

    public void writeInt(String key, int data) {
        config.writeInt(key, data);
    }

    public void writeDouble(String key, double data) {
        config.writeDouble(key, data);
    }

    public void writeFloat(String key, float data) {
        config.writeFloat(key, data);
    }

    public void writeLong(String key, long data) {
        config.writeLong(key, data);
    }

    public void writeString(String key, String data) {
        config.writeString(key, data);
    }

    public void writeBoolean(String key, boolean data) {
        writeInt(key, data ? 1 : 0);
    }

    public int getInt(String key, int defaultValue) {
        return config.getInt(key, defaultValue);
    }

    public double getDouble(String key, double defaultValue) {
        return config.getDouble(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return config.getFloat(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return config.getLong(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    public abstract void init();
}
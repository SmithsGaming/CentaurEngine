package smithsgaming.centaurengine.util.data;

import smithsgaming.centaurengine.util.FileUtils;

import java.io.*;

/**
 * Created by Tim on 23/12/2016.
 */
public class Configuration {

    private SavableTag config;
    private File saveFile;

    public Configuration(File saveFile) {
        this.config = new SavableTag();
        this.saveFile = saveFile;
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
    }

    public void save() {
        if (saveFile != null && config != null) {
            try {
                saveFile.createNewFile();
                FileWriter writer = new FileWriter(saveFile);
                writer.write(config.serializeToString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Failed to save the config file:\n\t" + saveFile.getAbsolutePath() + "\nEither the config or save file were null");
        }
    }

    public void load() {
        if (saveFile != null && config != null) {
            try {
                saveFile.createNewFile();
                InputStream inputStream = new BufferedInputStream(new FileInputStream(saveFile));
                String jsonString = FileUtils.readStream(inputStream);
                config = SavableTag.deserializeFromJson(jsonString);
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Failed to load the config file:\n\t" + saveFile.getAbsolutePath() + "\n either the config or save file were null");
        }
        if (config == null) {
            config = new SavableTag();
        }
    }

    public void writeInt(String key, int data) {
        config.updateInt(key, data);
    }

    public void writeDouble(String key, double data) {
        config.updateDouble(key, data);
    }

    public void writeFloat(String key, float data) {
        config.updateFloat(key, data);
    }

    public void writeLong(String key, long data) {
        config.updateLong(key, data);
    }

    public void writeString(String key, String data) {
        config.updateString(key, data);
    }

    public void writeBoolean(String key, boolean data) {
        writeInt(key, data ? 1 : 0);
    }

    public int getInt(String key, int defaultValue) {
        if (!config.hasTag(key)) {
            config.writeInt(key, defaultValue);
        }
        return config.getInt(key);
    }

    public double getDouble(String key, double defaultValue) {
        if (!config.hasTag(key)) {
            config.writeDouble(key, defaultValue);
        }
        return config.getDouble(key);
    }

    public float getFloat(String key, float defaultValue) {
        if (!config.hasTag(key)) {
            config.writeFloat(key, defaultValue);
        }
        return config.getFloat(key);
    }

    public long getLong(String key, long defaultValue) {
        if (!config.hasTag(key)) {
            config.writeLong(key, defaultValue);
        }
        return config.getLong(key);
    }

    public String getString(String key, String defaultValue) {
        if (!config.hasTag(key)) {
            config.writeString(key, defaultValue);
        }
        return config.getString(key);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (!config.hasTag(key)) {
            config.writeBoolean(key, defaultValue);
        }
        return config.getBoolean(key);
    }
}
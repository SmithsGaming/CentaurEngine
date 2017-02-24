package smithsgaming.centaurengine.util.data;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import smithsgaming.centaurengine.util.exception.SavableTagException;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by Tim on 20/08/2016.
 */
public class SavableTag implements Serializable {

    static final Type SAVABLETYPE = new TypeToken<SavableTag>() {
    }.getType();
    private static final Gson GSONSAVABLE = new GsonBuilder().registerTypeAdapter(SAVABLETYPE, SavableTagJSONHandler.instance).create();

    /**
     * The final thing to be saved to disk
     */
    private HashMap<String, String> valueMap;

    public SavableTag() {
        valueMap = new HashMap<>();
    }

    /**
     * Save an integer value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void writeInt(String key, int data) {
        if (valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " already exists in the SavableTag");
        }
        valueMap.put(key, String.valueOf(data));
    }

    /**
     * Save a double value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void writeDouble(String key, double data) {
        if (valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " already exists in the SavableTag");
        }
        valueMap.put(key, String.valueOf(data));
    }

    /**
     * Save a float value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void writeFloat(String key, float data) {
        if (valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " already exists in the SavableTag");
        }
        valueMap.put(key, String.valueOf(data));
    }

    /**
     * Save a long value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void writeLong(String key, long data) {
        if (valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " already exists in the SavableTag");
        }
        valueMap.put(key, String.valueOf(data));
    }

    /**
     * Save a String value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void writeString(String key, String data) {
        if (valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " already exists in the SavableTag");
        }
        valueMap.put(key, data);
    }

    /**
     * Save a boolean value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void writeBoolean(String key, boolean data) {
        writeInt(key, data ? 1 : 0);
    }

    /**
     * Write a SavableTag to the disk with the given key
     *
     * @param key Unique key for the value
     * @param tag The SavableTag to save
     */
    public void writeSavableTag(String key, SavableTag tag) {
        if (valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " already exists in the SavableTag");
        }
        valueMap.put(key, tag.serializeToString());
    }

    /**
     * Save an integer value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void updateInt(String key, int data) {
        if (!valueMap.containsKey(key)) {
            writeInt(key, data);
        } else {
            valueMap.put(key, String.valueOf(data));
        }
    }

    /**
     * Save a double value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void updateDouble(String key, double data) {
        if (!valueMap.containsKey(key)) {
            writeDouble(key, data);
        } else {
            valueMap.put(key, String.valueOf(data));
        }
    }

    /**
     * Save a float value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void updateFloat(String key, float data) {
        if (!valueMap.containsKey(key)) {
            writeFloat(key, data);
        } else {
            valueMap.put(key, String.valueOf(data));
        }
    }

    /**
     * Save a long value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void updateLong(String key, long data) {
        if (!valueMap.containsKey(key)) {
            writeLong(key, data);
        } else {
            valueMap.put(key, String.valueOf(data));
        }
    }

    /**
     * Save a String value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void updateString(String key, String data) {
        if (!valueMap.containsKey(key)) {
            writeString(key, data);
        } else {
            valueMap.put(key, data);
        }
    }

    /**
     * Save a boolean value to disk with the given key
     *
     * @param key  Unique key for the value
     * @param data The value to save
     */
    public void updateBoolean(String key, boolean data) {
        updateInt(key, data ? 1 : 0);
    }

    /**
     * Write a SavableTag to the disk with the given key
     *
     * @param key Unique key for the value
     * @param tag The SavableTag to save
     */
    public void updateSavableTag(String key, SavableTag tag) {
        if (!valueMap.containsKey(key)) {
            writeSavableTag(key, tag);
        } else {
            valueMap.put(key, tag.serializeToString());
        }
    }

    /**
     * Return the integer value associated with the given key
     *
     * @param key The key for the specified int
     * @return The integer value for the key
     */
    public int getInt(String key) {
        if (!valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " does not exist in the SavableTag");
        }
        return Integer.parseInt(valueMap.get(key));
    }

    /**
     * Return the double value associated with the given key
     *
     * @param key The key for the specified double
     * @return The double value for the key
     */
    public double getDouble(String key) {
        if (!valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " does not exist in the SavableTag");
        }
        return Double.parseDouble(valueMap.get(key));
    }

    /**
     * Return the double value associated with the given key
     *
     * @param key The key for the specified double
     * @return The double value for the key
     */
    public float getFloat(String key) {
        if (!valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " does not exist in the SavableTag");
        }
        return Float.parseFloat(valueMap.get(key));
    }

    /**
     * Return the long value associated with the given key
     *
     * @param key The key for the specified double
     * @return The double value for the key
     */
    public long getLong(String key) {
        if (!valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " does not exist in the SavableTag");
        }
        return Long.parseLong(valueMap.get(key));
    }

    /**
     * Return the string value associated with the given key
     *
     * @param key The key for the specified string
     * @return The string value for the key
     */
    public String getString(String key) {
        if (!valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " does not exist in the SavableTag");
        }
        return valueMap.get(key);
    }

    /**
     * Return the boolean value associated with the given key
     *
     * @param key The key for the specified boolean
     * @return The boolean value for the key
     */
    public boolean getBoolean(String key) {
        if (!valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " does not exist in the SavableTag");
        }
        return valueMap.get(key).equals("1");
    }

    /**
     * Return the SavableTag associated with the given key
     *
     * @param key The key for the specified Tag
     * @return The SavableTag for the key
     */
    public SavableTag getSavableTag(String key) {
        if (!valueMap.containsKey(key)) {
            throw new SavableTagException("The key: " + key + " does not exist in the SavableTag");
        }
        return SavableTag.deserializeFromJson(valueMap.get(key));
    }

    /**
     * Check if the SavableTag contains the specified key
     *
     * @param key The key to check for
     * @return Whether or not the SavableTag contains the specified key
     */
    public boolean hasTag(String key) {
        return valueMap.containsKey(key);
    }

    public static SavableTag deserializeFromJson(String jsonString) {
        return GSONSAVABLE.fromJson(jsonString, SAVABLETYPE);
    }

    public static SavableTag deserializeFromJson(JsonElement jsonElement) {
        return GSONSAVABLE.fromJson(jsonElement, SAVABLETYPE);
    }

    public static SavableTag deserializeFromJson(JsonObject jsonObject) {
        return GSONSAVABLE.fromJson(jsonObject, SAVABLETYPE);
    }

    public JsonElement serializeToJsonElement() {
        return GSONSAVABLE.toJsonTree(this, SAVABLETYPE);
    }

    public JsonObject serializeToJsonObject() {
        return this.serializeToJsonElement().getAsJsonObject();
    }

    public String serializeToString() {
        return GSONSAVABLE.toJson(this, SAVABLETYPE);
    }

    private static class SavableTagJSONHandler implements JsonDeserializer<SavableTag>, JsonSerializer<SavableTag> {

        public static final SavableTagJSONHandler instance = new SavableTagJSONHandler();

        private static final Type mapType = new TypeToken<HashMap<String, String>>() {
        }.getType();
        private static final Gson GSON = new Gson();

        private SavableTagJSONHandler() {
        }

        @Override
        public SavableTag deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                SavableTag result = new SavableTag();
                result.valueMap = GSON.fromJson(jsonElement, mapType);
                return result;
            } catch (Exception ex) {
                throw new JsonParseException("Failed to parse the SavableTag", ex);
            }
        }

        @Override
        public JsonElement serialize(SavableTag savableTag, Type type, JsonSerializationContext jsonSerializationContext) {
            return GSON.toJsonTree(savableTag.valueMap, mapType);
        }
    }

}

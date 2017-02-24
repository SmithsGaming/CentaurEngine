package smithsgaming.centaurengine.util.data;

/**
 * Created by Tim on 20/08/2016.
 */
public interface ISavable {

    void saveToDisk(SavableTag tag);

    void loadFromDisk(SavableTag tag);

}

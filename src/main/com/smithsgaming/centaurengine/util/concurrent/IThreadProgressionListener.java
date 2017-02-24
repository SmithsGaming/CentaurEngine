package smithsgaming.centaurengine.util.concurrent;

/**
 * Created by Tim on 21/08/2016.
 */
public interface IThreadProgressionListener extends IThreadCompletionListener {

    void onThreadProgressionChanged(final Thread thread, int type, float progression, String progressionString);

}

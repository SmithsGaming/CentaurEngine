package smithsgaming.centaurengine.util.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tim on 21/08/2016.
 */
public abstract class ThreadCompletionNotifier extends Thread {

    private CopyOnWriteArrayList<IThreadCompletionListener> completionListeners = new CopyOnWriteArrayList<>();

    public void registerNewCompletionListener(IThreadCompletionListener listener) {
        completionListeners.add(listener);
    }

    public void removeCompletionListener(IThreadCompletionListener listener) {
        completionListeners.remove(listener);
    }

    private void onThreadCompletion() {
        synchronized (completionListeners) {
            for (IThreadCompletionListener listener : completionListeners) {
                listener.onThreadCompleted(this);
            }
        }
    }

    @Override
    public final void run() {
        try {
            doRun();
        } catch (Exception exception) {
            throw exception;
        } finally {
            onThreadCompletion();
        }
    }

    public abstract void doRun();
}

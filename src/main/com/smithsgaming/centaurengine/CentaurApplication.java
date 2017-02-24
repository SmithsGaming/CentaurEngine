package smithsgaming.centaurengine;

import java.awt.*;

/**
 * Created by Tim on 24/02/2017.
 */
public abstract class CentaurApplication extends Canvas {

    private final String APPLICATION_NAME;
    private final int DEFAULT_WIDTH;
    private final int DEFAULT_HEIGHT;

    public CentaurApplication(String applicationName, int defaultWidth, int defaultHeight) {
        this.APPLICATION_NAME = applicationName;
        this.DEFAULT_WIDTH = defaultWidth;
        this.DEFAULT_HEIGHT = defaultHeight;
    }

    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    public int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
}

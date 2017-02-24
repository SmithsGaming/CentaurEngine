package smithsgaming.centaurengine.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Tim on 20/08/2016.
 */
public class GraphicUtils {

//    public static void loadTexture(String path, int[] pixels) {
//        try {
//            System.out.print("Trying to load texture: " + path + " ...");
//            BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
//            int w = image.getWidth();
//            int h = image.getHeight();
//            image.getRGB(0, 0, w, h, pixels, 0, w);
//            System.out.print(" Success!\n");
//        } catch (Exception e) {
//            System.out.print(" Failure!\n");
//            System.err.println("\nCould not load the specified texture, inserting blank texture \n\t" + path + "\n");
//            for (int i = 0; i < pixels.length; i++) {
//                pixels[i] = 0xFF0000;
//            }
//        }
//    }

    public static FontMetrics getFontMetricsForFont(Font font) {
        if (font != null) {
            return new Canvas().getFontMetrics(font);
        }
        return null;
    }

    public static void drawHCenteredString(Graphics g, String text, Font font, int x, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        x = x - (metrics.stringWidth(text) / 2);
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void drawVCenteredString(Graphics g, String text, Font font, int x, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        y = y - (metrics.getHeight() / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void drawCenteredString(Graphics g, String text, Font font, int x, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        x = x - (metrics.stringWidth(text) / 2);
        y = y - (metrics.getHeight() / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static String trimStringToLength(Graphics g, String text, Font font, int width) {
        FontMetrics metrics = g.getFontMetrics(font);
        String newText = new String(text);
        int i = 0;
        while (metrics.stringWidth(newText) > width) {
            newText = newText.substring(0, newText.length() - 1 - i++);
        }
        return newText;
    }

//    public static void drawSprite(Graphics g, ISprite sprite, int x, int y) {
//        if (sprite != null) {
//            BufferedImage image = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
//            int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(sprite.getFlatenedData(), 0, pixels, 0, pixels.length);
//            g.drawImage(image, x, y, sprite.getWidth(), sprite.getHeight(), null);
//        }
//    }

    public static int changeColorBrightness(int col, int amount) {
        int r = (col & 0xff0000) >> 16;
        int g = (col & 0xff00) >> 8;
        int b = (col & 0xff);
        if (amount > 0) amount = 0;
        if (amount < -150) amount = -150;

        r += amount;
        g += amount;
        b += amount;

        if (r < 0) r = 0;
        if (g < 0) g = 0;
        if (b < 0) b = 0;
        if (r > 255) r = 255;
        if (g > 255) g = 255;
        if (b > 255) b = 255;

        return r << 16 | g << 8 | b;
    }

    public static int tintColor(int col, double r, double g, double b) {
        int red = (col & 0xff0000) >> 16;
        int green = (col & 0xff00) >> 8;
        int blue = (col & 0xff);

        red += (int) r;
        green += (int) g;
        blue += (int) b;

        if (red < 0) red = 0;
        if (green < 0) green = 0;
        if (blue < 0) blue = 0;
        if (red > 255) red = 255;
        if (green > 255) green = 255;
        if (blue > 255) blue = 255;

        return red << 16 | green << 8 | blue;
    }

}

package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public final class FileUtils {
    private static final String FONTS_FOLDER = "fonts/";
    private static final String IMAGES_FOLDER = "images/";

    private FileUtils() {}

    /**
     * Finds {@code fileName} in resources root.
     * @param fileName path to image from resources folder.
     * @return file from resources or null if wasn't found
     */
    public static File loadFile(String fileName) {
        URL url = FileUtils.class.getClassLoader().getResource(fileName);
        return url != null ? new File(url.getFile()) : null;
    }

    /**
     * Loads image from resources root.
     * @param imageName path to image from images folder
     * @return image or null if wasn't found or couldn't be read
     */
    public static Image loadImage(String imageName) {
        Image img = null;
        try {
            File file = loadFile(IMAGES_FOLDER + imageName);
            if (file != null) img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    /**
     * Registers font from file to graphics environment, where it
     * could be pulled as the usual font.
     * @param fontName path to font from font folder
     * @return true if could register, false otherwise
     * @see GraphicsEnvironment
     */
    public static boolean registerFont(String fontName) {
        try {
            InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(FONTS_FOLDER + fontName);
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            return true;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Default font or current application which is mostly used
     * to apply the same theme.
     * @param style font styles
     * @param size font size
     */
    public static Font getDefaultFont(int style, int size) {
        return new Font("Peppa Pig", style, size);
    }
}

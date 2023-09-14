package me.nutt;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class TranslationTestOne {

    public static MainPanel mainPanel = new MainPanel();

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream file = classLoader.getResourceAsStream("NotoSansJP-VariableFont_wght.ttf");
        try{
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(12f));
            }catch (IOException | FontFormatException e) {
                throw new RuntimeException(e);
                }
        FileManager.mkdirs();
        Frame frame = new Frame();
    }
}
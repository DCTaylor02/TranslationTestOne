package me.nutt;

import javax.swing.*;
import java.awt.*;

public class Frame {

    private static JFrame frame;

    public static JFrame getFrame(){
        return frame;
    }

    public Frame(){
        initialize();
    }
    private static void initialize(){
        frame = new JFrame("Translator Test");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);

        frame.add(TranslationTestOne.mainPanel.getCenterPanel(), BorderLayout.CENTER);
        frame.add(TranslationTestOne.mainPanel.getLeftPanel(), BorderLayout.WEST);
        frame.add(TranslationTestOne.mainPanel.getNorthPanel(), BorderLayout.NORTH);
        frame.add(TranslationTestOne.mainPanel.getRightPanel(), BorderLayout.EAST);

        frame.setVisible(true);
    }
}

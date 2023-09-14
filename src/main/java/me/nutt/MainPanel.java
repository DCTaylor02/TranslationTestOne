package me.nutt;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPanel {

    private static JPanel centerPanel;
    private static JPanel leftPanel;

    private static JPanel northPanel;

    private static JPanel rightPanel;

    public  JPanel getCenterPanel(){
        return centerPanel;
    }

    public  JPanel getLeftPanel(){
        return leftPanel;
    }

    public JPanel getNorthPanel(){return northPanel;}

    public JPanel getRightPanel(){return rightPanel;}

    public MainPanel() {
        initialize();
    }

    private void initialize() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10,10));
        centerPanel.setBackground(Color.BLACK);
        JLabel label = new JLabel("Select A File To Translate");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label.setForeground(Color.WHITE);

        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        leftPanel.setBackground(Color.BLACK);
        for (String string : FileManager.getFileNames()){
            JButton button = new JButton(string);
            button.addActionListener(e -> setPreviewScreen(FileReader.getText(string),string));
            leftPanel.add(button);
        }

        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(Color.black);
        northPanel.setForeground(Color.WHITE);
        northPanel.add(label);

        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        rightPanel.setBackground(Color.black);
        rightPanel.setForeground(Color.WHITE);
    }

    public void setPreviewScreen(List<String> lines, String fileName){
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        Dimension dim = centerPanel.getSize();
        dim.setSize(centerPanel.getWidth()-250, centerPanel.getHeight()-250);
        JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(dim);

        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Noto Sans JP Thin", Font.PLAIN, 12));

        JLabel label = new JLabel("Selected File Preview");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label.setForeground(Color.WHITE);
        label.setLayout(new FlowLayout(FlowLayout.LEADING));

        centerPanel.removeAll();
        northPanel.removeAll();
        rightPanel.removeAll();

        for (String line : lines){
            textArea.append(line);
        }
        northPanel.add(label);
        northPanel.updateUI();
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.updateUI();

        JButton translate = new JButton("Translate");
        translate.addActionListener(e -> {
            setTranslatedTextInPanel(Translator.translateText(fileName, lines));
            JButton print = new JButton("Write To File");
            print.addActionListener(p -> {
                FileWriter.WriteToFile(fileName,Translator.translateText(fileName, lines));
            });
            rightPanel.removeAll();
            rightPanel.add(print);
            rightPanel.updateUI();

        });
        rightPanel.add(translate);
        rightPanel.updateUI();
    }

    public void setTranslatedTextInPanel(List<String> lines){
        centerPanel.removeAll();
        northPanel.removeAll();

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        Dimension dim = centerPanel.getSize();
        dim.setSize(centerPanel.getWidth()-250, centerPanel.getHeight()-250);
        JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(dim);

        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        JLabel label = new JLabel("Translated Text");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label.setForeground(Color.WHITE);
        label.setLayout(new FlowLayout(FlowLayout.LEADING));


        for (String string : lines){
            textArea.append(string);
        }
        centerPanel.add(scrollPane);
        northPanel.add(label);
        centerPanel.updateUI();
        northPanel.updateUI();
    }
}

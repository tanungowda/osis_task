
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.*;


class TextEditor extends JFrame implements ActionListener {

    JTextArea textField;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, viewMenu;
    JMenuItem newOption, openOption, saveOption, closeOption, cutOption;
    JMenuItem zoomOption, copyOption, pasteOption, printOption;

    TextEditor() 
    {
        textField = new JTextArea();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        newOption = new JMenuItem("New");
        openOption = new JMenuItem("Open");
        saveOption = new JMenuItem("Save");
        closeOption = new JMenuItem("Close");
        printOption = new JMenuItem("Print");
        fileMenu.add(newOption);
        fileMenu.add(openOption);
        fileMenu.add(saveOption);
        fileMenu.add(closeOption);
        fileMenu.addSeparator();
        fileMenu.add(printOption);

        editMenu = new JMenu("Edit");
        cutOption = new JMenuItem("Cut");
        copyOption = new JMenuItem("Copy");
        pasteOption = new JMenuItem("Paste");
        editMenu.add(cutOption);
        editMenu.add(copyOption);
        editMenu.add(pasteOption);

        viewMenu = new JMenu("View");
        zoomOption = new JMenuItem("Zoom");
        viewMenu.add(zoomOption);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);
        add(scrollPane);

        newOption.addActionListener(this);
        openOption.addActionListener(this);
        saveOption.addActionListener(this);
        closeOption.addActionListener(this);
        cutOption.addActionListener(this);
        copyOption.addActionListener(this);
        pasteOption.addActionListener(this);
        printOption.addActionListener(this);
        zoomOption.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == newOption) {
            textField.setText("");
            setTitle("Text Editor");
        }
        else if (ae.getSource() == openOption) 
        {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                File selectedFile = fileChooser.getSelectedFile();
                try 
                {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    String line = null;
                    textField.setText("");

                    while ((line = reader.readLine()) != null) 
                    {
                        textField.append(line + "\n");
                    }
                    setTitle("Simple Text Editor - " + selectedFile.getName());
                    reader.close();
                }
                catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (ae.getSource() == saveOption) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();

                try 
                {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                    writer.write(textField.getText());
                    writer.close();
                    setTitle("Text Editor - " + selectedFile.getName());
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (ae.getSource() == closeOption) 
        {
            System.exit(0);
        }
        else if (ae.getSource() == cutOption) 
        {
            textField.cut();
        }
        else if (ae.getSource() == copyOption) 
        {
            textField.copy();
        }
        else if (ae.getSource() == pasteOption) 
        {
            textField.paste();
        }
        else if (ae.getSource() == zoomOption)
        {
            //textField.zoom();
        }
        else if (ae.getSource() == printOption) 
        {
            try 
            {
                textField.print();
            }
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(this, "Error printing file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}


public class Text_Editor {
    public static void main(String[] args) {
        TextEditor te = new TextEditor();
        te.setTitle("Text Editor");
        te.setSize(900, 650);
        te.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        te.setVisible(true);
    }
}
package Util;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

public class Gui extends JFrame {

    private JButton reg;
    private JButton custom;

    private JCheckBox name;

    private JTextField tf;
    private JTextField directory;

    private JFileChooser directorypick;

    public Gui() {

        super("Pex Checker");
        setLayout(new FlowLayout());

        name = new JCheckBox("Names");
        add(name);

        tf = new JTextField("UUID Input", 20);
        tf.setFont(new Font("Serif", Font.PLAIN, 14));
        add(tf);

        //directorypick = new JFileChooser();
        //add(directorypick);

        directory = new JTextField("Directory", 20);
        directory.setFont(new Font("Serif", Font.PLAIN, 14));
        add(directory);

        reg = new JButton("Debugger");
        reg.setBackground(Color.cyan);
        add(reg);

        Icon b = new ImageIcon(getClass().getResource("button.png"));
        custom = new JButton("Search", b);
        custom.setBackground(Color.GREEN);
        add(custom);

        HandlerClass handler = new HandlerClass();
        CheckHandler CheckHandlerOB = new CheckHandler();
        Debug debugObject = new Debug();

        reg.addActionListener(debugObject);
        custom.addActionListener(handler);
        name.addItemListener(CheckHandlerOB);

    }

        private class HandlerClass implements ActionListener {
            public void actionPerformed(ActionEvent event) {

                File userFile = new File(directory.getText());

                if (userFile.exists()) {
                    if(name.isSelected()){

                        try {

                            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + tf.getText()).openConnection();
                            int timed_out = 1250;
                            connection.setConnectTimeout(timed_out);
                            connection.setReadTimeout(timed_out);
                            String returnMsg = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                            String uuid = returnMsg.substring(7,39);
                            String parseduuid = uuid.replaceFirst( "([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)", "$1-$2-$3-$4-$5" );

                            FileReader readFile = new FileReader();
                            readFile.openFile(directory.getText());
                            readFile.readFile((parseduuid));
                            readFile.closeFile();

                            connection.disconnect();

                            if (readFile.getStatus().equalsIgnoreCase("Failed")) {
                                JOptionPane.showMessageDialog(null, String.format("Status: %s", readFile.getStatus()));
                                return;
                            }
                            JOptionPane.showMessageDialog(null, String.format("Rank: %s\nStatus: %s\nUUD: %s", readFile.getRank(), readFile.getStatus(), parseduuid));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {

                        FileReader readFile = new FileReader();
                        readFile.openFile(directory.getText());
                        readFile.readFile(tf.getText());
                        readFile.closeFile();

                        if (readFile.getStatus().equalsIgnoreCase("Failed")) {
                            JOptionPane.showMessageDialog(null, String.format("Status: %s", readFile.getStatus()));
                            return;
                        }
                        JOptionPane.showMessageDialog(null, String.format("Rank: %s\nStatus: %s\nUUD: %s", readFile.getRank(), readFile.getStatus(), tf.getText()));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid directory");
                }
            }
        }

    private class Debug implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            File userFile = new File(directory.getText());

            if (userFile.exists()) {

                FileReader readFile = new FileReader();
                readFile.openFile(directory.getText());
                readFile.debugger();
                readFile.closeFile();

                if (name.isSelected()) {
                    JOptionPane.showMessageDialog(null, String.format("Length: %s\nStatus: %s\nMode: %s", readFile.getLength(), readFile.getStatus(), "Name"));
                } else {
                    JOptionPane.showMessageDialog(null, String.format("Length: %s\nStatus: %s\nMode: %s", readFile.getLength(), readFile.getStatus(), "UUID"));
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Please enter a valid directory");
            }
        }
    }

    private class CheckHandler implements ItemListener{
        public void itemStateChanged(ItemEvent event){

        }
    }
}


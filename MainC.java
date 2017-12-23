import javax.swing.JFrame;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainC {

    public static void main(String[] args) {

        Gui guiObject = new Gui();
        guiObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiObject.setSize(300, 200);
        guiObject.setVisible(true);

    }
}
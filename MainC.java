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
//    public void checkUpdate(String username) {
//
//        try {
//
//            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + username).openConnection();
//            int timed_out = 1250;
//            connection.setConnectTimeout(timed_out);
//            connection.setReadTimeout(timed_out);
//
//            String returnMsg = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
//            String uuid = returnMsg.substring(7,39);
//            System.out.print("printed " +  uuid.replaceFirst( "([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)", "$1-$2-$3-$4-$5" ));
//            connection.disconnect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
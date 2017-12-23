import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    ArrayList <String> outcome = new ArrayList<String>();

    private Scanner x;
    private String rank;
    private String status;

    private int counter;

    public void openFile(String directory) {

        try {

            File userFile = new File(directory);

            if (userFile.exists()) {

                x = new Scanner(userFile);

            }
        } catch (Exception e) {

            System.out.println("Not Found...");
        }
    }

    public void debugger(){

       while (x.hasNext() && x.next() != null) {
           counter++;
       }
    }

    //4906a0a7-8ec4-49d8-9b12-a43eb6d25981
    //C:\Users\Admin\Desktop\Working\KitPvPPurchaseHistory
    public void readFile(String input){

            while (x.hasNext()) {

                String a = x.next();

                if (a.equalsIgnoreCase(input + ":")) {

                    System.out.println("Worked as intended...");
                    String b = x.next();
                    String c = x.next();
                    String d = x.next();
                    String e = x.next();
                    String f = x.next();
                    String g = x.next();
                    String h = x.next();

                    System.out.println(a);
                    if (!c.equalsIgnoreCase("[]")) {
                        System.out.println(c);
                    }
                    System.out.println(d);
                    if (!e.equalsIgnoreCase("-")) {
                        System.out.println(e);
                    }
                    System.out.println(f);
                    outcome.add("A");
                    rank = f;
                }
            }

            if (!x.hasNext()) {
                if (!outcome.contains("A")) {
                    status = "Failed";
                } else {
                    status = "Worked";
                }
            }
    }

    public void closeFile(){
        x.close();
    }

    public String getRank(){
        return rank;
    }

    public String getStatus() {
        return status;
    }

    public int getLength(){
        return counter;
    }
}


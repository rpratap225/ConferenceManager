import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, Integer> titleAndTime = new HashMap<String, Integer>();
        File file = new File("C:\\Users\\admin\\Desktop\\testCase1.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            int numTime = 0;
            while ((line = reader.readLine()) != null) {
                int lastBlank = line.lastIndexOf(" ");
                String title = line.substring(0, lastBlank);
                String time = line.substring(lastBlank + 1);
//                System.out.println(time);
                if (time.equals("lightning")) {
                    numTime = 5;
                } else {
                    // Remove "min" suffix
                    numTime = Integer.parseInt(time.substring(0, time.length() - 3));
                }
                titleAndTime.put(title, numTime);
            }
//            System.out.println(titleAndTime);
            ConferenceManager.getSchedule(titleAndTime);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}

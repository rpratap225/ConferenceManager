import java.util.*;

public class ConferenceManager {

    public static void getSchedule(Map<String, Integer> schedule) {
        Map<String, Integer> titleAndTimeBak = new HashMap<String, Integer>(schedule);
        Map<String, Integer> track1Forenoon = getTrackMap(schedule, 180);
        Map<String, Integer> track1Afternoon = getTrackMap(schedule, 240);
        Map<String, Integer> track2Forenoon = getTrackMap(schedule, 180);
        Map<String, Integer> track2Afternoon = getTrackMap(schedule, 240);

        if (!schedule.isEmpty()) {
            Map<String, Integer> tempMap = new HashMap<String, Integer>(titleAndTimeBak);
            while(!tempMap.isEmpty()){
                tempMap = new HashMap<String, Integer>(titleAndTimeBak);
                track1Forenoon = getTrackMap(tempMap, 180);
                track1Afternoon = getTrackMap(tempMap, 240);
                track2Forenoon = getTrackMap(tempMap, 180);
                track2Afternoon = getTrackMap(tempMap, 240);
            }
        }

        System.out.println("Track 1:");
        printForenoonSchedule(track1Forenoon);
        printAfternoonSchedule(track1Afternoon);
        System.out.println("Track 2:");
        printForenoonSchedule(track2Forenoon);
        printAfternoonSchedule(track2Afternoon);

    }

    public static void printForenoonSchedule(Map<String, Integer> trackMap) {
        int sumTime = 0;
        int res = 0;
        String remainderStr = "00";
        for (Map.Entry<String, Integer> entry : trackMap.entrySet()) {
            String title = entry.getKey();
            int time = entry.getValue();
            String timeStr = time == 5 ? "lightning" : time + "";
            switch (res) {
                case 0:
                    System.out.println("09:" + remainderStr + "AM " + title + " " + timeStr + "min");
                    break;
                case 1:
                    System.out.println("10:" + remainderStr + "AM " + title + " " + timeStr + "min");
                    break;
                case 2:
                    System.out.println("11:" + remainderStr + "AM " + title + " " + timeStr + "min");
                    break;
                default:
                    break;
            }
            sumTime += time;
            res = sumTime / 60;
            int remainder = sumTime % 60;
            if (remainder / 10 == 0) {
                remainderStr = "0" + remainder;
            } else {
                remainderStr = remainder + "";
            }
        }
        System.out.println("12:00PM Lunch");
    }

    /**
     * Print afternoon schedule
     *
     * @param trackMap
     */
    public static void printAfternoonSchedule(Map<String, Integer> trackMap) {
        int sumTime = 0;
        int res = 0;
        String remainderStr = "00";
        for (Map.Entry<String, Integer> entry : trackMap.entrySet()) {
            String title = entry.getKey();
            int time = entry.getValue();
            String timeStr = time == 5 ? "lightning" : time + "";
            switch (res) {
                case 0:
                    System.out.println("01:" + remainderStr + "PM " + title + " " + timeStr + "min");
                    break;
                case 1:
                    System.out.println("02:" + remainderStr + "PM " + title + " " + timeStr + "min");
                    break;
                case 2:
                    System.out.println("03:" + remainderStr + "PM " + title + " " + timeStr + "min");
                    break;
                case 3:
                    System.out.println("04:" + remainderStr + "PM " + title + " " + timeStr + "min");
                    break;
                default:
                    break;
            }
            sumTime += time;
            res = sumTime / 60;
            int remainder = sumTime % 60;
            if (remainder / 10 == 0) {
                remainderStr = "0" + remainder;
            } else {
                remainderStr = remainder + "";
            }
        }
        System.out.println("05:00PM Networking Event");
    }

    public static Map<String, Integer> getTrackMap(Map<String, Integer> titleAndTime, int sessionMinute) {
        Map<String, Integer> trackMap = new HashMap<String, Integer>();
        List<String> titleList = new ArrayList<String>(titleAndTime.keySet());
        Random random = new Random();
        int randomIndex = 0;
        String randomTitle = null;
        int time = 0;
        int sumTime = 0;
        while (sumTime < sessionMinute && titleList.size() > 0) {
            randomIndex = random.nextInt(titleList.size());
            randomTitle = titleList.get(randomIndex);
            time = titleAndTime.get(randomTitle);
            sumTime += time;
            if (sumTime <= sessionMinute) {
                trackMap.put(randomTitle, time);
            }
            titleList.remove(randomTitle);
        }

        // Remove entry from titleAndTime which has already schedule
        Set<String> trackMapKeySet = trackMap.keySet();
        Iterator<Map.Entry<String, Integer>> it = titleAndTime.entrySet().iterator();
        while (it.hasNext()) {
            if (trackMapKeySet.contains(it.next().getKey())) {
                it.remove();
            }
        }
        return trackMap;
    }
}

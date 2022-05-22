import java.util.*;


/*  0 - seconds
 *  1 - minutes
 *  2 - hours
 *  3 - day of the month
 *  4 - month
 *  5 - day of the week
 */

public class ParametersHandler {
    HashMap<DateParameters, ArrayList<Integer>> parameters = new HashMap<>(6);
    String[] daysOfWeek = {"", "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};


    void add(DateParameters parameter, int value) {
        if (!parameters.containsKey(parameter)) {
            parameters.put(parameter, new ArrayList<>());
            parameters.get(parameter).add(value);
        }
        if (!parameters.get(parameter).contains(value)) {
            parameters.get(parameter).add(value);
        }
    }


    String createCron() {
        StringBuilder cronString = new StringBuilder();
        for (DateParameters par : DateParameters.values()) {
            cronString.append(processParameter(par));
            cronString.append(" ");
        }
        return cronString.toString();
    }


    String processParameter(DateParameters parameter) {
        ArrayList<Integer> set = parameters.get(parameter);
        if (set.size() == 1) {
            return set.get(0).toString();
        }
        int res = checkDistance(set);
        if (set.size() == 2) {
            if (set.get(1) - set.get(0) == 1) {
                return set.get(0) + "-" + set.get(1);
            } else {
                return set.get(0) + "/" + set.get(1);
            }
        } else if (res != -1) {
            return set.get(0) + "-" + res;
        } else {
            return "*";
        }
    }

    String cronToPrint(String cron) {
        String[] strArray = cron.split(" ");
        StringBuilder ans = new StringBuilder();
        if (strArray[5].length() == 1){
            strArray[5] = daysOfWeek[Integer.parseInt(strArray[5])];
        } else {
            strArray[5] = daysOfWeek[strArray[5].charAt(0) - '0'] + "-" + daysOfWeek[strArray[5].charAt(strArray[5].length() - 1) - '0'];
        }
        for (int i = 0; i < 6; i++) {
            ans.append(strArray[i]).append(" ");
        }
        ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }

    int checkDistance(ArrayList<Integer> set) {
        int dist = set.get(1) - set.get(0);
        int i;
        for (i = 1; i < set.size() - 1; i++) {
            if (set.get(i + 1) - set.get(i) != dist) {
                break;
            }
        }
        return set.get(i);
    }

    boolean checkCronExpression(String cron, LinkedList<Date> listOfDates) {
        if (cron.equals("* * * * * *")) {
            return false;
        }
        String[] strArray = cron.split(" ");
        int count = 0;
        for (Date date : listOfDates) {
            if (checkParameter(strArray, 0, date.getSeconds()) && checkParameter(strArray, 1, date.getMinutes())
                    && checkParameter(strArray, 2, date.getHours()) && checkParameter(strArray, 3, date.getDate())
                    && checkParameter(strArray, 4, date.getMonth()) && checkParameter(strArray, 5, date.getDay())) {
                count++;
            }
        }
        return (count * 100) / listOfDates.size() >= 50;
    }


    boolean checkParameter(String[] strArray, int i, int val) {
        if (!strArray[i].equals("*")) {
            if (strArray[i].contains(",") && strArray[i].contains(Integer.toString(val))) {
                return true;
            } else if (strArray[i].contains("/")) {
                String[] values = strArray[i].split("/");
                return val % (Integer.parseInt(values[1]) - Integer.parseInt(values[0])) == 0;
            } else if (strArray[i].contains("-")) {
                String[] values = strArray[i].split("-");
                return val <= Integer.parseInt(values[1]) && val >= Integer.parseInt(values[0]);
            } else if (strArray[i].equals(Integer.toString(val))) {
                return true;
            }
        }
        return false;
    }
}

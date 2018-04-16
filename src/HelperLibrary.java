import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Dawidek on 2018-04-05.
 */


public class HelperLibrary {

    public static int countWordsUsingSplit(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }

    public static String listToString(List<?> list) {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += " " + list.get(i);
        }
        return result;
    }

}

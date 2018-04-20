package example.com.myapplication.PermissionHandler;

import java.util.List;


public class Utils {

    public static String[] convertListToArray(List<String> list) {
        String[] converted = new String[list.size()];
        return list.toArray(converted);

    }
}

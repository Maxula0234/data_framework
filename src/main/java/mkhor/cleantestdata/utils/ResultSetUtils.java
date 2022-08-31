package mkhor.cleantestdata.utils;

import java.sql.ResultSet;

public class ResultSetUtils {
    public static boolean containsColumn(ResultSet resultSet, String nameColumn) {
        try {
            int column = resultSet.findColumn(nameColumn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

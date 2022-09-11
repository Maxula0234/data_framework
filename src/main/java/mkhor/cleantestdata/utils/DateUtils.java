package mkhor.cleantestdata.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DateUtils {
    protected static final Log logger = LogFactory.getLog(DateUtils.class);

    static List<SimpleDateFormat> dateFormats = List.of(
            new SimpleDateFormat("dd-MM-yyyy"),
            new SimpleDateFormat("yyyy-MM-dd")
    );

    public static LocalDate parseDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String parseDateToString(Date date) {
        AtomicReference<String> format = new AtomicReference<>();
        dateFormats.forEach(f -> {
            try {
                format.set(f.format(date));
            } catch (Exception e) {
                logger.info("Формат не корректный - " + f.toString());
            }
        });
        return format.get();
    }
}

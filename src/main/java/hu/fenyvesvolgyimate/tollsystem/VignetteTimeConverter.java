package hu.fenyvesvolgyimate.tollsystem;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class VignetteTimeConverter {
    public String convertUTCToZuluDate(Date date){
        Instant instant = Instant.ofEpochMilli(date.getTime());
        SimpleDateFormat zuluDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        zuluDateFormat.setTimeZone(TimeZone.getTimeZone("Zulu"));
        return instant.toString();
    }
}

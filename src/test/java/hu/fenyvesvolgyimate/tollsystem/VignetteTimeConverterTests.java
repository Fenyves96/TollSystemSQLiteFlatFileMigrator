package hu.fenyvesvolgyimate.tollsystem;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VignetteTimeConverterTests {
    VignetteTimeConverter converter = new VignetteTimeConverter();
    @Test
    public void testConvert() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = dateFormat.parse("Thu Jan 26 00:00:00 CET 2023");

        String zuluDateString = converter.convertUTCToZuluDate(date);
        assertEquals("2023-01-25T23:00:00Z", zuluDateString);
    }
}

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

public class Converter implements DatesToCronConverter {
    private final static String INPUT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public String convert(LinkedList<String> listOfDates) {
        LinkedList<Date> parsedDates = parseDates(listOfDates);
        Collections.sort(parsedDates);
        ParametersHandler handler = new ParametersHandler();
        for (Date date : parsedDates) {
            handler.add(DateParameters.DAY, date.getDate());
            handler.add(DateParameters.MONTH, date.getMonth());
            handler.add(DateParameters.HOUR, date.getHours());
            handler.add(DateParameters.MINUTES, date.getMinutes());
            handler.add(DateParameters.SECONDS, date.getSeconds());
            handler.add(DateParameters.WEEKDAY, date.getDay());
        }
        String cron = handler.createCron();
        if (handler.checkCronExpression(cron, parsedDates)) {
            return handler.cronToPrint(cron);
        } else {
            throw new DatesToCronConvertException("Cron expression can't be found");
        }
    }

    @Override
    public String getImplementationInfo() {
        return "Степанова Ольга Андреевна " + Converter.class.getSimpleName() + " " +
                Converter.class.getPackage().getName() + " https://github.com/oscuiruss";
    }

    LinkedList<Date> parseDates(LinkedList<String> listOfDates) {
        LinkedList<Date> parsedDates = new LinkedList<>();
        DateFormat inputFormatter = new SimpleDateFormat(INPUT_PATTERN);
        for (String date : listOfDates) {
            try {
                parsedDates.add(inputFormatter.parse(date));
            } catch (ParseException e) {
                throw new DateFormatException(date);
            }
        }
        return parsedDates;
    }
}

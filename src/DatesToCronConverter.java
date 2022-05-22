import java.util.LinkedList;

public interface DatesToCronConverter {
    String convert(LinkedList<String> listOfDates);

    String getImplementationInfo();
}

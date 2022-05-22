import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import java.util.LinkedList;

public class Tests {
    @Test
    public void checkTest1() {
        LinkedList<String> list = new LinkedList<>();
        Converter converter = new Converter();
        list.add("2022-01-24T19:53:00");
        list.add("2022-01-24T19:54:00");
        list.add("2022-01-24T19:55:00");
        list.add("2022-01-24T19:56:00");
        list.add("2022-01-24T19:57:00");
        list.add("2022-01-24T19:58:00");
        list.add("2022-01-24T19:59:00");
        list.add("2022-01-24T20:00:00");
        list.add("2022-01-24T20:01:00");
        list.add("2022-01-24T20:02:00");
        assertEquals("0 53-59 19-20 24 0 MON", converter.convert(list));
    }

    @Test
    public void checkTest2() {
        LinkedList<String> list = new LinkedList<>();
        Converter converter = new Converter();
        list.add("2022-01-25T08:00:00");
        list.add("2022-01-25T08:30:00");
        list.add("2022-01-25T09:00:00");
        list.add("2022-01-25T09:30:00");
        list.add("2022-01-26T08:00:00");
        list.add("2022-01-26T08:30:00");
        list.add("2022-01-26T09:00:00");
        list.add("2022-01-26T09:30:00");
        assertEquals("0 0/30 8-9 25-26 0 TUE-WED", converter.convert(list));
    }

    @Test(expected = DatesToCronConvertException.class)
    public void checkTest3() throws DatesToCronConvertException{
        LinkedList<String> list = new LinkedList<>();
        Converter converter = new Converter();
        list.add("2022-01-24T19:53:00");
        list.add("2022-01-24T19:54:00");
        list.add("2022-01-24T19:55:00");
        list.add("2022-01-24T19:56:00");
        list.add("2022-01-24T19:57:00");
        list.add("2022-01-24T19:58:00");
        list.add("2022-01-24T19:59:00");
        list.add("2022-01-24T20:00:00");
        list.add("2022-01-24T20:01:00");
        list.add("2022-01-24T20:02:00");
        list.add("2022-01-25T08:00:00");
        list.add("2022-01-25T08:30:00");
        list.add("2022-01-25T09:00:00");
        list.add("2022-01-25T09:30:00");
        list.add("2022-01-26T08:00:00");
        list.add("2022-01-26T08:30:00");
        list.add("2022-01-26T09:00:00");
        list.add("2022-01-26T09:30:00");
        converter.convert(list);
    }


}

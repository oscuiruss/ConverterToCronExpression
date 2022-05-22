public class DateFormatException extends RuntimeException {
    public DateFormatException(String date){
        super(date + "is in wrong format! Expected : yyyy-MM-dd'T'HH:mm:ss");
    }
}

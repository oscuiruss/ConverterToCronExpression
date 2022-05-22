package com.stepanova.olga.main;
import java.util.List;

public interface DatesToCronConverter {
    String convert(List<String> listOfDates);

    String getImplementationInfo();
}

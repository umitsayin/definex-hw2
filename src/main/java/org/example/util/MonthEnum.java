package org.example.util;

public enum MonthEnum {
    JANUARY("JANUARY"),
    FEBRUARY("FEBRUARY"),
    MARCH("MARCH"),
    APRIL("APRIL"),
    MAY("MAY"),
    JUNE("JUNE"),
    JULY("JULY"),
    AUGUST("AUGUST"),
    SEPTEMBER("SEPTEMBER"),
    OCTOBER("OCTOBER"),
    NOVEMBER("NOVEMBER"),
    DECEMBER("DECEMBER");

    private String value;

    MonthEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}

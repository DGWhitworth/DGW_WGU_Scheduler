package com.example.dgwwguscheduler.UI;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

public class Conversions {
    @TypeConverter
    public static Date fromTime(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTime(Date date) {
        return date == null ? null : date.getTime();
    }
}

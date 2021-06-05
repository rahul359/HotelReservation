package com.rahul.program.order.util;

import org.springframework.stereotype.Service;

@Service
public class ValidDate {
    public static boolean isLeap(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    public static boolean validDate(String date) {
       
        String[] dateParts = date.split("/");
        int d = Integer.parseInt(dateParts[0]);
        int m = Integer.parseInt(dateParts[1]);
        int y = Integer.parseInt(dateParts[2]);

        int MAX_VALID_YR = 9999;
        int MIN_VALID_YR = 1800;

        if (y > MAX_VALID_YR || y < MIN_VALID_YR)
            return true;
        if (m < 1 || m > 12)
            return true;
        if (d < 1 || d > 31)
            return true;

        if (m == 2) {
            if (isLeap(y))
                return (d > 29);
            else
                return (d > 28);
        }
        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d > 30);
        return false;
    }
}
package com.covid19.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ayaz.Ahmed
 * @description A class that save username with latest token issue date
 * so that previous tokens invalidate and only latest token can used.
 */
public class UserTokenIssue {

    private static final Map<String, Date> userWithLatestDate = new HashMap<>();

    public static Map<String, Date> getUserWithLatestDate() {
        return userWithLatestDate;
    }

    public static void setUserTokenIssue(final String userName, final Date latestIssueDate){
        userWithLatestDate.put(userName, latestIssueDate);
    }

    public static Date getLatestDate(final String userName){
        return userWithLatestDate.get(userName);
    }

    private UserTokenIssue() {}
}

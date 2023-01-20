package com.abnergmf.votesapi.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.abnergmf.votesapi.application.error.FormatDateConverterException;
import com.abnergmf.votesapi.application.error.NullObjectVotesAPIException;

public class DateUtil {

    public static Date parseStringToDate(String data)  {
        if (data == null) {
            throw new NullObjectVotesAPIException("Data", DateUtil.class.getName());
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", new Locale("pt","BR"));
            return formatter.parse(data);
        } catch (FormatDateConverterException | ParseException e) {
            throw new FormatDateConverterException(data);
        }
    }

}

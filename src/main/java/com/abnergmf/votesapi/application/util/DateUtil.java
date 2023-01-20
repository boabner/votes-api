package com.abnergmf.votesapi.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import com.abnergmf.votesapi.application.error.FormatDateConverterException;
import com.abnergmf.votesapi.application.error.NullObjectVotesAPIException;

public class DateUtil {

    public static Date getDataValidada(String data)  {
        if (data != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
                return formatter.parse(data);
            } catch (FormatDateConverterException | ParseException e) {
                throw new FormatDateConverterException(data);
            }
        }
        return null;
    }

    public static Date acrescentarMinutosNaData(Date data, int minutos) {

        try {

            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(data);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));

            gregorianCalendar.add(Calendar.MINUTE, minutos);

            String dataSaida = simpleDateFormat.format(gregorianCalendar.getTime());

            return simpleDateFormat.parse(dataSaida);

        } catch (ParseException e) {
            throw new FormatDateConverterException(e);
        }
    }

}

package com.busefisensi.efisiensiagen.Util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class StringUtil {

    public static String getPriceInRupiahFormat(Long amount) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        String result = kursIndonesia.format(amount);
        return result;
    }
}

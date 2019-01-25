package com.foo.cba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class Paragraph {

    public static String changeDateFormat(String paragraph) {
        if(paragraph.isEmpty()||null== paragraph){
            return paragraph;
        }

        StringBuilder resultDate = new StringBuilder("");
        SimpleDateFormat dfm = new SimpleDateFormat("MM/dd/yyyy");
        dfm.setLenient(false);
        SimpleDateFormat dfm1 = new SimpleDateFormat("dd/MM/YYYY");

        StringTokenizer st = new StringTokenizer(paragraph);

        String formatedDate = null;
        while (st.hasMoreElements()) {
            String str = st.nextElement().toString();
            try {
                formatedDate = dfm1.format((dfm.parse(str)));
            } catch (IllegalArgumentException e) {
                formatedDate = str;

            } catch (ParseException e) {
                formatedDate = str;
            }

            resultDate.append(" ").append(formatedDate);
        }

        return resultDate.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(changeDateFormat("aaaaa 07/41/2013 was on 07/25/2013 and today is 08/09/2013."));
    }
}

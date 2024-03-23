package com.example.innowise.service;

import com.example.innowise.model.CurrencyArray;
import com.example.innowise.model.CurrencyModel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class CurrencyService {

    public static String getCurrencyRate() throws IOException, ParseException {
        URL url = new URL("https://api.mexc.com/api/v3/ticker/price");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }
        JSONArray jsonArray = new JSONArray(result);
        CurrencyArray currencyArray = new CurrencyArray();

        for (int i = 0; i < 100; i++) {

            JSONObject object = jsonArray.getJSONObject(i);
            CurrencyModel model = new CurrencyModel();
            model.setCur_Name(object.getString("symbol"));
            model.setCur_OfficialRate(object.getDouble("price"));
            model.setDate(java.time.LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

            currencyArray.addModel(model);

        }
        return currencyArray.toString();
    }

    public static String getSearchedCurrencyRate(String message) throws IOException, ParseException {
        URL url = new URL("https://api.mexc.com/api/v3/ticker/price");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }
        JSONArray jsonArray = new JSONArray(result);
        CurrencyArray currencyArray = new CurrencyArray();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject object = jsonArray.getJSONObject(i);

            if (object.getString("symbol").contains(message)) {
                CurrencyModel model = new CurrencyModel();
                model.setCur_Name(object.getString("symbol"));
                model.setCur_OfficialRate(object.getDouble("price"));
                model.setDate(java.time.LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

                currencyArray.addModel(model);
            }
        }
        return currencyArray.toString();
    }

}

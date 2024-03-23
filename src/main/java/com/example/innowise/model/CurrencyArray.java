package com.example.innowise.model;

import lombok.Data;

import java.util.ArrayList;

import lombok.Data;
import java.util.ArrayList;

@Data
public class CurrencyArray {
    private ArrayList<CurrencyModel> currencyArray;

    public CurrencyArray() {
        currencyArray = new ArrayList<>();
    }

    public void addModel(CurrencyModel currencyModel){
        currencyArray.add(currencyModel);
    }

    @Override
    public String toString() {
        String str = "";
        String time = currencyArray.get(0).getDate();
        for (CurrencyModel currencyModel : currencyArray) {
            str = str.concat(currencyModel.toString());
        }
        return "Date: " + time +'\n'+'\n'+
                str;
    }
}

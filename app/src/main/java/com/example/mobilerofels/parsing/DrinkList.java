package com.example.mobilerofels.parsing;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DrinkList {

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public String toString() {
        return "Drinks{" +
                "drinks=" + drinks.toString() +
                '}';
    }
}
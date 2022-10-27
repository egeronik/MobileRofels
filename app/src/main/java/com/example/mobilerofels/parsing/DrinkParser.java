package com.example.mobilerofels.parsing;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrinkParser {
    Retrofit retrofit;
    private DrinkInterface aPie;

    public DrinkParser() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aPie = retrofit.create(DrinkInterface.class);
    }

    public DrinkInterface getaPie() {
        return aPie;
    }
}

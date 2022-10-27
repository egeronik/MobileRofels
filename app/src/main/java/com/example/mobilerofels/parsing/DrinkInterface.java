package com.example.mobilerofels.parsing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrinkInterface {
    @GET("search.php")
    Call<DrinkList> getByName(@Query("s") String name);

    @GET("filter.php")
    Call<DrinkList> getByIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Call<DrinkList> getById(@Query("i") String id);
}

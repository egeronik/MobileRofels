package com.example.mobilerofels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mobilerofels.parsing.Drink;
import com.example.mobilerofels.parsing.DrinkList;
import com.example.mobilerofels.parsing.DrinkParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    final String LOG_TAG = "InfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Log.d(LOG_TAG, getIntent().getStringExtra("id"));
        DrinkParser drinkParser = new DrinkParser();
        drinkParser.getaPie().getById(getIntent().getStringExtra("id")).enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {
                if (response.isSuccessful()) {
                    fillData(response.body().getDrinks().get(0));
                } else {
                    Toast.makeText(InfoActivity.this, "Something go wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "RequestError", Toast.LENGTH_SHORT).show();
            }
        });
    }


    void fillData(Drink drink){
        Log.d(LOG_TAG,drink.toString());
    }
}
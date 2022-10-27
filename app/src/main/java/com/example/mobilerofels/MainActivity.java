package com.example.mobilerofels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mobilerofels.parsing.Drink;
import com.example.mobilerofels.parsing.DrinkList;
import com.example.mobilerofels.parsing.DrinkParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DrinkParser drinkParser;

    EditText editText;
    Button button;
    RadioGroup radioGroup;
    ListView listView;

    private final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextSearchRequest);
        button = findViewById(R.id.buttonFind);
        radioGroup = findViewById(R.id.radioGroupSearchType);
        listView = findViewById(R.id.drinkLV);

        drinkParser = new DrinkParser();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioName:
                        getDrinksByName(editText.getText().toString());
                        break;
                    case R.id.radioIngredient:
                        getDrinksByIngredient(editText.getText().toString());
                        break;
                }
            }
        });


    }

    private void getDrinksByIngredient(String ingredient) {
        drinkParser.getaPie().getByIngredient(ingredient).enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {
                if (response.isSuccessful()) {
                    populateListView(response.body().getDrinks());

                }else {
                    Toast.makeText(MainActivity.this, "Something go wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "RequestError", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getDrinksByName(String name) {
        drinkParser.getaPie().getByName(name).enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {
                if (response.isSuccessful()) {
                    populateListView(response.body().getDrinks());
                }else {
                    Toast.makeText(MainActivity.this, "Something go wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                Log.d(LOG_TAG,t.toString());
                Toast.makeText(MainActivity.this, "RequestError", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void populateListView(List<Drink> drinkList) {
        DrinkAdapter drinkAdapter = new DrinkAdapter(getApplicationContext(),R.layout.list_item,drinkList);
        listView.setAdapter(drinkAdapter);

    }
}
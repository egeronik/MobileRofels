package com.example.mobilerofels;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilerofels.parsing.Drink;
import com.example.mobilerofels.parsing.DrinkList;
import com.example.mobilerofels.parsing.DrinkParser;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    final String LOG_TAG = "InfoActivity";

    TextView nameTV;
    TextView alcoTV;
    TextView glasTV;
    TextView instTV;

    ImageView imageView;

    TableLayout tabl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        nameTV = findViewById(R.id.infName);
        alcoTV = findViewById(R.id.infAlcoholic);
        glasTV = findViewById(R.id.infGlass);
        instTV = findViewById(R.id.infInstructions);


        tabl = findViewById(R.id.tabl);

        imageView.setVisibility(View.GONE);

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


    TableRow generateRow(String ingredient, String measure) {
        TableRow tableRow = new TableRow(this);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);

        TableRow.LayoutParams lp1 = new TableRow.LayoutParams();
        lp1.weight = 1.0f;

        TableRow.LayoutParams lp2 = new TableRow.LayoutParams();
        lp2.weight = 1.0f;
        lp2.gravity = Gravity.RIGHT;


        tv1.setText(ingredient);
        tv1.setLayoutParams(lp1);

        tv2.setText(measure);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        tv2.setLayoutParams(lp2);

        tableRow.addView(tv1);
        tableRow.addView(tv2);

        return tableRow;
    }

    void fillData(Drink drink) {
        nameTV.setText(drink.getStrDrink());
        alcoTV.setText(drink.getStrAlcoholic());
        glasTV.setText(drink.getStrGlass());
        instTV.setText(drink.getStrInstructions());



        String ingStr = "getStrIngredient";
        String mesStr = "getStrMeasure";
        int i = 1;
        try {
            Method m = Drink.class.getMethod(ingStr + Integer.toString(i),
                    null);
            String ing = (String) m.invoke(drink, (Object[]) null);
            Method n = Drink.class.getMethod(mesStr + Integer.toString(i),
                    null);
            String mes = (String) n.invoke(drink, (Object[]) null);
            Log.d(LOG_TAG, ing);
            while (ing != null && i < 16) {
                m = Drink.class.getMethod(ingStr + Integer.toString(i),
                        null);
                ing = (String) m.invoke(drink, (Object[]) null);
                n = Drink.class.getMethod(mesStr + Integer.toString(i),
                        null);
                mes = (String) n.invoke(drink, (Object[]) null);

                tabl.addView(generateRow(ing, mes));
                i++;
            }

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, drink.toString());
    }
}
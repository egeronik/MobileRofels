package com.example.mobilerofels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobilerofels.parsing.Drink;

import java.util.List;

public class DrinkAdapter extends ArrayAdapter<Drink> {

    private Context mContext;
    private final LayoutInflater layoutInflater;

    int mResource;


    public DrinkAdapter(@NonNull Context context, int resource, @NonNull List<Drink> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView drinkNameTV = convertView.findViewById(R.id.drinkNameTV);
        TextView drinkAlcoholicTV = convertView.findViewById(R.id.drinkAlcoholicTV);
        TextView drinkCategoryTV = convertView.findViewById(R.id.drinkCategoryTV);
        ImageButton imageButton = convertView.findViewById(R.id.imageButton);


        Drink drink = getItem(position);

        drinkNameTV.setText(drink.getStrDrink());
        if (drink.getStrCategory() == null) {
            drinkCategoryTV.setVisibility(View.GONE);
        } else {
            drinkCategoryTV.setVisibility(View.VISIBLE);
            drinkCategoryTV.setText(drink.getStrCategory());
        }

        if (drink.getStrAlcoholic() == null) {
            drinkAlcoholicTV.setVisibility(View.GONE);
        } else {
            drinkAlcoholicTV.setVisibility(View.VISIBLE);
            drinkAlcoholicTV.setText(drink.getStrAlcoholic());
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", drink.getIdDrink());
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }
}

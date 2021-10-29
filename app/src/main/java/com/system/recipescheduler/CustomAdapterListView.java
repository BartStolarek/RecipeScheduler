package com.system.recipescheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.system.recipescheduler.database.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterListView extends BaseAdapter {

    Context context;
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();


    public CustomAdapterListView(Context context, ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.ingredient_listview, parent, false);
        }

        Ingredient tempIngredient = (Ingredient) getItem(position);

        TextView ingredient_name = convertView.findViewById(R.id.ingredient_name_listview);
        TextView quantity = convertView.findViewById(R.id.quantity_listview);
        TextView recipe_measurement = convertView.findViewById(R.id.recipe_measurement_listview);

        ingredient_name.setText(tempIngredient.getIngredient_name());
        quantity.setText(tempIngredient.getQuantity());


        return convertView;
    }
}

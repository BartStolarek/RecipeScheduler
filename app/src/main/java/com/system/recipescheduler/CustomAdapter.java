package com.system.recipescheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList name, favourite, duration, category;

    CustomAdapter(Activity activity, Context context, ArrayList name, ArrayList favourite, ArrayList duration,
                  ArrayList category){
        this.activity = activity;
        this.context = context;
        this.name = name;
        this.favourite = favourite;
        this.duration = duration;
        this.category = category;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cookbook_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.favourite_txt.setText(String.valueOf(favourite.get(position)));
        holder.duration_txt.setText(String.valueOf(duration.get(position)));
        holder.category_txt.setText(String.valueOf(category.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateRecipeActivity.class);
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("favourite", String.valueOf(favourite.get(position)));
                intent.putExtra("duration", String.valueOf(duration.get(position)));
                intent.putExtra("category", String.valueOf(category.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_txt, favourite_txt, duration_txt, category_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.name_txt);
            favourite_txt = itemView.findViewById(R.id.favourite_image);
            duration_txt = itemView.findViewById(R.id.duration_txt);
            category_txt = itemView.findViewById(R.id.category_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

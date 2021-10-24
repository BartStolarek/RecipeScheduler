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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList recipe_id, name, favourite, duration, category, health_rating;

    CustomAdapter(Activity activity, Context context, ArrayList recipe_id, ArrayList name, ArrayList favourite, ArrayList duration,
                  ArrayList category, ArrayList health_rating){
        this.activity = activity;
        this.context = context;
        this.recipe_id = recipe_id;
        this.name = name;
        this.favourite = favourite;
        this.duration = duration;
        this.category = category;
        this.health_rating = health_rating;
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
        //Name
        String arrName[] = String.valueOf(name.get(position)).split("(?i)with",2);
        holder.name_txt.setText(arrName[0].trim());

        //Favourite
        if(String.valueOf(favourite.get(position)).equals("1")) {
            holder.favourite_img.setVisibility(View.VISIBLE);
        }else{
            holder.favourite_img.setVisibility(View.GONE);
        }

        //Duration
        String arrDuration[] = String.valueOf(duration.get(position)).trim().split(":",3);
        int dur = Integer.parseInt(arrDuration[0])*60 + Integer.parseInt(arrDuration[1]);
        holder.duration_txt.setText(String.format("%s minutes",dur));

        //Category
        holder.category_txt.setText(String.valueOf(category.get(position)).trim());

        //Health Rating
        String health_str;
        if(String.valueOf(health_rating.get(position)).trim().equals("1")){
            health_str = String.valueOf(health_rating.get(position)).trim() + " (Abusive)";
        }else if(String.valueOf(health_rating.get(position)).trim().equals("2")){
            health_str = String.valueOf(health_rating.get(position)).trim() + " (Unhealthy)";
        }else if((String.valueOf(health_rating.get(position)).trim().equals("3"))){
            health_str = String.valueOf(health_rating.get(position)).trim() + " (Healthy)";
        }else{
            health_str = " ";
        }
        holder.health_rating_txt.setText(health_str);

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, RecipeActivity.class);

                intent.putExtra("recipe_id", String.valueOf(recipe_id.get(position)));
                intent.putExtra("name_1", arrName[0].trim());
                if(arrName.length > 1){
                    arrName[1] = "with" + arrName[1];
                    intent.putExtra("name_2", arrName[1].trim());
                }
                intent.putExtra("favourite", String.valueOf(favourite.get(position)));
                intent.putExtra("duration", String.valueOf(dur));
                intent.putExtra("category", String.valueOf(category.get(position)));
                intent.putExtra("health_rating", String.valueOf(health_rating.get(position)));
                
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        String recipe_id_txt;
        TextView name_txt, duration_txt, category_txt, health_rating_txt;
        ImageView favourite_img;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_id_txt = null;
            name_txt = itemView.findViewById(R.id.name_txt_cookbook_row);
            favourite_img = itemView.findViewById(R.id.favourite_image_cookbook_row);
            duration_txt = itemView.findViewById(R.id.duration_txt_cookbook_row);
            category_txt = itemView.findViewById(R.id.category_txt_cookbook_row);
            health_rating_txt = itemView.findViewById(R.id.health_rating_txt_cookbook_row);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

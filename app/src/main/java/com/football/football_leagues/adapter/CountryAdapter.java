package com.football.football_leagues.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.football.football_leagues.R;
import com.football.football_leagues.controller.CountryController;
import com.football.football_leagues.model.CountryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private List<CountryModel> countryUtil;
    Context context;
    public CountryAdapter(CountryController context, List countryUtil) {
        this.context = (Context) context;
        this.countryUtil = countryUtil;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_country, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CountryModel countryList = countryUtil.get(position);
        //display all
        holder.countryName.setText(countryList.getCountryName());
        if (countryList.getCountryLogo().isEmpty()) {
            holder.countryLogo.setImageResource(R.drawable.ic_broken_image);
        }
        else{
            Picasso.get().load(countryList.getCountryLogo()).into(holder.countryLogo);
        }




    }


    @Override
    public int getItemCount()throws NullPointerException {
        if(countryUtil == null){
            Toast.makeText(context.getApplicationContext(),"No data loaded...", Toast.LENGTH_SHORT).show();
        }

        return countryUtil.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        //VARIABLE PARA MABASA
        public TextView countryName;
        public ImageView countryLogo;

        public ViewHolder(View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.country_name_countries);
            countryLogo = itemView.findViewById(R.id.country_logo);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
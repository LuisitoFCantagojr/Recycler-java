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
import com.football.football_leagues.controller.LeagueController;
import com.football.football_leagues.model.LeaguesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.ViewHolder> {
    private List<LeaguesModel> leaguesUtil;
    Context context;
    public LeaguesAdapter(LeagueController context, List leaguesUtil) {
        this.context = (Context) context;
        this.leaguesUtil = leaguesUtil;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_leagues, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final LeaguesModel leagueList = leaguesUtil.get(position);
        //display all
        if(leagueList == null){
            holder.leagueLogo.setImageResource(R.drawable.ic_broken_image);
            holder.typeChampionship.setText("please refresh");

            holder.countryName.setText("please refresh");
            holder.leagueSeason.setText("Please reload the data");
            holder.countryLogo.setImageResource(R.drawable.ic_broken_image);
        }
        Picasso.get().load(leagueList.getLeagueLogo()).into(holder.leagueLogo);
        holder.typeChampionship.setText(leagueList.getLeagueName());


        if (leagueList.getCountryLogo().isEmpty()) {
            holder.countryLogo.setImageResource(R.drawable.ic_broken_image);
        } else {
            Picasso.get().load(leagueList.getCountryLogo()).into(holder.countryLogo);

        }


        holder.countryName.setText(leagueList.getCountryName());
        holder.leagueSeason.setText(leagueList.getLeagueSeason());



    }


    @Override
    public int getItemCount()throws NullPointerException {
        if(leaguesUtil == null){
            Toast.makeText(context.getApplicationContext(),"No data loaded...", Toast.LENGTH_SHORT).show();
        }

        return leaguesUtil.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        //VARIABLE PARA MABASA
        public TextView typeChampionship;
        public TextView countryName;
        public TextView leagueSeason;
        public ImageView leagueLogo;
        public ImageView countryLogo;

        public ViewHolder(View itemView) {
            super(itemView);
            typeChampionship = itemView.findViewById(R.id.type_championship);
            countryName = itemView.findViewById(R.id.country_name);
            leagueSeason = itemView.findViewById(R.id.league_season);

            leagueLogo = itemView.findViewById(R.id.league_logo);
            countryLogo = itemView.findViewById(R.id.country_logo);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
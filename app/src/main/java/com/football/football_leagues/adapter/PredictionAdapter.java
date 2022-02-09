package com.football.football_leagues.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.football.football_leagues.R;
import com.football.football_leagues.controller.PredictionController;
import com.football.football_leagues.model.PredictionModel;

import java.util.List;

public class PredictionAdapter extends RecyclerView.Adapter<PredictionAdapter.ViewHolder> {
    private List<PredictionModel> predictionUtil;
    Context context;

    public PredictionAdapter(PredictionController context, List predictionUtil) {
        this.context = context;
        this.predictionUtil = predictionUtil;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_prediction, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PredictionModel predictionModel = predictionUtil.get(position);



        holder.countryName.setText(predictionModel.getCountryName());

        holder.leagueName.setText(predictionModel.getLeagueName());

        holder.hTeam.setText(predictionModel.getMatchHometeamName());

        holder.hScore.setText(predictionModel.getMatchHometeamScore());

        holder.aTeam.setText(predictionModel.getMatchAwayteamName());

        holder.aScore.setText(predictionModel.getMatchAwayteamScore());

        holder.matchDate.setText(predictionModel.getMatchDate());


    }


    @Override
    public int getItemCount()throws NullPointerException {

        return predictionUtil.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        //VARIABLE PARA MABASA
        public TextView countryName;
        public TextView leagueName;
        public TextView hTeam;
        public TextView hScore;
        public TextView aTeam;
        public TextView aScore;
        public TextView matchDate;



        public ViewHolder(View itemView) {
            super(itemView);

            countryName= itemView.findViewById(R.id.countryName_prediction);
            leagueName= itemView.findViewById(R.id.league_name_prediction);
            hTeam= itemView.findViewById(R.id.h_t_prediction);
            hScore= itemView.findViewById(R.id.h_s_prediction);
            aTeam= itemView.findViewById(R.id.a_t_prediction);
            aScore= itemView.findViewById(R.id.a_s_prediction);
            matchDate= itemView.findViewById(R.id.date_activity_prediction);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
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
import com.football.football_leagues.controller.StandingController;
import com.football.football_leagues.model.StandingModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandingAdapter extends RecyclerView.Adapter<StandingAdapter.ViewHolder> {
    private List<StandingModel> standingUtil;
    Context context;

    public StandingAdapter(StandingController context, List standingUtil) {
        this.context = (Context) context;
        this.standingUtil = standingUtil;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_standing, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final StandingModel standingList = standingUtil.get(position);

        Picasso.get().load(standingList.getTeamBadge()).into(holder.badge);
        holder.leagueName.setText(standingList.getLeagueName());

        holder.countryName.setText(standingList.getCountryName());
        holder.overallWin.setText(standingList.getOverallLeagueW());
        holder.overallLose.setText(standingList.getOverallLeagueL());


    }


    @Override
    public int getItemCount()throws NullPointerException {
        if(standingUtil == null){
            Toast.makeText(context.getApplicationContext(),"No data loaded...", Toast.LENGTH_SHORT).show();
        }

        return standingUtil.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        //VARIABLE PARA MABASA
        public TextView leagueName;
        public TextView countryName;
        public TextView overallWin;
        public TextView overallLose;
        public ImageView badge;


        public ViewHolder(View itemView) {
            super(itemView);

            leagueName = itemView.findViewById(R.id.league_name);
            countryName = itemView.findViewById(R.id.country_name);
            overallWin = itemView.findViewById(R.id.overall_win);
            overallLose = itemView.findViewById(R.id.overall_lose);

            badge = itemView.findViewById(R.id.badge);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}


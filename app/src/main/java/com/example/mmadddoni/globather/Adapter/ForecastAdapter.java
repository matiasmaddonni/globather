package com.example.mmadddoni.globather.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mmadddoni.globather.Activity.DescriptionActivity;
import com.example.mmadddoni.globather.Entity.City;
import com.example.mmadddoni.globather.Entity.Forecast;
import com.example.mmadddoni.globather.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {
    private Context context;
    private City city;
    private List<Forecast> forecastList;

    public ForecastAdapter(Context context, City city, List<Forecast> forecastList) {
        this.context = context;
        this.city = city;
        this.forecastList = forecastList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Forecast forecast = forecastList.get(position);
        holder.city.setText(city.getName());
        //Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:MM");
        String formatted = dateFormat.format(forecast.getTime()*1000);
        holder.date.setText(formatted);
        //Icon
        Picasso.with(context)
                .load("http://openweathermap.org/img/w/" + forecast.getWeather().get(0).getIcon() + ".png")
                .into(holder.placeholder);
        //Temp
        Double temp = forecast.getTemp().getValueDay() - 273.16;
        holder.temp.setText(new DecimalFormat("##.#").format(temp) + "°C");

        //onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("CITY", city);
                intent.putExtra("FORECAST", forecast);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView city;
        public TextView date;
        public ImageView placeholder;
        public TextView temp;

        public MyViewHolder(View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city);
            date = (TextView) itemView.findViewById(R.id.date);
            placeholder = (ImageView) itemView.findViewById(R.id.placeholder);
            temp = (TextView) itemView.findViewById(R.id.temp);
        }
    }
}

package com.example.myalarm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myalarm.R;
import com.example.myalarm.model.CityZone_Model;

import java.sql.Array;
import java.util.List;

public class InternationalTimeAdapter extends RecyclerView.Adapter<InternationalTimeAdapter.ViewHolder> {
    private Context context;
    private List<CityZone_Model> cityTimeList;

    public InternationalTimeAdapter(Context context, List<CityZone_Model> cityTimeList) {
        this.context = context;
        this.cityTimeList = cityTimeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gio_quoc_te, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityZone_Model cityTime = cityTimeList.get(position);
        String[] arr = cityTime.getZone().split("/");
        holder.tvCity.setText(arr[1]);
        holder.tvTime.setText(cityTime.getTime());
        // Assuming you want to set date or some other info in tvDate
        holder.tvDate.setText("Today");

        holder.itemView.setOnClickListener(v -> {
            String message = "City: " + cityTime.getTenThanhPho() +
                    ", Country: " + cityTime.getQuocGia() +
                    ", Timezone: " + cityTime.getZone() +
                    ", Current Time: " + cityTime.getTime();
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return cityTimeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity, tvTime, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}

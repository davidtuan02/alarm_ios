package com.example.myalarm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myalarm.R;
import com.example.myalarm.model.CityZone_Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityTimeAdapter extends BaseAdapter {
    private Context context;
    private List<Object> items; // Danh sách các mục, bao gồm cả tiêu đề và thành phố
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_CITY = 1;

    public CityTimeAdapter(Context context, List<CityZone_Model> cityZoneList) {
        this.context = context;
        items = new ArrayList<>();

        // Sắp xếp danh sách thành phố theo tên thành phố và quốc gia
        Collections.sort(cityZoneList, new Comparator<CityZone_Model>() {
            @Override
            public int compare(CityZone_Model city1, CityZone_Model city2) {
                return city1.getTenThanhPho().compareToIgnoreCase(city2.getTenThanhPho());
            }
        });

        // Thêm các tiêu đề và thành phố vào danh sách items
        String currentLetter = "";
        for (CityZone_Model city : cityZoneList) {
            String cityName = city.getTenThanhPho() + ", " + city.getQuocGia();
            String firstLetter = cityName.substring(0, 1).toUpperCase();

            // Thêm tiêu đề nếu chưa có
            if (!currentLetter.equals(firstLetter)) {
                currentLetter = firstLetter;
                items.add(currentLetter);
            }

            // Thêm thành phố vào danh sách
            items.add(city);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) instanceof String ? TYPE_TITLE : TYPE_CITY;
    }

    @Override
    public int getViewTypeCount() {
        return 2; // Số loại view: tiêu đề và thành phố
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int viewType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (viewType) {
                case TYPE_TITLE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_header_city, parent, false);
                    holder.titleTextView = convertView.findViewById(R.id.section_header);
                    break;
                case TYPE_CITY:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_gio_quoc_te, parent, false);
                    holder.cityNameTextView = convertView.findViewById(R.id.tvCity);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Hiển thị dữ liệu tương ứng với loại view
        switch (viewType) {
            case TYPE_TITLE:
                String title = (String) getItem(position);
                holder.titleTextView.setText(title);
                break;
            case TYPE_CITY:
                CityZone_Model cityZone = (CityZone_Model) getItem(position);
                String cityName = cityZone.getTenThanhPho() + ", " + cityZone.getQuocGia();
                holder.cityNameTextView.setText(cityName);

                // Set click listener to show Toast with city info
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = "City: " + cityZone.getTenThanhPho() +
                                ", Country: " + cityZone.getQuocGia() +
                                ", Timezone: " + cityZone.getZone();
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }

        return convertView;
    }

    public void setCurrentTime(String currentTime) {
        // Tìm thành phố cần cập nhật currentTime và gọi notifyDataSetChanged()
        for (Object item : items) {
            if (item instanceof CityZone_Model) {
                CityZone_Model cityZone = (CityZone_Model) item;
                // Kiểm tra và cập nhật dữ liệu cho thành phố thích hợp
                // Ví dụ: nếu cityZone có thông tin timezone thì cập nhật dữ liệu vào đây
            }
        }
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView cityNameTextView;
    }
}

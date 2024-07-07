package com.example.myalarm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myalarm.R;
import com.example.myalarm.activity.Selected_ItemClock_TimeActivity;
import com.example.myalarm.adapter.InternationalTimeAdapter;
import com.example.myalarm.model.CityZone_Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GioquocteFragment extends Fragment {

    private TextView tvAddTime;
    private List<CityZone_Model> cityZoneList = new ArrayList<>();
    private InternationalTimeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioquocte, container , false);
        return  view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvAddTime = view.findViewById(R.id.tv_AddTime);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new InternationalTimeAdapter(requireContext(), cityZoneList);
        recyclerView.setAdapter(adapter);

        tvAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), Selected_ItemClock_TimeActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null) {
            String currentTime = data.getStringExtra("currentTime");
            if (currentTime != null) {
//                Toast.makeText(requireContext(), "Current time: " + currentTime, Toast.LENGTH_LONG).show();

                try {
                    JSONObject jsonObject = new JSONObject(currentTime);
                    String time = jsonObject.getString("time");
                    String timeZone = jsonObject.getString("timeZone");

                    CityZone_Model cityZone = new CityZone_Model();
                    cityZone.setZone(timeZone);
                    cityZone.setTime(time);

                    cityZoneList.add(cityZone);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Error parsing JSON", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

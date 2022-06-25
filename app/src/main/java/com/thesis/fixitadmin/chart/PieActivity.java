package com.thesis.fixitadmin.chart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.fixitadmin.R;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {
    int[] colorClass = new int[]{Color.LTGRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.rgb(255, 58, 186)};

    int countEm = 0;
    int countElec = 0;
    int counthouse = 0;
    int countpublic = 0;
    int countroad = 0;


    ArrayList<PieEntry> dataVals = new ArrayList<>();
    ArrayList dataVals1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        PieChart pieChart = findViewById(R.id.pieChart);


        ///////////////////////////PieChart////////////////////////////////////////////////
        ////////////////////////PieData//////////////////////////////

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Posts");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        countEm += 1;
                    } else if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        countElec += 1;
                    } else if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        counthouse += 1;
                    } else if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        countpublic += 1;
                    } else if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        countroad += 1;
                    }
                }
                dataVals.add(new PieEntry(countEm));
                dataVals.add(new PieEntry(countElec));
                dataVals.add(new PieEntry(counthouse));
                dataVals.add(new PieEntry(countpublic));
                dataVals.add(new PieEntry(countroad));

                PieDataSet pieDataSet = new PieDataSet(dataVals, "FixiT");
                pieDataSet.setColors(colorClass);
                pieDataSet.setValueTextColor(Color.BLACK);
                pieDataSet.setValueTextSize(16f);

                PieData pieData = new PieData(pieDataSet);

                pieChart.setData(pieData);
                pieChart.getDescription().setEnabled(false);
                pieChart.setCenterText("Total Reports Submitted");
                pieChart.animate();
                pieChart.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);


    }
}
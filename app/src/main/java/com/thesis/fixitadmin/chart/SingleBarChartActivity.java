package com.thesis.fixitadmin.chart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.fixitadmin.R;

import java.util.ArrayList;
import java.util.Date;

public class SingleBarChartActivity extends AppCompatActivity {
    int EjuncountEm1, ELjuncountEm1, PjuncountEm1, HjuncountEm1, RjuncountEm1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_bar_chart);

        ///////////////////BarChart////////////////////////////////////////////////////
        barDatas();

    }

    public void barDatas() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Posts");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pTime;
                Date d;
                String[] splitsdate;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    pTime = ds.child("pTime").getValue().toString();
                    d = new Date(Long.valueOf(pTime));
                    splitsdate = d.toString().split(" ");
                    //Jun//////////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Jun")) {
                            EjuncountEm1 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Jun")) {
                            ELjuncountEm1 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Jun")) {
                            PjuncountEm1 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Jun")) {
                            HjuncountEm1 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Jun")) {
                            RjuncountEm1 += 1;
                        }
                    }
                }

                BarChart barChart = findViewById(R.id.barChart);

                barChart.setDrawBarShadow(false);
                barChart.getDescription().setEnabled(false);
                barChart.setPinchZoom(false);
                barChart.setDrawGridBackground(false);
                // empty labels so that the names are spread evenly
                String[] label = {" ","Report this June", " "};
                XAxis xAxis = barChart.getXAxis();
                xAxis.setCenterAxisLabels(true);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(true);
//                xAxis.setGranularity(1f); // only intervals of 1 day
                xAxis.setTextColor(Color.BLACK);
                xAxis.setTextSize(10);
                xAxis.setAxisLineColor(Color.WHITE);
                xAxis.setAxisMinimum(1f);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(label));

                YAxis leftAxis = barChart.getAxisLeft();
                leftAxis.setTextColor(Color.BLACK);
                leftAxis.setTextSize(6);
                leftAxis.setAxisLineColor(Color.WHITE);
                leftAxis.setDrawGridLines(true);
                leftAxis.setGranularity(2);
                leftAxis.setLabelCount(1, true);
                leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

                barChart.getAxisRight().setEnabled(true);
                barChart.getLegend().setEnabled(false);

                int[] valOne1 = {EjuncountEm1, 0};
                int[] valTwo1 = {ELjuncountEm1, 0};
                int[] valThree1 = {PjuncountEm1, 0};
                int[] valFour1 = {HjuncountEm1, 0};
                int[] valFive1 = {RjuncountEm1, 0};

                ArrayList<BarEntry> barOne1 = new ArrayList<>();
                ArrayList<BarEntry> barTwo1 = new ArrayList<>();
                ArrayList<BarEntry> barThree1 = new ArrayList<>();
                ArrayList<BarEntry> barFour1 = new ArrayList<>();
                ArrayList<BarEntry> barFive1 = new ArrayList<>();


                for (int i = 0; i < valOne1.length; i++) {
                    barOne1.add(new BarEntry(i, valOne1[i]));
                    barTwo1.add(new BarEntry(i, valTwo1[i]));
                    barThree1.add(new BarEntry(i, valThree1[i]));
                    barFour1.add(new BarEntry(i, valFour1[i]));
                    barFive1.add(new BarEntry(i, valFive1[i]));
                }

                BarDataSet set1 = new BarDataSet(barOne1, "barOne");
                set1.setColor(Color.LTGRAY);
                BarDataSet set2 = new BarDataSet(barTwo1, "barTwo");
                set2.setColor(Color.BLUE);
                BarDataSet set3 = new BarDataSet(barThree1, "barThree");
                set3.setColor(Color.CYAN);
                BarDataSet set4 = new BarDataSet(barFour1, "barFour");
                set4.setColor(Color.DKGRAY);
                BarDataSet set5 = new BarDataSet(barFive1, "barFive");
                set5.setColor(Color.rgb(255, 58, 186));

                set1.setHighlightEnabled(false);
                set2.setHighlightEnabled(false);
                set3.setHighlightEnabled(false);
                set4.setHighlightEnabled(false);
                set5.setHighlightEnabled(false);
                set1.setDrawValues(false);
                set2.setDrawValues(false);
                set3.setDrawValues(false);
                set4.setDrawValues(false);
                set5.setDrawValues(false);

                ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                dataSets.add(set2);
                dataSets.add(set3);
                dataSets.add(set4);
                dataSets.add(set5);
                BarData data = new BarData(dataSets);
                float groupSpace = 0.4f;
                float barSpace = 0f;
                float barWidth = 0.100000001f;
                // (barSpace + barWidth) * 2 + groupSpace = 1
                data.setBarWidth(barWidth);
                // so that the entire chart is shown when scrolled from right to left
                xAxis.setAxisMaximum(1);
                barChart.setData(data);
                barChart.setScaleEnabled(true);
                barChart.setVisibleXRangeMaximum(6f);
                barChart.groupBars(0f, groupSpace, barSpace);
                barChart.animateY(2000);
                barChart.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);
    }

}
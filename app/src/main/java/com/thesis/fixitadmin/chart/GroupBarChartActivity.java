package com.thesis.fixitadmin.chart;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
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

public class GroupBarChartActivity extends AppCompatActivity {
    BarChart mChart;

    //Group
    //Emergency
    int EjancountEm, EfebcountEm, EmarcountEm, EaprcountEm, EmaycountEm, EjuncountEm, EjulcountEm, EaugcountEm, EsepcountEm, EoctcountEm, EnovcountEm, EdeccountEm = 0;
    //Electric Concerns
    int ELjancountEm, ELfebcountEm, ELmarcountEm, ELaprcountEm, ELmaycountEm, ELjuncountEm, ELjulcountEm, ELaugcountEm, ELsepcountEm, ELoctcountEm, ELnovcountEm, ELdeccountEm = 0;
    //Public Incidents
    int PjancountEm, PfebcountEm, PmarcountEm, PaprcountEm, PmaycountEm, PjuncountEm, PjulcountEm, PaugcountEm, PsepcountEm, PoctcountEm, PnovcountEm, PdeccountEm = 0;
    //Household Concerns
    int HjancountEm, HfebcountEm, HmarcountEm, HaprcountEm, HmaycountEm, HjuncountEm, HjulcountEm, HaugcountEm, HsepcountEm, HoctcountEm, HnovcountEm, HdeccountEm = 0;
    //Road Complaints
    int RjancountEm, RfebcountEm, RmarcountEm, RaprcountEm, RmaycountEm, RjuncountEm, RjulcountEm, RaugcountEm, RsepcountEm, RoctcountEm, RnovcountEm, RdeccountEm = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_bar_chart);

        mChart = findViewById(R.id.GroupbarChart);
        GroupBarChart();

    }

    public void GroupBarChart() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Posts");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pTime;
                Date d;
                String[] splitsdate;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    pTime = ds.child("pTime").getValue().toString();
                    d = new Date(Long.valueOf(pTime));
                    splitsdate = d.toString().split(" ");
                    //Jan//////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Jan")) {
                            EjancountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Jan")) {
                            ELjancountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Jan")) {
                            PjancountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Jan")) {
                            HjancountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Jan")) {
                            RjancountEm += 1;
                        }
                    }
                    //Feb////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Feb")) {
                            EfebcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Feb")) {
                            ELfebcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Feb")) {
                            PfebcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Feb")) {
                            HfebcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Feb")) {
                            RfebcountEm += 1;
                        }
                    }
                    //Mar/////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Mar")) {
                            EmarcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Mar")) {
                            ELmarcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Mar")) {
                            PmarcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Mar")) {
                            HmarcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Mar")) {
                            RmarcountEm += 1;
                        }
                    }
                    //Apr///////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Apr")) {
                            EaprcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Apr")) {
                            ELaprcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Apr")) {
                            PaprcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Apr")) {
                            HaprcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Apr")) {
                            RaprcountEm += 1;
                        }
                    }
                    //May//////////////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("May")) {
                            EmaycountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("May")) {
                            ELmaycountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("May")) {
                            PmaycountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("May")) {
                            HmaycountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("May")) {
                            RmaycountEm += 1;
                        }
                    }
                    //Jun//////////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Jun")) {
                            EjuncountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Jun")) {
                            ELjuncountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Jun")) {
                            PjuncountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Jun")) {
                            HjuncountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Jun")) {
                            RjuncountEm += 1;
                        }
                    }
                    //Jul////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Jul")) {
                            EjulcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Jul")) {
                            ELjulcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Jul")) {
                            PjulcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Jul")) {
                            HjulcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Jul")) {
                            RjulcountEm += 1;
                        }
                    }
                    //Aug//////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Aug")) {
                            EaugcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Aug")) {
                            ELaugcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Aug")) {
                            PaugcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Aug")) {
                            HaugcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Aug")) {
                            RaugcountEm += 1;
                        }
                    }
                    //Sep///////////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Sep")) {
                            EsepcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Sep")) {
                            ELsepcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Sep")) {
                            PsepcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Sep")) {
                            HsepcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Sep")) {
                            RsepcountEm += 1;
                        }
                    }
                    //Oct////////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Oct")) {
                            EoctcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Oct")) {
                            ELoctcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Oct")) {
                            PoctcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Oct")) {
                            HoctcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Oct")) {
                            RoctcountEm += 1;
                        }
                    }
                    //Nov////////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Nov")) {
                            EnovcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Nov")) {
                            ELnovcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Nov")) {
                            PnovcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Nov")) {
                            HnovcountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Nov")) {
                            RnovcountEm += 1;
                        }
                    }
                    //Dec////////////////////////////////////////////////////////////////////////////////////////
                    if (ds.child("type").getValue(String.class).equals("Emergency")) {
                        if (splitsdate[1].equals("Dec")) {
                            EdeccountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if (splitsdate[1].equals("Dec")) {
                            ELdeccountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Public Incidents")) {
                        if (splitsdate[1].equals("Dec")) {
                            PdeccountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Household Concerns")) {
                        if (splitsdate[1].equals("Dec")) {
                            HdeccountEm += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Road Complaints")) {
                        if (splitsdate[1].equals("Dec")) {
                            RdeccountEm += 1;
                        }
                    }
                }



                mChart.setDrawBarShadow(false);
                mChart.getDescription().setEnabled(false);
                mChart.setPinchZoom(false);
                mChart.setDrawGridBackground(true);
                // empty labels so that the names are spread evenly
                String[] labels = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", ""};
                XAxis xAxis = mChart.getXAxis();
                xAxis.setCenterAxisLabels(true);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(true);
//                xAxis.setGranularity(1f); // only intervals of 1 day
                xAxis.setTextColor(Color.BLACK);
                xAxis.setTextSize(10);
                xAxis.setAxisLineColor(Color.WHITE);
                xAxis.setAxisMinimum(1f);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

                YAxis leftAxis = mChart.getAxisLeft();
                leftAxis.setTextColor(Color.BLACK);
                leftAxis.setTextSize(6);
                leftAxis.setAxisLineColor(Color.WHITE);
                leftAxis.setDrawGridLines(true);
                leftAxis.setGranularity(2);
                leftAxis.setLabelCount(12, true);
                leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
                leftAxis.setAxisMinimum(0f);
                leftAxis.setAxisMaximum(100f);

                mChart.getAxisLeft().setEnabled(true);
                mChart.getAxisRight().setEnabled(false);
                mChart.getLegend().setEnabled(false);

                int[] valOne = {EjancountEm, EfebcountEm, EmarcountEm, EaprcountEm, EmaycountEm, EjuncountEm, EjulcountEm, EaugcountEm, EsepcountEm, EoctcountEm, EnovcountEm, EdeccountEm};
                int[] valTwo = {ELjancountEm, ELfebcountEm, ELmarcountEm, ELaprcountEm, ELmaycountEm, ELjuncountEm, ELjulcountEm, ELaugcountEm, ELsepcountEm, ELoctcountEm, ELnovcountEm, ELdeccountEm};
                int[] valThree = {PjancountEm, PfebcountEm, PmarcountEm, PaprcountEm, PmaycountEm, PjuncountEm, PjulcountEm, PaugcountEm, PsepcountEm, PoctcountEm, PnovcountEm, PdeccountEm};
                int[] valFour = {HjancountEm, HfebcountEm, HmarcountEm, HaprcountEm, HmaycountEm, HjuncountEm, HjulcountEm, HaugcountEm, HsepcountEm, HoctcountEm, HnovcountEm, HdeccountEm};
                int[] valFive = {RjancountEm, RfebcountEm, RmarcountEm, RaprcountEm, RmaycountEm, RjuncountEm, RjulcountEm, RaugcountEm, RsepcountEm, RoctcountEm, RnovcountEm, RdeccountEm};
                long postsum = snapshot.getChildrenCount();
                float[] sumpost = {
                        EjancountEm + ELjancountEm + PjancountEm + HjancountEm + RjancountEm,
                        EfebcountEm + ELfebcountEm + PfebcountEm + HfebcountEm + RfebcountEm,
                        EmarcountEm + ELmarcountEm + PmarcountEm + HmarcountEm + RmarcountEm,
                        EaprcountEm + ELaprcountEm + PaprcountEm + HaprcountEm + RaprcountEm,
                        EmaycountEm + ELmaycountEm + PmaycountEm + HmaycountEm + RmaycountEm,
                        EjuncountEm + ELjuncountEm + PjuncountEm + HjuncountEm + RjuncountEm,
                        EjulcountEm + ELjulcountEm + PjulcountEm + HjulcountEm + RjulcountEm,
                        EaugcountEm + ELaugcountEm + PaugcountEm + HaugcountEm + RaugcountEm,
                        EsepcountEm + ELsepcountEm + PsepcountEm + HsepcountEm + RsepcountEm,
                        EoctcountEm + ELoctcountEm + PoctcountEm + HoctcountEm + RoctcountEm,
                        EnovcountEm + ELnovcountEm + PnovcountEm + HnovcountEm + RnovcountEm,
                        EdeccountEm + ELdeccountEm + PdeccountEm + HdeccountEm + RdeccountEm
                };
//
                float sumapr = EaprcountEm + ELaprcountEm + PaprcountEm + HaprcountEm + RaprcountEm;

                ArrayList<BarEntry> barOne = new ArrayList<>();
                ArrayList<BarEntry> barTwo = new ArrayList<>();
                ArrayList<BarEntry> barThree = new ArrayList<>();
                ArrayList<BarEntry> barFour = new ArrayList<>();
                ArrayList<BarEntry> barFive = new ArrayList<>();
                //0
                barOne.add(new BarEntry(0, (valOne[0] / sumpost[0]) * 100, '%'));
                barTwo.add(new BarEntry(0, (valTwo[0] / sumpost[0]) * 100));
                barThree.add(new BarEntry(0, (valThree[0] / sumpost[0]) * 100));
                barFour.add(new BarEntry(0, (valFour[0] / sumpost[0]) * 100));
                barFive.add(new BarEntry(0, (valFive[0] / sumpost[0]) * 100));
                //1
                barOne.add(new BarEntry(1, (valOne[1] / sumpost[1]) * 100));
                barTwo.add(new BarEntry(1, (valTwo[1] / sumpost[1]) * 100));
                barThree.add(new BarEntry(1, (valThree[1] / sumpost[1]) * 100));
                barFour.add(new BarEntry(1, (valFour[1] / sumpost[1]) * 100));
                barFive.add(new BarEntry(1, (valFive[1] / sumpost[1]) * 100));
                //2
                barOne.add(new BarEntry(2, (valOne[2] / sumpost[2]) * 100));
                barTwo.add(new BarEntry(2, (valTwo[2] / sumpost[2]) * 100));
                barThree.add(new BarEntry(2, (valThree[2] / sumpost[2]) * 100));
                barFour.add(new BarEntry(2, (valFour[2] / sumpost[2]) * 100));
                barFive.add(new BarEntry(2, (valFive[2] / sumpost[2]) * 100));
                //3
                barOne.add(new BarEntry(3, (valOne[3] / sumpost[3]) * 100));
                barTwo.add(new BarEntry(3, (valTwo[3] / sumpost[3]) * 100));
                barThree.add(new BarEntry(3, (valThree[3] / sumpost[3]) * 100));
                barFour.add(new BarEntry(3, (valFour[3] / sumpost[3]) * 100));
                barFive.add(new BarEntry(3, (valFive[3] / sumpost[3]) * 100));
                //4
                barOne.add(new BarEntry(4, (valOne[4] / sumpost[4]) * 100));
                barTwo.add(new BarEntry(4, (valTwo[4] / sumpost[4]) * 100));
                barThree.add(new BarEntry(4, (valThree[4] / sumpost[4]) * 100));
                barFour.add(new BarEntry(4, (valFour[4] / sumpost[4]) * 100));
                barFive.add(new BarEntry(4, (valFive[4] / sumpost[4]) * 100));
                //5
                barOne.add(new BarEntry(5, (valOne[5] / sumpost[5]) * 100));
                barTwo.add(new BarEntry(5, (valTwo[5] / sumpost[5]) * 100));
                barThree.add(new BarEntry(5, (valThree[5] / sumpost[5]) * 100));
                barFour.add(new BarEntry(5, (valFour[5] / sumpost[5]) * 100));
                barFive.add(new BarEntry(5, (valFive[5] / sumpost[5]) * 100));


//                for (int i = 0; i < valOne.length; i++) {
//
//                    barOne.add(new BarEntry(i, (valOne[i] / sumpost[3]) * 100));
//                    barTwo.add(new BarEntry(i, valTwo[i]));
//                    barThree.add(new BarEntry(i, valThree[i]));
//                    barFour.add(new BarEntry(i, valFour[i]));
//                    barFive.add(new BarEntry(i, valFive[i]));
//                }



                BarDataSet set1 = new BarDataSet(barOne, "%");
                set1.setColor(Color.LTGRAY);
                BarDataSet set2 = new BarDataSet(barTwo, "barTwo");
                set2.setColor(Color.BLUE);
                BarDataSet set3 = new BarDataSet(barThree, "barThree");
                set3.setColor(Color.CYAN);
                BarDataSet set4 = new BarDataSet(barFour, "barFour");
                set4.setColor(Color.DKGRAY);
                BarDataSet set5 = new BarDataSet(barFive, "barFive");
                set5.setColor(Color.rgb(255, 58, 186));

                set1.setHighlightEnabled(true);
                set2.setHighlightEnabled(true);
                set3.setHighlightEnabled(true);
                set4.setHighlightEnabled(true);
                set5.setHighlightEnabled(true);
                set1.setDrawValues(true);
                set2.setDrawValues(true);
                set3.setDrawValues(true);
                set4.setDrawValues(true);
                set5.setDrawValues(true);

                ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                dataSets.add(set2);
                dataSets.add(set3);
                dataSets.add(set4);
                dataSets.add(set5);

                BarData data = new BarData(dataSets);
                data.setValueTextSize(6f);
                float groupSpace = 0.4f;
                float barSpace = 0f;
                float barWidth = 0.122222222222222222222200000001f;

                // (barSpace + barWidth) * 2 + groupSpace = 1
                data.setBarWidth(0.15f);
                // so that the entire chart is shown when scrolled from right to left
                xAxis.setAxisMaximum(labels.length - 1.1f);
                mChart.setData(data);
                mChart.setScaleEnabled(true);
                mChart.setVisibleXRangeMaximum(6f);
                mChart.groupBars(1f, groupSpace, barSpace);
                mChart.animateY(2000);
                mChart.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);
    }


}
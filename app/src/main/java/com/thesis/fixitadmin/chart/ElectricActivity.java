package com.thesis.fixitadmin.chart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.StringValue;
import com.thesis.fixitadmin.R;
import com.thesis.fixitadmin.activity_analytics;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ElectricActivity extends AppCompatActivity {
    LineChart lineChart, lineChart1;
    String[] legendName = {"Electric Complaints", "Dog", "Cat", "Rat"};
    int[] colorArray = new int[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA};
    List<String> days = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
    List<String> week = Arrays.asList("Week 1", "Week 2", "Week 3", "Week 4");
    List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

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

    //Day Data
    int ElcountMon, ElcountTue, ElcountWed, ElcountThu, ElcountFri, ElcountSat, ElcountSun = 1;

    //Week data
    int Elcount1, Elcount2, Elcount3, Elcount4, Elcount5, Elcount6, Elcount7 = 1;
    int Elcount8, Elcount9, Elcount10, Elcount11, Elcount12, Elcount13, Elcount14 = 1;
    int Elcount15, Elcount16, Elcount17, Elcount18, Elcount19, Elcount20, Elcount21 = 1;
    int Elcount22, Elcount23, Elcount24, Elcount25, Elcount26, Elcount27, Elcount28, Elcount29, Elcount30, Elcount31 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);

        ImageView backtoA = findViewById(R.id.backtoA);
        backtoA.setOnClickListener(v -> {
            Intent intent = new Intent(this, activity_analytics.class);
            startActivity(intent);
        });

        Button btn = findViewById(R.id.print);
        btn.setOnClickListener(v -> {

            View view = getWindow().getDecorView().findViewById(android.R.id.content);
            view.setDrawingCacheEnabled(true);
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(true);

            PrintHelper photoPrinter = new PrintHelper(this); // Asume that 'this' is your activity
            photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
            photoPrinter.printBitmap("print", bitmap);
        });

        ///MONTHLY////////////////////////////////////////////////////////////////////////////////////////////

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

                lineChart1 = findViewById(R.id.lineChartEl);
                LineDataSet lineDataSet2 = new LineDataSet(dataValues2(), "Data 1");
//                LineDataSet lineDataSet2 = new LineDataSet(barTwo, "Data 2");

                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet2);
//                dataSets.add(lineDataSet2);

                lineChart1.setPinchZoom(true);
                lineChart1.setDrawGridBackground(true);


                lineChart1.setNoDataText("No Data");
                lineChart1.setNoDataTextColor(Color.BLUE);

                lineChart1.setDrawGridBackground(false);
                lineChart1.setDrawBorders(true);
                lineChart1.setBorderColor(Color.BLACK);
                lineChart1.setBorderWidth(2);

                lineDataSet2.setLineWidth(1);
                lineDataSet2.setColor(Color.RED);
                lineDataSet2.setDrawCircles(true);
                lineDataSet2.setDrawCircleHole(true);
                lineDataSet2.setCircleColor(Color.GRAY);
                lineDataSet2.setCircleColorHole(Color.GREEN);
                lineDataSet2.setCircleRadius(2);
                lineDataSet2.setCircleHoleRadius(2);

                lineDataSet2.setValueTextSize(8);
                lineDataSet2.setValueTextColor(Color.BLUE);
//        lineDataSet1.enableDashedLine(5, 10, 0);
//        lineDataSet1.setColors(colorArray, LineGraphActivity.this);

//        lineDataSet2.setLineWidth(4);
//                lineDataSet2.setColor(Color.RED);
//                lineDataSet2.setDrawCircles(true);
//                lineDataSet2.setDrawCircleHole(true);
//                lineDataSet2.setCircleColor(Color.YELLOW);
//                lineDataSet2.setCircleColorHole(Color.BLUE);
//                lineDataSet2.setCircleRadius(2);
//                lineDataSet2.setCircleHoleRadius(2);
//
//                lineDataSet2.setValueTextSize(4);
//                lineDataSet2.setValueTextColor(Color.BLUE);

                Legend legend = lineChart1.getLegend();

                legend.setEnabled(false);
                legend.setTextColor(Color.RED);
                legend.setTextSize(15);
                legend.setForm(Legend.LegendForm.LINE);
                legend.setFormSize(20);
                legend.setXEntrySpace(15);
                legend.setFormToTextSpace(10);

                LegendEntry[] legendEntries = new LegendEntry[1];

                for (int ichart = 0; ichart < legendEntries.length; ichart++) {
                    LegendEntry entry = new LegendEntry();
                    entry.formColor = colorArray[ichart];
                    entry.label = String.valueOf(legendName[ichart]);
                    legendEntries[ichart] = entry;
                }

                legend.setCustom(legendEntries);

                XAxis xAxis = lineChart1.getXAxis();
                xAxis.setGranularity(0);

                YAxis yAxisLeft = lineChart1.getAxisLeft();
                yAxisLeft.setGranularity(1);

                YAxis yAxisRight = lineChart1.getAxisRight();
                yAxisRight.setEnabled(false);
                yAxisLeft.setEnabled(true);
                yAxisLeft.setLabelCount(12, true);
                yAxisLeft.setAxisMaximum(100);
                yAxisLeft.setAxisMinimum(1);


                xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
//        yAxisLeft.setValueFormatter(new MyAxisValueFormatter());

                yAxisLeft.setCenterAxisLabels(true);

                Description description1 = new Description();
                description1.setText("Yearly Data");
                description1.setTextColor(Color.BLUE);
                description1.setTextSize(10);
                lineChart1.setDescription(description1);

                LineData data1 = new LineData(dataSets);
                xAxis.setAxisMaximum(12);
                lineChart1.setScaleEnabled(true);

                data1.setValueFormatter(new ElectricActivity.MyValueFormatter());
                lineChart1.setData(data1);
                lineChart1.invalidate();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);


        ///DAILY////////////////////////////////////////////////////////////////////////////////////////////

        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Posts");
        ValueEventListener valueEventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pTime;
                Date d;
                String[] splitsdate;


                for (DataSnapshot ds : snapshot.getChildren()) {
                    pTime = ds.child("pTime").getValue().toString();
                    d = new Date(Long.valueOf(pTime));
                    splitsdate = d.toString().split(" ");

                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[0].equals("Mon")){
                            ElcountMon += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[0].equals("Tue")){
                            ElcountTue += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[0].equals("Wed")){
                            ElcountWed += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[0].equals("Thu")){
                            ElcountThu += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[0].equals("Fri")){
                            ElcountFri += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[0].equals("Sat")){
                            ElcountSat += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[0].equals("Sun")){
                            ElcountSun += 1;
                        }
                    }
                }

                lineChart1 = findViewById(R.id.lineChartEl2);
                LineDataSet lineDataSet2 = new LineDataSet(dataValues3(), "Data 1");
//                LineDataSet lineDataSet2 = new LineDataSet(barTwo, "Data 2");

                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet2);
//                dataSets.add(lineDataSet2);

                lineChart1.setPinchZoom(true);
                lineChart1.setDrawGridBackground(true);


                lineChart1.setNoDataText("No Data");
                lineChart1.setNoDataTextColor(Color.BLUE);

                lineChart1.setDrawGridBackground(false);
                lineChart1.setDrawBorders(true);
                lineChart1.setBorderColor(Color.BLACK);
                lineChart1.setBorderWidth(2);

                lineDataSet2.setLineWidth(1);
                lineDataSet2.setColor(Color.RED);
                lineDataSet2.setDrawCircles(true);
                lineDataSet2.setDrawCircleHole(true);
                lineDataSet2.setCircleColor(Color.GRAY);
                lineDataSet2.setCircleColorHole(Color.GREEN);
                lineDataSet2.setCircleRadius(2);
                lineDataSet2.setCircleHoleRadius(2);

                //Font sa loob ng graph yung may %
                lineDataSet2.setValueTextSize(8);
                lineDataSet2.setValueTextColor(Color.BLUE);
//        lineDataSet1.enableDashedLine(5, 10, 0);
//        lineDataSet1.setColors(colorArray, LineGraphActivity.this);

//        lineDataSet2.setLineWidth(4);
//                lineDataSet2.setColor(Color.RED);
//                lineDataSet2.setDrawCircles(true);
//                lineDataSet2.setDrawCircleHole(true);
//                lineDataSet2.setCircleColor(Color.YELLOW);
//                lineDataSet2.setCircleColorHole(Color.BLUE);
//                lineDataSet2.setCircleRadius(2);
//                lineDataSet2.setCircleHoleRadius(2);
//
//                lineDataSet2.setValueTextSize(4);
//                lineDataSet2.setValueTextColor(Color.BLUE);

                Legend legend = lineChart1.getLegend();

                legend.setEnabled(false);
                legend.setTextColor(Color.RED);
                legend.setTextSize(15);
                legend.setForm(Legend.LegendForm.LINE);
                legend.setFormSize(20);
                legend.setXEntrySpace(15);
                legend.setFormToTextSpace(10);

                LegendEntry[] legendEntries = new LegendEntry[1];

                for (int ichart = 0; ichart < legendEntries.length; ichart++) {
                    LegendEntry entry = new LegendEntry();
                    entry.formColor = colorArray[ichart];
                    entry.label = String.valueOf(legendName[ichart]);
                    legendEntries[ichart] = entry;
                }

                legend.setCustom(legendEntries);

                XAxis xAxis = lineChart1.getXAxis();
                xAxis.setGranularity(1);

                YAxis yAxisLeft = lineChart1.getAxisLeft();
                yAxisLeft.setGranularity(2);

                YAxis yAxisRight = lineChart1.getAxisRight();
                yAxisRight.setEnabled(false);
                yAxisLeft.setEnabled(true);
                yAxisLeft.setLabelCount(8, true);
                yAxisLeft.setAxisMaximum(50);
                yAxisLeft.setAxisMinimum(1);


                xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
//        yAxisLeft.setValueFormatter(new MyAxisValueFormatter());

                yAxisLeft.setCenterAxisLabels(true);

                Description description1 = new Description();
                description1.setText("Daily Data");
                description1.setTextColor(Color.BLUE);
                description1.setTextSize(10);
                lineChart1.setDescription(description1);

                LineData data1 = new LineData(dataSets);
                xAxis.setAxisMaximum(7);
                lineChart1.setScaleEnabled(true);

                data1.setValueFormatter(new ElectricActivity.MyValueFormatter());
                lineChart1.setData(data1);
                lineChart1.invalidate();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        };
        ref1.addListenerForSingleValueEvent(valueEventListener1);



        ///WEEK////////////////////////////////////////////////////////////////////////////////////////////

        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Posts");
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pTime;
                Date d;
                String[] splitsdate;


                for (DataSnapshot ds : snapshot.getChildren()) {
                    pTime = ds.child("pTime").getValue().toString();
                    d = new Date(Long.valueOf(pTime));
                    splitsdate = d.toString().split(" ");

//                    int data2 = Elcount8 + Elcount9 + Elcount10 + Elcount11 + Elcount12 + Elcount13 + Elcount14;
//                    TextView text = findViewById(R.id.textView);
//                    text.setText(String.valueOf(data2));

                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("1")){
                            Elcount1 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("2")){
                            Elcount2 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("3")){
                            Elcount3 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("4")){
                            Elcount4 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("5")){
                            Elcount5 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("6")){
                            Elcount6 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("7")){
                            Elcount7 += 1;
                        }
                    }
                    //Week2
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("8")){
                            Elcount8 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("9")){
                            Elcount9 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("10")){
                            Elcount10 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("11")){
                            Elcount11 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("12")){
                            Elcount12 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("13")){
                            Elcount13 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("14")){
                            Elcount14 += 1;
                        }
                    }

                    //Week 3
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("15")){
                            Elcount15 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("16")){
                            Elcount16 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("17")){
                            Elcount17 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("18")){
                            Elcount18 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("19")){
                            Elcount19 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("20")){
                            Elcount20 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("21")){
                            Elcount21 += 1;
                        }
                    }

                    //Week 3
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("22")){
                            Elcount22 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("23")){
                            Elcount23 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("24")){
                            Elcount24 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("25")){
                            Elcount25 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("26")){
                            Elcount26 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("27")){
                            Elcount27 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("28")){
                            Elcount28 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("29")){
                            Elcount29 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("30")){
                            Elcount30 += 1;
                        }
                    }
                    if (ds.child("type").getValue(String.class).equals("Electrical Complaints")) {
                        if(splitsdate[3].equals("31")){
                            Elcount31 += 1;
                        }
                    }
                }

                lineChart1 = findViewById(R.id.lineChartEl1);
                LineDataSet lineDataSet3 = new LineDataSet(dataValues4(), "Data 1");
//                LineDataSet lineDataSet2 = new LineDataSet(barTwo, "Data 2");

                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet3);
//                dataSets.add(lineDataSet2);

                lineChart1.setPinchZoom(true);
                lineChart1.setDrawGridBackground(true);


                lineChart1.setNoDataText("No Data");
                lineChart1.setNoDataTextColor(Color.BLUE);

                lineChart1.setDrawGridBackground(false);
                lineChart1.setDrawBorders(true);
                lineChart1.setBorderColor(Color.BLACK);
                lineChart1.setBorderWidth(2);

                lineDataSet3.setLineWidth(1);
                lineDataSet3.setColor(Color.RED);
                lineDataSet3.setDrawCircles(true);
                lineDataSet3.setDrawCircleHole(true);
                lineDataSet3.setCircleColor(Color.GRAY);
                lineDataSet3.setCircleColorHole(Color.GREEN);
                lineDataSet3.setCircleRadius(2);
                lineDataSet3.setCircleHoleRadius(2);

                //Font sa loob ng graph yung may %
                lineDataSet3.setValueTextSize(10);
                lineDataSet3.setValueTextColor(Color.BLUE);
//        lineDataSet1.enableDashedLine(5, 10, 0);
//        lineDataSet1.setColors(colorArray, LineGraphActivity.this);

//        lineDataSet2.setLineWidth(4);
//                lineDataSet2.setColor(Color.RED);
//                lineDataSet2.setDrawCircles(true);
//                lineDataSet2.setDrawCircleHole(true);
//                lineDataSet2.setCircleColor(Color.YELLOW);
//                lineDataSet2.setCircleColorHole(Color.BLUE);
//                lineDataSet2.setCircleRadius(2);
//                lineDataSet2.setCircleHoleRadius(2);
//
//                lineDataSet2.setValueTextSize(4);
//                lineDataSet2.setValueTextColor(Color.BLUE);

                Legend legend = lineChart1.getLegend();

                legend.setEnabled(false);
                legend.setTextColor(Color.RED);
                legend.setTextSize(15);
                legend.setForm(Legend.LegendForm.LINE);
                legend.setFormSize(20);
                legend.setXEntrySpace(15);
                legend.setFormToTextSpace(10);

                LegendEntry[] legendEntries = new LegendEntry[1];

                for (int ichart = 0; ichart < legendEntries.length; ichart++) {
                    LegendEntry entry = new LegendEntry();
                    entry.formColor = colorArray[ichart];
                    entry.label = String.valueOf(legendName[ichart]);
                    legendEntries[ichart] = entry;
                }

                legend.setCustom(legendEntries);

                XAxis xAxis = lineChart1.getXAxis();
                xAxis.setGranularity(1f);

                YAxis yAxisLeft = lineChart1.getAxisLeft();
                yAxisLeft.setGranularity(2);

                YAxis yAxisRight = lineChart1.getAxisRight();
                yAxisRight.setEnabled(false);
                yAxisLeft.setEnabled(true);
                yAxisLeft.setLabelCount(7, true);
                yAxisLeft.setAxisMaximum(50);
                yAxisLeft.setAxisMinimum(1);


                xAxis.setValueFormatter(new IndexAxisValueFormatter(week));
//        yAxisLeft.setValueFormatter(new MyAxisValueFormatter());

                yAxisLeft.setCenterAxisLabels(true);

                Description description1 = new Description();
                description1.setText("Weekly Data");
                description1.setTextColor(Color.BLUE);
                description1.setTextSize(10);
                lineChart1.setDescription(description1);

                LineData data1 = new LineData(dataSets);
                xAxis.setAxisMaximum(7);
                lineChart1.setScaleEnabled(true);

                data1.setValueFormatter(new ElectricActivity.MyValueFormatter());
                lineChart1.setData(data1);
                lineChart1.invalidate();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        };
        ref2.addListenerForSingleValueEvent(valueEventListener2);


    }

    //Year
    private ArrayList<Entry> dataValues2(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        int[] valOne = {EjancountEm, EfebcountEm, EmarcountEm, EaprcountEm, EmaycountEm, EjuncountEm, EjulcountEm, EaugcountEm, EsepcountEm, EoctcountEm, EnovcountEm, EdeccountEm};
        int[] valTwo = {ELjancountEm, ELfebcountEm, ELmarcountEm, ELaprcountEm, ELmaycountEm, ELjuncountEm, ELjulcountEm, ELaugcountEm, ELsepcountEm, ELoctcountEm, ELnovcountEm, ELdeccountEm};
        int[] valThree = {PjancountEm, PfebcountEm, PmarcountEm, PaprcountEm, PmaycountEm, PjuncountEm, PjulcountEm, PaugcountEm, PsepcountEm, PoctcountEm, PnovcountEm, PdeccountEm};
        int[] valFour = {HjancountEm, HfebcountEm, HmarcountEm, HaprcountEm, HmaycountEm, HjuncountEm, HjulcountEm, HaugcountEm, HsepcountEm, HoctcountEm, HnovcountEm, HdeccountEm};
        int[] valFive = {RjancountEm, RfebcountEm, RmarcountEm, RaprcountEm, RmaycountEm, RjuncountEm, RjulcountEm, RaugcountEm, RsepcountEm, RoctcountEm, RnovcountEm, RdeccountEm};

        ArrayList<Entry> barOne = new ArrayList<Entry>();
        ArrayList<Entry> barTwo = new ArrayList<>();
        ArrayList<Entry> barThree = new ArrayList<>();
        ArrayList<Entry> barFour = new ArrayList<Entry>();
        ArrayList<Entry> barFive = new ArrayList<Entry>();
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
        float sumapr = EaprcountEm + ELaprcountEm + PaprcountEm + HaprcountEm + RaprcountEm;

        //5
        barThree.add(new Entry(0, (valTwo[0] / sumpost[3]) * 100));
        barThree.add(new Entry(1, (valTwo[1] / sumpost[3]) * 100));
        barThree.add(new Entry(2, (valTwo[2] / sumpost[3]) * 100));
        barThree.add(new Entry(3, (valTwo[3] / sumpost[3]) * 100));
        barThree.add(new Entry(4, (valTwo[4] / sumpost[3]) * 100));
        barThree.add(new Entry(5, (valTwo[5] / sumpost[3]) * 100));
        barThree.add(new Entry(6, (valTwo[6] / sumpost[3]) * 100));
        barThree.add(new Entry(7, (valTwo[7] / sumpost[3]) * 100));
        barThree.add(new Entry(8, (valTwo[8] / sumpost[3]) * 100));
        barThree.add(new Entry(9, (valTwo[9] / sumpost[3]) * 100));
        barThree.add(new Entry(10, (valTwo[10] / sumpost[3]) * 100));
        barThree.add(new Entry(11, (valTwo[11] / sumpost[3]) * 100));

        return barThree;
    }
    //Day
    private ArrayList<Entry> dataValues3(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        int[] valOne = {EjancountEm, EfebcountEm, EmarcountEm, EaprcountEm, EmaycountEm, EjuncountEm, EjulcountEm, EaugcountEm, EsepcountEm, EoctcountEm, EnovcountEm, EdeccountEm};
        int[] valTwo = {ELjancountEm, ELfebcountEm, ELmarcountEm, ELaprcountEm, ELmaycountEm, ELjuncountEm, ELjulcountEm, ELaugcountEm, ELsepcountEm, ELoctcountEm, ELnovcountEm, ELdeccountEm};
        int[] valThree = {PjancountEm, PfebcountEm, PmarcountEm, PaprcountEm, PmaycountEm, PjuncountEm, PjulcountEm, PaugcountEm, PsepcountEm, PoctcountEm, PnovcountEm, PdeccountEm};
        int[] valFour = {HjancountEm, HfebcountEm, HmarcountEm, HaprcountEm, HmaycountEm, HjuncountEm, HjulcountEm, HaugcountEm, HsepcountEm, HoctcountEm, HnovcountEm, HdeccountEm};
        int[] valFive = {RjancountEm, RfebcountEm, RmarcountEm, RaprcountEm, RmaycountEm, RjuncountEm, RjulcountEm, RaugcountEm, RsepcountEm, RoctcountEm, RnovcountEm, RdeccountEm};

        int[] Daily = {ElcountMon, ElcountTue, ElcountWed, ElcountThu, ElcountFri, ElcountSat, ElcountSun};

        ArrayList<Entry> barOne = new ArrayList<Entry>();
        ArrayList<Entry> barTwo = new ArrayList<>();
        ArrayList<Entry> barThree = new ArrayList<>();
        ArrayList<Entry> barFour = new ArrayList<Entry>();
        ArrayList<Entry> barFive = new ArrayList<Entry>();
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
        float sumapr = EaprcountEm + ELaprcountEm + PaprcountEm + HaprcountEm + RaprcountEm;

        //5
        barThree.add(new Entry(0, Daily[0] ));
        barThree.add(new Entry(1, Daily[1] ));
        barThree.add(new Entry(2, Daily[2] ));
        barThree.add(new Entry(3, Daily[3] ));
        barThree.add(new Entry(4, Daily[4] ));
        barThree.add(new Entry(5, Daily[5] ));
        barThree.add(new Entry(6, Daily[6] ));

        return barThree;
    }
    //Month
    private ArrayList<Entry> dataValues4(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        int[] valOne = {EjancountEm, EfebcountEm, EmarcountEm, EaprcountEm, EmaycountEm, EjuncountEm, EjulcountEm, EaugcountEm, EsepcountEm, EoctcountEm, EnovcountEm, EdeccountEm};
        int[] valTwo = {ELjancountEm, ELfebcountEm, ELmarcountEm, ELaprcountEm, ELmaycountEm, ELjuncountEm, ELjulcountEm, ELaugcountEm, ELsepcountEm, ELoctcountEm, ELnovcountEm, ELdeccountEm};
        int[] valThree = {PjancountEm, PfebcountEm, PmarcountEm, PaprcountEm, PmaycountEm, PjuncountEm, PjulcountEm, PaugcountEm, PsepcountEm, PoctcountEm, PnovcountEm, PdeccountEm};
        int[] valFour = {HjancountEm, HfebcountEm, HmarcountEm, HaprcountEm, HmaycountEm, HjuncountEm, HjulcountEm, HaugcountEm, HsepcountEm, HoctcountEm, HnovcountEm, HdeccountEm};
        int[] valFive = {RjancountEm, RfebcountEm, RmarcountEm, RaprcountEm, RmaycountEm, RjuncountEm, RjulcountEm, RaugcountEm, RsepcountEm, RoctcountEm, RnovcountEm, RdeccountEm};
        int data = Elcount1 + Elcount2 + Elcount3 + Elcount4 + Elcount5 + Elcount6 + Elcount7;
        int data2 = Elcount8 + Elcount9 + Elcount10 + Elcount11 + Elcount12 + Elcount13 + Elcount14;
        int data3 = Elcount15, Elcount16, Elcount17, Elcount18, Elcount19, Elcount20, Elcount21;
        int data4 = Elcount22, Elcount23, Elcount24, Elcount25, Elcount26, Elcount27, Elcount28, Elcount29, Elcount30, Elcount31;


        ArrayList<Entry> barOne = new ArrayList<Entry>();
        ArrayList<Entry> barTwo = new ArrayList<>();
        ArrayList<Entry> barThree = new ArrayList<>();
        ArrayList<Entry> barFour = new ArrayList<Entry>();
        ArrayList<Entry> barFive = new ArrayList<Entry>();
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
        float sumapr = EaprcountEm + ELaprcountEm + PaprcountEm + HaprcountEm + RaprcountEm;

        //5
        barThree.add(new Entry(0, data ));
        barThree.add(new Entry(1, data2 ));
        barThree.add(new Entry(2, data3 ));
        barThree.add(new Entry(3, data4 ));

        return barThree;
    }

    private class MyValueFormatter implements IValueFormatter {
        private DecimalFormat mFormat;
        public MyValueFormatter(){
            mFormat = new DecimalFormat("#.0");
        }
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value) + "%";
        }
    }

    private class MyAxisValueFormatter implements IAxisValueFormatter{
        private DecimalFormat mFormat;
        public MyAxisValueFormatter(){
            mFormat = new DecimalFormat("#");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "Week" + mFormat.format(value + 1);
        }
    }
}
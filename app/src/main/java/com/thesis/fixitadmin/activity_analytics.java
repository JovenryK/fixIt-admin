package com.thesis.fixitadmin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.print.PrintHelper;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;import java.util.Iterator;import java.util.List;
import java.util.ListIterator;import java.util.stream.*;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.fixitadmin.authEmail.SignIn;
import com.thesis.fixitadmin.chart.Analytics;
import com.thesis.fixitadmin.chart.ElectricActivity;
import com.thesis.fixitadmin.chart.EmergencyActivity;
import com.thesis.fixitadmin.chart.HomeActivity;
import com.thesis.fixitadmin.chart.LineChart2Activity;
import com.thesis.fixitadmin.chart.LineChart3Activity;
import com.thesis.fixitadmin.chart.LineGraphActivity;
import com.thesis.fixitadmin.chart.PublicActivity;
import com.thesis.fixitadmin.chart.RoadActivity;
import com.thesis.fixitadmin.model.ModelChart;
import com.thesis.fixitadmin.model.ModelPost;

import java.util.Date;

import java.util.ArrayList;

public class activity_analytics extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    TextView Emergency, Electrical_complaints, Public_incidents, Road_complaints, Household, debugtext;
    ArrayList postList;
    int[] colorClass = new int[]{Color.LTGRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.rgb(255, 58, 186)};
    Spinner spinnerCategory;
    BarChart mChart;
    ArrayList<PieEntry> dataVals = new ArrayList<>();
    ArrayList dataVals1 = new ArrayList<>();
    ImageView logout;

    Button btn;

    ArrayList<BarEntry> barEntries;
    ArrayList<String> labelName;

    ArrayList<ModelChart> monthSaleData = new ArrayList<>();

    CardView cardView, cardView1, cardView2, cardView3, cardView4, cardView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        Emergency = findViewById(R.id.emergency);
        Electrical_complaints = findViewById(R.id.electric_complaints);
        Public_incidents = findViewById(R.id.public_incident);
        Road_complaints = findViewById(R.id.road_complaints);
        Household = findViewById(R.id.household);
        btn = findViewById(R.id.print);

        cardView = findViewById(R.id.card_view);
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmergencyActivity.class);
            startActivity(intent);
            finish();
        });

        cardView1 = findViewById(R.id.card_view1);
        cardView1.setOnClickListener(v -> {
            Intent intent = new Intent(this, ElectricActivity.class);
            startActivity(intent);
            finish();
        });

        cardView2 = findViewById(R.id.card_view2);
        cardView2.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        cardView3 = findViewById(R.id.card_view3);
        cardView3.setOnClickListener(v -> {
            Intent intent = new Intent(this, PublicActivity.class);
            startActivity(intent);
        });

        cardView4 = findViewById(R.id.card_view4);
        cardView4.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoadActivity.class);
            startActivity(intent);
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        });

        getAnalytics();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(nevigationSelected);

        TextView text = findViewById(R.id.test);
//        text.setOnClickListener(v ->{
//            Intent intent = new Intent(this, LineChart2Activity.class);
//            startActivity(intent);
//        });

//        btn.setOnClickListener(v -> {
//
//            View view = getWindow().getDecorView().findViewById(android.R.id.content);
//            view.setDrawingCacheEnabled(true);
//            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//            view.buildDrawingCache(true);
//            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
//            view.setDrawingCacheEnabled(false);
//
//            PrintHelper photoPrinter = new PrintHelper(this); // Asume that 'this' is your activity
//            photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
//            photoPrinter.printBitmap("print", bitmap);
//        });
    }

    private void getAnalytics() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Posts");

         ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ModelPost> modelPosts = new ArrayList<ModelPost>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ModelPost modelPost = new ModelPost();
                    modelPost.setpTime(ds.child("pTime").getValue(String.class));
                    modelPost.setType(ds.child("type").getValue(String.class));
                    
                    modelPosts.add(modelPost);
                }
                Analytics.generateReport(modelPosts);

                Emergency.setText(String.valueOf(Analytics.getTotalPerType("Emergency")));
                Electrical_complaints.setText(String.valueOf(Analytics.getTotalPerType("Electrical Complaints")));
                Public_incidents.setText(String.valueOf(Analytics.getTotalPerType("Public Incidents")));
                Household.setText(String.valueOf(Analytics.getTotalPerType("Household Concerns")));
                Road_complaints.setText(String.valueOf(Analytics.getTotalPerType("Road Complaints")));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener nevigationSelected = item -> {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent homeFrag = new Intent(this, MainActivity.class);
                startActivity(homeFrag);
                break;
            case R.id.nav_search:
                Intent intent = new Intent(this, activity_analytics.class);
                startActivity(intent);
                break;
        }
        return true;
    };
}
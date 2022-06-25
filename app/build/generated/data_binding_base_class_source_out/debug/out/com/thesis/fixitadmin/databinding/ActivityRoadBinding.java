// Generated by view binder compiler. Do not edit!
package com.thesis.fixitadmin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.LineChart;
import com.thesis.fixitadmin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRoadBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout EmergencyLayoutEl;

  @NonNull
  public final ConstraintLayout EmergencyLayoutEl2;

  @NonNull
  public final ConstraintLayout EmergencyLayoutEl3;

  @NonNull
  public final TextView EmergencyTv1;

  @NonNull
  public final ImageView backtoA;

  @NonNull
  public final RelativeLayout header;

  @NonNull
  public final LineChart lineChartR;

  @NonNull
  public final LineChart lineChartR1;

  @NonNull
  public final LineChart lineChartR2;

  @NonNull
  public final Button print;

  @NonNull
  public final TextView textView;

  private ActivityRoadBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout EmergencyLayoutEl, @NonNull ConstraintLayout EmergencyLayoutEl2,
      @NonNull ConstraintLayout EmergencyLayoutEl3, @NonNull TextView EmergencyTv1,
      @NonNull ImageView backtoA, @NonNull RelativeLayout header, @NonNull LineChart lineChartR,
      @NonNull LineChart lineChartR1, @NonNull LineChart lineChartR2, @NonNull Button print,
      @NonNull TextView textView) {
    this.rootView = rootView;
    this.EmergencyLayoutEl = EmergencyLayoutEl;
    this.EmergencyLayoutEl2 = EmergencyLayoutEl2;
    this.EmergencyLayoutEl3 = EmergencyLayoutEl3;
    this.EmergencyTv1 = EmergencyTv1;
    this.backtoA = backtoA;
    this.header = header;
    this.lineChartR = lineChartR;
    this.lineChartR1 = lineChartR1;
    this.lineChartR2 = lineChartR2;
    this.print = print;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRoadBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRoadBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_road, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRoadBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.EmergencyLayoutEl;
      ConstraintLayout EmergencyLayoutEl = ViewBindings.findChildViewById(rootView, id);
      if (EmergencyLayoutEl == null) {
        break missingId;
      }

      id = R.id.EmergencyLayoutEl2;
      ConstraintLayout EmergencyLayoutEl2 = ViewBindings.findChildViewById(rootView, id);
      if (EmergencyLayoutEl2 == null) {
        break missingId;
      }

      id = R.id.EmergencyLayoutEl3;
      ConstraintLayout EmergencyLayoutEl3 = ViewBindings.findChildViewById(rootView, id);
      if (EmergencyLayoutEl3 == null) {
        break missingId;
      }

      id = R.id.EmergencyTv1;
      TextView EmergencyTv1 = ViewBindings.findChildViewById(rootView, id);
      if (EmergencyTv1 == null) {
        break missingId;
      }

      id = R.id.backtoA;
      ImageView backtoA = ViewBindings.findChildViewById(rootView, id);
      if (backtoA == null) {
        break missingId;
      }

      id = R.id.header;
      RelativeLayout header = ViewBindings.findChildViewById(rootView, id);
      if (header == null) {
        break missingId;
      }

      id = R.id.lineChartR;
      LineChart lineChartR = ViewBindings.findChildViewById(rootView, id);
      if (lineChartR == null) {
        break missingId;
      }

      id = R.id.lineChartR1;
      LineChart lineChartR1 = ViewBindings.findChildViewById(rootView, id);
      if (lineChartR1 == null) {
        break missingId;
      }

      id = R.id.lineChartR2;
      LineChart lineChartR2 = ViewBindings.findChildViewById(rootView, id);
      if (lineChartR2 == null) {
        break missingId;
      }

      id = R.id.print;
      Button print = ViewBindings.findChildViewById(rootView, id);
      if (print == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityRoadBinding((ConstraintLayout) rootView, EmergencyLayoutEl,
          EmergencyLayoutEl2, EmergencyLayoutEl3, EmergencyTv1, backtoA, header, lineChartR,
          lineChartR1, lineChartR2, print, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
package com.example.subscriptionforme.recommendation.detail_recommendation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Detail_BurgerKing extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burgerking_recommendation);
        drawBarChart();
        drawLineChart();

        button = findViewById(R.id.service_add_button_ativity_detail);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.skmc.okcashbag.home_google")));
            }
        });
    }

    @SuppressLint("ResourceType")
    public void drawLineChart(){
        LineChart lineChart = (LineChart) findViewById(R.id.linechart_activity_burgerking_recommendation);

        //확대 불가능
        lineChart.setPinchZoom(true);
        lineChart.setScaleXEnabled(false);
        lineChart.setScaleYEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        List<Entry> entry_chart = new ArrayList<>();

        ValueFormatter xAxisFormatter = new DayAxisValueLineChartFormatter(lineChart);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setTextColor(Color.parseColor("#000000"));
        xAxis.setGranularity(1f);

        //remove horizontal lines
        AxisBase axisBase = lineChart.getAxisLeft();
        axisBase.setDrawGridLines(false);

        YAxis yAxisL = lineChart.getAxisLeft();
        YAxis yAxisR = lineChart.getAxisRight();
        yAxisL.setAxisMinimum(25000);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(8);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        yAxisL.setDrawAxisLine(false);
        yAxisL.setDrawLabels(false);
        yAxisL.setDrawZeroLine(true);
        yAxisR.setDrawGridLines(false);
        yAxisR.setDrawAxisLine(false);
        yAxisR.setDrawLabels(false);

        //여기서 금액 넣기!
        entry_chart.add(new BarEntry(1,48000));
        entry_chart.add(new BarEntry(3,59000));
        entry_chart.add(new BarEntry(5,31200));
        entry_chart.add(new BarEntry(7,41400));

        LineData lineData = new LineData();

        LineDataSet lineDataSet = new LineDataSet(entry_chart, "햄버거 관련 지출");
        lineDataSet.setColors(new int[] {Color.parseColor(getString(R.color.mainColor))});
        lineData.addDataSet(lineDataSet);
        lineDataSet.setValueFormatter(new MyValueFormatter());
        lineDataSet.setValueTextColor(Color.parseColor("#000000"));
        lineDataSet.setValueTextSize(10);
        lineDataSet.setCircleColor(Color.parseColor(getString(R.color.mainColor)));
        lineDataSet.setLineWidth(1.5f);
        lineChart.setData(lineData);
        lineChart.setDescription(null);
        lineChart.setTouchEnabled(false);

        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setDrawInside(false);

        lineChart.setBackgroundColor(Color.parseColor("#00000000"));
        lineChart.invalidate();
    }

    @SuppressLint("ResourceType")
    public void drawBarChart() {
        BarChart barChart = (BarChart) findViewById(R.id.barchart_activity_burgerking_recommendation);

        //확대 불가능
        barChart.setPinchZoom(true);
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setTextColor(Color.parseColor("#000000"));
        xAxis.setGranularity(1f);

        YAxis yAxisL = barChart.getAxisLeft();
        YAxis yAxisR = barChart.getAxisRight();
        yAxisL.setAxisMinimum(5000);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        yAxisL.setDrawAxisLine(false);
        yAxisL.setDrawLabels(false);
        yAxisL.setDrawZeroLine(true);
        yAxisR.setDrawGridLines(false);
        yAxisR.setDrawAxisLine(false);
        yAxisR.setDrawLabels(false);

        //remove horizontal lines
        AxisBase axisBase = barChart.getAxisLeft();
        axisBase.setDrawGridLines(false);

        List<BarEntry> entry_chart = new ArrayList<>();

        //여기서 금액 넣기!
        entry_chart.add(new BarEntry(0,41400));
        entry_chart.add(new BarEntry(1,14700));

        BarData barData = new BarData();
        barData.setBarWidth(0.3f);

        BarDataSet barDataSet = new BarDataSet(entry_chart, "");
        barDataSet.setColors(new int[] {Color.parseColor("#dbc8f6"),Color.parseColor(getString(R.color.mainColor))});
        barData.addDataSet(barDataSet);
        barDataSet.setValueFormatter(new MyValueFormatter());
        barDataSet.setValueTextColor(Color.parseColor("#000000"));
        barDataSet.setValueTextSize(10);
        barChart.setData(barData);
        barChart.setDescription(null);
        barChart.setTouchEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        barChart.setBackgroundColor(Color.parseColor("#00000000"));

        barChart.invalidate();
    }
}

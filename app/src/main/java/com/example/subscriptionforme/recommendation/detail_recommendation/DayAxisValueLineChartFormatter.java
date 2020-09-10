package com.example.subscriptionforme.recommendation.detail_recommendation;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class DayAxisValueLineChartFormatter extends ValueFormatter {
    private final BarLineChartBase<?> lineChart;

    public DayAxisValueLineChartFormatter(BarLineChartBase<?> chart) {
        this.lineChart = chart;
    }

    @Override
    public String getFormattedValue(float value) {
        switch ((int) value){
            case 1 :
                return "5월";

            case 3:
                return "6월";

            case 5 :
                return "7월";

            case 7:
                return "8월";

            default:
                return "";
        }
    }
}
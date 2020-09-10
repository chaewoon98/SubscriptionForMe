package com.example.subscriptionforme.recommendation.detail_recommendation;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class DayAxisValueFormatter extends ValueFormatter {
    private final BarLineChartBase<?> barChart;

    public DayAxisValueFormatter(BarLineChartBase<?> chart) {
        this.barChart = chart;
    }

    @Override
    public String getFormattedValue(float value) {
        switch ((int) value){
            case 0 :
                return "서비스 이용 전 지출";

            case 1:
                return "서비스 이용 후 예상 지출";

            default:
                return "0";
        }
    }
}

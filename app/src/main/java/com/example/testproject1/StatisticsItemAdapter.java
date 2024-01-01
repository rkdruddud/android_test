package com.example.testproject1;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class StatisticsItemAdapter extends RecyclerView.Adapter<StatisticsItemAdapter.StatisticsItemViewHolder> {
    private ArrayList<StatisticsInfo> items;
    private String testCount; //검사횟수
    private String bradycardia; //서맥 통계값
    private String tachycardia; //빈맥 통계값
    private String atrial_fibrillation; //심방세동 통계값
    private String not_classified; //분류되지 않음 통계값
    private String[] datelist; // 총 검사한 날짜
    private double[] dayvalue; // 검사 날짜별 값
    public StatisticsItemAdapter(String testCount,String bradycardia,String tachycardia,String atrial_fibrillation,String not_classified,
    String[] datelist,double[] dayvalue){
        this.testCount = testCount;
        this.bradycardia = bradycardia;
        this.tachycardia = tachycardia;
        this.atrial_fibrillation = atrial_fibrillation;
        this.not_classified = not_classified;
        this.datelist=datelist;
        this.dayvalue=dayvalue;
    }

    @NonNull
    @Override
    public StatisticsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistics_card_item,parent,false);
        StatisticsItemViewHolder holder = new StatisticsItemViewHolder(view);
        Log.d("실행","adf");
        return holder;
    }

    //각 아이템들에 대한 매칭을 해줌
    @Override
    public void onBindViewHolder(@NonNull StatisticsItemAdapter.StatisticsItemViewHolder holder, int position) {
        initBarChart(holder);
        initLineChart(holder);
        StatisticsInfo item = new items.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class StatisticsItemViewHolder extends RecyclerView.ViewHolder {

        private LineChart lineChart;
        private BarChart barChart;
        public StatisticsItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.lineChart = itemView.findViewById(R.id.ecg_record_statistics_linechart);
            this.barChart = itemView.findViewById(R.id.ecg_record_statistics_barchart);

        }
    }

    public void initBarChart (@NonNull StatisticsItemAdapter.StatisticsItemViewHolder holder) {

        ArrayList<BarEntry> entrybarchart = new ArrayList<>();

        BarData barData = new BarData();

        entrybarchart.add(new BarEntry(Float.parseFloat(bradycardia),Float.parseFloat(bradycardia)));
        entrybarchart.add(new BarEntry(Float.parseFloat(tachycardia),Float.parseFloat(tachycardia)));
        entrybarchart.add(new BarEntry(Float.parseFloat(atrial_fibrillation),Float.parseFloat(atrial_fibrillation)));
        entrybarchart.add(new BarEntry(Float.parseFloat(not_classified),Float.parseFloat(not_classified)));

        BarDataSet barDataSet = new BarDataSet(entrybarchart, "bardataset");

        barDataSet.setColor(Color.RED);

        barData.addDataSet(barDataSet);

        holder.barChart.setData(barData);

        holder.barChart.invalidate(); // 차트 업데이트
        holder.barChart.setTouchEnabled(false); // 차트 터치 불가능하게
    }

    public void initLineChart (@NonNull StatisticsItemAdapter.StatisticsItemViewHolder holder) {

        ArrayList<Entry> entrylinechart = new ArrayList<>();

        LineData lineData = new LineData();

        entrylinechart.add(new Entry(Float.parseFloat(bradycardia),Float.parseFloat(bradycardia)));
        entrylinechart.add(new Entry(Float.parseFloat(tachycardia),Float.parseFloat(tachycardia)));
        entrylinechart.add(new Entry(Float.parseFloat(atrial_fibrillation),Float.parseFloat(atrial_fibrillation)));
        entrylinechart.add(new Entry(Float.parseFloat(not_classified),Float.parseFloat(not_classified)));

        LineDataSet lineDataSet = new LineDataSet(entrylinechart, "linedataset");

        lineDataSet.setColor(Color.rgb(221, 221, 221));

        lineData.addDataSet(lineDataSet);

        holder.lineChart.setData(lineData);

        holder.lineChart.invalidate(); // 차트 업데이트
        holder.lineChart.setTouchEnabled(false); // 차트 터치 불가능하게
    }

}

package com.example.testproject1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class StatisticsFragment extends Fragment {


    private View view;
    private LinearLayout statistics_card_linearlaout;
    private TextView gobackText;

    private BarChart barChart;

    private LineChart lineChart;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private String testCount; //검사횟수
    private String bradycardia; //서맥 통계값
    private String tachycardia; //빈맥 통계값
    private String atrial_fibrillation; //심방세동 통계값
    private String not_classified; //분류되지 않음 통계값
    private String[] datelist; // 총 검사한 날짜
    private double[] dayvalue; // 검사 날짜별 값

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_statistics, container, false);

       gobackText = view.findViewById(R.id.goBackPage_Text);

        statistics_card_linearlaout = view.findViewById(R.id.ecg_record_statistics_linearlayout);

        recyclerView = view.findViewById(R.id.ecg_record_statistics_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);


       if(getArguments() != null){
           testCount = getArguments().getString("testCount");
           bradycardia = getArguments().getString("bradycardia");
           tachycardia = getArguments().getString("tachycardia");
           atrial_fibrillation = getArguments().getString("atrial_fibrillation");
           not_classified = getArguments().getString("not_classified");
           datelist = getArguments().getStringArray("datelist");
           dayvalue = getArguments().getDoubleArray("dayvalue");
       }

        adapter = new StatisticsItemAdapter(testCount,bradycardia,tachycardia,atrial_fibrillation,not_classified,datelist,dayvalue);
        recyclerView.setAdapter(adapter);

        gobackText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MainFragment mainFragment = new MainFragment();
                transaction.replace(R.id.main_frameLayout,mainFragment);
                transaction.commit();
            }
        });

       return view;

    }


    public void initBarChart () {
        barChart = view.findViewById(R.id.ecg_record_statistics_barchart);

        ArrayList<BarEntry> entrybarchart = new ArrayList<>();

        BarData barData = new BarData();

        entrybarchart.add(new BarEntry(Float.parseFloat(bradycardia),Float.parseFloat(bradycardia)));
        entrybarchart.add(new BarEntry(Float.parseFloat(tachycardia),Float.parseFloat(tachycardia)));
        entrybarchart.add(new BarEntry(Float.parseFloat(atrial_fibrillation),Float.parseFloat(atrial_fibrillation)));
        entrybarchart.add(new BarEntry(Float.parseFloat(not_classified),Float.parseFloat(not_classified)));

        BarDataSet barDataSet = new BarDataSet(entrybarchart, "bardataset");

        barDataSet.setColor(Color.RED);

        barData.addDataSet(barDataSet);

        barChart.setData(barData);

        barChart.invalidate(); // 차트 업데이트
        barChart.setTouchEnabled(false); // 차트 터치 불가능하게
    }

}
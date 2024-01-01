package com.example.testproject1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainFragment extends Fragment {


    private View view;

    private TextView statisticsText;

    private String[] testdatelist = {"2023년 8월 1일", "2023년 8월 5일", "2023년 8월 10일", "2023년 8월 15일", "2023년 8월 20일", "2023년 8월 25일", "2023년 8월 30일"};
    private double[] valueOfValue = {89.2,89.2,78,91.2,91.5,110.2,77.5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_main, container, false);

       statisticsText = view.findViewById(R.id.ecg_record_statistics_text);

       statisticsText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle = new Bundle();
               bundle.putString("testCount","11");  //검사횟수
               bundle.putString("bradycardia","15"); //서맥 통계값
               bundle.putString("tachycardia","5"); //빈맥 통계값
               bundle.putString("atrial_fibrillation","4"); //심방세동 통계값
               bundle.putString("not_classified","10"); //분류되지 않음 통계값
               bundle.putStringArray("datelist",testdatelist); // 총 검사한 날짜
               bundle.putDoubleArray("dayvalue",valueOfValue); // 검사 날짜별 값

               FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               StatisticsFragment statisticsFragment = new StatisticsFragment();
               statisticsFragment.setArguments(bundle);
               transaction.replace(R.id.main_frameLayout,statisticsFragment);
               transaction.commit();
           }
       });

       return view;

    }


}
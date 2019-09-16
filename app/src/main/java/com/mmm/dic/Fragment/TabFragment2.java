package com.mmm.dic.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmm.dic.Adapter.TermAdapter;
import com.mmm.dic.Adapter.TestAdapter;
import com.mmm.dic.Classes.GridView;
import com.mmm.dic.DataBase.TermEntity;
import com.mmm.dic.DataBase.TestEntity;
import com.mmm.dic.PersonalityTestActivity;
import com.mmm.dic.R;
import com.mmm.dic.ui.main.Fragment1ViewModel;
import com.mmm.dic.ui.main.Fragment2ViewModel;
import com.mmm.dic.ui.main.PageViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class TabFragment2 extends Fragment implements TestAdapter.OnItemClickListner{
    private static final String TAG = "اختبارات";
    private List<TestEntity> test= new ArrayList<>();
    private RecyclerView recyclerView;
    private TestAdapter mAdapter;
    private List<String> questionsAsString = new ArrayList<>();
    private List<String> questionsDescription = new ArrayList<>();



    public TabFragment2(){

    }

    public static TabFragment2 newInstance(){
        return new TabFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment2,container,false);
        ButterKnife.bind(this,rootView);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.test_recycler_view);
        mAdapter = new TestAdapter(this);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float screenWidthDp = metrics.widthPixels / metrics.density;
        double count = screenWidthDp/150;
        int span = Math.max(1,(int) count);
        //GridLayoutManager layoutManager = new GridLayoutManager(getContext(),span);
        //layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        GridView layoutManager = new GridView(getContext(),span);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        updateUi(test);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Fragment2ViewModel viewModel = ViewModelProviders.of(this).get(Fragment2ViewModel.class);


        viewModel.getTests().observe(getViewLifecycleOwner(), new Observer<List<TestEntity>>() {
            @Override
            public void onChanged(@Nullable List<TestEntity> tests) {
                //viewModel.getRecipes().removeObserver(this);
                updateUi(tests);
                for(int i = 0; i < tests.size(); i++){
                    questionsAsString.add(i,tests.get(i).getQuestions());
                    questionsDescription.add(i,tests.get(i).getDescription());
                }
            }
        });
    }
    private void updateUi(List<TestEntity> testEntities){
        mAdapter.setData(testEntities);
    }

    @Override
    public void onItemClick(int id) {
        Intent intentToPersonalityTestActivity= new Intent(getContext(), PersonalityTestActivity.class);
        if(id == 0){
            intentToPersonalityTestActivity.putExtra("QUESTIONS_AS_STRING",questionsAsString.get(id));
            intentToPersonalityTestActivity.putExtra("QUESTION_DESCREPTION",questionsDescription.get(id));
            startActivity(intentToPersonalityTestActivity);
        }

    }
}

package com.mmm.dic;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mmm.dic.Adapter.PersonalityTestAdapter;
import com.mmm.dic.Classes.Converters;
import com.mmm.dic.Classes.Question;
import com.mmm.dic.Classes.QuestionItem;
import com.mmm.dic.ui.main.PersonalityTestViewModel;
import com.mmm.dic.ui.main.PersonalityTestViewModelFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalityTestActivity extends AppCompatActivity implements PersonalityTestAdapter.OnItemChangeListner {

    private String questionsAsString = "";
    private List<Question> questions = new ArrayList<>();
    private String questionDescription;
    private PersonalityTestViewModel viewModel;
    private PersonalityTestAdapter mAdapter;
    private List<Integer> choises = new ArrayList<>();
    private List<QuestionItem> questionItems = new ArrayList<>();

    @BindView(R.id.personality_test_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.test_description)
    TextView mTestDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ActionBar bar = this.getSupportActionBar();
        if(bar != null){
            bar.setDisplayHomeAsUpEnabled(true);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new PersonalityTestAdapter(this);
        recyclerView.setAdapter(mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.personality_test_item_devider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        if(intent.hasExtra("QUESTIONS_AS_STRING") && intent.hasExtra("QUESTION_DESCREPTION")){
            questionsAsString = intent.getExtras().getString("QUESTIONS_AS_STRING");
            questionDescription = intent.getExtras().getString("QUESTION_DESCREPTION");
            mTestDescription.setText(questionDescription);
            PersonalityTestViewModelFactory factory = new PersonalityTestViewModelFactory(questionsAsString);
            viewModel = ViewModelProviders.of(this,factory).get(PersonalityTestViewModel.class);
            viewModel.prepareQuestions();
            questionItems = viewModel.getQuestionItems();
            mAdapter.setData(questionItems);
            //viewModel.getLiveQuestions().observe(this, new Observer<List<QuestionItem>>() {
                //@Override
                //public void onChanged(@Nullable List<QuestionItem> questionItems) {
                   // mAdapter.setData(questionItems);
                //}
            //});

        }
    }

    @Override
    public void onItemChange(int index,int checkedId) {
        //Toast toast=Toast.makeText(getApplicationContext(),"Hello Javatpoint" + "    " + id + "   " + checkedId,Toast.LENGTH_LONG);
        //toast.show();
        //choises.set(id,checkedId);
        viewModel.setChoise(index,checkedId);

    }
}

package com.mmm.dic.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmm.dic.Adapter.TermAdapter;
import com.mmm.dic.DataBase.TermEntity;
import com.mmm.dic.MainActivity;
import com.mmm.dic.R;
import com.mmm.dic.TermDetailsActivity;
import com.mmm.dic.ui.main.Fragment1ViewModel;
import com.mmm.dic.ui.main.PageViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class TabFragment1 extends Fragment implements TermAdapter.OnItemClickListner{

    private List<TermEntity> terms= new ArrayList<>();
    private RecyclerView recyclerView;
    private TermAdapter mAdapter;
    public String NAME_AR_ID = "NAME_IN_AR";
    public String NAME_EN_ID = "NAME_IN_EN";
    public String DESCRIPTION_ID = "DESCRIPTION";
    public String DETAILS_ID = "DETAILS";


    public TabFragment1(){

    }

    public static TabFragment1 newInstance(){
        return new TabFragment1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment1,container,false);
        ButterKnife.bind(this,rootView);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.main_recycler_view);
        mAdapter = new TermAdapter(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.term_item_devider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        updateUi(terms);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Fragment1ViewModel viewModel = ViewModelProviders.of(this).get(Fragment1ViewModel.class);


        viewModel.getTerms().observe(getViewLifecycleOwner(), new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(@Nullable List<TermEntity> terms) {
                //viewModel.getRecipes().removeObserver(this);
                updateUi(terms);
            }
        });
    }

    public void updateUi(List<TermEntity> terms){
        this.terms = terms;
        mAdapter.setData(terms);
    }
    @Override
    public void onItemClick(int id) {
        Intent intentToStartDetails = new Intent(getContext(), TermDetailsActivity.class);
        intentToStartDetails.putExtra(NAME_AR_ID,terms.get(id).getName_in_arabic());
        intentToStartDetails.putExtra(NAME_EN_ID,terms.get(id).getName());
        intentToStartDetails.putExtra(DESCRIPTION_ID,terms.get(id).getDescription());
        intentToStartDetails.putExtra(DETAILS_ID,terms.get(id).getDetails());
        startActivity(intentToStartDetails);
    }
}

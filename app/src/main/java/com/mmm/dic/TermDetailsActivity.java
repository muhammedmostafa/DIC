package com.mmm.dic;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mmm.dic.Fragment.TabFragment1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermDetailsActivity extends AppCompatActivity {
    @BindView(R.id.term_name_in_ar)
    TextView mTermNameAr;

    @BindView(R.id.term_name_in_en)
    TextView mTermNameEn;

    @BindView(R.id.term_description)
    TextView mTermDescription;

    @BindView(R.id.term_details)
    TextView mTermDetails;

    public String NAME_AR_ID = "NAME_IN_AR";
    public String NAME_EN_ID = "NAME_IN_EN";
    public String DESCRIPTION_ID = "DESCRIPTION";
    public String DETAILS_ID = "DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ActionBar bar = this.getSupportActionBar();
        if(bar != null){
            bar.setDisplayHomeAsUpEnabled(true);
        }
        if(intent.hasExtra(NAME_AR_ID) && intent.hasExtra(NAME_EN_ID) &&
                intent.hasExtra(DESCRIPTION_ID)&& intent.hasExtra(DETAILS_ID)){
            mTermNameAr.setText(intent.getExtras().getString(NAME_AR_ID));
            mTermNameEn.setText(intent.getExtras().getString(NAME_EN_ID));
            mTermDescription.setText(intent.getExtras().getString(DESCRIPTION_ID));
            mTermDetails.setText(intent.getExtras().getString(DETAILS_ID));
        }
    }
}

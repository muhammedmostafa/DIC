package com.mmm.dic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmm.dic.DataBase.TermEntity;
import com.mmm.dic.DataBase.TestEntity;
import com.mmm.dic.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<TestEntity> testEntityArrayList = new ArrayList<>();
    private final TestAdapter.OnItemClickListner listner;
    private Context context;


    public interface OnItemClickListner{
        void onItemClick(int recipeID);

    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_item,viewGroup,false);
        return new TestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder viewHolder, int i) {

        viewHolder.bind(i,listner);

    }

    @Override
    public int getItemCount() {
        if(testEntityArrayList == null){
            return 0;
        }else{
            return testEntityArrayList.size();
        }
    }

    public TestAdapter(OnItemClickListner listener){
        this.listner = listener;

    }

    public void setData(List<TestEntity> TestList){
        this.testEntityArrayList = TestList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.test_name)
        TextView mTestName;

        @BindView(R.id.test_image)
        ImageView mTestImage;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
        public void bind(final int id, final OnItemClickListner listner){
            TestEntity testEntity = testEntityArrayList.get(id);
            mTestName.setText(testEntity.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onItemClick(id);
                    Log.d("firdt"," this was clicked"+id);
                }
            });
        }

    }
}

package com.mmm.dic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmm.dic.DataBase.TermEntity;
import com.mmm.dic.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private List<TermEntity> terms = new ArrayList<>();
    private final OnItemClickListner listner;
    private Context context;


    public interface OnItemClickListner{
        void onItemClick(int recipeID);

    }

    @NonNull
    @Override
    public TermAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.term_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.ViewHolder viewHolder, int i) {

        viewHolder.bind(i,listner);

    }

    @Override
    public int getItemCount() {
        if(terms == null){
            return 0;
        }else{
            return terms.size();
        }
    }

    public TermAdapter(OnItemClickListner listener){
        this.listner = listener;

    }

    public void setData(List<TermEntity> TermList){
        this.terms = TermList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.Term_Name_Ar_View)
        TextView mTermNameAr;

        @BindView(R.id.Term_Name_En_View)
        TextView mTermNameEn;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
        public void bind(final int id, final OnItemClickListner listner){
            TermEntity term = terms.get(id);
            mTermNameAr.setText(term.getName_in_arabic());
            mTermNameEn.setText(term.getName());
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

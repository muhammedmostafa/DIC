package com.mmm.dic.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mmm.dic.Classes.Question;
import com.mmm.dic.Classes.QuestionItem;
import com.mmm.dic.DataBase.TestEntity;
import com.mmm.dic.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalityTestAdapter extends RecyclerView.Adapter<PersonalityTestAdapter.ViewHolder> {

    private List<QuestionItem> questions = new ArrayList<>();
    private List<Integer> choises = new ArrayList<>();
    private final PersonalityTestAdapter.OnItemChangeListner listner;
    private Context context;
    private Boolean isUser = true;

    public interface OnItemChangeListner{
        void onItemChange(int id,int checkedId);

    }

    @NonNull
    @Override
    public PersonalityTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.personality_test_question_item,viewGroup,false);

        return new PersonalityTestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalityTestAdapter.ViewHolder viewHolder, int i) {

        viewHolder.bind(i,listner);

    }

    @Override
    public int getItemCount() {
        if(questions == null){
            return 0;
        }else{
            return questions.size();
        }
    }

    public PersonalityTestAdapter(OnItemChangeListner listener){
        this.listner = listener;

    }

    public void setData(List<QuestionItem> questions){

        this.questions = questions;

        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.personality_test_question_id)
        TextView mQuestionId;

        @BindView(R.id.personality_test_question)
        TextView mQuestion;

        @BindView(R.id.personality_test_radio_group)
        RadioGroup mRadioGroup;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);


        }

        public void bind(final int id, final OnItemChangeListner listner){
            final int index = getAdapterPosition();
            final QuestionItem question = questions.get(index);
            if(question != null){
                mQuestionId.setText(String.valueOf(question.getId()));

                Log.d("id", String.valueOf(question.getId()));
                mQuestion.setText(question.getQuestion());
                isUser = false;
                mRadioGroup.clearCheck();
                if(question.getSelection() != -1){
                    isUser = false;
                    mRadioGroup.check(question.getSelection());

                }else {
                    isUser = false;
                    mRadioGroup.clearCheck();

                }


            }


            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    //int id = getAdapterPosition();
                    if(isUser){

                            question.setSelection(checkedId);
                            questions.get(index).setSelection(checkedId);
                            Log.d("recyclerView", String.valueOf(questions.get(index).getSelection()));
                            //listner.onItemChange(index,checkedId);


                    }else{
                        isUser = true;
                    }




                }
            });
            //itemView.setOnClickListener(new View.OnClickListener() {
               // @Override
                //public void onClick(View v) {
                   //listner.onItemClick(id);
                    //Log.d("firdt"," this was clicked"+id);
                //}
            //});
        }

    }
}

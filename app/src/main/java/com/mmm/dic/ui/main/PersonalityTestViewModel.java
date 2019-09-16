package com.mmm.dic.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mmm.dic.Classes.Converters;
import com.mmm.dic.Classes.Question;
import com.mmm.dic.Classes.QuestionItem;

import java.util.ArrayList;
import java.util.List;


public class PersonalityTestViewModel extends ViewModel {

    private String questionsAsString;
    private List<Question> questions = new ArrayList<>();
    private List<QuestionItem> questionItems = new ArrayList<>();
    private List<Integer> choises = new ArrayList<>();
    MutableLiveData<List<QuestionItem>> questionsLive = new MutableLiveData<>();

    public PersonalityTestViewModel (String questionsAsString) {
        this.questionsAsString = questionsAsString;
    }

    public void prepareQuestions(){
        questions = Converters.fromString(questionsAsString);
        questionItems = Converters.fromQuestion(questions);
        if(choises.isEmpty()){
            for(int i = 0; i < questions.size(); i++){
                choises.add(i,-1);
            }
        }else {
            for (int i = 0; i < questions.size(); i++){
                questionItems.get(i).setSelection(choises.get(i));
            }
        }

    }

    public List<QuestionItem> getQuestionItems(){
        return this.questionItems;
    }

    public LiveData<List<QuestionItem>> getLiveQuestions(){
        return this.questionsLive;
    }

    public void setChoise(int index, int choise){
        choises.set(index,choise);
        QuestionItem questionItem = questionItems.get(index);
        questionItem.setSelection(choise);
        questionItems.set(index,questionItem);
        questionsLive.setValue(questionItems);
        questionsLive.postValue(questionItems);

    }

    private List<Integer> getChoises(){
        return this.choises;
    }





}

package com.mmm.dic.Classes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {

    public  static List<Question> fromString(String questionsCommaSeparated) {

        List<String> questions = Arrays.asList(questionsCommaSeparated.split(","));
        List<Question> questionArrayList = new ArrayList<>();
        for(int i = 0; i < questions.size(); i++){
            int id = i + 1;
            questionArrayList.add(i,new Question(id,questions.get(i)));
        }
        return questionArrayList;
    }


    public  static String fromArrayList(List<Question> questionArrayList) {
        List<String> questions = new ArrayList<>();
        for(int i =0; i < questionArrayList.size(); i++){
            questions.add(i,questionArrayList.get(i).getQuestion());
        }
        String questionsCommaSeparated = android.text.TextUtils.join(",", questions);

        return questionsCommaSeparated;
    }

    public static List<QuestionItem> fromQuestion(List<Question> questions){
        List<QuestionItem> questionItems = new ArrayList<>();
        for(int i = 0; i < questions.size(); i++){
            Question question = questions.get(i);
            QuestionItem questionItem = new QuestionItem(question.getId(),question.getQuestion(),-1);
            questionItems.add(i,questionItem);
        }
        return questionItems;
    }
}

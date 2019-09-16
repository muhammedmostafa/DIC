package com.mmm.dic.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.mmm.dic.Classes.Converters;
import com.mmm.dic.Classes.Question;
import com.mmm.dic.Classes.Term;
import com.mmm.dic.Classes.Test;
import com.mmm.dic.DataBase.DicDao;
import com.mmm.dic.DataBase.DicDb;
import com.mmm.dic.DataBase.TermEntity;
import com.mmm.dic.DataBase.TestEntity;
import com.mmm.dic.Interface.TermPlaceHolder;
import com.mmm.dic.Interface.TestPlaceHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DicRepository {


        private Context context;
        MutableLiveData<List<Term>> terms = new MutableLiveData<>();
        private List<Term> termList = new ArrayList<>();
        private List<Term> termListNew = new ArrayList<>();
        private List<Test> testList = new ArrayList<>();
        private List<Test> testListNew = new ArrayList<>();
        int countNew;
        private Retrofit retrofit;
        private LiveData<List<TermEntity>> allTermList;
        private DicDao dicDao;
        private static int count;
        private static DicDao dao;
        //private String[] fetchParams = new String[15];
        private DicDb dicDb;



        public DicRepository(Context context1){
            context = context1;
            retrofit = new Retrofit.Builder().
                    baseUrl("https://raw.githubusercontent.com/muhammedmostafa/capStone_stage2/master/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Term term = new Term();
            term.setName("PLEASE MAKE SURE YOU ARE ONLINE!!!!");
            List<Term> termsInitialization = new ArrayList<>();
            termsInitialization.add(term);
            terms.setValue(termsInitialization);


            dicDb = DicDb.getInstance(context);
            dicDao = dicDb.taskDao();
            dao = dicDb.taskDao();
            allTermList = dicDb.taskDao().loadAllTerms();
            count = dicDb.taskDao().getTermCount();
        }



        public void fetchTerms(){

            TermPlaceHolder termPlaceHolder = retrofit.create(TermPlaceHolder.class);
            Call<List<Term>> call = termPlaceHolder.getTerms();
            call.enqueue(new Callback<List<Term>>() {
                @Override
                public void onResponse(Call<List<Term>> call, Response<List<Term>> response) {
                    if (!response.isSuccessful()){
                        Log.d("onResponse"," Code: " + response.code());
                        return;
                    }
                    Log.d("onResponse"," done "  );
                    termList = response.body() ;
                    Log.d("onResponse",termList.toString()  );
                    updateTerms(termList);
                }
                @Override
                public void onFailure(Call<List<Term>> call, Throwable t) {
                    Log.d("onFailure",t.getMessage());
                }
            });


        }
    public void fetchTest(){

        TestPlaceHolder termPlaceHolder = retrofit.create(TestPlaceHolder.class);
        Call<List<Test>> call = termPlaceHolder.getTest();
        call.enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                if (!response.isSuccessful()){
                    Log.d("onResponse"," Code: " + response.code());
                    return;
                }
                Log.d("onResponse"," done test "  );
                testList = response.body() ;
                Log.d("onResponse",testList.toString()  );
                updateTest(testList);
            }
            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });


    }


    public int getCount(){
            return dicDb.taskDao().getTermCount();
        }
        private void updateTerms(List<Term> terms1) {
            this.termListNew = terms1;
            this.terms.setValue(terms1);
            this.terms.postValue(terms1);
            countNew = terms1.size();
            if(count == 0 && termListNew != null){
                for( int i = 0 ; i < termListNew.size(); i++){
                    Term term = termListNew.get(i);
                    TermEntity termEntity = new TermEntity(term.getName(),term.getNameInArabic(),term.getDescription(),term.getMeaning());
                    insertTerm(termEntity);
                }
            }else if(count != 0){
                if(count != countNew && termListNew != null){
                    dicDb.taskDao().clearTerms();
                    for( int i = 0 ; i < termListNew.size(); i++){
                        Term term = termListNew.get(i);
                        TermEntity termEntity = new TermEntity(term.getName(),term.getNameInArabic(),term.getDescription(),term.getMeaning());
                        insertTerm(termEntity);
                    }
                }
            }

        }
    private void updateTest (List<Test> tests){
            for(int i = 0; i < tests.size(); i++){
                List<Question> questionList = new ArrayList<>();
                questionList = tests.get(i).getQuestions();
                String questionsAsString = Converters.fromArrayList(questionList);
                Log.d("convert",questionsAsString);
                TestEntity testEntity = new TestEntity(tests.get(i).getId(),tests.get(i).getName(),tests.get(i).getDescription(),questionsAsString);
                insertTest(testEntity);
            }

    }
        public void insertTerm(TermEntity termEntity){
            dicDb.taskDao().insertTerm(termEntity);
        }
        public List<Term> gettermsList(){
            return this.termList;
        }
        public void insertTest(TestEntity testEntity){
            dicDb.taskDao().insertTest(testEntity);
        }

        public LiveData<List<TermEntity>> getTerms(){
            return dicDb.taskDao().loadAllTerms();
        }

         public LiveData<List<Term>> getFavTerms(){
            return null;
        }
        public LiveData<List<TestEntity>> getTests(){
            return dicDb.taskDao().loadAllTest();
        }
}

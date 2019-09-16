package com.mmm.dic.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mmm.dic.Classes.Term;
import com.mmm.dic.DataBase.TermEntity;
import com.mmm.dic.DataBase.TestEntity;
import com.mmm.dic.Repository.DicRepository;
import com.mmm.dic.Repository.Repo;

import java.util.List;

public class Fragment2ViewModel extends AndroidViewModel {
    private DicRepository repository;
    private LiveData<List<TestEntity>> testList  ;
    private Context context;


    public Fragment2ViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.repository = Repo.getRepository(context);
        this.testList = repository.getTests();
    }


    public LiveData<List<TestEntity>> getTests() {

        return repository.getTests();
    }
    public String getQuestions(int id){
        return testList.getValue().get(id).getQuestions();
    }
}

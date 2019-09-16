package com.mmm.dic.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.mmm.dic.Classes.Term;
import com.mmm.dic.DataBase.DicDb;
import com.mmm.dic.DataBase.TermEntity;
import com.mmm.dic.DataBase.TestEntity;
import com.mmm.dic.Repository.DicRepository;
import com.mmm.dic.Repository.Repo;

import java.util.List;

public class Fragment1ViewModel extends AndroidViewModel {

    private DicRepository repository;
    private LiveData<List<TermEntity>> terms  ;
    private  Context context;
    private DicDb dicDb ;


    public Fragment1ViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.repository = Repo.getRepository(context);
        dicDb = DicDb.getInstance(context);
        init();
        this.terms = repository.getTerms();
    }

    private void init(){
        if(repository.getCount() == 0){
            if(!checkConnection(context)){
                TermEntity termEntity = new TermEntity(1,"Please make sure you are ONLINE!","","","");
                TestEntity testEntity = new TestEntity(1,"Please make sure you are ONLINE!","","");

                dicDb.taskDao().insertTest(testEntity);
                dicDb.taskDao().insertTerm(termEntity);
            }else{
                updateDatabase();
            }
        }else if(repository.getCount() == 1 ){
            if(checkConnection(context)){
                updateDatabase();
            }
        }

    }
    private void updateDatabase(){
        dicDb.taskDao().clearTerms();
        dicDb.taskDao().clearTest();
        repository.fetchTerms();
        repository.fetchTest();
    }

    private static boolean checkConnection(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }


    public LiveData<List<TermEntity>> getTerms() {

        return repository.getTerms();
    }





}

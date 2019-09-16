package com.mmm.dic.Repository;

import android.content.Context;

public class Repo {
    private static DicRepository repository = null;

    public  static synchronized DicRepository getRepository(Context context){
        if (repository == null){
            repository = new DicRepository(context);
            //repository.fetchTerms();
            //repository.fetchTest();
            return repository;
        }else {
            return repository;
        }
    }
}

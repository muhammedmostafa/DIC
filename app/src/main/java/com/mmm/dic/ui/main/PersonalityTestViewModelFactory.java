package com.mmm.dic.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.mmm.dic.Classes.Question;

import java.util.List;


public class PersonalityTestViewModelFactory extends ViewModelProvider.NewInstanceFactory{



    private final String questions ;

    public PersonalityTestViewModelFactory(String questions) {

        this.questions = questions;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new PersonalityTestViewModel(questions);
    }
}

package com.mmm.dic.Interface;

import com.mmm.dic.Classes.Term;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TermPlaceHolder {
    @GET("DIC.json")
    Call<List<Term>> getTerms();
}

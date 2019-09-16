package com.mmm.dic.Interface;

import com.mmm.dic.Classes.Term;
import com.mmm.dic.Classes.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestPlaceHolder {
    @GET("test.json")
    Call<List<Test>> getTest();
}

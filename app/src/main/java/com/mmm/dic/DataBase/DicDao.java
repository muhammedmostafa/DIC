package com.mmm.dic.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.Update;

import com.mmm.dic.Classes.Converters;

import java.util.List;

@Dao
public interface DicDao {

    @Query("SELECT * FROM terms  ORdER BY uid")
    LiveData<List<TermEntity>> loadAllTerms();

    @Query("SELECT * FROM terms WHERE id = :id ")
    LiveData<TermEntity> loadTermById(int id);

    @Query("SELECT COUNT(id) FROM terms")
    int getTermCount();

    @Query("DELETE FROM terms")
    void clearTerms();
    @Query("DELETE FROM test")
    void clearTest();

    @Query("SELECT * FROM test ORDER BY uid")
    LiveData<List<TestEntity>> loadAllTest();

    @Query("SELECT * FROM test WHERE id = :id")
    LiveData<TestEntity> loadTestById(int id);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(TermEntity termEntity);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTest(TestEntity testEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTerm(TermEntity termEntity);

    @Delete
    void deleteTerm(TermEntity termEntity);

}

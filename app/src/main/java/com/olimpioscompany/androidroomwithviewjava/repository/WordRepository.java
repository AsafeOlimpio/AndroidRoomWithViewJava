package com.olimpioscompany.androidroomwithviewjava.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.olimpioscompany.androidroomwithviewjava.dao.WordDao;
import com.olimpioscompany.androidroomwithviewjava.database.WordRoomDatabase;
import com.olimpioscompany.androidroomwithviewjava.model.Word;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert (Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao myAsyncTaskDao;

        insertAsyncTask(WordDao dao){
            myAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            myAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}

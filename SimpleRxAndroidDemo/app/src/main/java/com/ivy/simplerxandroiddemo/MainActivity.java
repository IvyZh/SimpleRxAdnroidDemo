package com.ivy.simplerxandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Observer<MoviesBean> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvResult = (TextView) findViewById(R.id.textView);

        observer = new Observer<MoviesBean>() {

            @Override
            public void onCompleted() {
                Log.v("way","complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.v("way","onError"+e.getLocalizedMessage());
            }

            @Override
            public void onNext(MoviesBean o) {
                tvResult.setText(o.toString());
            }
        };
    }

    public void load(View v) {

        NetWork.getService().getTop250(20, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}

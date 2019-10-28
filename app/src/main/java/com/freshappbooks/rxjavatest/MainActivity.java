package com.freshappbooks.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Function<String, Integer> stringToInteger = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.parseInt(s);
            }
        };

        Observable<Integer> observable = Observable
                .fromArray("1", "2", "3").map(stringToInteger);
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v(TAG, "onSubscribe worked " + d);

            }

            @Override
            public void onNext(Integer integer) {
                Log.v(TAG, "onNext worked " + integer);
            }



            @Override
            public void onError(Throwable e) {
                Log.v(TAG, "onError worked" + e);

            }

            @Override
            public void onComplete() {
                Log.v(TAG, "onComplete worked");
            }
        };

        observable.subscribe(observer);

    }

}

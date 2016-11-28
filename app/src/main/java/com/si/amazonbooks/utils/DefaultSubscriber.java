package com.si.amazonbooks.utils;

import rx.Subscriber;

/**
 * Created by Agustin Tomas Larghi on 27/11/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */

public abstract class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        //Implement it if you want
    }

}

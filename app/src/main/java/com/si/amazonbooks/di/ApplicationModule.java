package com.si.amazonbooks.di;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * <ul>
 *  <li>
 *      This module provides a {@link Context}.
 *  </li>
 *  <li>
 *      This module provides a {@link LayoutInflater} so we can inflate easily any view such as
 *      ViewHolders.
 *  </li>
 * </ul>
 *
 */

import android.content.Context;
import android.view.LayoutInflater;

import com.si.amazonbooks.BuildConfig;
import com.si.amazonbooks.net.services.RetrofitAmazonService;
import com.si.amazonbooks.net.services.ServiceFactory;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class ApplicationModule {

    private final Context mContext;
    private final ServiceFactory mServiceFactory;

    public ApplicationModule(Context context) {
        mContext = context;
        mServiceFactory = new ServiceFactory(mContext);
    }

    @Provides
    LayoutInflater provideInflater() {
        return LayoutInflater.from(mContext);
    }

    @Provides
    RetrofitAmazonService provideChallengeService() {
        return mServiceFactory.createRetrofitService(RetrofitAmazonService.class, BuildConfig.BASE_HOST);
    }
}

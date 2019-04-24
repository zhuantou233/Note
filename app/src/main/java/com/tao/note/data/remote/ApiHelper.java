package com.tao.note.data.remote;

import io.reactivex.Observable;

import dagger.Provides;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.remote
 */
public interface ApiHelper {
    Observable<Boolean> doRequestVerCode(String phone);

    Observable<Boolean> doSignUp(String phone, String password, String code);

    Observable<Boolean> doSignIn(String phone, String password);

    ApiHeader getApiHeader();
}

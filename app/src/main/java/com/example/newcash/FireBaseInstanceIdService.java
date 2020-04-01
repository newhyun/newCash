package com.example.newcash;

import android.app.Service;

import com.google.firebase.messaging.FirebaseMessagingService;

public class FireBaseInstanceIdService extends FirebaseMessagingService {

    // 구글 토큰을 얻는 값입니다. 아래 토큰은 앱이 설치된 디바이스에 대한 고유값으로 푸시를 보낼때 사용됩니다.
    @Override
    public void onNewToken(String string) {

        super.onNewToken(string);
    }

}

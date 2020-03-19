package com.example.newcash;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.kakao.auth.*;
import com.kakao.network.ErrorResult;
import com.kakao.util.helper.log.Logger;

public class KakaoSDKAdapter extends KakaoAdapter {
    /**
     * Session Config에 대해서는 default값들이 존재한다. * 필요한 상황에서만 override해서 사용하면 됨. * @return Session의 설정값.
     */
    @Override
    public ISessionConfig getSessionConfig() {
        return new ISessionConfig() {
            @Override
            public AuthType[] getAuthTypes() {
                return new AuthType[]{AuthType.KAKAO_TALK};
            }

            @Override
            public boolean isUsingWebviewTimer() {
                return false;
            }

            @Override
            public ApprovalType getApprovalType() {
                return ApprovalType.INDIVIDUAL;
            }

            @Override
            public boolean isSaveFormData() {
                return true;
            }
        };
    }

    @Override
    public IApplicationConfig getApplicationConfig() {
        return new IApplicationConfig() {
            @Override
            public Activity getTopActivity() {
                return GlobalApplication.getCurrentActivity();
            }

            @Override
            public Context getApplicationContext() {
                return GlobalApplication.getGlobalApplicationContext();
            }
        };
    }

    public ApiResponseCallback<Integer> getTokenRegisterCallback() {
        return new ApiResponseCallback<Integer>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Toast.makeText(getApplicationConfig().getApplicationContext(), errorResult.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Logger.e(errorResult.getErrorMessage());
                Logger.e("login again...");
            }

            @Override
            public void onNotSignedUp() {
                Logger.e("You should signup first");
            }

            @Override
            public void onSuccess(Integer result) {
                Toast.makeText(getApplicationConfig().getApplicationContext(), "succeeded to register fcm token...", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
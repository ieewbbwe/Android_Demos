package com.webber.demos.google;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.consent.DebugGeography;
import com.webber.demos.R;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.ads.consent.ConsentStatus.NON_PERSONALIZED;
import static com.google.ads.consent.ConsentStatus.PERSONALIZED;
import static com.google.ads.consent.ConsentStatus.UNKNOWN;

public class AdvertiseActivity extends AppCompatActivity {

    private ConsentInformation consentInformation;
    private ConsentForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);
        //为了测试 做一些配置 模拟在欧洲 否则打不开页面
        ConsentInformation.getInstance(this).addTestDevice("872DCACB4F3D4EF8ADB87B24DEAA85A2");

        ConsentInformation.getInstance(this).
                setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);

        //检查同意年龄
        //ConsentInformation.getInstance(this).setTagForUnderAgeOfConsent(false);

        consentInformation = ConsentInformation.getInstance(this);
        request();
        findViewById(R.id.m_show_form_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form.show();
            }
        });
    }

    private void request() {
        String[] publisherId = {"pub-3513878437385321","pub-7350"};
        consentInformation.requestConsentInfoUpdate(publisherId, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                Log.d("picher", "consentCallBack:" + consentStatus.name());
                boolean isInEurp = consentInformation.isRequestLocationInEeaOrUnknown();
                Log.d("picher", "是否在歐洲：" + isInEurp);

                if(isInEurp){
                    //在欧洲
                    if(consentStatus == PERSONALIZED || consentStatus == NON_PERSONALIZED){
                        //表示已经选择过 仅接受非个性化广告
                       /* Bundle extras = new Bundle();
                        extras.putString("npa", "1");

                        PublisherAdRequest request = new PublisherAdRequest.Builder()
                                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                                .build();*/
                    }else if(consentStatus == UNKNOWN){
                        //表示从未选择过 弹出收集同意书 问用户
                        showConsentForm();
                    }
                }else{
                    //不在欧洲
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String s) {
                Log.d("picher", "ConsentFail:" + s);
            }
        });
    }

    private void showConsentForm() {
        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("https://www.adventori.com/fr/avec-nous/mentions-legales/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
        form = new ConsentForm.Builder(this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                        Log.d("picher", "onConsentFormLoaded");
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                        Log.d("picher", "onConsentFormOpened");
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                        Log.d("picher", "onConsentFormClosed:"+consentStatus.name()+"AdFree:"+userPrefersAdFree);
                        ConsentInformation.getInstance(AdvertiseActivity.this)
                                .setConsentStatus(ConsentStatus.PERSONALIZED);
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                        Log.d("picher", "onConsentFormError:"+errorDescription);
                    }
                })
                .withPersonalizedAdsOption()//表示同意书应显示个性化广告选项。
                .withNonPersonalizedAdsOption()//表示同意书应显示非个性化广告选项。
                .withAdFreeOption()//表示同意书应显示无广告应用选项。
                .build();
        form.load();
    }
}

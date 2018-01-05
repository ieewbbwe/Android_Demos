package com.webber.demos.weight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.webber.demos.R;

public class SpannableActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable);
        textView = (TextView) findViewById(R.id.m_spannbel_tv);
        //createClickableText();
        createClickableAllText();
        //createClickableText3();
    }

    private void createClickableText3() {
        String commonProblem = getResources().getString(R.string.payment_common_problem);
        String serviceTel = getResources().getString(R.string.payment_service_tel);
        String email = getResources().getString(R.string.payment_email);

        SpannableStringBuilder commonBuilder = new SpannableStringBuilder(commonProblem);
        SpannableStringBuilder telBuilder = new SpannableStringBuilder(serviceTel);
        SpannableStringBuilder emailBuilder = new SpannableStringBuilder(email);

        commonBuilder.setSpan(onClickableSpan(),
                0, commonProblem.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        telBuilder.setSpan(onClickableSpan(),
                0, telBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        emailBuilder.setSpan(onClickableSpan(),
                0, emailBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(TextUtils.concat());
    }

    private void createClickableAllText() {
        String commonProblem = getResources().getString(R.string.payment_common_problem);
        String serviceTel = getResources().getString(R.string.payment_service_tel);
        String email = getResources().getString(R.string.payment_email);
        String all = String.format(getResources().getString(R.string.payment_issue_tel_email), commonProblem, serviceTel, email);

        SpannableString spannableString = new SpannableString(all);
        spannableString.setSpan(onClickableSpan(), all.indexOf(commonProblem), all.indexOf(commonProblem) + commonProblem.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(onClickableSpan(), all.indexOf(serviceTel), all.indexOf(serviceTel) + serviceTel.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(onClickableSpan(), all.indexOf(email), all.indexOf(email) + email.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    private void createClickableText() {
        String commonProblem = getResources().getString(R.string.payment_common_problem);
        String serviceTel = getResources().getString(R.string.payment_service_tel);
        String email = getResources().getString(R.string.payment_email);

        SpannableStringBuilder commonBuilder = new SpannableStringBuilder(commonProblem);
        SpannableStringBuilder telBuilder = new SpannableStringBuilder(serviceTel);
        SpannableStringBuilder emailBuilder = new SpannableStringBuilder(email);

        commonBuilder.setSpan(onClickableSpan(),
                0, commonProblem.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        telBuilder.setSpan(onClickableSpan(),
                0, telBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        emailBuilder.setSpan(onClickableSpan(),
                0, emailBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CharSequence str = TextUtils.concat(getResources().getString(R.string.payment_issue_tel_email), commonBuilder, telBuilder, email);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        textView.setText(new SpannableString(str));
    }

    private ClickableSpan onClickableSpan() {

        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(SpannableActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.black_overlay));
                ds.setUnderlineText(true);

            }
        };
    }
}

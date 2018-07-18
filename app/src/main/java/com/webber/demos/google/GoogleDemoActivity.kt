package com.webber.demos.google

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.webber.demos.R
import kotlinx.android.synthetic.main.activity_google_demo.*
import android.speech.RecognizerIntent
import android.content.Intent
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.content.DialogInterface
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.webber.demos.MainActivity
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import java.util.*


class GoogleDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_demo)
        googleVoice.setOnClickListener({
            // 顯示語音Dialog
            //openGoogleVoice()
            // 自定義Dialog
            showDialog()
        })
    }

    private fun showDialog() {
        // 通过Intent传递语音识别的模式，开启语音
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        // 语言模式和自由模式的语音识别
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        // 提示语音开始
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請嘗試説話\n以轉換新聞分類")
        Log.d("picher", "字體:" + Locale.getDefault().toString())

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toString())
        // 开始语音识别
        startActivityForResult(intent, 1)

    }


    private fun openGoogleVoice() {
        //开启语音识别功能
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        //设置模式，目前设置的是自由识别模式
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        //提示语言开始文字，就是效果图上面的文字
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please start your voice")
        startActivityForResult(intent, 1);
        showTv.text = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if (resultCode == Activity.RESULT_OK && data != null){
                val text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                Log.d("picher", Gson().toJson(text))
                showTv.text = (text[0])
            }
        }
    }
}

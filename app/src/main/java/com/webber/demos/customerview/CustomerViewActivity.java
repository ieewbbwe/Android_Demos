package com.webber.demos.customerview;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webber.demos.R;

import java.net.URL;

public class CustomerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        LinearLayout linearLayout = findViewById(R.id.activity_customer_view);

        String str = "twmotherlode://article?a=1_1248785&m=10101";

        Uri uri = Uri.parse(str);

        Log.d("picher","Sche:"+uri.getScheme());
        Log.d("picher","Path:"+uri.getPath());
        Log.d("picher","Port:"+uri.getPort());
        Log.d("picher","Host"+uri.getHost());
        Toast.makeText(this,uri.getQueryParameter("a"),Toast.LENGTH_SHORT).show();


        TextView textView = createDefaultTabView(this);
        String str3 = "男\u2022女";
        String api="男．女";
        Log.d("picher","."+api.contains("."));
        Log.d("picher","．"+api.contains("."));
        Log.d("picher","·"+api.contains("·"));
        Log.d("picher","．"+api.contains("．"));

        textView.setText(str3);
        linearLayout.addView(textView);

        String str1 = ".";
        String str2 = "．";

        int temp = (int)str.toCharArray()[0];
        Log.d("picher","str:"+(int)str1.toCharArray()[0]+"str1:"+(int)str1.toCharArray()[0]);
        Log.d("picher","111:"+isAllFullWidth(str1)+"22:"+isAllFullWidth(str3));
    }


    public void change(){
        String org="新莊市１２３";
        char[] chars = org.toCharArray();
        int tranTemp = 0;

        for(int i = 0; i < chars.length; i++){
            tranTemp = (int)chars[i];
            if(tranTemp-65248 >= 48 && tranTemp-65248<=57) //ASCII碼: 是number
                tranTemp -= 65248; //此數字是 Unicode編碼轉為十進位 和 ASCII碼的 差
            org += (char)tranTemp;
        }
    }

    public static boolean isAllFullWidth(String str) {
        for (char c : str.toCharArray())
            if ((c & 0xff00) != 0xff00)
                return false;
        return true;
    }

    protected TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.global_article_topic_tab_textsize));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setMaxLines(1);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        textView.setBackgroundResource(outValue.resourceId);
        //textView.setTextColor(Color.WHITE);
        //textView.setAllCaps(true);

        int padding = (int) (11 * getResources().getDisplayMetrics().density);
        int topBottomPadding = (int) (11 * getResources().getDisplayMetrics().density);
        int topPadding = topBottomPadding;
        int bottomPadding = topBottomPadding;
        textView.setPadding(padding, topPadding, padding, bottomPadding);
        return textView;
    }
}

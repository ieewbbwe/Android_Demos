package com.webber.demos.view.dialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.webber.demos.R
import kotlinx.android.synthetic.main.activity_dialog_demo.*

class DialogDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_demo)
        showDialog.setOnClickListener({
            var dialog:VoiceDialog = VoiceDialog()
            dialog.show(supportFragmentManager, "voicedialog")
        })
    }
}

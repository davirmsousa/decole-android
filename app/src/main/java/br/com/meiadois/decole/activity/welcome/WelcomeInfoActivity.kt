package br.com.meiadois.decole.activity.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.meiadois.decole.R
import kotlinx.android.synthetic.main.activity_welcome_info.*

class WelcomeInfoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_info)

        nextBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == nextBtn.id) {
            val intent = Intent(this, WelcomeSlideActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

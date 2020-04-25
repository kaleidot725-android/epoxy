package jp.kaleidot725.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.kaleidot725.sample.view.HeaderCustomViewController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        epoxy_recycler_view.setControllerAndBuildModels(HeaderCustomViewController())
        epoxy_recycler_view.requestModelBuild()
    }
}

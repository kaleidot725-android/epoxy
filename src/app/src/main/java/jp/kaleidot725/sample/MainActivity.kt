package jp.kaleidot725.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kaleidot725.sample.view.HeaderDatabindingViewController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = HeaderDatabindingViewController()
        recycler_view.adapter = controller.adapter
        recycler_view.layoutManager = LinearLayoutManager(applicationContext).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        controller.setData(listOf("one", "two", "three"), false)
    }
}

package at.fh.swengb.loggingviewsandactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import at.fh.swengb.loggingviewsandactivity.LessonListActivity.Companion.ADD_OR_EDIT_RATING_REQUEST
import at.fh.swengb.loggingviewsandactivity.common.SharedClass
import com.squareup.moshi.JsonClass
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val str = SharedClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("MyActivity", "onCreate")
        Log.d("MyActivity", "onCreate")
        Log.i("MyActivity", "onCreate")
        Log.w("MyActivity", "onCreate")
        Log.e("MyActivity", "onCreate")
        Log.e("shared","$str")

        share_main.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

        open_views.setOnClickListener {
            val intent = Intent(this, ViewsActivity::class.java)
            startActivity(intent)
        }

        open_rating.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        open_lessons.setOnClickListener {
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)
        }

        /*open_higher_order.setOnClickListener {
            val intent = Intent(this, HigherOrderActivity::class.java)
            startActivity(intent)
        }*/
    }

    override fun onStart() {
        super.onStart()
        Log.w("MyActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("MyActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w("MyActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("MyActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("MyActivity", "onDestroy")
    }
}

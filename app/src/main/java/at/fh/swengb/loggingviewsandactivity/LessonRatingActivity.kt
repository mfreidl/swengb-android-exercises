package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.moshi.JsonClass
import kotlinx.android.synthetic.main.activity_lesson_rating.*
import kotlinx.android.synthetic.main.activity_rating.*

class LessonRatingActivity : AppCompatActivity() {

    var lessonName : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)

        if (lessonId == null) {
            finish()
        } else {
            LessonRepository.lessonById(
                    id = lessonId,
            success = {
                lesson_rating_header.text = it.name
                lessonName = it.name
            },
            error = {
                Log.e("ERROR", it)
            })

            rate_lesson.setOnClickListener{
                val myRating = lesson_rating_bar.rating.toDouble()
                val myFeedback = lesson_feedback.text.toString()
                val lessonRating = LessonRating(myRating,myFeedback)

                LessonRepository.rateLesson(lessonId, lessonRating)

                setResult(Activity.RESULT_OK)
                finish()

            }
        }
        }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId ?: 0) {
            R.id.logout -> consume { Toast.makeText(this, getString(R.string.logout), Toast.LENGTH_SHORT).show() }
            R.id.share -> consume { Toast.makeText(this, getString(R.string.share), Toast.LENGTH_SHORT).show() }
            R.id.save -> consume { Toast.makeText(this, getString(R.string.save), Toast.LENGTH_SHORT).show() }
            R.id.play -> consume { Toast.makeText(this, getString(R.string.play), Toast.LENGTH_SHORT).show() }
            R.id.stop -> consume { Toast.makeText(this, getString(R.string.stop), Toast.LENGTH_SHORT).show() }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


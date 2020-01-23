package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import at.fh.swengb.loggingviewsandactivity.common.LessonRating
import at.fh.swengb.loggingviewsandactivity.common.LessonRepository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_lesson_rating.*
import java.math.RoundingMode

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
                Glide
                    .with(imageView)
                    .load(it.imageUrl)
                    .into(imageView)
                item_lesson_avg_rating_bar2.rating = it.ratingAverage().toFloat()
                item_lesson_avg_rating_value.text = it.ratingAverage().toBigDecimal().setScale(2, RoundingMode.CEILING).toString()
                comments.text = it.ratings.filter { it.feedback != "" }.joinToString { it.feedback + "\n\n" }

            },
            error = {
                Log.e("ERROR", it)
            })

            rate_lesson.setOnClickListener{
                val myRating = lesson_rating_bar.rating.toDouble()
                val myFeedback = lesson_feedback.text.toString()
                val lessonRating =
                    LessonRating(myRating, myFeedback)

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


package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_lesson_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_lesson.*

class LessonListActivity : AppCompatActivity() {
    val lessonAdapter = LessonAdapter() {val intent = Intent(this, LessonRatingActivity::class.java)
        intent.putExtra(EXTRA_LESSON_ID, it.id)
        startActivityForResult(intent, ADD_OR_EDIT_RATING_REQUEST)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        LessonRepository.lessonsList(
                success = {
                    lessonAdapter.updateList(it)
                },
                error = {
                    Log.e("ERROR", it)
                })
        lesson_recycler_view.layoutManager = LinearLayoutManager(this)
        lesson_recycler_view.adapter = lessonAdapter
        parseJson()
        SleepyAsyncTask().execute()
}
    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == Activity.RESULT_OK) {
            LessonRepository.lessonsList(
                    success = {
                        lessonAdapter.updateList(it)
                    },
                    error = {
                        Log.e("ERROR", it)
                    })
        }
    }

    fun parseJson() {

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)

        val json = """
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Sanja Illes"
                    }
                ],
                "ratings": [],
                "imageUrl": ""
            }
        """

        val result = jsonAdapter.fromJson(json)

        Log.e("JSON", "${result?.name}")

    }
}
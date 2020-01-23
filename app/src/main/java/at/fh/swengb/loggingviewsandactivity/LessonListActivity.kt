package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import at.fh.swengb.loggingviewsandactivity.common.LessonRepository
import at.fh.swengb.loggingviewsandactivity.common.SleepyAsyncTask
import kotlinx.android.synthetic.main.activity_lesson_list.*

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

}
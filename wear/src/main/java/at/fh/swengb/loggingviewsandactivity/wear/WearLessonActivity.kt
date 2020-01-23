package at.fh.swengb.loggingviewsandactivity.wear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.widget.Toast
import androidx.wear.widget.WearableLinearLayoutManager
import kotlinx.android.synthetic.main.activity_wear_lesson.*


class WearLessonActivity : WearableActivity() {

    val lessonAdapter = LessonAdapter() {
        Toast.makeText(this, "Lesson with name ${it.name} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wear_lesson)

        wearable_recycler.layoutManager = WearableLinearLayoutManager(this)
        wearable_recycler.adapter = lessonAdapter
        wearable_recycler.isEdgeItemsCenteringEnabled = true

        loadLessons()
    }

    private fun loadLessons() {
        at.fh.swengb.loggingviewsandactivity.common.LessonRepository.lessonsList(
            success = {
                lessonAdapter.updateList(it)
            },
            error = {
                Log.e("REPOSITORY_ERROR", it)
            }
        )
    }
}
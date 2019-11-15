package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Lesson(val id : String, val name : String, val date : String, val topic : String, val type: LessonType, val lecturers : List<Lecturer>, val ratings: List<LessonRating>) {
    fun ratingAverage() : Double{
        return 0.0
    }
}


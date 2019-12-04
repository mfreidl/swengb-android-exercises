package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Lesson(val id : String, val name : String, val date : String, val topic : String, val type: LessonType, val lecturers : List<Lecturer>, val ratings: MutableList<LessonRating>) {
    fun ratingAverage() : Double{
        var average = ratings.map { it.ratingValue }.average()

        if (average.isNaN()) {
            average = 0.0
        }
        return average
    }
}


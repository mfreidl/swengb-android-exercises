package at.fh.swengb.loggingviewsandactivity.common

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Lesson(val id : String, val name : String, val date : String, val topic : String, val type: LessonType, val lecturers : List<Lecturer>, val ratings: MutableList<LessonRating>, val imageUrl : String) {
    fun ratingAverage() : Double{
        var average = ratings.map { it.ratingValue }.average()

        if (average.isNaN()) {
            average = 0.0
        }
        return average
    }
}


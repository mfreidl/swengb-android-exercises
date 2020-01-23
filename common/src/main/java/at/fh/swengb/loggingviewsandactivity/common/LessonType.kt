package at.fh.swengb.loggingviewsandactivity.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.moshi.JsonClass

enum class LessonType(val description: String) {
    LECTURE("Lecture"),
    PRACTICAL("Practical")
}

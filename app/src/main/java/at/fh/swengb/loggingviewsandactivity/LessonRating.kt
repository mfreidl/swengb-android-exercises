package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LessonRating(val ratingValue : Double, val feedback : String)


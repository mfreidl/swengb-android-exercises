package at.fh.swengb.loggingviewsandactivity.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Lecturer(val name : String)
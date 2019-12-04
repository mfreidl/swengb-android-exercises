package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HigherOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_order)
        myHigherOrderFunction{a -> a*102}
        myHigherOrderFunction(fun(a: Int): Int = a * 102)
        kotlinHigherOrderWithList()
    }

    private fun myHigherOrderFunction(param: (Int) -> Int) {
        if (param(6) == 612) {
            Log.e("HIGHER_ORDER", "Congrats, your first lambda works :)")
        }
    }

    private fun kotlinHigherOrderWithList() {

        val list = LessonRepository.lessonsList()

        val heldByBloder = list.filter{it.lecturers.any{x -> x.name == "Lukas Bloder"}}.joinToString { it.name }   //list.filter{ it.lecturers.contains(Lecturer("Lukas Bloder"))}
        // so that you create a list of
        // all lessons held by Lukas Bloder
        // print the list to logcat
        // notice something weird in the output?

        val topicMap = list.map{ it.topic to it}.toMap()
        // use Kotlins built-in higher order functions
        // on list in order to get a Map<String>, List <Lesson>, Map<String, Lesson>??
        // with the map Key being the lesson topic
        // print the resulting map to logcat

        val avgLecture =
            if (list.filter{it.type == LessonType.LECTURE}.map{it.ratings.count()}.sum() == 0)
                0
            else list.filter{it.type == LessonType.LECTURE}.map{it.ratingAverage()}.sum() / list.filter{it.type == LessonType.LECTURE}.map{it.ratings.count()}.sum()
        // calculate the average rating of all LECTURES
        // print the result to logcat


        Log.e("Bloder", "$heldByBloder")

        Log.e("Map","$topicMap")

        Log.e("Average","$avgLecture")
    }
}

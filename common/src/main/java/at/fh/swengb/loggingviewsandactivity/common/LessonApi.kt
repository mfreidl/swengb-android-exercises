package at.fh.swengb.loggingviewsandactivity.common

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

object LessonApi {
    const val accessToken = "8cb649a3-5921-421c-b61e-0d1085a1631a"
    val retrofit: Retrofit
    val retrofitService: LessonApiService
    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://lessons.bloder.xyz")
            .build()
        retrofitService = retrofit.create(
            LessonApiService::class.java)
    }
}
interface LessonApiService {
    @GET("/${LessonApi.accessToken}/lessons")
    fun lessons(): Call<List<Lesson>>

    @GET("/${LessonApi.accessToken}/lessons/{id}")
    fun lessonById(@Path("id") lessonId: String) :Call<Lesson>

    @POST("/${LessonApi.accessToken}/lessons/{id}/rate")
    fun rateLesson(@Path("id") lessonId: String, @Body rating: LessonRating): Call<Unit>
}
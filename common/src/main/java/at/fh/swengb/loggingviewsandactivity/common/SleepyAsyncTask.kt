package at.fh.swengb.loggingviewsandactivity.common

import android.os.AsyncTask
import android.util.Log

class SleepyAsyncTask(): AsyncTask<Unit, Unit, Unit>() {

    override fun onPreExecute() {
        super.onPreExecute()
        Log.e("SleepingAsyncTask", "Going to sleep")
        val thr = Thread.currentThread().name
        Log.e("POST", "$thr")
    }

    override fun doInBackground(vararg params: Unit?) {
        val thr = Thread.currentThread().name
        Log.e("POST", "$thr")
        Thread.sleep(5000)
        Log.e("POST", "$thr")
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        val thr = Thread.currentThread().name
        Log.e("SleepingAsyncTask", "Done sleeping")
        Log.e("POST", "$thr")
    }
}
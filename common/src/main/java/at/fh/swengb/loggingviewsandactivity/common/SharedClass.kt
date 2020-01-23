package at.fh.swengb.loggingviewsandactivity.common

open class SharedClass {
    val str : String = "Shared between app and wear"
    fun mySharedFunction() : String{
        return str
    }
}
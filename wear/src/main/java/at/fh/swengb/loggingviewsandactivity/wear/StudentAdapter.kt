package at.fh.swengb.loggingviewsandactivity.wear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.loggingviewsandactivity.common.Lesson
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item.view.*

class LessonAdapter(val clickListener: (lesson: Lesson) -> Unit): RecyclerView.Adapter<LessonViewHolder>() {

    private var lessonList = listOf<Lesson>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val lessonItemView = inflater.inflate(R.layout.item, parent, false)
        return LessonViewHolder(lessonItemView, clickListener)
        }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        var lesson = lessonList[position]
        holder.bindItem(lesson)
    }

    fun updateList(newList: List<Lesson>) {
        lessonList = newList
        notifyDataSetChanged()
    }
}

class LessonViewHolder(itemView: View, val clickListener: (lesson: Lesson) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(lesson: Lesson) {
        Glide.with(itemView)
            .load(lesson.imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.imageView)
        itemView.item_lesson_name.text = lesson.name
        itemView.setOnClickListener {
            clickListener(lesson)
        }
    }
}
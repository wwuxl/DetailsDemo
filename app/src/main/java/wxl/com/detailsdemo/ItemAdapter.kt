package wxl.com.detailsdemo

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class ItemAdapter(var context:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var datas=ArrayList<String>()
    init {
        for (i in 1..10){
            datas.add("小明同学$i")
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var textView = TextView(context)
        var params=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(30,40,30,40)
        textView.setPadding(30,40,30,40)
        textView.setBackgroundColor(Color.parseColor("#0ff000"))
        textView.layoutParams=params
        return MyViewHolder(textView)
    }

    override fun getItemCount(): Int =datas.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
       var textView=p0.itemView as TextView
        textView.text=datas[p1]

    }

    inner class MyViewHolder(itemView :View):RecyclerView.ViewHolder(itemView)

}
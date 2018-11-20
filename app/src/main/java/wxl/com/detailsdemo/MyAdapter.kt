package wxl.com.detailsdemo

import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.com_view.view.*
import kotlinx.android.synthetic.main.detail_view.view.*
import wxl.com.detailsdemo.R.id.recyclerView
import wxl.com.detailsdemo.R.id.webView
import java.lang.StringBuilder

class MyAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var datas: ArrayList<String> = ArrayList()
    private val PRO_TYPE = 0
    private val DETAIL_TYPE = 1
    private val COM_TYPE = 2
    private val RECOM_TYPE = 3

    init {
        Log.e("===","init")
        datas.add("商品")
        datas.add("详情")
        datas.add("评价")
        datas.add("推荐")

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        Log.e("===","onCreateViewHolder")
        if (p1 == PRO_TYPE) {
            return ProViewHolder(LayoutInflater.from(context).inflate( R.layout.pro_view, p0,false))
        } else if (p1 == DETAIL_TYPE) {
            return DetailViewHolder(LayoutInflater.from(context).inflate( R.layout.detail_view, p0,false))
        } else if (p1 == COM_TYPE) {
            return ComViewHolder(LayoutInflater.from(context).inflate( R.layout.com_view, p0,false))
        } else {
            return RecomViewHolder(LayoutInflater.from(context).inflate( R.layout.recom_view, p0,false))
        }

    }

    override fun getItemCount(): Int {
        val size = datas.size
        //Log.e("===","size = $size")
        return size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        Log.e("===","onBindViewHolder")
        when (getItemViewType(p1)) {
            PRO_TYPE -> {
                var viewHolder = p0 as ProViewHolder

            }
            DETAIL_TYPE -> {
                var viewHolder = p0 as DetailViewHolder
                var webView = viewHolder.detailItemView.webView
                Log.e("===","webView= $webView")
                initWebView(webView)
            }
            COM_TYPE -> {
                var viewHolder = p0 as ComViewHolder
                var itemRecyclerView = viewHolder.comItemView.itemRecyclerView
                Log.e("===","itemRecyclerView= $itemRecyclerView")
                itemRecyclerView.isFocusableInTouchMode = false
                itemRecyclerView.layoutManager=LinearLayoutManager(context)
                itemRecyclerView.adapter=ItemAdapter(context)

            }
            RECOM_TYPE -> {
                var viewHolder = p0 as RecomViewHolder


            }
        }
    }


    override fun getItemViewType(position: Int): Int {
       // Log.e("===","getItemViewType position = $position")
        if (position == 0) {
            return PRO_TYPE
        } else if (position == 1) {
            return DETAIL_TYPE
        } else if (position == 2) {
            return COM_TYPE
        } else if (position == 3) {
            return RECOM_TYPE
        }
        return super.getItemViewType(position)
    }


    private fun initWebView(webView: WebView){
        //要加载的页面
        webView.setVerticalScrollBarEnabled(true)
        webView.setHorizontalScrollBarEnabled(true)
        webView.setSaveEnabled(false)
        var webSettings = webView.getSettings()
        //有网络，去网路获取数据
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSupportZoom(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);

        //如果少了这句话,iframe的视屏将加载不出来
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //webSettings.setUseWideViewPort(true);
        //webSettings.setLoadWithOverviewMode(true);

        //加载进来的html自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置载入页面自适应手机屏幕，居中显示,测试没有起作用
        webSettings.setLoadWithOverviewMode(true);
        webView.requestFocus()

        var header = "<html>\n" +
                "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>*{margin:0;padding:0;}img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>" +
                "<body>"

var content = "<div class=\"ssd-module M15030361414309 animate-M15030361414309\" style=\"margin: 0px; padding: 0px; background-repeat: no-repeat; background-position: left top; background-size: 100% 100%; width: 750px; position: relative; overflow: hidden; height: 1139px; background-color: rgb(203, 203, 203); background-image: url(&quot;https://img30.360buyimg.com/sku/jfs/t9436/99/44329289/234704/d3b059df/599f8d41N30be205c.jpg&quot;); color: rgb(102, 102, 102); font-family: tahoma, arial, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, u5b8bu4f53, sans-serif; font-size: 12px; white-space: normal;\"></div>\n" +
        "<div class=\"ssd-module M15031471983251 animate-M15031471983251\" style=\"margin: 0px; padding: 0px; background-repeat: no-repeat; background-position: left top; background-size: 100% 100%; width: 750px; position: relative; overflow: hidden; height: 688px; background-color: rgb(179, 179, 179); background-image: url(&quot;https://img30.360buyimg.com/sku/jfs/t7339/58/1712533552/137078/8997ac6c/599f8d40Nb59a1733.jpg&quot;); color: rgb(102, 102, 102); font-family: tahoma, arial, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, u5b8bu4f53, sans-serif; font-size: 12px; white-space: normal;\"></div>"

        var tail = "</body>\n" +
                "</html>"
        var sb=StringBuilder()
        sb.append(header)
        sb.append(content)
        sb.append(tail)

        webView.loadDataWithBaseURL("", sb.toString(), "text/html", "UTF-8", "")

    }


    inner class ProViewHolder(var proItemView: View) : RecyclerView.ViewHolder(proItemView)
    inner class DetailViewHolder(var detailItemView: View) : RecyclerView.ViewHolder(detailItemView)
    inner class ComViewHolder(var comItemView: View) : RecyclerView.ViewHolder(comItemView)
    inner class RecomViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView)
}
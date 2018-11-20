package wxl.com.detailsdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import wxl.com.detailsdemo.R.id.recyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()

        initEvent()

    }

    var isDown: Boolean = false
    var view: View? = null
    private fun initEvent() {
        product.setOnClickListener {
            moveToPosition(0)
            product.isSelected = !product.isSelected
            view?.isSelected = false
            view = product
            isDown = true
        }
        detail.setOnClickListener {
            moveToPosition(1)
            detail.isSelected = !detail.isSelected
            view?.isSelected = false
            view = detail
            isDown = true
        }
        commont.setOnClickListener {
            moveToPosition(2)
            commont.isSelected = !commont.isSelected
            view?.isSelected = false
            view = commont
            isDown = true
        }
        tj.setOnClickListener {
            moveToPosition(3)
            tj.isSelected = !tj.isSelected
            view?.isSelected = false
            view = tj
            isDown = true
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var firstPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                var childCount = recyclerView.childCount
                //Log.e("===", "onScrolled  dy = $dy  firstPosition= $firstPosition  childCount$childCount")
                if (!isDown) {
                    when (firstPosition) {
                        0 -> {
                            if (view != product) {
                                product.isSelected = true
                                view?.isSelected = false
                                view = product
                            }

                        }
                        1 -> {
                            if (view != detail) {
                                detail.isSelected = true
                                view?.isSelected = false
                                view = detail

                            }
                        }
                        2 -> {
                            if (view != commont) {
                                commont.isSelected = true
                                view?.isSelected = false
                                view = commont

                            }
                        }
                        3 -> {
                            if (view != tj) {
                                tj.isSelected = true
                                view?.isSelected = false
                                view = tj

                            }
                        }

                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.e("===", "onScrollStateChanged = $newState")
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    // mShouldScroll = false
                    var firstPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    Log.e("===", "firstPositon= $firstPosition")

                }
                isDown=false
            }

        })

    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = MyAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.isFocusableInTouchMode = false


    }

    var position: Int = 0
    private fun moveToPosition(position: Int) {
        this.position = position
        Log.e("===", "position= $position")
        if (position != -1) {
            recyclerView.scrollToPosition(position)
            var mLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            mLayoutManager.scrollToPositionWithOffset(position, 0)

        }
    }

}

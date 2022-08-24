
package unifedideas.ama.gamekotlin.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import unifedideas.ama.kotlinapp.util.AppUtil


class MarginDecoration : ItemDecoration {
    private var margin = 0

    private var isTopBottom = false

    /**
     * Instantiates a new Margin decoration.
     *
     * @param context the context
     * @param margin  the margin
     */
    constructor(context: Context, margin: Int) {
        this.margin = context.let { AppUtil.convertDpToPixel(it, margin.toFloat()) }
        isTopBottom = false
    }

    /**
     * Instantiates a new Margin decoration.
     *
     * @param context     the context
     * @param margin      the margin
     * @param isTopBottom the is top bottom
     */
    constructor(context: Context?, margin: Int, isTopBottom: Boolean) {
        this.margin = context?.let { AppUtil.convertDpToPixel(it, margin.toFloat()) }!!
        this.isTopBottom = isTopBottom
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager: RecyclerView.LayoutManager? = parent.layoutManager
        val position: Int = parent.getChildAdapterPosition(view)
        val isLast = position == state.getItemCount() - 1
        if (layoutManager is GridLayoutManager) {
            outRect[margin, margin, margin] = margin
        } else {
            if (isTopBottom) outRect[0, margin, 0] = margin else {
                if (position == 0) {
                    outRect[margin, margin, margin] = margin / 2
                } else if (isLast) {
                    outRect[margin, margin / 2, margin] = margin
                } else {
                    outRect[margin, margin / 2, margin] = margin / 2
                }
            }
        }
    }
}
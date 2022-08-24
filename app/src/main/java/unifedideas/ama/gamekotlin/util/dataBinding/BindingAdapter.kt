package unifedideas.ama.gamekotlin.util.dataBinding

import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.google.android.material.textfield.TextInputLayout
import unifedideas.ama.gamekotlin.R
import unifedideas.ama.gamekotlin.features.allGames.adapter.GamesAdapter
import unifedideas.ama.gamekotlin.util.MarginDecoration
import unifedideas.ama.gamekotlin.util.TextChange
import java.io.File

@BindingAdapter("loadImage")
fun loadImage(view: SimpleDraweeView, url: String?) {
    Log.e("LoadImage", "URL : $url")
    val mDraweeController: DraweeController
    mDraweeController = if (URLUtil.isValidUrl(url)) {
        Fresco.newDraweeControllerBuilder()
            .setUri(url)
            .setAutoPlayAnimations(true)
            .build()
    } else if (url != null && !url.isEmpty()) {
        val picUri = Uri.fromFile(File(url))
        val mImageRequest = ImageRequestBuilder.newBuilderWithSource(picUri).build()
        Fresco.newDraweeControllerBuilder()
            .setUri(mImageRequest.sourceUri)
            .setAutoPlayAnimations(true)
            .build()
    } else {
        val mImageRequest =
            ImageRequestBuilder.newBuilderWithResourceId(R.drawable.placeholder).build()
        Fresco.newDraweeControllerBuilder()
            .setUri(mImageRequest.sourceUri)
            .setAutoPlayAnimations(true)
            .build()
    }
    view.controller = mDraweeController
}

@BindingAdapter("marginDecoration")
fun setMarginDecoration(view: RecyclerView, margin: Int) {
    val marginDecoration = MarginDecoration(view.context, margin)
    view.addItemDecoration(marginDecoration)
}

@BindingAdapter("textChange")
fun setTextChange(view: EditText, mTextChange: TextChange) {
    view.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mTextChange.onChange(s.toString())
        }

        override fun afterTextChanged(s: Editable) {}
    })
}


@BindingAdapter("errorText")
fun setErrorText(view: TextInputLayout, errorRes: Int?) {
    if (errorRes == null || errorRes == 0) {
        view.error = ""
    } else {
        view.error = view.context.resources.getString(errorRes)
    }
}


@BindingAdapter("textViewError")
fun setErrorText(view: TextView, errorRes: Int?) {
    if (errorRes == null || errorRes == 0) {
        view.error = ""
    } else {
        view.error = view.context.resources.getString(errorRes)
    }
}

@BindingAdapter("setImage")
fun setImage(view: ImageView, res: Int?) {
    if (res != null) {
        view.setImageResource(res)
    }
}

@BindingAdapter("textViewChange")
fun setTextChange(view: TextView, mTextChange: TextChange) {
    view.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mTextChange.onChange(s.toString())
        }

        override fun afterTextChanged(s: Editable) {}
    })
}


@BindingAdapter("gamesAdapter")
fun setAdapter(view: RecyclerView, adapter: GamesAdapter?) {
    view.adapter = adapter
}


@BindingAdapter("setRefreshing")
fun setRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean?) {
    isRefreshing?.let { view.isRefreshing = it }
}

@BindingAdapter("onRefreshListener")
fun setRefreshing(
    view: SwipeRefreshLayout,
    mListener: SwipeRefreshLayout.OnRefreshListener?
) {
    view.setColorSchemeResources(R.color.colorPrimary)
    view.setOnRefreshListener(mListener)
}


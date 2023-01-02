package fr.systemathicdev.commons.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import fr.systemathicdev.commons.R

const val HEADER_USER_AGENT = "User-Agent"
const val HEADER_ACCEPT_ENCODING = "Accept-Encoding"
const val HEADER_ACCEPT_ENCODING_ALL = "*-Encoding"

@BindingAdapter(
    "async",
    "placeHolderLoadingResource",
    "placeHolderErrorResource",
    requireAll = false
)
fun ImageView.setImageUrl(
    url: String?,
    placeHolderLoadingResource: Int = R.drawable.ic_placeholder,
    placeHolderErrorResource: Int = R.drawable.ic_placeholder_ko,
) {
    url?.run {
        setImage(
            placeHolderLoadingResource,
            placeHolderErrorResource,
        ) {
            it.load(
                GlideUrl(
                    this,
                    LazyHeaders.Builder()
                        .setHeader(
                            HEADER_USER_AGENT,
                            null as String?
                        ).setHeader(
                            HEADER_ACCEPT_ENCODING,
                            HEADER_ACCEPT_ENCODING_ALL
                        ).build()
                )
            )
        }
    }
}

fun <T : Any> ImageView.setImage(
    placeHolderLoadingResource: Int = R.drawable.ic_placeholder,
    placeHolderErrorResource: Int = R.drawable.ic_placeholder_ko,
    loader: (RequestManager) -> RequestBuilder<T>
) {
    Glide
        .with(this)
        .let {
            loader(it)
        }
        .placeholder(placeHolderLoadingResource)
        .error(placeHolderErrorResource)
        .into(this)
}
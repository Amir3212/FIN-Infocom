package com.amir.fininfocom.controller

import com.amir.fininfocom.R
import com.amir.fininfocom.databinding.HeaderViewBinding
import com.amir.fininfocom.utils.epoxy.ViewBindingKotlinModel


data class HeaderEpoxyModel(
    val header: String,
) : ViewBindingKotlinModel<HeaderViewBinding>(R.layout.header_view) {
    override fun HeaderViewBinding.bind() {
        textView3.text = header
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}
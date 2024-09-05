package com.amir.fininfocom.controller

import androidx.core.content.ContextCompat
import com.amir.fininfocom.R
import com.amir.fininfocom.databinding.HomeViewBinding
import com.amir.fininfocom.model.NumberModel
import com.amir.fininfocom.model.NumberUI
import com.amir.fininfocom.utils.App
import com.amir.fininfocom.utils.epoxy.ViewBindingKotlinModel

data class NumberEpoxyModel(
    val model: NumberUI,
    val onNumberClick: (NumberModel) -> Unit,
) : ViewBindingKotlinModel<HomeViewBinding>(R.layout.home_view) {
    override fun HomeViewBinding.bind() {
        numberModel = model.numberModel


        val stokeColor =
            if (model.isSelected) model.selectedColor else R.color.default_rule_stoke_color

        val strokeWidthForCard = if (model.isSelected) 10 else 4


        numberCardView.apply {
            strokeColor = ContextCompat.getColor(App.instance, stokeColor)
            strokeWidth = strokeWidthForCard
        }

        textView.setOnClickListener {
            onNumberClick.invoke(model.numberModel)
        }
    }
}
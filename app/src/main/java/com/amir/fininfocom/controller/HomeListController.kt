package com.amir.fininfocom.controller

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.amir.fininfocom.R
import com.amir.fininfocom.databinding.RuleViewBinding
import com.amir.fininfocom.model.HomeUI
import com.amir.fininfocom.model.RuleIDS
import com.amir.fininfocom.model.RuleModel
import com.amir.fininfocom.model.RuleUI
import com.amir.fininfocom.utils.App
import com.amir.fininfocom.utils.epoxy.ViewBindingKotlinModel
import com.amir.fininfocom.viewModels.HomeViewModel

class HomeListController(private val viewModel: HomeViewModel) : TypedEpoxyController<HomeUI>() {

    override fun buildModels(userData: HomeUI?) {

        if (userData == null) return


        //Rule view Title here
        HeaderEpoxyModel(HEADER_TEXT_RULE).id(HEADER_TEXT_RULE_ID).addTo(this)

        //adding the rule view here
        val ruleItems = userData.ruleUI.map {
            RuleEpoxyModel(it) { ruleModel ->
                viewModel.updateRuleSelection(ruleModel)
            }.id(it.ruleModel.rule)
        }
        CarouselModel_().id(HEADER_RULE_VIEW_ID).models(ruleItems).numViewsToShowOnScreen(2.5f)
            .addTo(this)


        //Number view Title here
        HeaderEpoxyModel(HEADER_TEXT_NUMBER).id(HEADER_TEXT_NUMBER_ID).addTo(this)


        //adding the number views here
        userData.numberUI.forEach {
            NumberEpoxyModel(it) {
                // viewModel.updateSelectedNumberIds(it)
            }.id(it.numberModel.text).addTo(this)
        }

    }


    companion object {
        const val HEADER_TEXT_RULE = "Rules"
        const val HEADER_TEXT_NUMBER = "Result:-"
        const val HEADER_TEXT_NUMBER_ID = "result"
        const val HEADER_RULE_VIEW_ID = "rule_view"
        const val HEADER_TEXT_RULE_ID = "rule_list"
    }
}


data class RuleEpoxyModel(
    val model: RuleUI,
    val onRuleClick: (RuleModel) -> Unit,
) : ViewBindingKotlinModel<RuleViewBinding>(R.layout.rule_view) {
    override fun RuleViewBinding.bind() {
        ruleModel = model.ruleModel
        textView2.setOnClickListener {
            onRuleClick.invoke(model.ruleModel)
        }
        val stokeColor =
            if (model.isSelected) model.selectedColor else R.color.default_rule_stoke_color
        val strokeWidthForCard = if (model.isSelected) 10 else 4

        ruleCardView.apply {
            strokeColor = ContextCompat.getColor(App.instance, stokeColor)
            strokeWidth = strokeWidthForCard
        }


    }


    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}
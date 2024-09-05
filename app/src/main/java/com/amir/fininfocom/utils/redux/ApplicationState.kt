package com.amir.fininfocom.utils.redux

import com.amir.fininfocom.model.NumberModel
import com.amir.fininfocom.model.RuleIDS
import com.amir.fininfocom.model.RuleModel

data class ApplicationState(
    val isKeyBoardOpen: Boolean = false,
    val numList: List<NumberModel> = getList(),
    val ruleList: List<RuleModel> = getRuleList(),
    val selectedRuleIds: Set<RuleIDS> = setOf(),
    val selectedNumberIds: Set<Int> = setOf(),
)

fun getRuleList(): List<RuleModel> {
    val list: MutableList<RuleModel> = ArrayList()
    list.add(RuleModel(rule = "Even", id = RuleIDS.EVEN))
    list.add(RuleModel(rule = "Odd", id = RuleIDS.ODD))
    list.add(RuleModel(rule = "Prime", id = RuleIDS.PRIME))
    list.add(RuleModel(rule = "Fibonacci", id = RuleIDS.FIBONACCI))
    return list
}

fun getList(): List<NumberModel> {
    val list: MutableList<NumberModel> = ArrayList()
    repeat(101) {
        if (it > 0) list.add(
            NumberModel(
                text = "$it", id = it
            )
        )
    }
    return list
}

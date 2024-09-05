package com.amir.fininfocom.model

data class HomeUI(
    val ruleUI: List<RuleUI>,
    val numberUI: List<NumberUI>,
)


data class NumberUI(
    val numberModel: NumberModel,
    val isSelected: Boolean = false,
    val selectedColor: Int,
)

data class RuleModel(
    val rule: String,
    val id: RuleIDS,
)

enum class RuleIDS {
    EVEN, ODD, PRIME, FIBONACCI
}

data class RuleUI(
    val ruleModel: RuleModel,
    val isSelected: Boolean = false,
    val selectedColor: Int,
)

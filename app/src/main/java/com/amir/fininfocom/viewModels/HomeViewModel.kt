package com.amir.fininfocom.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.fininfocom.R
import com.amir.fininfocom.model.HomeUI
import com.amir.fininfocom.model.NumberUI
import com.amir.fininfocom.model.RuleIDS
import com.amir.fininfocom.model.RuleModel
import com.amir.fininfocom.model.RuleUI
import com.amir.fininfocom.utils.getBgColor
import com.amir.fininfocom.utils.getFibonacciSeries
import com.amir.fininfocom.utils.isArmstrong
import com.amir.fininfocom.utils.isPalindrome
import com.amir.fininfocom.utils.isPerfectNumber
import com.amir.fininfocom.utils.isPrime
import com.amir.fininfocom.utils.redux.ApplicationState
import com.amir.fininfocom.utils.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val store: Store<ApplicationState>,
) : ViewModel() {


    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val numbers = store.stateFlow.value.numList


    val homeUI = combine(
        store.stateFlow.map { it.numList },
        store.stateFlow.map { it.ruleList },
        store.stateFlow.map { it.selectedRuleIds },
        store.stateFlow.map { it.selectedNumberIds },
    ) { numList, ruleList, ruleListIds, selectedNumberIds ->

        val numberUI = numList.map {
            NumberUI(
                numberModel = it,
                isSelected = (selectedNumberIds.contains(it.id)),
                selectedColor = getColor(ruleListIds)
            )

        }

        val ruleUI = ruleList.map {
            RuleUI(
                ruleModel = it,
                isSelected = (ruleListIds.contains(it.id)),
                selectedColor = getColor(ruleListIds)
            )
        }

        HomeUI(
            numberUI = numberUI, ruleUI = ruleUI
        )
    }

    private fun getColor(ruleListIds: Set<RuleIDS>): Int {

        if (ruleListIds.isEmpty()) return R.color.default_rule_stoke_color
        return getBgColor(ruleListIds.first())
    }


    fun updateRuleSelection(ruleModel: RuleModel) {
        viewModelScope.launch {
            store.update {
                return@update it.copy(
                    selectedRuleIds = setOf(ruleModel.id)
                )
            }
            when (ruleModel.id) {
                RuleIDS.EVEN -> updateForEven()
                RuleIDS.ODD -> updateForOdd()
                RuleIDS.PRIME -> updateForPrime()
                RuleIDS.FIBONACCI -> updateForFibonacci()
                RuleIDS.PALINDROME -> updateForPalindrome()
                RuleIDS.ARMSTRONG -> updateForArmstrong()
                RuleIDS.PERFECT_NUMBER -> updateForPerfectNumber()
            }
        }


    }

    private fun updateForPerfectNumber() {
        scope.launch {
            val idsToUpdate = numbers.filter {
                it.text.toInt().isPerfectNumber()
            }.map { it.id }.toSet()
            updateIds(idsToUpdate)
        }
    }

    private fun updateForArmstrong() {
        scope.launch {
            val idsToUpdate = numbers.filter {
                it.text.toInt().isArmstrong()
            }.map { it.id }.toSet()
            updateIds(idsToUpdate)
        }
    }

    private fun updateForPalindrome() {
        scope.launch {
            val idsToUpdate = numbers.filter {
                it.text.toInt().isPalindrome()
            }.map { it.id }.toSet()
            updateIds(idsToUpdate)
        }
    }

    private fun updateForFibonacci() {
        scope.launch {
            val idsToUpdate = numbers.map { it.text.toInt() }.getFibonacciSeries()
            updateIds(idsToUpdate)
        }

    }

    private fun updateIds(idsToUpdate: Set<Int>) {
        viewModelScope.launch {
            store.update {
                return@update it.copy(
                    selectedNumberIds = idsToUpdate
                )
            }
        }
    }

    private fun updateForPrime() {
        scope.launch {
            val idsToUpdate = numbers.filter {
                it.text.toInt().isPrime()
            }.map { it.id }.toSet()
            updateIds(idsToUpdate)
        }
    }


    private fun updateForOdd() {
        scope.launch {
            val idsToUpdate = numbers.filter { it.text.toInt() % 2 != 0 }.map { it.id }.toSet()
            updateIds(idsToUpdate)
        }
    }

    private fun updateForEven() {
        scope.launch {
            val idsToUpdate = numbers.filter { it.text.toInt() % 2 == 0 }.map { it.id }.toSet()
            updateIds(idsToUpdate)
        }
    }


}
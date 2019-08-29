package com.liu.maji.ui.income

import com.liu.maji.base.BaseView

interface IncomeView : BaseView{
    fun getIncomeDataResult(totalIncome: Double?, yestIncome: Double?, todIncome: Double?)
}
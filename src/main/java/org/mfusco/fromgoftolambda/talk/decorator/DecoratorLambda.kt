package org.mfusco.fromgoftolambda.talk.decorator

import org.funktionale.composition.andThen

fun calculateWithReduce(vararg calculators: (Double) -> Double): (Double) -> Double {
    return calculators.reduce({ f1, f2 -> f1 andThen f2 })
}

fun calculateWithFold(vararg calculators: (Double) -> Double): (Double) -> Double = { salary ->
    calculators.fold(salary, { partial, function -> function(partial) })
}

fun main(args: Array<String>) {
    val monthlyCalculator = { salary: Double -> salary / 12.0 }

    val calculator = monthlyCalculator andThen
            Taxes ::generalTax andThen
            Taxes ::regionalTax andThen
            Taxes ::healthInsurance
    println("Salary ${calculator(30000.0)}")

    val calculatorReduce = calculateWithReduce(monthlyCalculator, Taxes ::generalTax, Taxes ::regionalTax, Taxes ::healthInsurance)
    println("Salary ${calculatorReduce(30000.0)}")

    val calculatorFold = calculateWithFold(monthlyCalculator, Taxes ::generalTax, Taxes ::regionalTax, Taxes ::healthInsurance)
    println("Salary ${calculatorFold(30000.0)}")
}

package com.calculator

import java.util.Stack

object Calc {
    private fun precedence(op: Char): Int {
        return when (op) {
            '+', '-' -> 1
            '*', '/' -> 2
            else -> 0
        }
    }

    private fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/'
    }

    private fun applyOperation(a: Double, b: Double, operation: Char): Number {
        return when (operation) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b.toInt() == 0) {
                    throw ArithmeticException("Ошибка")
                }

                a / b
            }
            else -> throw IllegalArgumentException("Ошибка")
        }
    }


    private fun checkDoubleDot(input: String): Boolean {
        var lastDot: Boolean = false
        for (char in input) {
            if (char == '.') {
                if (lastDot) {
                    return false
                }
                lastDot = true
                continue
            }
            lastDot = false
        }
        return true
    }

    private fun checkDoubleOperation(input: String): Boolean {
        var lastWasOperator = false
        for (char in input) {
            if (isOperator(char)) {
                if (lastWasOperator) return false
                lastWasOperator = true
            } else if (char.isDigit()) {
                lastWasOperator = false
            }
        }
        return true
    }

    private fun validateExpression(input: String): Boolean {
        return checkDoubleOperation(input) && checkDoubleDot(input)
    }

    private fun executeWithoutBrackets(input: String): Number {
        val values = Stack<Number>()
        val operations = Stack<Char>()

        var i = 0
        var lastCharWasOperator = true

        while (i < input.length) {
            val char = input[i]

            if (char.isDigit() || (char == '-' && lastCharWasOperator)) {
                val numbStr = StringBuilder()

                if (char == '-') {
                    numbStr.append('-')
                    i++
                }

                while (i < input.length &&  (input[i].isDigit() || input[i] == '.')) {
                    numbStr.append(input[i])
                    i++
                }

                values.push(numbStr.toString().toDouble())
                lastCharWasOperator = false
                continue
            }

            if (isOperator(char)) {
                while (operations.isNotEmpty() && precedence(operations.peek()) >= precedence(char)) {
                    val b = values.pop().toDouble()
                    val a = values.pop().toDouble()
                    val op = operations.pop()
                    values.push(applyOperation(a, b, op))
                }
                operations.push(char)
                lastCharWasOperator = true
            }
            i++
        }

        while (operations.isNotEmpty()) {
            val b = values.pop().toDouble()
            val a = values.pop().toDouble()
            val op = operations.pop()
            values.push(applyOperation(a, b, op))
        }

        return values.pop()
    }


    fun calculate(input: String): String {
        if (!validateExpression(input)) {
            throw Exception("Ошибка")
        }
        return this.executeWithoutBrackets(input).toString()
    }

}
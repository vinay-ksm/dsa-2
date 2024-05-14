package org.example.dsa

class StackWithArrayList<T>(value: T?) {
    var arrayList: ArrayList<T> = ArrayList()

    fun push(value: T) {
        arrayList.add(value)
    }

    fun pop(): T {
        return arrayList.removeLast()
    }

    init {
        if (value != null) {
            arrayList.add(value)
        }
    }

    fun print() {
        println("$arrayList")
    }
}

fun reverseString(string: String): String {
    val stack = StackWithArrayList<Char>(null)
    var reversedString = ""
    for (s in string) {
        stack.push(s)
    }
    for (s in string) {
        reversedString += stack.pop()
    }
    return reversedString
}

fun isBalancedParentheses(string: String): Boolean{
    val stack = StackWithArrayList<Char>(null)
    for(s in string){
        if(s == '(') {
            stack.push(s)
        } else {
            if(stack.arrayList.isEmpty()) return false
            stack.pop()
        }
    }

    return stack.arrayList.isEmpty()
}

fun main() {
    val myString = "((())())"
    println("$myString is balanced: ${isBalancedParentheses(myString)}")
}
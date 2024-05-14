package org.example.dsa

class Stack(value: Int?) {
    data class StackNode(var value: Int, var next: StackNode?)

    var top: StackNode? = null
    var height = 0

    init {
        if (value != null) {
            val node = StackNode(value, null)
            top = node
            height++
        }
    }

    fun push(value: Int) {
        val node = StackNode(value, null)
        if (top == null) {
            top = node
        } else {
            node.next = top
            top = node
        }
        height++
    }

    fun pop(): Int? {
        if (top == null) return null

        val currentTop = top
        top = top!!.next
        currentTop!!.next = null
        height--
        return currentTop.value
    }

    fun print() {
        var current = top
        while (current != null) {
            println("value: ${current.value}, next: ${current.next?.value}, isTop: ${top == current}")
            current = current.next
        }
        println("Height: $height")
    }
}

fun main() {
    val stack = Stack(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    stack.push(5)
    println("popped: ${stack.pop()}")
    stack.print()
    println("popped: ${stack.pop()}")
    stack.print()
    println("popped: ${stack.pop()}")
    stack.print()
    println("popped: ${stack.pop()}")
    stack.print()
    println("popped: ${stack.pop()}")
    stack.print()
    println("popped: ${stack.pop()}")
    stack.print()

}
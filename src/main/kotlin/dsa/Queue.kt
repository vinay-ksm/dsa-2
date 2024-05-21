package org.example.dsa

class Queue<T>(value: T?) {
    data class QueueNode<T>(var value: T?, var next: QueueNode<T>?)

    var length = 0
    var first: QueueNode<T>? = null
    var last: QueueNode<T>? = null

    init {
        if (value != null) {
            val node = QueueNode<T>(value, null)
            first = node
            last = node
            length++
        }
    }

    fun enqueue(value: T) {
        val node = QueueNode(value, null)
        if (last == null) {
            first = node
        } else {
            last!!.next = node
        }
        last = node
        length++
    }

    fun dequeue(): T? {
        if (first == null) return null
        val currentFirst = first

        if (first == last) {
            first = null
            last = null
        } else {
            first = first!!.next
            currentFirst!!.next = null
        }
        length--

        return currentFirst!!.value
    }

    fun print() {
        var current = first
        while (current != null) {
            println("value: ${current.value}, next: ${current.next?.value}, isFirst: ${first == current}, isLast: ${last == current}")
            current = current.next
        }

        println("Queue length: $length")
    }

}

fun main() {
    val queue = Queue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.enqueue(4)
    queue.enqueue(5)
    queue.print()
    println("dequeued: ${queue.dequeue()}")
    queue.print()
    println("dequeued: ${queue.dequeue()}")
    queue.print()
    println("dequeued: ${queue.dequeue()}")
    queue.print()
    println("dequeued: ${queue.dequeue()}")
    queue.print()
    println("dequeued: ${queue.dequeue()}")
    queue.print()
    println("dequeued: ${queue.dequeue()}")
    queue.print()

}
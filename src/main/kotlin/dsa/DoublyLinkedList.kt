package org.example.dsa

class DoublyLinkedList(value: Int?) {
    data class DLLNode(var value: Int, var prev: DLLNode?, var next: DLLNode?)

    var head: DLLNode? = null
    var tail: DLLNode? = null
    var length = 0

    init {
        if (value != null) {
            val node = DLLNode(value, null, null)
            head = node
            tail = node
            length++
        }
    }

    fun append(value: Int) {
        val node = DLLNode(value, null, null)
        if (head == null) {
            head = node
            tail = node
        } else {
            tail!!.next = node
            node.prev = tail
            tail = node
        }
        length++
    }

    fun removeLast(): Int? {
        if (head == null) {
            return null
        }

        val temp = tail
        if (head == tail) {
            head = null
            tail = null
        } else {
            tail = tail!!.prev
            temp!!.prev = null
            tail!!.next = null
        }

        length--
        return temp?.value
    }

    fun prepend(value: Int) {
        val node = DLLNode(value, null, null)
        if (head == null) {
            head = node
            tail = node
        } else {
            head!!.prev = node
            node.next = head
            head = node
        }
        length++
    }

    fun get(index: Int): Int? {
        if (index < 0 || index >= length) {
            return null
        }
        val closer = length / 2
        var current: DLLNode?
        if (index > closer) {
            current = tail
            val end = length - index - 1
            for (i in 0 until end) {
                current = current?.prev
            }
        } else {
            current = head
            for (i in 0 until index) {
                current = current?.next
            }
        }

        return current?.value
    }

    fun set(index: Int, value: Int): Boolean {
        if (index < 0 || index >= length) {
            return false
        }

        var current: DLLNode?
        if (index <= length / 2) {
            current = head
            for (i in 0 until index) {
                current = current?.next
            }
        } else {
            current = tail
            val end = length - index - 1
            for (i in 0 until end) {
                current = current?.prev
            }
        }
        current!!.value = value

        return true
    }

    fun removeFirst(): Int? {
        when (head) {
            null -> {
                return null
            }

            tail -> {
                return removeLast()
            }

            else -> {
                val removedNode = head
                head = head!!.next
                head!!.prev = null
                removedNode!!.next = null
                length--
                return removedNode.value
            }
        }
    }

    fun insert(index: Int, value: Int): Boolean {
        if (index < 0 || index > length) {
            return false
        }

        if (index == 0) {
            prepend(value)
            return true
        } else if (index == length) {
            append(value)
            return true
        }

        val node = DLLNode(value, null, null)
        var current = head
        for (i in 0 until index) {
            current = current!!.next
        }

        val prev = current!!.prev
        prev!!.next = node
        current.prev = node
        node.next = current
        node.prev = prev
        length++
        return true
    }

    fun remove(index: Int): Int? {
        if (index < 0 || index >= length) {
            return null
        }
        if (index == 0) return removeFirst()
        if (index == length - 1) return removeLast()

        var current = head
        for (i in 0 until index) {
            current = current!!.next
        }
        current!!.prev!!.next = current.next
        current.next!!.prev = current.prev
        current.next = null
        current.prev = null
        length--
        return current.value
    }

    fun swapFirstAndLast() {
        if (length <= 1) return

        val first = head!!.value
        val last = tail!!.value
        head!!.value = last
        tail!!.value = first
    }

    fun reverse() {
        if (length <= 1) return

        var current = head
        var next = current!!.next
        var prev = current.prev
        tail = head
        while (current != null) {
            current.prev = next
            current.next = prev
            prev = current
            current = next
            next = current?.next
        }
        head = prev
    }

    fun isPalindrome(): Boolean {
        var first = head
        var last = tail
        for (i in 0 until length / 2) {
            if (first!!.value != last!!.value) return false

            first = first.next
            last = last.prev
        }
        return true
    }

    fun swapPairs() {
        if (length <= 1) return

        var node1 = head
        var node2 = head!!.next
        var next = node2!!.next
        var prev = node1!!.prev
        var prevNode1: DLLNode? = null
        head = node2
        while (node1 != null && node2 != null) {
            if (prevNode1 != null) prevNode1.next = node2
            node1.prev = node2
            node2.next = node1
            node1.next = next
            node2.prev = prev
            prevNode1 = node1
            prev = node1
            node1 = next
            node2 = next?.next
            next = node2?.next
        }

        if (node1 == null) {
            tail = prev
        } else {
            node1.prev = prev
        }
    }

    fun printList() {
        var current = head
        println("printing doubly linked list")
        while (current != null) {
            println(
                "value: ${current.value}, " +
                        "prev: ${current.prev?.value}, " +
                        "next: ${current.next?.value}, " +
                        "isHead: ${head == current}," +
                        " isTail: ${tail == current}"
            )
            current = current.next
        }
        println("length: $length")
    }
}

fun main() {
    val list = DoublyLinkedList(1)
    list.append(2)
    list.append(3)
    list.append(4)
    list.append(5)
    list.append(6)

    list.printList()
    list.swapPairs()
    list.printList()
}
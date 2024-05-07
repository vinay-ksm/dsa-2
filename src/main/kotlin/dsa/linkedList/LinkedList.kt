package org.example.dsa.linkedList

import kotlin.math.pow

class LinkedList(private var value: Int?) {
    var head: Node? = null
    var tail: Node? = null
    var length = 0

    init {
        if (value != null) {
            val node = Node(value!!, null)
            head = node
            tail = node
            length++
        }
    }

    fun append(value: Int) {
        val node = Node(value, null)

        if (length < 1) {
            head = node
            tail = node
        } else {
            tail!!.next = node
            tail = node
        }
        length++
    }

    fun removeLast(): Node? {
        if (head == null) {
            return null
        }

        var temp = head
        var last: Node? = null
        while (temp != tail) {
            if (temp!!.next == tail) {
                last = temp.next
                temp.next = null
                tail = temp
                break
            } else {
                temp = temp.next
            }
        }
        length--
        if (length == 0) {
            head = null
            tail = null
        }
        return last
    }

    fun prepend(value: Int) {
        val node = Node(value, null)
        if (length == 0) {
            head = node
            tail = node
        } else {
            node.next = head
            head = node
        }
        length++
    }

    fun removeFirst(): Int? {
        if (length == 0) {
            return null
        }
        val value = head!!.value

        if (length == 1) {
            head = null
            tail = null
        } else {
            val tempHead = head!!.next
            head!!.next = null
            head = tempHead
        }
        length--
        return value
    }

    fun get(index: Int): Int? {
        if (index >= length || index <0) {
            return null
        }

        var current = head
        for (i in 0 until index) {
            current = current?.next
        }

        return current?.value
    }

    fun set(value: Int, index: Int): Boolean{
        val temp = get(index)
        if(temp == null){
            return false
        }
        var current = head
        for(i in 0 until index){
            current = current?.next
        }
        current?.value = value
        return true
    }

    fun insert(value: Int, index: Int) : Boolean{
        if(index<0 || index>length){
            return false
        }
        if(index == length){
            append(value)
            return true
        }
        if(index == 0){
            prepend(value)
            return true
        }
        var current:Node? = null
        var next = head

        for(i in 0 until index){
            current = next
            next = current?.next
        }
        val node = Node(value, next)
        current?.next = node
        length++
        return true
    }

    fun remove(index: Int): Int?{
        if(index <0 || index>= length){
            return null
        }
        if(index == 0){
            return removeFirst()
        }
        if(index == length - 1){
            return removeLast()?.value
        }
        var current:Node? = null
        var next:Node? = head
        for(i in 0 until index){
            current = next
            next = current?.next
        }
        val newNext = next?.next
        next?.next = null
        current?.next = newNext
        length--
        return next?.value
    }

    fun reverse(){
        if(length <= 1){
            return
        }
        var current = head
        var next = head?.next
        current?.next = null
        tail = current
        for(i in 0 until length - 1){
            val tempNext = next?.next
            next?.next = current
            current = next
            next = tempNext
        }
        head = current
    }

    fun findMiddleNode(): Int?{
        var slow = head
        var fast = head?.next
        while(fast!=null){
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow?.value
    }

    fun hasLoop(): Boolean{
        var slow = head
        var fast = head
        while(fast!=null && fast?.next !=null){
            if(slow == fast) return true
            slow = slow?.next
            fast = fast?.next?.next
        }
        return false
    }

    fun findKthElementFromLast(k:Int): Int?{
        var slow = head
        var fast = head
        for(i in 1 .. k){
            if(fast == null) return null

            fast = fast.next
        }

        while(fast!=null){
            slow = slow?.next
            fast = fast.next
        }

        return slow?.value
    }

    fun partitionList(x:Int){
        var highNodeCurrent: Node? = null
        var lowNodeCurrent: Node? = null
        var firstHighNode: Node? = null
        var current = head
        var next = head?.next

        while(current!=null){
            if(current.value >= x){
                if (highNodeCurrent == null) {
                    firstHighNode = current
                    highNodeCurrent = current
                } else {
                    highNodeCurrent.next = current
                    highNodeCurrent = current
                }
                if (next!=null && next.value < x) {
                    current.next = null
                }
            } else {
                if(lowNodeCurrent == null) {
                    head = current
                    lowNodeCurrent = current
                } else {
                    lowNodeCurrent.next = current
                    lowNodeCurrent = current
                }
                if (next !=null && next.value >= x) {
                    lowNodeCurrent.next = null
                }
            }
            current = next
            next = next?.next

        }

        lowNodeCurrent?.next = firstHighNode

    }

    fun removeDuplicates(){
        var mySet = HashSet<Int>()
        var current = head
        var next = head?.next
        mySet.add(head!!.value)
        while(current != null && next != null) {
            if(!mySet.contains(next.value)){
                mySet.add(next.value)
                current.next = next
                current = next
                next = next.next
            } else {
                current.next = null
                length--
                next = next.next
            }
        }
    }

    fun reverseBetween(start: Int, end: Int){
        println("start: $start, end: $end")
        if(head == null || start == end) {
            return
        }
        var prev = head
        var current = head?.next
        var next = head?.next?.next
        var tempStart: Node? = null
        var reverseStart: Node? = null
        var i = 1


        while(start>i){
            i++
            prev = prev?.next
            current = current?.next
            next = next?.next
        }

        if(start == 0) {
            head = null
            current?.next = prev
        }

        prev?.next = null
        tempStart = prev
        if(start==0) {
            reverseStart = prev
        } else {
            reverseStart = current
        }
        prev = current
        current = next
        next = next?.next
        i++

        while(i <= end) {
            current?.next = prev

            prev = current
            current = next
            next = next?.next
            i++
        }

        tempStart?.next = prev
        reverseStart?.next = current
        if(head == null) head = prev
    }

    fun printList() {
        println("printing linked list")
        var temp = head
        while (temp != null) {
            println("value: ${temp.value}, isHead: ${head == temp}, isTail: ${tail == temp}")
            temp = temp.next
        }
        println("length: $length")
    }
}

class Node(var value: Int, var next: Node?)

fun main() {
    var linkedList = LinkedList(1)
    linkedList.append(2)
    linkedList.append(3)
    linkedList.append(4)
    linkedList.append(5)
    linkedList.append(6)
    linkedList.append(7)
    linkedList.reverseBetween(2,2)
    linkedList.printList()
}
package org.example.dsa

import kotlin.math.max

class Heap {
    val heap: ArrayList<Int> = ArrayList()

    fun size() = heap.size

    fun insert(value: Int) {
        heap.add(value)

        var currentIndex = heap.size - 1
        var currentParentIndex = parent((currentIndex))

        while (currentParentIndex >= 0 && heap[currentParentIndex] < value) {
            swap(currentParentIndex, currentIndex)
            currentIndex = currentParentIndex
            currentParentIndex = parent(currentParentIndex)
        }
    }

    fun remove(): Int? {
        if (heap.size == 0) {
            return null
        }

        if (heap.size == 1) {
            return heap.removeFirst()
        }

        val removable = heap.first()
        heap[0] = heap.removeLast()


        var currentIndex = 0
        var maxIndex = currentIndex
        while (true) {
            val leftIndex = leftChild(currentIndex)
            val rightIndex = rightChild(currentIndex)
            if (leftIndex < heap.size && heap[currentIndex] < heap[leftIndex]) {
                maxIndex = leftIndex
            }
            if (rightIndex < heap.size && heap[currentIndex] < heap[rightIndex]) {
                if (maxIndex == currentIndex) {
                    maxIndex = rightIndex
                } else if (heap[maxIndex] < heap[rightIndex]) {
                    maxIndex = rightIndex
                }
            }

            if (currentIndex != maxIndex) {
                swap(currentIndex, maxIndex)
                currentIndex = maxIndex
                continue
            }
            return removable
        }

    }

    fun leftChild(index: Int) = (index * 2) + 1

    fun rightChild(index: Int) = (index * 2) + 2

    fun parent(index: Int) = (index - 1) / 2

    private fun swap(index1: Int, index2: Int) {
        val temp = heap[index1]
        heap[index1] = heap[index2]
        heap[index2] = temp
    }

    fun print() {
        println("$heap")
    }
}

fun streamMax(nums: IntArray): List<Int>{
    val heap = Heap()
    val result = ArrayList<Int>()
    for(num in nums) {
        heap.insert(num)
        result.add(heap.heap.first())
    }
    return result
}

fun findKthSmallest(nums: IntArray, k: Int): Int {

    val heap = Heap()
    for (n in nums) {
        heap.insert(n)
    }
    heap.print()
    while (heap.size() > k) {
        heap.remove()
    }
    return heap.remove()!!
}

fun main() {
    val nums = intArrayOf(3, 3, 3, 3, 3)

    val result = streamMax(nums)

    println("result: ${result.joinToString(",")}")

}
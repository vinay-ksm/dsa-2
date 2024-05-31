package org.example.dsa

class HashTable {
    val size = 7
    var nodeMap: Array<HashTableNode?> = arrayOfNulls(size)

    data class HashTableNode(var key: String, var value: Int, var next: HashTableNode?)

    fun set(key: String, value: Int) {
        val node = HashTableNode(key, value, null)
        val hashKey = hash(key)
        if (nodeMap[hashKey] == null) {
            nodeMap[hashKey] = node
            return
        }
        var current = nodeMap[hashKey]
        while (current?.next != null) {
            current = current.next
        }
        current!!.next = node
    }

    fun get(key: String): Int? {
        val index = hash(key)
        if (index < 0 || index >= size) {
            return null
        }
        var current = nodeMap[index]

        while (current?.key != key) {
            if (current == null) return null

            current = current.next
        }
        return current.value
    }

    fun keys(): List<String> {
        val keys = ArrayList<String>()
        for (i in 0 until size) {
            var current = nodeMap[i]
            while (current != null) {
                keys.add(current.key)
                current = current.next
            }
        }
        return keys
    }

    fun hash(key: String): Int {
        var hash = 0
        for (asciiValue in key.chars()) {
            hash = (hash + asciiValue * 23) % size
        }
        return hash
    }

    fun print() {
        for (i in 0 until size) {
            var node: HashTableNode? = nodeMap[i]
            while (node != null) {
                println("key: ${node.key}, value: ${node.value}, next: ${node.next?.value}")
                node = node.next
            }
        }
    }
}

fun itemInCommon(array1: Array<Int>, array2: Array<Int>): Boolean {
    val myMap = HashMap<Int, Boolean>()
    for (i in array1) {
        myMap[i] = true
    }
    for (i in array2) {
        if (myMap[i] == true) return true
    }
    return false
}

fun firstNonRepeatingCharacter(word: String): Char? {
    var result = HashMap<Char, Int>()
    for (char in word.toCharArray()) {
        val count: Int = result[char] ?: 0
        result[char] = count + 1
    }
    for (key in result.keys) {
        if (result[key] == 1) {
            return key
        }
    }
    return null
}

fun findDuplicates(nums: Array<Int>): List<Int> {
    val myMap = HashMap<Int, Boolean>()
    val resultMap = HashMap<Int, Boolean>()
    val result = ArrayList<Int>()
    for (num in nums) {
        if (myMap[num] == true) {
            resultMap[num] = true
        } else {
            myMap[num] = true
        }
    }
    return resultMap.keys.toList()
}

fun groupAnagrams(strings: Array<String>): List<List<String>> {
    val result = ArrayList<MutableList<String>>()
    val resultMap = HashMap<String, ArrayList<String>>()
    for (string in strings) {
        var sortedString = string.toCharArray().sorted().joinToString()
        val currentList = resultMap[sortedString]
        if (currentList == null) {
            resultMap[sortedString] = arrayListOf(string)
        } else {
            resultMap[sortedString]!!.add(string)
        }
    }
    for (anagram in resultMap) {
        result.add(anagram.value)
    }
    return result
}

fun twoSum(nums: Array<Int>, sum: Int): Array<Int> {
    val numMap = HashMap<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        numMap[num] = index
    }

    for ((index, num) in nums.withIndex()) {
        val rem: Int? = numMap[sum - num]
        if (rem != null) {
            return arrayOf(index, rem)
        }
    }

    return arrayOf()
}

fun subArraySum(nums: Array<Int>, target: Int): Array<Int> {
    val numsMap = HashMap<Int, Int>()
    numsMap[0] = -1
    var currentSum = 0
    for ((index, num) in nums.withIndex()) {
        currentSum += num
        if (numsMap.containsKey(currentSum - target)) {
            return arrayOf(numsMap[currentSum - target]!! + 1, index)
        } else {
            numsMap[currentSum] = index
        }
    }

    return arrayOf()
}

fun removeDuplicates(nums: List<Int>) = nums.toSet().toList()

fun hasUniqueChars(string: String): Boolean {
    return string.toCharArray().toSet().joinToString("") == string
}

fun findPairs(arr1: Array<Int>, arr2: Array<Int>, target: Int): List<Array<Int>> {
    val result = ArrayList<Array<Int>>()
    var nums = arr1.toSet()
    for (num in arr2) {
        if (nums.contains(target - num)) {
            result.add(arrayOf(target - num, num))
        }
    }
    return result
}

fun longestConsecutiveSequence(nums: Array<Int>): Int {
    val numSet = nums.toSet()
    var currentStreak = 0
    for(num in numSet){
        if(numSet.contains(num - 1)){
            continue
        }

        var tempCurrentStreak  = 1
        var tempNum = num
        while(numSet.contains(tempNum + 1)) {
            tempCurrentStreak++
            tempNum+=1
        }
        if(tempCurrentStreak > currentStreak) {
            currentStreak = tempCurrentStreak
        }
    }
    return currentStreak
}

fun main() {
    val arr1 = arrayOf(1, 2, 100, 3, 4, 5)
    println("longest streak: ${longestConsecutiveSequence(arr1)}")
}
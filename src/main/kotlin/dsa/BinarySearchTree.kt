package org.example.dsa

class BinarySearchTree(value: Int?) {
    data class BstNode(var value: Int, var left: BstNode?, var right: BstNode?, var printed: Boolean = false)

    var root: BstNode? = null

    init {
        if (value != null) {
            val node = BstNode(value, null, null)
            root = node
        }
    }

    fun insert(value: Int) : Boolean {
        val node = BstNode(value, null, null)
        if (root == null) {
            root = node
            return true
        }

        var current = root!!
        while (true) {
            if(value == current.value) {
                return false
            }
            if (value < current.value) {
                if (current.left == null) {
                    current.left = node
                    return true
                } else {
                    current = current.left!!
                }
            } else {
                if (current.right == null) {
                    current.right = node
                    return true
                } else {
                    current = current.right!!
                }
            }
        }
    }

    fun contains(value: Int): Boolean{
        var current = root
        while(current!=null) {
            if(value == current.value) return true

            if(value < current.value) {
                current = current.left
            } else {
                current = current.right
            }
        }
        return false
    }
}

fun main() {
    val bst = BinarySearchTree(1)
    println( bst.insert(2))
    println( bst.insert(3))
    println( bst.insert(3))
}
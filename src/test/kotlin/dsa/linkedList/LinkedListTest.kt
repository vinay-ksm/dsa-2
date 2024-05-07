package dsa.linkedList

import org.example.dsa.linkedList.LinkedList
import org.junit.jupiter.api.Test
import javax.swing.plaf.ListUI
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

class LinkedListTest {

    @Test
    fun `init initializes a new linked list`() {
        val linkedList = LinkedList(1)
        assertEquals(1, linkedList.length)
        assertEquals(1, linkedList.head?.value)
    }

    //region append
    @Test
    fun `append when list is empty`() {
        var linkedList = LinkedList(null)
        assertEquals(0, linkedList.length)
        assertEquals(null, linkedList.head)

        linkedList.append(1)
        assertEquals(1, linkedList.length)
        assertEquals(1, linkedList.head!!.value)
        assertEquals(1, linkedList.tail!!.value)
    }

    @Test
    fun `append when head node is already present`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        assertEquals(2, linkedList.length)
        assertEquals(2, linkedList.tail!!.value)
        assertNotEquals(2, linkedList.head!!.value)
    }

    @Test
    fun `append when multiple nodes are present`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        linkedList.append(3)
        linkedList.append(4)
        linkedList.append(5)
        assertEquals(5, linkedList.length)
        assertEquals(5, linkedList.tail!!.value)
    }
    //endregion

    //region removeLast
    @Test
    fun `removeLast when no nodes are present in the list`() {
        val linkedList = LinkedList(null)
        val node = linkedList.removeLast()
        assertEquals(0, linkedList.length)
    }

    @Test
    fun `removeLast when only head is present`() {
        val linkedList = LinkedList(1)
        val node = linkedList.removeLast()
        assertEquals(1, node?.value)
        assertEquals(0, linkedList.length)
    }

    @Test
    fun `removeLast when only 2 nodes are in the list`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        val node = linkedList.removeLast()
        assertEquals(2, node?.value)
        assertEquals(1, linkedList.length)
        assertEquals(1, linkedList.head?.value)
        assertEquals(1, linkedList.tail?.value)
    }

    @Test
    fun `removeLast when more than 2 nodes are in the list`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        linkedList.append(3)
        linkedList.append(4)
        var node = linkedList.removeLast()
        assertEquals(4, node?.value)
        node = linkedList.removeLast()
        assertEquals(3, node?.value)
    }
    //endregion

    //region prepend
    @Test
    fun `prepend when no nodes in the list`() {
        val linkedList = LinkedList(null)
        linkedList.prepend(1)
        assertEquals(1, linkedList.length)
        assertEquals(1, linkedList.head?.value)
        assertEquals(1, linkedList.tail?.value)
    }

    @Test
    fun `prepend when there is only one node in the list`() {
        val linkedList = LinkedList(1)
        linkedList.prepend(0)
        assertEquals(2, linkedList.length)
        assertEquals(0, linkedList.head?.value)
        assertEquals(1, linkedList.tail?.value)
    }

    @Test
    fun `prepend when there are more than one nodes in the list`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        linkedList.append(3)
        linkedList.append(4)
        linkedList.prepend(9)
        assertEquals(5, linkedList.length)
        assertEquals(9, linkedList.head?.value)
    }
    //endregion

    //region removeFirst
    @Test
    fun `removeFirst when no nodes in the list`() {
        val linkedList = LinkedList(null)
        assertEquals(null, linkedList.removeFirst())
    }

    @Test
    fun `removeFirst when only one node is in the list`() {
        val linkedList = LinkedList(1)
        val removed = linkedList.removeFirst()
        assertEquals(1, removed)
        assertEquals(0, linkedList.length)
        assertNull(linkedList.head)
        assertNull(linkedList.tail)
    }

    @Test
    fun `removeFirst when only 2 nodes are in the list`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        val removed = linkedList.removeFirst()
        assertEquals(1, removed)
        assertEquals(1, linkedList.length)
        assertEquals(2, linkedList.head?.value)
        assertEquals(2, linkedList.tail?.value)
    }

    @Test
    fun `removeFirst when more than 2 nodes are in the list`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        linkedList.append(3)
        linkedList.append(4)
        val removed = linkedList.removeFirst()
        assertEquals(1, removed)
        assertEquals(3, linkedList.length)
        assertEquals(2, linkedList.head?.value)
        assertEquals(4, linkedList.tail?.value)
    }
    //endregion

    //region get
    @Test
    fun `get when no nodes in the list`() {
        val linkedList = LinkedList(null)
        val value = linkedList.get(0)
        assertNull(value)
    }

    @Test
    fun `get when only one node in the list`() {
        val linkedList = LinkedList(100)
        assertEquals(100, linkedList.get(0))
    }

    @Test
    fun `get last node when more than one node in the list`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        linkedList.append(3)
        linkedList.append(4)
        assertEquals(4, linkedList.get(3))
    }

    @Test
    fun `get intermediate node when more than one node in the list`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        linkedList.append(3)
        linkedList.append(4)
        assertEquals(3, linkedList.get(2))
    }

    @Test
    fun `get node when index is out of range`() {
        val linkedList = LinkedList(1)
        linkedList.append(2)
        linkedList.append(3)
        linkedList.append(4)
        assertEquals(null, linkedList.get(5))
    }

    @Test
    fun `get node when index is negative`() {
        val linkedList = LinkedList(1)
        assertNull(linkedList.get(-1))
    }
    //endregion
}
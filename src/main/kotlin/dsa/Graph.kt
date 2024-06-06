package org.example.dsa

class Graph {
    private val adjacencyList = HashMap<String, ArrayList<String>>()

    fun addVertex(vertex: String): Boolean {
        if (adjacencyList.containsKey(vertex)) return false

        adjacencyList[vertex] = ArrayList()
        return true
    }

    fun addEdge(vertex1: String, vertex2: String): Boolean {
        if (!adjacencyList.containsKey(vertex1) || !adjacencyList.containsKey(vertex2)) return false

        adjacencyList[vertex1]!!.add(vertex2)
        adjacencyList[vertex2]!!.add(vertex1)
        return true
    }

    fun removeEdge(vertex1: String, vertex2: String): Boolean {
        if (!adjacencyList.containsKey(vertex1) || !adjacencyList.containsKey(vertex2)) return false

        adjacencyList[vertex1]!!.remove(vertex2)
        adjacencyList[vertex2]!!.remove(vertex1)
        return true
    }

    fun removeVertex(vertexIn: String): Boolean {
        if(!adjacencyList.containsKey(vertexIn)) return false

        for(vertex in adjacencyList) {
            vertex.value.remove(vertexIn)
        }

        adjacencyList.remove(vertexIn)
        return true
    }

    fun print() {
        for (vertex in adjacencyList) {
            println("Vertex: ${vertex.key}, edges: ${vertex.value.joinToString(", ")}")
        }
    }
}

fun main() {
    val graph = Graph()
    graph.addVertex("foo")
    graph.addVertex("bar")
    graph.addVertex("baz")
    graph.addEdge("foo", "bar")
    graph.addEdge("foo", "baz")
    graph.addEdge("bar", "baz")
    graph.print()
    println("after removing : baz")
    graph.removeVertex("baz")
    graph.print()
}
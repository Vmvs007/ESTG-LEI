/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

/**
 *
 * @author vmvs0
 */

import java.util.*;

public class Graph<T> implements GraphADT<T>
{
   protected final int DEFAULT_CAPACITY = 10;
   protected int numVertices;   // number of vertices in the graph
   protected boolean[][] adjMatrix;   // adjacency matrix
   protected T[] vertices;   // values of vertices

   /******************************************************************
     Creates an empty graph.
   ******************************************************************/
   public Graph()
   {
      numVertices = 0;
      this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
      this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
   }

   /******************************************************************
     Returns a string representation of the adjacency matrix. 
   ******************************************************************/
   public String toString()
   {
      if (numVertices == 0)
         return "Graph is empty";

      String result = new String("");

      result += "Adjacency Matrix\n";
      result += "----------------\n";
      result += "index\t";

      for (int i = 0; i < numVertices; i++) 
      {
         result += "" + i;
         if (i < 10)
            result += " ";
      }
      result += "\n\n";

      for (int i = 0; i < numVertices; i++)
      {
         result += "" + i + "\t";
      
         for (int j = 0; j < numVertices; j++)
         {
            if (adjMatrix[i][j])
               result += "1 ";
            else
               result += "0 ";
         }
         result += "\n";
      }

      result += "\n\nVertex Values";
      result += "\n-------------\n";
      result += "index\tvalue\n\n";

      for (int i = 0; i < numVertices; i++)
      {
         result += "" + i + "\t";
         result += vertices[i].toString() + "\n";
      }
      result += "\n";
      return result;
   }

   /******************************************************************
     Inserts an edge between two vertices of the graph.
   ******************************************************************/
   public void addEdge (int index1, int index2)
   {
      if (indexIsValid(index1) && indexIsValid(index2))
      {
         adjMatrix[index1][index2] = true;
         adjMatrix[index2][index1] = true;
      }
   }

   /******************************************************************
     Removes an edge between two vertices of the graph.
   ******************************************************************/
   public void removeEdge (int index1, int index2)
   {
      if (indexIsValid(index1) && indexIsValid(index2))
      {
         adjMatrix[index1][index2] = false;
         adjMatrix[index2][index1] = false;
      }
   }

   /******************************************************************
     Inserts an edge between two vertices of the graph.
   ******************************************************************/
   public void addEdge (T vertex1, T vertex2)
   {
      addEdge (getIndex(vertex1), getIndex(vertex2));
   }

   /******************************************************************
     Removes an edge between two vertices of the graph.
   ******************************************************************/
   public void removeEdge (T vertex1, T vertex2)
   {
      removeEdge (getIndex(vertex1), getIndex(vertex2));
   }

   /******************************************************************
     Adds a vertex to the graph, expanding the capacity of the graph
     if necessary.
   ******************************************************************/
   public void addVertex ()
   {
      if (numVertices == vertices.length)
         expandCapacity();

      vertices[numVertices] = null;
      for (int i = 0; i <= numVertices; i++)
      {
         adjMatrix[numVertices][i] = false;
         adjMatrix[i][numVertices] = false;
      }      
      numVertices++;
   }

   /******************************************************************
     Adds a vertex to the graph, expanding the capacity of the graph
     if necessary.  It also associates an object with the vertex.
   ******************************************************************/
   public void addVertex (T vertex)
   {
      if (numVertices == vertices.length)
         expandCapacity();

      vertices[numVertices] = vertex;
      for (int i = 0; i <= numVertices; i++)
      {
         adjMatrix[numVertices][i] = false;
         adjMatrix[i][numVertices] = false;
      }      
      numVertices++;
   }

   /******************************************************************
     Removes a vertex at the given index from the graph.  Note that 
     this may affect the index values of other vertices.
   ******************************************************************/
   public void removeVertex (int index)
   {
      if (indexIsValid(index))
      {
         numVertices--;

         for (int i = index; i < numVertices; i++)
            vertices[i] = vertices[i+1];

         for (int i = index; i < numVertices; i++)
            for (int j = 0; j <= numVertices; j++)
               adjMatrix[i][j] = adjMatrix[i+1][j];

         for (int i = index; i < numVertices; i++)
            for (int j = 0; j < numVertices; j++)
               adjMatrix[j][i] = adjMatrix[j][i+1];
      }
   }

   /******************************************************************
     Removes a single vertex with the given value from the graph.  
   ******************************************************************/
   public void removeVertex (T vertex)
   {
      for (int i = 0; i < numVertices; i++)
      {
         if (vertex.equals(vertices[i]))
         {
            removeVertex(i);
            return;
         }
      }
   }

   /******************************************************************
     Returns an iterator that performs a depth first search 
     traversal starting at the given index.
   ******************************************************************/
   public Iterator<T> iteratorDFS(int startIndex)
   {
      Integer x;
      boolean found;
      LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
      ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
      boolean[] visited = new boolean[numVertices];

      if (!indexIsValid(startIndex))
         return resultList.iterator();

      for (int i = 0; i < numVertices; i++)
         visited[i] = false;
      
      traversalStack.push(new Integer(startIndex));
      resultList.addToRear(vertices[startIndex]);
      visited[startIndex] = true;
      
      while (!traversalStack.isEmpty())
      {
         x = traversalStack.peek();
         found = false;

         /** Find a vertex adjacent to x that has not been visited
             and push it on the stack */
         for (int i = 0; (i < numVertices) && !found; i++)
         {
            if (adjMatrix[x.intValue()][i] && !visited[i])
            {
               traversalStack.push(new Integer(i));
               resultList.addToRear(vertices[i]);
               visited[i] = true;
               found = true;
            }
         }
         if (!found && !traversalStack.isEmpty())
            traversalStack.pop();
      }
      return resultList.iterator();
   }

   /******************************************************************
     Returns an iterator that performs a depth first search 
     traversal starting at the given vertex.
   ******************************************************************/
   public Iterator<T> iteratorDFS(T startVertex)
   {      
      return iteratorDFS(getIndex(startVertex));
   }

   /******************************************************************
     Returns an iterator that performs a breadth first search 
     traversal starting at the given index.
   ******************************************************************/
   public Iterator<T> iteratorBFS(int startIndex)
   {
      Integer x;
      LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
      ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

      if (!indexIsValid(startIndex))
         return resultList.iterator();

      boolean[] visited = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++)
         visited[i] = false;
      
      traversalQueue.enqueue(new Integer(startIndex));
      visited[startIndex] = true;
      
      while (!traversalQueue.isEmpty())
      {
         x = traversalQueue.dequeue();
         resultList.addToRear(vertices[x.intValue()]);

         /** Find all vertices adjacent to x that have not been visited
             and queue them up */
         for (int i = 0; i < numVertices; i++)
         {
            if (adjMatrix[x.intValue()][i] && !visited[i])
            {
               traversalQueue.enqueue(new Integer(i));
               visited[i] = true;
            }
         }
      }
      return resultList.iterator();
   }

   /******************************************************************
     Returns an iterator that performs a breadth first search 
     traversal starting at the given vertex.
   ******************************************************************/
   public Iterator<T> iteratorBFS(T startVertex)
   {      
      return iteratorBFS(getIndex(startVertex));
   }

   /******************************************************************
     Returns an iterator that contains the indices of the vertices 
     that are in the shortest path between the two given vertices.
   ******************************************************************/
   protected Iterator<Integer> iteratorShortestPathIndices
                              (int startIndex, int targetIndex)
   {
      int index = startIndex;
      int[] pathLength = new int[numVertices];
      int[] predecessor = new int[numVertices];
      LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
      ArrayUnorderedList<Integer> resultList = 
                                  new ArrayUnorderedList<Integer>();

      if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || 
                                       (startIndex == targetIndex))
         return resultList.iterator();

      boolean[] visited = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++)
         visited[i] = false;
      
      traversalQueue.enqueue(new Integer(startIndex));
      visited[startIndex] = true;
      pathLength[startIndex] = 0;
      predecessor[startIndex] = -1;

      while (!traversalQueue.isEmpty() && (index != targetIndex))
      {
         index = (traversalQueue.dequeue()).intValue();

         /** Update the pathLength for each unvisited vertex adjacent 
             to the vertex at the current index. */
         for (int i = 0; i < numVertices; i++)
         {
            if (adjMatrix[index][i] && !visited[i])
            {
               pathLength[i] = pathLength[index] + 1;
               predecessor[i] = index;
               traversalQueue.enqueue(new Integer(i));
               visited[i] = true;
            }
         }
      }
      if (index != targetIndex)  // no path must have been found
         return resultList.iterator();

      LinkedStack<Integer> stack = new LinkedStack<Integer>();
      index = targetIndex;
      stack.push(new Integer(index));
      do
      {
         index = predecessor[index];
         stack.push(new Integer(index));
      } while (index != startIndex);
      
      while (!stack.isEmpty())
         resultList.addToRear(((Integer)stack.pop()));

      return resultList.iterator();
   }

   /******************************************************************
     Returns an iterator that contains the shortest path between
     the two vertices.
   ******************************************************************/
   public Iterator<T> iteratorShortestPath(int startIndex, 
                                           int targetIndex)
   {
      ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
      if (!indexIsValid(startIndex) || !indexIsValid(targetIndex))
         return resultList.iterator();

      Iterator<Integer> it = iteratorShortestPathIndices(startIndex, 
                             targetIndex);      
      while (it.hasNext())
         resultList.addToRear(vertices[((Integer)it.next()).intValue()]);
      return resultList.iterator();
   }

   /******************************************************************
     Returns an iterator that contains the shortest path between
     the two vertices.
   ******************************************************************/
   public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
   {
      return iteratorShortestPath(getIndex(startVertex), 
                                  getIndex(targetVertex));
   }

   /******************************************************************
     Returns the weight of the least weight path in the network.  
     Returns positive infinity if no path is found.
   ******************************************************************/
   public int shortestPathLength(int startIndex, int targetIndex)
   {
      int result = 0;
      if (!indexIsValid(startIndex) || !indexIsValid(targetIndex))
         return 0;

      int index1, index2;
      Iterator<Integer> it = iteratorShortestPathIndices(startIndex, 
                             targetIndex);

      if (it.hasNext())
         index1 = ((Integer)it.next()).intValue();
      else
         return 0;

      while (it.hasNext())
      {
         result++;
         it.next();
      }
      
      return result;
   }

   /******************************************************************
     Returns the weight of the least weight path in the network.  
     Returns positive infinity if no path is found.
   ******************************************************************/
   public int shortestPathLength(T startVertex, T targetVertex)
   {
      return shortestPathLength(getIndex(startVertex), getIndex(
                                                       targetVertex));
   }

   /******************************************************************
     Returns a minimum spanning tree of the graph.
   ******************************************************************/
   public Graph getMST()
   {
      int x, y;
      int[] edge = new int[2];
      LinkedStack<int[]> vertexStack = new LinkedStack<int[]>();
      Graph<T> resultGraph = new Graph<T>();

      if (isEmpty() || !isConnected())
         return resultGraph;
      
      resultGraph.adjMatrix = new boolean[numVertices][numVertices];
      
      for (int i = 0; i < numVertices; i++)
         for (int j = 0; j < numVertices; j++)
            resultGraph.adjMatrix[i][j] = false;
            
      resultGraph.vertices = (T[])(new Object[numVertices]);
      boolean[] visited = new boolean[numVertices];
      
      for (int i = 0; i < numVertices; i++)
         visited[i] = false;      
      
      edge[0] = 0;
      resultGraph.vertices[0] = this.vertices[0];
      resultGraph.numVertices++;
      visited[0] = true;

      /** Add all edges that are adjacent to vertex 0 to the stack. */
      for (int i = 0; i < numVertices; i++)
      {
         if (!visited[i] && this.adjMatrix[0][i])
         {
            edge[1] = i;
            vertexStack.push(edge.clone());
            visited[i] = true;
         }
      }

      while ((resultGraph.size() < this.size()) && !vertexStack.isEmpty())
      {
         /** Pop an edge off the stack and add it to the resultGraph. */
         edge = vertexStack.pop();
         x = edge[0];
         y = edge[1];
         resultGraph.vertices[y] = this.vertices[y];
         resultGraph.numVertices++;
         resultGraph.adjMatrix[x][y] = true;
         resultGraph.adjMatrix[y][x] = true;
         visited[y] = true;

         /** Add all unvisited edges that are adjacent to vertex y
             to the stack. */
         for (int i = 0; i < numVertices; i++)
         {
            if (!visited[i] && this.adjMatrix[i][y])
            {
               edge[0] = y;
               edge[1] = i;
               vertexStack.push(edge.clone());
               visited[i] = true;
            }
         }
      }

      return resultGraph;
   }

   /******************************************************************
     Creates new arrays to store the contents of the graph with
     twice the capacity.
   ******************************************************************/
   protected void expandCapacity()
   {
      T[] largerVertices = (T[])(new Object[vertices.length*2]);
      boolean[][] largerAdjMatrix = 
            new boolean[vertices.length*2][vertices.length*2];

      for (int i = 0; i < numVertices; i++)
      {
         for (int j = 0; j < numVertices; j++)
         {
            largerAdjMatrix[i][j] = adjMatrix[i][j];
         }
         largerVertices[i] = vertices[i];
      }

      vertices = largerVertices;
      adjMatrix = largerAdjMatrix;
   }

   /******************************************************************
     Returns the number of vertices in the graph.
   ******************************************************************/
   public int size()
   {
      return numVertices;
   }

   /******************************************************************
     Returns true if the graph is empty and false otherwise. 
   ******************************************************************/
   public boolean isEmpty()
   {
      return (numVertices == 0);
   }

   /******************************************************************
     Returns true if the graph is connected and false otherwise. 
   ******************************************************************/
   public boolean isConnected()
   {
      if (isEmpty())
         return false;
      
      Iterator<T> it = iteratorBFS(0);
      int count = 0;

      while (it.hasNext())
      {
         it.next();
         count++;
      }
      return (count == numVertices);
   }

   /******************************************************************
     Returns the index value of the first occurrence of the vertex.
     Returns -1 if the key is not found.
   ******************************************************************/
   public int getIndex(T vertex)
   {
      for (int i = 0; i < numVertices; i++)
         if (vertices[i].equals(vertex))
            return i;
      return -1;
   }

   /******************************************************************
     Returns true if the given index is valid. 
   ******************************************************************/
   protected boolean indexIsValid(int index)
   {
      return ((index < numVertices) && (index >= 0));
   }

   /******************************************************************
     Returns a copy of the vertices array.
   ******************************************************************/
   public Object[] getVertices()
   {
      Object[] vertices = new Object[numVertices];
      Object vertex;
      
      for (int i = 0; i < numVertices; i++)
      {
         vertex = this.vertices[i];
         vertices[i] = vertex;
      }
      return vertices;
   }
}
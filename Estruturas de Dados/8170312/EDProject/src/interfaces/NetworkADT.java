/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Vitor Santos - 8170312
 */
public interface NetworkADT<T> extends GraphADT<T> {
    
    /**
 * Inserts an edge between two vertices of this graph.
 *
 * @param vertex1 the first vertex
 * @param vertex2 the second vertex
 * @param weight the weight
 */
 public void addEdge (T vertex1, T vertex2, double weight);
 

 /**
 * Returns the weight of the shortest path in this network.
 *
 * @param vertex1 the first vertex
 * @param vertex2 the second vertex
 * @return the weight of the shortest path in this network
 */
 public double shortestPathWeight(T vertex1, T vertex2);
 
}
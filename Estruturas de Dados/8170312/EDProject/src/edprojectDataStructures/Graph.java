/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

import edproject.CargosProfissionais;
import edproject.User;
import interfaces.GraphADT;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Vitor Santos - 8170312
 */

public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 5;
    protected int numVertices; // number of vertices in the graph
    protected double[][] adjMatrix; // adjacency matrix
    protected T[] vertices; //Value of Vertices

    public Graph() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public void setVertex(T vertex) {
        User[] users = new User[numVertices];
        User user = (User) vertex;
        int i = 0;

        while (i < numVertices) {
            users[i] = (User) vertices[i];
            i++;
        }

        i = 0;

        while (i < numVertices) {
            if (user.getId() == users[i].getId()) {
                vertices[i] = (T) user;
            }
            i++;
        }
    }

    /**
     * Método que retorna o index de um vértice
     *
     * @param vertex
     * @return index corresponde ao vertice ou -1 caso o vértice não exista na
     * nossa lista de vértices
     */
    public int getVertexIndex(T vertex) {
        int i = 0;
        while (i < vertices.length) {
            if (vertex.equals(vertices[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Método que adiciona vertice
     *
     * @param vertex
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacityArray();
            expandCapacityMatriz();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = 0.;
            adjMatrix[i][numVertices] = 0;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        //Implementado em NetworkGraph
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int v1, v2;

        v1 = getVertexIndex(vertex1);
        v2 = getVertexIndex(vertex2);

        adjMatrix[v1][v2] = 0;
        adjMatrix[v2][v1] = 0;
    }

    public boolean indexIsValid(int index) {
        if (vertices[index] != null) {
            return true;
        } else {
            return false;
        }

    }

    public User getVertex(int id) {
        int i = 0;
        User[] users = new User[vertices.length];

        while (i < vertices.length) {
            users[i] = (User) vertices[i];
            // 
            i++;
        }
        i = 0;
        while (i <users.length) {
            if (id == users[i].getId()) {
                return users[i];
            }
            i++;
        }
        return null;
    }

    public Iterator adjVertex(T vertex) {
        Integer x, startVertexaux, j = 0;
        startVertexaux = getVertexIndex(vertex);

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (startVertexaux == -1) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startVertexaux));
        visited[startVertexaux] = true;

        x = 0;
        while (j < 2) {
            while (!traversalQueue.isEmpty()) {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x.intValue()]);
            }

            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            if (j < 1) {
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                        traversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            }
            j++;
        }

        return resultList.iterator();

    }

    public Iterator usersOneMiddleMan(T vertex) {
        Iterator itr;
        int i = 0;
        User auxU;
        ArrayUnorderedList total = new ArrayUnorderedList();
        ArrayUnorderedList aux = new ArrayUnorderedList();

        itr = adjVertex(vertex);

        while (itr.hasNext()) {
            auxU = (User) itr.next();
            total.addToRear(auxU);
            aux.addToRear(auxU);
        }

        while (!aux.isEmpty()) {
            itr = adjVertex((T) aux.removeFirst());
            while (itr.hasNext()) {
                auxU = (User) itr.next();
                if (!total.contains(auxU)) {
                    total.addToRear(auxU);
                }
            }
        }

        return total.iterator();
    }

    @Override
    public Iterator iteratorBFS(T startVertex) {
        Integer x, startVertexaux;
        startVertexaux = getVertexIndex(startVertex);

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (startVertexaux == -1) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startVertexaux));
        visited[startVertexaux] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);
            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator iteratorDFS(T startVertex) {
        Integer x, startVertexaux;
        boolean found;
        startVertexaux = getVertexIndex(startVertex);

        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startVertexaux)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startVertexaux));
        resultList.addToRear(vertices[startVertexaux]);
        visited[startVertexaux] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;
            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        Integer x, startVertexaux, targetVertexaux, aux;
        startVertexaux = getVertexIndex(startVertex);
        targetVertexaux = getVertexIndex(targetVertex);

        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<Double>();
        ArrayUnorderedList resultList = new ArrayUnorderedList();
        LinkedStack<Integer> stack = new LinkedStack<Integer>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startVertexaux) || !indexIsValid(targetVertexaux)
                || (startVertexaux == targetVertexaux) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startVertexaux] = 0;
        predecessor[startVertexaux] = 0;
        visited[startVertexaux] = true;
        weight = 0;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && adjMatrix[startVertexaux][i] > 0) {
                pathWeight[i] = pathWeight[startVertexaux] + adjMatrix[startVertexaux][i];
                predecessor[i] = startVertexaux;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }
        if (!traversalMinHeap.isEmpty()) {
            do {
                weight = (traversalMinHeap.removeMin());
                while (!traversalMinHeap.isEmpty()) {
                    traversalMinHeap.removeMin();
                }
                if (weight == Double.POSITIVE_INFINITY) {
                    return resultList.iterator();
                } else {
                    index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                    visited[index] = true;
                }

                for (int i = 0; i < numVertices; i++) {
                    if (!visited[i]) {
                        if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY) && (adjMatrix[index][i] > 0) && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                            pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                            predecessor[i] = index;
                        }
                        traversalMinHeap.addElement(pathWeight[i]);
                    }
                }
            } while (!traversalMinHeap.isEmpty() && !visited[targetVertexaux]);

            index = targetVertexaux;
            stack.push(index);
            do {
                index = predecessor[index];
                stack.push(index);
            } while (index != startVertexaux);

            while (!stack.isEmpty()) {
                aux = stack.pop();

                resultList.addToRear(vertices[aux]);

            }
        }

        return resultList.iterator();
    }

    public int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] < Double.POSITIVE_INFINITY)
                            && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    public Iterator usersWithSkillOrdered(T vertex, String skill) {
        Iterator connectedUsers;
        Iterator[] shortPath;
        User aux;
        User[] users = new User[numVertices];
        LinkedHeap teste = new LinkedHeap();
        ArrayUnorderedList usersWithSkill = new ArrayUnorderedList();
        ArrayUnorderedList users1 = new ArrayUnorderedList();
        ArrayUnorderedList users2 = new ArrayUnorderedList();
        String[] skills;
        double[][] indexWeight = new double[numVertices][2];
        double auxD;
        T auxT;
        int auxI;

        int i = 0, j = 0, z = 0;

        connectedUsers = iteratorBFS(vertex);
        while (connectedUsers.hasNext()) {
            users[i] = (User) connectedUsers.next();
            i++;
        }

        while (j < i) {
            skills = users[j].getSkills();
            while (z < skills.length) {
                if (skills[z].equals(skill)) {
                    usersWithSkill.addToRear(users[j]);
                }
                z++;
            }
            z = 0;
            j++;
        }

        j = 0;
        shortPath = new Iterator[usersWithSkill.size()];

        while (!usersWithSkill.isEmpty()) {
            auxT = (T) usersWithSkill.removeFirst();
            shortPath[j] = iteratorShortestPath(vertex, auxT);

            indexWeight[j][1] = calculatePathWeigth(shortPath[j]);
            teste.addElement(indexWeight[j][1]);

            shortPath[j] = iteratorShortestPath(vertex, auxT);

            while (shortPath[j].hasNext()) {
                aux = (User) shortPath[j].next();
                indexWeight[j][0] = aux.getId();
            }
            j++;
        }
        i = 0;

        while (!teste.isEmpty()) {
            auxD = (double) teste.removeMin();
            while (i < j) {
                if (indexWeight[i][1] == auxD) {
                    users1.addToRear(indexWeight[i][0]);

                }
                i++;
            }
            i = 0;
        }

        while (!users1.isEmpty()) {

            auxD = (double) users1.removeFirst();
            auxI = (int) auxD;
            users2.addToRear(getVertex(auxI));
        }

        return users2.iterator();
    }

    public void orderIndexWeight(double[][] indexWeight, int size) {
        int i = 0;

    }

    public double calculatePathWeigth(Iterator path) {
        User[] users = new User[numVertices];
        Iterator pathAux;
        pathAux = path;
        int i = 0, j = 0;
        double weight = 0;

        while (pathAux.hasNext()) {
            users[i] = (User) pathAux.next();
            i++;
        }

        i--;
        while (j < i) {
            weight = weight + (1.0 / users[j + 1].getVisualizacoes());
            j++;
        }

        System.out.println(weight);
        return weight;
    }

    @Override
    public boolean isEmpty() {
        return (vertices.length == 0);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        Iterator itr;
        int contador = 0, i = 0;
        boolean re, entrou = false;

        while (i < numVertices) {
            entrou = true;
            itr = iteratorBFS(vertices[i]);

            while (itr.hasNext()) {
                itr.next();
                contador++;
            }
            if (contador < numVertices) {
                return false;
            }
            contador = 0;
            i++;
        }
        if (entrou == true) {
            return true;
        } else {
            return false;
        }

    }

    public Iterator reachableUsersEmpresa(T vertex, String empresa) {
        Iterator itr;
        int i = 0, j = 0, z = 0;
        User[] usersAux = new User[size()];
        User[] usersAux2 = new User[size()];
        ArrayUnorderedList resultList = new ArrayUnorderedList();

        User userAux = (User) vertex;
        boolean emp = false;
        CargosProfissionais[] cp = new CargosProfissionais[size()];

        itr = iteratorBFS(vertex);

        while (itr.hasNext()) {
            usersAux[i] = (User) itr.next();
            cp = usersAux[i].getCargos();
            while (j < cp.length) {
                if (cp[j].getEmpresa().equals(empresa) && emp == false) {
                    resultList.addToRear(usersAux[i]);
                    emp = true;
                }
                j++;
            }
            emp = false;
            j = 0;
            i++;
        }

        return resultList.iterator();

    }

    public Iterator reachableUsers(T vertex) {
        Iterator itr;

        itr = iteratorBFS(vertex);

        itr.next();

        return itr;

    }

    public boolean unrelatedUsersEmpresa(String empresaA, String empresaB) {
        Iterator itr;
        ArrayUnorderedList usersEmpresaA = new ArrayUnorderedList();
        ArrayUnorderedList usersEmpresaB = new ArrayUnorderedList();
        int i = 0, z = 0;
        CargosProfissionais[] cp;
        User[] users = new User[numVertices];
        User user;
        boolean added = false;

        while (i < numVertices) {
            users[i] = (User) vertices[i];
            i++;
        }

        i = 0;
        while (i < numVertices) {
            cp = users[i].getCargos();
            while (z < cp.length) {
                if (cp[z].getEmpresa().equals(empresaA) && added == false) {
                    usersEmpresaA.addToRear(users[i]);
                    added = true;
                }
                z++;
            }
            added = false;
            z = 0;
            i++;
        }

        z = 0;

        while (!usersEmpresaA.isEmpty()) {
            itr = iteratorBFS((T) usersEmpresaA.removeFirst());

            while (itr.hasNext()) {
                user = (User) itr.next();
                cp = user.getCargos();
                while (z < cp.length) {
                    if (cp[z].getEmpresa().equals(empresaB)) {
                        return false;
                    }
                    z++;
                }
                z = 0;

            }
        }
        return true;
    }

    public Iterator unreachableUsers(T vertex) {
        Iterator itr;
        User[] usersAux = new User[size()];
        ArrayUnorderedList lista = new ArrayUnorderedList();
        ArrayUnorderedList resultList = new ArrayUnorderedList();

        int i = 0, j = 0;

        itr = iteratorBFS(vertex);

        while (itr.hasNext()) {
            lista.addToFront((User) itr.next());
        }

        i = 0;

        while (i < size()) {
            if (!lista.contains(vertices[i])) {
                resultList.addToRear((User) vertices[i]);
            }
            i++;
        }

        return resultList.iterator();

    }

    @Override
    public int size() {
        int i = 0;

        while (i < vertices.length && vertices[i] != null) {
            i++;
        }
        return i;
    }

    protected void expandCapacityArray() {
        T[] larger = (T[]) (new Object[numVertices * 2]);

        for (int scan = 0; scan < numVertices; scan++) {
            larger[scan] = vertices[scan];
        }

        vertices = larger;
    }

    protected void expandCapacityMatriz() {
        double[][] larger = (new double[numVertices * 2][numVertices * 2]);

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                larger[i][j] = adjMatrix[i][j];
            }
        }
        adjMatrix = larger;
    }

    public String printADJMatrix() {
        int i = 0, j = 0;
        String matriz = "";
        while (i < adjMatrix.length) {
            while (j < adjMatrix.length) {
                matriz = matriz + adjMatrix[i][j] + " |";
                j++;
            }
            j = 0;
            i++;
            matriz = matriz + "\n";
        }
        return matriz;
    }
}
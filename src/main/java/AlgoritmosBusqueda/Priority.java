package AlgoritmosBusqueda;

import java.util.Arrays;

public class Priority {
   static int[][] preState;//mantiene el estado anterior.
    static Nodo neighbors_nodeArray[];

    //toma una matriz de tipo nodo, los ordena según la distancia de fn
    //y retorna el que tenga menor valor en fn
    public static Nodo sort(Nodo[] nodeArray) {
        
        if(preState!=null){//padre existente
            nodeArray = getParentRemovedNodeArray(nodeArray, preState);//elimina un vecino
        }
        
        //ordena nodos basados en fn
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = nodeArray.length - 1; j > i; j--) {
                if (nodeArray[j].fn < nodeArray[j - 1].fn) {
                    Nodo temp = nodeArray[j];
                    nodeArray[j] = nodeArray[j - 1];
                    nodeArray[j - 1] = temp;
                }
            }
        }
        Priority.neighbors_nodeArray = nodeArray;
        return nodeArray[0];
    }

    //toma la matriz de nodos y pre-estados
    //Elimina el vecino que tiene pre-estados iguales y retorna el vecino eliminado como un arreglo de tipo Nodo
    public static Nodo[] getParentRemovedNodeArray(Nodo []nodeArray, int[][] preState) {
        Nodo[] parentRemovedNodeArray = new Nodo[nodeArray.length - 1];
        int j = 0;
        for (int i = 0; i < nodeArray.length; i++) {
            if (Arrays.deepEquals(nodeArray[i].state, preState)) {
                //System.out.println("removed parent");
            } else {
                parentRemovedNodeArray[j] = nodeArray[i];
                j++;
            }
        }
        return parentRemovedNodeArray;
    }
}

//Clase Node
/*class Node {

    int fn;//valor fn
    int[][] state;//estados
    int [][] parent;
    public Node(int fn, int[][] state, int[][]parent) {
        this.fn = fn;
        this.state = state;
        this.parent = parent;
    }
}*/
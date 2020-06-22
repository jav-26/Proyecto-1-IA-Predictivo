/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmosBusqueda;

import java.util.Stack;

/**
 *
 * @author javie
 */
public class AscensoALaColina {
     Stack<Nodo> stack_state = new Stack<>();
     int emptyTile_row = 0;
     int emptyTile_col = 0;
     int stepCounter = 0;

     int min_fn;
     Nodo min_fn_node;
     
     public void hill_climbing_search() throws Exception {

        while (true) {
            System.out.println(">========================================<");
            System.out.println("costo/pasos: " + (++stepCounter));
            System.out.println("-------------");

            //Priority.preState = game_board;//change pre state
            Nodo lowestPossible_fn_node = getLowestPossible_fn_node();
            addToStackState(Priority.neighbors_nodeArray);//agregar vecinos para apilar en alto a bajo orden fn

            printState(lowestPossible_fn_node.state, "-------new state");
            //print all fn values
//            System.out.print("all sorted fn of current state: ");
//            for (int i = 0; i < Priority.neighbors_nodeArray.length; i++) {
//                System.out.print(Priority.neighbors_nodeArray[i].fn + " ");
//            }
//            System.out.println();

            //busqueda de la maxima local
            int fnCounter = 1;
            for (int i = 1; i < Priority.neighbors_nodeArray.length; i++) {
                if (Priority.neighbors_nodeArray[i - 1].fn == Priority.neighbors_nodeArray[i].fn) {//fns son iguales.
                    fnCounter++;
                }
            }
            if (Priority.neighbors_nodeArray.length != 1 && fnCounter == Priority.neighbors_nodeArray.length) {//todas las fns son iguales, igualdad de oportunidades para elegir
                System.out.println("---fn's son iguales, encontrada la maxima local---");

                //retroceso dentro de la pila
                for (int i = 0; i < Priority.neighbors_nodeArray.length; i++) {
                    if (stack_state != null) {
                        System.out.println("pop " + (i + 1));
                        stack_state.pop();
                    } else {
                        System.out.println("pila vacía dentro del bucle");
                    }
                }

                if (stack_state != null) {
                    Node gameNode = stack_state.pop();
                    game_board = gameNode.state;//actualizar el puzzle
                    Priority.preState = gameNode.parent;//actualizar los estados previos
                    locateEmptyTilePosition();//localizar la posición vacia dentro del nuevo estado

                    printState(game_board, "estado emergente de todos los fns iguales");
                    System.out.println("Posición vacía del puzzle: " + emptyTile_row + ", " + emptyTile_col);
                } else {
                    System.out.println("pila vacía dentro del primer chequeo de lm");
                }
            } else {//para el retroceso

                System.out.println("lowest fn: " + lowestPossible_fn_node.fn);

                if (lowestPossible_fn_node.fn == 0) {
                    System.out.println("-------------------------");
                    System.out.println("8-Puzzle ha sido resuelto!");
                    System.out.println("-------------------------");
                    System.out.println("Costo total/pasos para alcanzar la meta: " + stepCounter);
                    System.out.println("-------------------------------------");
                    break;
                }

                if (lowestPossible_fn_node.fn <= min_fn) {
                    min_fn = lowestPossible_fn_node.fn;
                    min_fn_node = lowestPossible_fn_node;//almacena la solución de la fn más baja para el camino

                    if (stack_state != null) {
                        Node gameNode = stack_state.pop();
                        game_board = gameNode.state;//actualizar el puzzle
                        Priority.preState = gameNode.parent;//actualiza los estados previos
                        locateEmptyTilePosition();//localizar la posición vacia dentro del nuevo estado

                        printState(game_board, "-------nuevo estado para seguir más profundo");
                        System.out.println("Posicion vacia del puzzle: " + emptyTile_row + ", " + emptyTile_col);
                    } else {
                        System.out.println("pila vacia");
                    }

                } else {
                    System.out.println("---Atasco en máximos locales.---");
                    System.out.println("Obtener el más alto no es posible");
                //break;

                    //retroceso dentro de la pila
                    for (int i = 0; i < Priority.neighbors_nodeArray.length; i++) {
                        if (stack_state != null) {
                            System.out.println("pop " + (i + 1));
                            stack_state.pop();
                        } else {
                            System.out.println("pila vacía dentro del bucle");
                        }

                    }
                    if (stack_state != null) {

                        Node gameNode = stack_state.pop();
                        game_board = gameNode.state;//actualiza el puzzle
                        Priority.preState = gameNode.parent;//actualiza los estados previos
                        locateEmptyTilePosition();//Localiza el espacio vacio para el nuevo estado

                        printState(game_board, "estado emergente de los altos");
                        System.out.println("Posición vacia del puzzle: " + emptyTile_row + ", " + emptyTile_col);
                    } else {
                        System.out.println("pila vacia dentro de la segunda verificación de lm");
                    }
                }
            }
        }
    }
}

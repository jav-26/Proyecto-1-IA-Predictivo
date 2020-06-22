package AlgoritmosBusqueda;

import java.util.ArrayList;

public class Nodo {
    private boolean visited;

    private String palabra;
    private ArrayList<Nodo> children;
    private Nodo parent;
    private int costo;
    private int estimatedCostToGoal;
    private int totalCost;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }

    public int getEstimatedCostToGoal() {
        return estimatedCostToGoal;
    }

    public void setEstimatedCostToGoal(int estimatedCostToGoal) {
        this.estimatedCostToGoal = estimatedCostToGoal;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int cost) {
        this.costo = cost;
    }

    public void setState(String palabra) {
        this.palabra = palabra;
    }

    public Nodo getParent() {
        return parent;
    }

    public void setParent(Nodo parent) {
        this.parent = parent;
    }


    // Constructor
    public Nodo(String palabra, int costo) {
        this.palabra = palabra;
        children = new ArrayList<Nodo>();
        this.costo = costo;
    }

    // Properties
    public String getState() {
        return palabra;
    }

    public ArrayList<Nodo> getChildren() {
        return children;
    }

    // Public interface
    public void addChild(Nodo child) {
        children.add(child);
    }
}

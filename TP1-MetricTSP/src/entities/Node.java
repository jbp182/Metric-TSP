package entities;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Node {
	
	private int cost;
	private Node parent;
	private List<Node> children;
	
	public Node(int cost, Node parent) {
		this.cost = cost;
		this.parent = parent;
		this.children = new LinkedList<Node>();
	}
	
	public int cost() {
		return cost;
	}
	
	public Node parent() {
		return parent;
	}
	
	public void addChild(Node child) {
		children.add(child);
	}
	
	public Iterator<Node> listChildren() {
		return children.iterator();
	}

}

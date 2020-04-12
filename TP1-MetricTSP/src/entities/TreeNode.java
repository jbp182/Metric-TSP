package entities;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class TreeNode {
	
	private int element;
	private int cost;
	private TreeNode parent;
	private Deque<TreeNode> children;
	
	// TODO ver q metodos nao servem de nada
	
	public TreeNode(int element, int cost, TreeNode parent) {
		this.element = element;
		this.cost = cost;
		this.parent = parent;
		this.children = new LinkedList<TreeNode>();
	}
	
	public int element() {
		return element;
	}
	
	public int cost() {
		return cost;
	}
	
	public TreeNode parent() {
		return parent;
	}
	
	public void addChild(TreeNode child) {
		children.add(child);
	}
	
	public Iterator<TreeNode> listDescendingChildren() {
		return children.descendingIterator();
	}
	
	public Iterator<TreeNode> listChildren() {
		return children.iterator();
	}
	
	public boolean isLeaf() {
		return children.isEmpty();
	}

}

package entities;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Tree {
	
	private TreeNode root;
	private Deque<Integer> preorder;
	private double preorderCost;
	private Graph graph;
	
	public Tree(TreeNode root, Graph graph) {
		this.root = root;
		this.preorderCost = -1;
		this.preorder = new LinkedList<Integer>();
		this.graph = graph;
	}
	
	public Iterator<Integer> preorderHamiltonian() {
		if (preorderCost < 0)
			computePreorderAndCost();
		return preorder.iterator();
	}
	
	public double getPreorderCost() {
		if (preorderCost < 0)
			computePreorderAndCost();
		return preorderCost;
	}
	
	private void computePreorderAndCost() {
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);

		preorderCost = 0;
		while (!stack.isEmpty()) {
			TreeNode n = stack.pop();
			
			if (!preorder.isEmpty()) {
				if (n.parent().element() == preorder.getLast())
					preorderCost += n.cost();
				else
					preorderCost += graph.getEdgeCost(preorder.getLast(), n.element());
			}

			preorder.add(n.element());
			
			Iterator<TreeNode> children = n.listDescendingChildren();
			while (children.hasNext())
				stack.push(children.next());
		}

		preorderCost += graph.getEdgeCost(preorder.getLast(), preorder.getFirst());
		preorder.add(preorder.getFirst());
	}

}

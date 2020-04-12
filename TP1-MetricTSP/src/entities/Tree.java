package entities;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tree {
	
	private TreeNode root;
	private List<Integer> preorder;
	private int preorderCost;
	
	public Tree(TreeNode root) {
		this.root = root;
		this.preorderCost = -1;
		this.preorder = new LinkedList<Integer>();
	}
	
	public Iterator<Integer> preorderTraversal() {
		if (preorderCost == -1)
			computePreorderAndCost();
		return preorder.iterator();
	}
	
	public int getPreorderCost() {
		if (preorderCost == -1)
			computePreorderAndCost();
		return preorderCost;
	}
	
	private void computePreorderAndCost() {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		preorderCost = 0;
		while (!stack.isEmpty()) {
			TreeNode n = stack.pop();
			preorder.add(n.element());
			preorderCost += n.cost();
			
			Iterator<TreeNode> children = n.listDescendingChildren();
			while (children.hasNext())
				stack.push(children.next());
		}
	}

}

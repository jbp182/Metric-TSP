package algorithms;

import java.util.Iterator;

import entities.Graph;
import entities.Tree;
import entities.TreeNode;

public class GreedyTSP {
	
	private Graph graph;
	private Iterator<Integer> preorder;
	private double totalCost;
	
	public GreedyTSP(Graph g) {
		this.graph = g;
	}
	
	public void solve() {
		Prim p = new Prim(graph);
		TreeNode mst = p.mstPrimTree();
		
		Tree t = new Tree(mst, graph);
		preorder = t.preorderHamiltonian();
		totalCost = t.getPreorderCost();
	}

	public Iterator<Integer> preorderTraversal() {
		return preorder;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public String getPermString() {
		String perm = "";
		while(preorder.hasNext()) {
			int n = preorder.next() + 1;
			perm += n + " ";
		}
		return perm;
	}

}

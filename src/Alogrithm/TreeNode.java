package Alogrithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Pair {
	TreeNode node;
	boolean flag;

	// flag records whether the node has been traversed
	public Pair(TreeNode node, boolean flag) {
		this.node = node;
		this.flag = flag;
	}
}

class TreeLinkNode{
	TreeLinkNode left, right, next;
	int val;
	public TreeLinkNode(int val){
		this.val = val;
	}
}

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {

	}

	public TreeNode(int x) {
		val = x;
	}

	public void setVal(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "[" + val + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + val;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TreeNode))
			return false;
		final TreeNode node = (TreeNode) obj;
		if (this.val == node.val)
			return true;
		return false;
	}

	
	public static TreeNode buildTree(String str) {
		// 1,#,2,3
		if (str == null || str.startsWith("#"))
			return null;
		if (str.contains(",")) {
			str = str.replaceAll(",", "");
		}
		char[] node = str.toCharArray();
		TreeNode root = new TreeNode(node[0] - '0');
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		TreeNode cur = queue.poll();
		int len = str.length();

		for (int i = 1; i < len; i++) {
			if (node[i] == '#') {
				if (i % 2 == 0) {
					cur = queue.poll();
				}
				continue;
			}
			TreeNode t = new TreeNode(node[i] - '0');
			queue.offer(t);
			if (cur != null) {
				if (i % 2 == 1) {
					cur.left = t;
				} else {
					cur.right = t;
					cur = queue.poll();
				}
			}
		}

		return root;
	}

	public static TreeNode createTree(int[] val) {
		LinkedList<TreeNode> nodeList = new LinkedList<TreeNode>();
		if (val.length == 0)
			return null;
		for (int i = 0; i < val.length; i++) {
			nodeList.add(new TreeNode(val[i]));
		}
		for (int i = 0; i < val.length / 2 - 1; i++) {
			nodeList.get(i).left = nodeList.get(2 * i + 1);
			nodeList.get(i).right = nodeList.get(2 * i + 2);
		}
		int lastRightParent = val.length / 2 - 1;
		nodeList.get(lastRightParent).left = nodeList
				.get(2 * lastRightParent + 1);
		if (val.length % 2 == 1)
			nodeList.get(lastRightParent).right = nodeList
					.get(2 * lastRightParent + 2);
		return nodeList.get(0);
	}

	public static void preorderTraversalRecursive(TreeNode root) {
		if (root == null)
			return;
		System.out.print(root.val + " ");
		preorderTraversalRecursive(root.left);
		preorderTraversalRecursive(root.right);
	}

	/** iterator methods should use stack */
	public static void preorderTraversalIterative(TreeNode root) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode cur = root;
		while (!s.isEmpty() || cur != null) { // cur != null is used to put root into while
			if (cur != null) {
				s.push(cur);
				System.out.print(cur.val + " ");
				cur = cur.left;
			} else {
				TreeNode p = s.pop();
				cur = p.right;
			}
		}
		System.out.println();
	}

	public static void inorderTraversalRecursive(TreeNode root) {
		if (root == null)
			return;
		inorderTraversalRecursive(root.left);
		System.out.print(root.val + " ");
		inorderTraversalRecursive(root.right);
	}

	public static void inorderTraversalIterative(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curr = root;
		while (!stack.isEmpty() || curr != null) { // cur != null is used to put root into while
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				System.out.print(stack.peek().val + " ");
				TreeNode p = stack.pop();
				curr = p.right;
			}
		}
		System.out.println();
	}

	public static void postorderTraversalRecursive(TreeNode root) {
		if (root == null)
			return;
		postorderTraversalRecursive(root.left);
		postorderTraversalRecursive(root.right);
		System.out.print(root.val + " ");
	}

	public static void postorderTraversalIterativeFlag(TreeNode root) {
		Stack<Pair> stack = new Stack<Pair>();
		TreeNode curr = root;
		while (curr != null) {
			stack.push(new Pair(curr, false));
			curr = curr.left;
		}

		while (!stack.isEmpty()) {
			Pair p = stack.peek();
			if (p.node.right == null || p.flag == true) { // 没有右子树或者右子树被访问过，应该被访问，这是唯一的打印出口
				System.out.print(stack.peek().node.val + " ");
				stack.pop();
			} else { // 有右子树且右子树没有被访问过
				p.flag = true; // 该右子树已经被访问过
				curr = p.node.right; // 对于该右子树，将其左子树迭代入栈
				while (curr != null) {
					stack.push(new Pair(curr, false));
					curr = curr.left;
				}
			}
		}
	}

	/** post order traversal should use two stacks, output to print */
	public static void postorderTraversalIterativeTwoStacks(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<TreeNode> output = new Stack<TreeNode>();
		TreeNode curr = root;
		while (!stack.isEmpty() || curr != null) { // cur != null is used to put root into while
			if (curr != null) {
				stack.push(curr);
				output.push(curr);
				curr = curr.right;
			} else {
				TreeNode node = stack.pop();
				curr = node.left;
			}
		}
		while (!output.isEmpty()) {
			System.out.print(output.pop().val + " ");
		}
	}

	/**
	 * 102. Binary Tree Level Order Traversal
	 * 二叉树层序遍历,bfs
	 * @param root
	 */
	public static void levelOrderTraversalIterative(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return;
		}
		queue.offer(root); // 初始化queue，加入root
		while (!queue.isEmpty()) {
			int levelNum = queue.size(); // 每一层的节点个数
			List<Integer> row = new ArrayList<Integer>();
			for (int i = 0; i < levelNum; i++) {
				TreeNode curr = queue.poll(); // 处理该层节点
				if (curr.left != null) {
					queue.offer(curr.left);
				}
				if (curr.right != null) {
					queue.offer(curr.right);
				}
				row.add(curr.val);
			}
			res.add(row);
		}
		System.out.println(res.toString());
	}

	/**
	 * 107. Binary Tree Level Order Traversal II
	 * 二叉树层序遍历，自底向上，bfs
	 * @param root
	 */
	public static void levelOrderTraversalBottomUp(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return;
		}
		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelNum = queue.size(); // 当前层级节点个数
			List<Integer> row = new ArrayList<Integer>();
			for (int i = 0; i < levelNum; i++) { 
				TreeNode curr = queue.poll();
				if (curr.left != null)
					queue.offer(curr.left);
				if (curr.right != null)
					queue.offer(curr.right);
				row.add(curr.val);
			}
			res.add(0, row);
		}
		System.out.println(res.toString());
	}

	public static TreeNode buildTreeWithPreAndIn(int[] preorder, int[] inorder) {
		if (inorder.length == 0 || preorder.length == 0 || inorder.length != preorder.length)
			return null;
		
		int i = 0, j = 0; 
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode root = new TreeNode(preorder[i]);
		stack.push(root);
		i++;
		
		while(i < inorder.length) {
			TreeNode curr = stack.peek();
			if(curr.val != inorder[j]) {
				curr.left = new TreeNode(preorder[i++]);
				curr = curr.left;
				stack.push(curr);
			} else {
				stack.pop();
				j++;
				if(stack.isEmpty() || stack.peek().val != inorder[j]) { // the node inorder[j] has not been added to the tree
					curr.right = new TreeNode(preorder[i++]);
					curr = curr.right;
					stack.push(curr);
				}
			}
		}
		
		return root;
	}
	
	public static TreeNode buildTreeWithInAndPost(int[] inorder, int[] postorder) {
		if (postorder.length == 0 || inorder.length == 0 || inorder.length != postorder.length)
			return null;

		int i = inorder.length - 1, j = postorder.length - 1;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode root = new TreeNode(postorder[j]);
		stack.push(root);
		j--;

		TreeNode prev = null;

		while (j >= 0) {
			while (!stack.isEmpty() && stack.peek().val == inorder[i]) {
				i--;
				prev = stack.pop();
			}

			TreeNode curr = new TreeNode(postorder[j]);
			if (prev != null) {
				prev.left = curr;
			} else if(!stack.isEmpty()){
				TreeNode father = stack.peek();
				father.right = curr;
			}
			stack.push(curr);
			j--;
			prev = null;
		}
		return root;
	}

	public static void main(String[] args) {
		// int[] vals = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		//String nodes = "1,2,3,4,5,#,#,#,#,6";
		//TreeNode root = TreeNode.buildTree(nodes);
		//TreeNode.inorderTraversalIterative(root);
		//TreeNode.postorderTraversalIterativeFlag(root);
		// TreeNode.levelOrderTraversalIterative(root);
		// TreeNode.levelOrderTraversalBottomUp(root);
		
		int[] preorder = {1,2,4,5,6,3};
		int[] inorder = {4,2,6,5,1,3};
		int[] postorder = {4,6,5,2,3,1};
		TreeNode root = TreeNode.buildTreeWithInAndPost(inorder, postorder);
		TreeNode.preorderTraversalIterative(root);
		/*TreeNode.inorderTraversalIterative(root);
		TreeNode.postorderTraversalIterativeFlag(root);*/
	}

}

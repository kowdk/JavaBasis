package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Alogrithm.TreeNode;

public class TreeSeries {

	/**
	 * 104. Maximum Depth of Binary Tree
	 * 求二叉树的最大深度
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
	
	/**
	 * 100. Same Tree
	 * 判断两个树是否相同
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		// p == null && q == null -> true;
        // (p == null && q != null) || (p != null && q == null) -> false
        if(p == null || q == null) {
			return p == q;
        }
        else {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
	}
	
	/**
	 * 判断树是否平衡
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		return treeHeight(root) != -1;
	}

	/**
	 * isBalance的辅助程序
	 * @param root
	 * @return
	 */
	private int treeHeight(TreeNode root) {
		if (root == null)
			return 0;
		int left = treeHeight(root.left); // 左树高
		int right = treeHeight(root.right); // 右树高
		if (Math.abs(left - right) > 1) // 不平衡即刻返回-1
			return -1;
		return Math.max(left, right) + 1; // 否则返回maxHeight
	}
	
	/**
	 * 226. Invert Binary Tree
	 * 将一棵树左右反转
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return null;
		root.left = invertTree(root.right);
		root.right = invertTree(root.left);
		return root;
	}

	/**
	 * 反转二叉树的迭代版本，bfs
	 * @param root
	 * @return
	 */
	public TreeNode invertTreeIteratively(TreeNode root) {
		if (root == null) return null;
	    Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.add(root);
	    while (!queue.isEmpty()) {
	        TreeNode current = queue.poll();
	        TreeNode temp = current.left;
	        current.left = current.right;
	        current.right = temp;
	        if (current.left != null) queue.add(current.left);
	        if (current.right != null) queue.add(current.right);
	    }
	    return root;
	}
	
	/**
	 * 判断一棵树是不是对称的
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isSymmetricHelp(root.left, root.right); //单树问题拆成二树问题
	}

	/**
	 * 判断树是不是对称树的辅助函数
	 * @param left
	 * @param right
	 * @return
	 */
	private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
		if (left == null || right == null)
			return left == right;
		return left.val == right.val && isSymmetricHelp(left.right, right.left)
				&& isSymmetricHelp(left.left, right.right);
	}
	
	/**
	 * 求一棵二叉树从根到叶子节点最短路径，如果当前缺失左或者右子树，缺失部分不算
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		int left = 0, right = 0;
		if (root == null) {
			return 0;
		} else if (root.left == null) {
			return minDepth(root.right) + 1;
		} else if (root.right == null) {
			return minDepth(root.left) + 1;
		} else {
			left = minDepth(root.left);
			right = minDepth(root.right);
		}
		return Math.min(left, right) + 1;
	}
	
	/**
	 * 判断一棵树是不是二叉排序树，迭代 BST中序遍历序列是升序排列的
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curr = root;
		TreeNode prev = null;
		while (!stack.isEmpty() || curr != null) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				TreeNode p = stack.pop();
				//System.out.println(p.val + " ");
				if (prev != null && prev.val >= p.val)
					return false;
				prev = p;
				curr = p.right;
			}
		}
		return true;
	}

	
	/**
	 * 修复一棵排序二叉树，找到树中的两个错误节点，也就是不按照中序排序的两个节点
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode recoverTree(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curr = root;
		TreeNode pre = new TreeNode(Integer.MIN_VALUE);
		TreeNode w1 = null, w2 = null;
		while (!stack.isEmpty() || curr != null) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				TreeNode p = stack.pop();
				if (w1 == null && pre.val >= p.val) {
					w1 = pre;
				}
				if (w1 != null && pre.val >= p.val) {
					w2 = pre;
				}
				pre = p;
				curr = p.right;
			}
		}
		int tmp = w1.val;
		w1.val = w2.val;
		w2.val = tmp;
		return root;
	}
	
	/**
	 * 将一个升序数组转化为一个BST
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;
		return mergeBST(nums, 0, nums.length - 1);
	}

	/**
	 * sortedArrayToBST的辅助程序
	 * @param nums
	 * @param lo
	 * @param hi
	 * @return
	 */
	private TreeNode mergeBST(int[] nums, int lo, int hi) {
		if (lo > hi) // 当mid=lo=hi的时候，创建节点自己
			return null;
		int mid = (hi - lo) / 2 + lo; // mid是当前的根节点
		TreeNode node = new TreeNode(nums[mid]); // 创建根节点
		node.left = mergeBST(nums, lo, mid - 1);
		node.right = mergeBST(nums, mid + 1, hi);
		return node;
	}
	
	/**
	 * 找出BST中第K小个节点
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode curr = root;
		while (!s.isEmpty() || curr != null) {
			if (curr != null) {
				s.push(curr);
				curr = curr.left;
			} else {
				TreeNode p = s.pop();
				k--;
				if (k == 0)
					return p.val;
				curr = p.right;
			}
		}
		return -1;
	}
	
	/**
	 * 求二叉排序树中给定节点p和q的最低公共祖先
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){ // p和q都比root小，公共祖先应该在左子树
            return lowestCommonAncestorBST(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){ // p和q都比root大，公共祖先应该在右子树
            return lowestCommonAncestorBST(root.right, p, q);
        }else{ // p和q把root夹在中间，公共祖先就是root
            return root;
        }
    }
	
	/**
	 * 求普通二叉树中给定节点p和q的最低公共祖先
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestorBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
    	if(root == null || root == p || root == q) return root; // 如果root等于其中的一个
    	TreeNode left = lowestCommonAncestorBinaryTree(root.left, p, q); // 找出root左子树中的公共祖先
    	TreeNode right = lowestCommonAncestorBinaryTree(root.right, p, q); // 找出root右子树中的公共祖先
    	if(left != null && right != null) return root; // 如果root左子树和右子树都是公共祖先
    	return left == null ? right : left;
    }
	
	/**
	 * 计算完全二叉树的节点个数
	 * 
	 * @param root
	 * @return
	 */
	public int countNodes(TreeNode root) {
		int nodes = 0, h = height(root);

		while (root != null) {
			if (height(root.right) == h - 1) { // 说明最后一个叶子节点在右子树
				nodes += 1 << h; // 2^h
				root = root.right;
			} else { // 说明最后一个叶子节点在左子树
				nodes += 1 << h - 1; // 2^(h-1)
				root = root.left;
			}
			h--; // 树高每次减一，下探
		}
		return nodes;
	}
	
	/**
	 * countNodes的辅助程序，这里返回的height是树高-1
	 * 
	 * @param root
	 * @return
	 */
	private int height(TreeNode root) {
		return root == null ? -1 : 1 + height(root.left);
	}
	
	/**
	 * 将二叉树调整为链表，左子树均为空，右子树为先序遍历序列
	 * 
	 * @param root
	 */
	public void flatten(TreeNode root) {
		flatten(root, null);
	}

	/**
	 * flatten辅助程序，先序遍历的反转版本
	 * 
	 * @param root
	 * @param pre
	 * @return
	 */
	private TreeNode flatten(TreeNode root, TreeNode pre) {
		if (root == null)
			return pre;
		pre = flatten(root.right, pre); // 自底向上，从最右节点开始
		pre = flatten(root.left, pre);
		root.right = pre; // right指向前一个底部节点，
		root.left = null; // left置为null
		pre = root; // pre上移
		return pre;
	}
	
	/**
	 * 257. Binary Tree Paths
	 * 求二叉树的所有路径，dfs
	 *     1
		 /   \
		2     3
		 \
		  5
	 * => ["1->2->5", "1->3"]
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<String>();
		Stack<TreeNode> stack = new Stack<TreeNode>(); // 用stack来保存TreeNode
		Stack<String> paths = new Stack<String>(); // 用path保存路径

		if (root == null)
			return res;
		stack.push(root);
		paths.push("");

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop(); // 弹出节点
			String str = paths.pop(); // 弹出路径
			if (node.left == null && node.right == null) { // 当没有儿子节点时，该条路径结束
				res.add(str + Integer.toString(node.val));
			}
			if (node.left != null) { // 左节点不空，更新左节点，更新路径
				stack.push(node.left); 
				paths.push(str + Integer.toString(node.val) + "->");
			}
			if (node.right != null) { // 右节点不空，更新右节点，更新路径
				stack.push(node.right); 
				paths.push(str + Integer.toString(node.val) + "->");
			}
		}
		return res;
    }
	
	public List<String> binaryTreePathsII(TreeNode root) {
		Queue<TreeNode> nodes = new LinkedList<TreeNode>();
		Queue<String> paths = new LinkedList<String>();
		List<String> res = new ArrayList<String>();
		if(root == null) return res;
		
		nodes.offer(root);
		paths.offer("");
		while(!nodes.isEmpty()) {
			TreeNode curr = nodes.poll();
			String str = paths.poll();
			if(curr.left == null && curr.right == null) {
				res.add(str + Integer.valueOf(curr.val));
			} else {
				if(curr.left != null) {
					nodes.offer(curr.left);
					paths.offer(str + Integer.valueOf(curr.val) + "->");
				} 
				if(curr.right != null) {
					nodes.offer(curr.right);
					paths.offer(str + Integer.valueOf(curr.val) + "->");
				}
			}
		}
		return res;
	}
	
	/**
	 * 二叉树的序列化和反序列化
	 */
	private static final String spliter = ",";
	private static final String NN = "#";

	/**
	 * 将一棵树序列化为字符串
	 * @param root
	 * @return
	 */
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	private void buildString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append(NN).append(spliter);
			return;
		}
		sb.append(node.val).append(spliter);
		buildString(node.left, sb);
		buildString(node.right, sb);
	}

	/**
	 * 将字符串反序列化为一棵树
	 * @param data
	 * @return
	 */
	public TreeNode deserialize(String data) {
		Deque<String> nodes = new LinkedList<String>();
		nodes.addAll(Arrays.asList(data.split(spliter)));
		return buildTree(nodes);
	}

	private TreeNode buildTree(Deque<String> nodes) {
		String val = nodes.poll();
		if (val.equals(NN)) {
			return null;
		}

		TreeNode node = new TreeNode(Integer.valueOf(val));
		node.left = buildTree(nodes);
		node.right = buildTree(nodes);
		return node;
	}
	
	/**
	 * 求二叉树的最大路径和，路径可以跨越根节点
	 * @param root
	 * @return
	 */
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    private int maxValue;
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left)); // 之所以和0比较，是因为节点可能有负值
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
	
	/**
	 * 根据先序遍历和中序遍历构建一棵树，迭代方式
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTreeFromPreOrderAndInOrderIteratively(int[] preorder, int[] inorder) {
		if (preorder.length == 0 || inorder.length == 0) {
			return null;
		}

		int i = 0, j = 0;
		TreeNode root = new TreeNode(preorder[0]);
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		i++;

		while (i < preorder.length) {
			TreeNode node = stack.peek();
			if (node.val != inorder[j]) {
				node.left = new TreeNode(preorder[i++]);
				node = node.left;
				stack.push(node);
			} else {
				stack.pop();
				j++;
				if (stack.isEmpty() || stack.peek().val != inorder[j]) {
					node.right = new TreeNode(preorder[i++]);
					node = node.right;
					stack.push(node);
				}
			}
		}
		return root;
	}
	
	public TreeNode buildTreeFromPreorderAndInorderRecursively(int[] preorder, int[] inorder){
		return buildTreeCorePreAndIn(0, 0, inorder.length - 1, preorder, inorder);
	}
	
	/**
	 *  inorder和preorder是配合完成的，preorder的preStart是根元素，然后从inorder中找到根，其左边是左子树，右边是右子树
	 *  对于preorder来说，可以根据inorder的下标确定出左子树和右子树，然后分别传入左右子树进行递归
	 * @param preStart
	 * @param inLo
	 * @param inHi
	 * @param preorder
	 * @param inorder
	 * @return
	 */
    private TreeNode buildTreeCorePreAndIn(int preStart, int inLo, int inHi, int[] preorder, int[] inorder) {
        if(preStart > preorder.length - 1 || inLo > inHi)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);//用当前的根节点来分治
        int inRoot = 0;
        for(int i = inLo; i <= inHi; i++) { // 在inorder中找根
            if(preorder[preStart] == inorder[i]) {
                inRoot = i;
            }
        }
        
        //分别传入先序和中序的左子树和右子树
        root.left = buildTreeCorePreAndIn(preStart+1, inLo, inRoot - 1, preorder, inorder);
        root.right = buildTreeCorePreAndIn(preStart + inRoot - inLo + 1, inRoot+1, inHi, preorder, inorder);
        
        return root;
    }
	
    /**
     * 根据后序和中序数组重建二叉树，迭代
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeFromPostorderAndInorderIteratively(int[] inorder, int[] postorder) {
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
    
    /**
     * 根据后序和中序数组重建二叉树，递归
     * @param postorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeFromPostorderAndInorderRecursively(int[] postorder, int[] inorder){
		return buildTreeCorePostAndIn(0, postorder.length - 1, 0, inorder.length - 1, postorder, inorder);
	}
    
    /**
     * 也是在以postorder为基准在inorder中找root，对于inorder来说，就是0~root-1和root+1~inorder.length;
     * 对于postorder来说，最主要是通过计算得出中间的分割点，而计算依赖于inorder中左子树与右子树的节点数量
     * @param postLo
     * @param postHi
     * @param inLo
     * @param inHi
     * @param postorder
     * @param inorder
     * @return
     */
    private TreeNode buildTreeCorePostAndIn(int postLo, int postHi, int inLo, int inHi, int[] postorder, int[] inorder) {
        if(postLo > postHi || inLo > inHi)
            return null;
        TreeNode root = new TreeNode(postorder[postHi]); // post的最后一个是根
        int inRoot = 0;
        for(int i = inLo; i <= inHi; i++) { // 在inorder中寻找根
            if(postorder[postHi] == inorder[i]) {
                inRoot = i;
            }
        }
        
        // 分别传入后序和中序的左子树和右子树
        root.left = buildTreeCorePostAndIn(postLo, postLo + (inRoot - inLo - 1), inLo, inRoot - 1, postorder, inorder);
        root.right = buildTreeCorePostAndIn(postLo + (inRoot - inLo), postHi - 1, inRoot+1, inHi, postorder, inorder);
        
        return root;
    }
	
	public static void main(String[] args) {
		TreeSeries t = new TreeSeries();

		int[] preorder = new int[] { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] inorder = new int[] { 4, 7, 2, 1, 5, 3, 8, 6 };
		int[] postorder = new int[] {7, 4, 2, 5, 8, 6, 3, 1};
		TreeNode node = t.buildTreeFromPreOrderAndInOrderIteratively(preorder, inorder);
		TreeNode.preorderTraversalRecursive(node);
	}

}

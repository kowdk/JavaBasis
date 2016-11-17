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
	 * ���������������
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
	 * �ж��������Ƿ���ͬ
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
	 * �ж����Ƿ�ƽ��
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		return treeHeight(root) != -1;
	}

	/**
	 * isBalance�ĸ�������
	 * @param root
	 * @return
	 */
	private int treeHeight(TreeNode root) {
		if (root == null)
			return 0;
		int left = treeHeight(root.left); // ������
		int right = treeHeight(root.right); // ������
		if (Math.abs(left - right) > 1) // ��ƽ�⼴�̷���-1
			return -1;
		return Math.max(left, right) + 1; // ���򷵻�maxHeight
	}
	
	/**
	 * 226. Invert Binary Tree
	 * ��һ�������ҷ�ת
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
	 * ��ת�������ĵ����汾��bfs
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
	 * �ж�һ�����ǲ��ǶԳƵ�
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isSymmetricHelp(root.left, root.right); //���������ɶ�������
	}

	/**
	 * �ж����ǲ��ǶԳ����ĸ�������
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
	 * ��һ�ö������Ӹ���Ҷ�ӽڵ����·���������ǰȱʧ�������������ȱʧ���ֲ���
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
	 * �ж�һ�����ǲ��Ƕ��������������� BST��������������������е�
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
	 * �޸�һ��������������ҵ����е���������ڵ㣬Ҳ���ǲ�������������������ڵ�
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
	 * ��һ����������ת��Ϊһ��BST
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;
		return mergeBST(nums, 0, nums.length - 1);
	}

	/**
	 * sortedArrayToBST�ĸ�������
	 * @param nums
	 * @param lo
	 * @param hi
	 * @return
	 */
	private TreeNode mergeBST(int[] nums, int lo, int hi) {
		if (lo > hi) // ��mid=lo=hi��ʱ�򣬴����ڵ��Լ�
			return null;
		int mid = (hi - lo) / 2 + lo; // mid�ǵ�ǰ�ĸ��ڵ�
		TreeNode node = new TreeNode(nums[mid]); // �������ڵ�
		node.left = mergeBST(nums, lo, mid - 1);
		node.right = mergeBST(nums, mid + 1, hi);
		return node;
	}
	
	/**
	 * �ҳ�BST�е�KС���ڵ�
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
	 * ������������и����ڵ�p��q����͹�������
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){ // p��q����rootС����������Ӧ����������
            return lowestCommonAncestorBST(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){ // p��q����root�󣬹�������Ӧ����������
            return lowestCommonAncestorBST(root.right, p, q);
        }else{ // p��q��root�����м䣬�������Ⱦ���root
            return root;
        }
    }
	
	/**
	 * ����ͨ�������и����ڵ�p��q����͹�������
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestorBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
    	if(root == null || root == p || root == q) return root; // ���root�������е�һ��
    	TreeNode left = lowestCommonAncestorBinaryTree(root.left, p, q); // �ҳ�root�������еĹ�������
    	TreeNode right = lowestCommonAncestorBinaryTree(root.right, p, q); // �ҳ�root�������еĹ�������
    	if(left != null && right != null) return root; // ���root�����������������ǹ�������
    	return left == null ? right : left;
    }
	
	/**
	 * ������ȫ�������Ľڵ����
	 * 
	 * @param root
	 * @return
	 */
	public int countNodes(TreeNode root) {
		int nodes = 0, h = height(root);

		while (root != null) {
			if (height(root.right) == h - 1) { // ˵�����һ��Ҷ�ӽڵ���������
				nodes += 1 << h; // 2^h
				root = root.right;
			} else { // ˵�����һ��Ҷ�ӽڵ���������
				nodes += 1 << h - 1; // 2^(h-1)
				root = root.left;
			}
			h--; // ����ÿ�μ�һ����̽
		}
		return nodes;
	}
	
	/**
	 * countNodes�ĸ����������ﷵ�ص�height������-1
	 * 
	 * @param root
	 * @return
	 */
	private int height(TreeNode root) {
		return root == null ? -1 : 1 + height(root.left);
	}
	
	/**
	 * ������������Ϊ������������Ϊ�գ�������Ϊ�����������
	 * 
	 * @param root
	 */
	public void flatten(TreeNode root) {
		flatten(root, null);
	}

	/**
	 * flatten����������������ķ�ת�汾
	 * 
	 * @param root
	 * @param pre
	 * @return
	 */
	private TreeNode flatten(TreeNode root, TreeNode pre) {
		if (root == null)
			return pre;
		pre = flatten(root.right, pre); // �Ե����ϣ������ҽڵ㿪ʼ
		pre = flatten(root.left, pre);
		root.right = pre; // rightָ��ǰһ���ײ��ڵ㣬
		root.left = null; // left��Ϊnull
		pre = root; // pre����
		return pre;
	}
	
	/**
	 * 257. Binary Tree Paths
	 * �������������·����dfs
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
		Stack<TreeNode> stack = new Stack<TreeNode>(); // ��stack������TreeNode
		Stack<String> paths = new Stack<String>(); // ��path����·��

		if (root == null)
			return res;
		stack.push(root);
		paths.push("");

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop(); // �����ڵ�
			String str = paths.pop(); // ����·��
			if (node.left == null && node.right == null) { // ��û�ж��ӽڵ�ʱ������·������
				res.add(str + Integer.toString(node.val));
			}
			if (node.left != null) { // ��ڵ㲻�գ�������ڵ㣬����·��
				stack.push(node.left); 
				paths.push(str + Integer.toString(node.val) + "->");
			}
			if (node.right != null) { // �ҽڵ㲻�գ������ҽڵ㣬����·��
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
	 * �����������л��ͷ����л�
	 */
	private static final String spliter = ",";
	private static final String NN = "#";

	/**
	 * ��һ�������л�Ϊ�ַ���
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
	 * ���ַ��������л�Ϊһ����
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
	 * ������������·���ͣ�·�����Կ�Խ���ڵ�
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
        int left = Math.max(0, maxPathDown(node.left)); // ֮���Ժ�0�Ƚϣ�����Ϊ�ڵ�����и�ֵ
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
	
	/**
	 * ������������������������һ������������ʽ
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
	 *  inorder��preorder�������ɵģ�preorder��preStart�Ǹ�Ԫ�أ�Ȼ���inorder���ҵ���������������������ұ���������
	 *  ����preorder��˵�����Ը���inorder���±�ȷ��������������������Ȼ��ֱ��������������еݹ�
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
        TreeNode root = new TreeNode(preorder[preStart]);//�õ�ǰ�ĸ��ڵ�������
        int inRoot = 0;
        for(int i = inLo; i <= inHi; i++) { // ��inorder���Ҹ�
            if(preorder[preStart] == inorder[i]) {
                inRoot = i;
            }
        }
        
        //�ֱ���������������������������
        root.left = buildTreeCorePreAndIn(preStart+1, inLo, inRoot - 1, preorder, inorder);
        root.right = buildTreeCorePreAndIn(preStart + inRoot - inLo + 1, inRoot+1, inHi, preorder, inorder);
        
        return root;
    }
	
    /**
     * ���ݺ�������������ؽ�������������
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
     * ���ݺ�������������ؽ����������ݹ�
     * @param postorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeFromPostorderAndInorderRecursively(int[] postorder, int[] inorder){
		return buildTreeCorePostAndIn(0, postorder.length - 1, 0, inorder.length - 1, postorder, inorder);
	}
    
    /**
     * Ҳ������postorderΪ��׼��inorder����root������inorder��˵������0~root-1��root+1~inorder.length;
     * ����postorder��˵������Ҫ��ͨ������ó��м�ķָ�㣬������������inorder�����������������Ľڵ�����
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
        TreeNode root = new TreeNode(postorder[postHi]); // post�����һ���Ǹ�
        int inRoot = 0;
        for(int i = inLo; i <= inHi; i++) { // ��inorder��Ѱ�Ҹ�
            if(postorder[postHi] == inorder[i]) {
                inRoot = i;
            }
        }
        
        // �ֱ��������������������������
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

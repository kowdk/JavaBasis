package CollectionAndGenericity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SortedBinTree<T extends Comparable>
{
	static class Node
	{
		Object data;
		Node parent; 
		Node left;
		Node right;
		public Node(Object data , Node parent 
			, Node left , Node right)
		{
			this.data = data;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		public String toString()
		{
			return "[data=" + data + "]"; 
		}
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (obj.getClass() == Node.class)
			{
				Node target = (Node)obj;
				return data.equals(target.data)
					&& left == target.left
					&& right == target.right
					&& parent == target.parent;
			}
			return false;
		}
	}
	private Node root;
	//两个构造器用于创建排序二叉树
	public SortedBinTree()
	{
		root = null;
	}
	public SortedBinTree(T o)
	{
		root = new Node(o , null , null , null);
	}
	//添加节点
	public void add(T ele)
	{
		//如果根节点为null
		if (root == null)
		{
			root = new Node(ele , null , null , null);
		}
		else
		{
			Node current = root;
			Node parent = null;
			int cmp = 0;
			//搜索合适的叶子节点，以该叶子节点为父节点添加新节点
			do
			{
				parent = current;
				cmp = ele.compareTo(current.data);
				//如果新节点的值大于当前节点的值
				if (cmp > 0)
				{
					//以右子节点作为当前节点
					current = current.right;
				}
				//如果新节点的值小于当前节点的值
				else
				{
					//以左子节点作为当前节点
					current = current.left;
				}
			}
			while (current != null);
			//创建新节点
			Node newNode = new Node(ele , parent , null , null);
			//如果新节点的值大于父节点的值
			if (cmp > 0)
			{
				//新节点作为父节点的右子节点
				parent.right = newNode;
			}
			//如果新节点的值小于父节点的值
			else
			{
				//新节点作为父节点的左子节点
				parent.left = newNode;
			}
		}
	}
	//删除节点
	public void remove(T ele)
	{
		//获取要删除的节点
		Node target = getNode(ele);
		if (target == null)
		{
			return;
		}
		//左、右子树为空
		if (target.left == null
			&& target.right == null)
		{
			//被删除节点是根节点
			if (target == root)
			{
				root = null;
			}
			else
			{
				//被删除节点是父节点的左子节点
				if (target == target.parent.left)
				{
					//将target的父节点的left设为null
					target.parent.left = null;
				}
				else
				{
					//将target的父节点的right设为null
					target.parent.right = null;
				}
				target.parent = null;
			}
		}
		//左子树为空，右子树不为空
		else if (target.left == null
			&& target.right != null)
		{
			//被删除节点是根节点
			if (target == root)
			{
				root = target.right;
			}
			else
			{
				//被删除节点是父节点的左子节点
				if (target == target.parent.left)
				{
					//让target的父节点的left指向target的右子树
					target.parent.left = target.right;
				}
				else
				{
					//让target的父节点的right指向target的右子树
					target.parent.right = target.right;
				}
				//让target的右子树的parent指向target的parent
				target.right.parent = target.parent;
			}
		}
		//左子树不为空，右子树为空
		else if(target.left != null
			&& target.right == null)
		{
			//被删除节点是根节点
			if (target == root)
			{
				root = target.left;
			}
			else
			{
				//被删除节点是父节点的左子节点
				if (target == target.parent.left)
				{
					//让target的父节点的left指向target的左子树
					target.parent.left = target.left;
				}
				else
				{
					//让target的父节点的right指向target的左子树
					target.parent.right = target.left;
				}
				//让target的左子树的parent指向target的parent
				target.left.parent = target.parent;
			}
		}
		//左、右子树都不为空
		else
		{
			//leftMaxNode用于保存target节点的左子树中值最大的节点
			Node leftMaxNode = target.left;
			//搜索target节点的左子树中值最大的节点
			while (leftMaxNode.right != null)
			{
				leftMaxNode = leftMaxNode.right;
			}
			//从原来的子树中删除leftMaxNode节点
			leftMaxNode.parent.right = null;
			//让leftMaxNode的parent指向target的parent
			leftMaxNode.parent = target.parent;
			//被删除节点是父节点的左子节点
			if (target == target.parent.left)
			{
				//让target的父节点的left指向leftMaxNode
				target.parent.left = leftMaxNode;
			}
			else
			{
				//让target的父节点的right指向leftMaxNode
				target.parent.right = leftMaxNode;
			}
			leftMaxNode.left = target.left;
			leftMaxNode.right = target.right;
			target.parent = target.left = target.right = null;
		}
	}
	//根据给定的值搜索节点
	public Node getNode(T ele)
	{
		//从根节点开始搜索
		Node p = root;
		while (p != null) 
		{
			int cmp = ele.compareTo(p.data);
			//如果搜索的值小于当前p节点的值
			if (cmp < 0)
			{
				//向左子树搜索
				p = p.left;
			}
			//如果搜索的值大于当前p节点的值
			else if (cmp > 0)
			{
				//向右子树搜索
				p = p.right;
			}
			else
			{
				return p;
			}
		}
		return null;
	}
	//广度优先遍历
	public List<Node> breadthFirst()
	{
		Queue<Node> queue = new ArrayDeque<Node>();
		List<Node> list = new ArrayList<Node>();
		if( root != null)
		{
			//将根元素加入“队列”
			queue.offer(root);
		}
		while(!queue.isEmpty())
		{
			//将该队列的“队尾”的元素添加到List中
			list.add(queue.peek());
			Node p = queue.poll();
			//如果左子节点不为null，将它加入“队列”
			if(p.left != null)
			{
				queue.offer(p.left);
			}
			//如果右子节点不为null，将它加入“队列”
			if(p.right != null)
			{
				queue.offer(p.right);
			}
		}
		return list;
	}
}

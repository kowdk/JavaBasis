package CollectionAndGenericity;


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
public class SortedBinTreeTest
{
	public static void main(String[] args) 
	{
		SortedBinTree<Integer> tree 
			= new SortedBinTree<Integer>();
		//添加节点
		tree.add(5);
		tree.add(20);
		tree.add(10);
		tree.add(3);
		tree.add(15);
		tree.add(8);
		
		tree.add(30);
		System.out.println(tree.breadthFirst());
		//删除节点
		tree.remove(20);
		System.out.println(tree.breadthFirst());
	}
}

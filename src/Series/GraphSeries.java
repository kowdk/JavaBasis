package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphSeries {
	/**
	 * n表示有n个顶点，edges是边集合
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n == 1)
			return Collections.singletonList(0);
		List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
		List<Integer> leaves = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			adj.add(new HashSet<>());
		}
		for (int i = 0; i < edges.length; i++) {
			adj.get(edges[i][0]).add(edges[i][1]);
			adj.get(edges[i][1]).add(edges[i][0]);
		}

		for (int i = 0; i < n; i++) {
			if (adj.get(i).size() == 1) {
				leaves.add(i);
			}
		}

		while (n > 2) {
			n -= leaves.size();
			List<Integer> tmp = new ArrayList<Integer>();
			for (int i : leaves) {
				int j = adj.get(i).iterator().next();
				adj.get(j).remove(i);
				if (adj.get(j).size() == 1)
					tmp.add(j);
			}
			leaves = tmp;
		}

		return leaves;
	}

	/**
	 * 判断一个有向图是否存在环路
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			adj.add(new HashSet<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		int[] indegree = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			for (int j : adj.get(i)) {
				indegree[j]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0)
				queue.offer(i);
		}

		while (!queue.isEmpty()) {
			int pre = queue.poll();
			for (int ready : adj.get(pre)) {
				if (--indegree[ready] == 0) {
					queue.offer(ready);
				}
			}
			numCourses--;
		}

		return numCourses == 0;
	}

	/**
	 * 返回一个正确的学习序列，图的拓扑排序
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (prerequisites.length == 0) {
			int[] res = new int[numCourses];
			for (int i = numCourses - 1; i >= 0; i--) {
				res[i] = numCourses - (i + 1);
			}
			return res;
		}

		List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			adj.add(new HashSet<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		int[] indegree = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			for (int j : adj.get(i)) {
				indegree[j]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0)
				queue.offer(i);
		}

		int[] res = new int[numCourses];
		int i = 0;
		while (!queue.isEmpty()) {
			int pre = queue.poll();
			res[i++] = pre;
			for (int ready : adj.get(pre)) {
				if (--indegree[ready] == 0) {
					queue.offer(ready);
				}
			}
			numCourses--;
		}

		return numCourses == 0 ? res : new int[0];
	}

	/**
	 * clone一个无向图, 不仅要拷贝节点本身和邻居节点
	 * 
	 * @param node
	 * @return
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.offer(node);

		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

		map.put(node, new UndirectedGraphNode(node.label));

		while (!queue.isEmpty()) {
			UndirectedGraphNode cur = queue.poll();

			for (UndirectedGraphNode neighbor : cur.neighbors) {
				if (!map.containsKey(neighbor)) {
					map.put(neighbor, new UndirectedGraphNode(neighbor.label));
					queue.add(neighbor);
				} else {
					map.get(cur).neighbors.add(map.get(neighbor));
				}
			}
		}

		return map.get(node);
	}
	
	public static void main(String[] args) {
		GraphSeries t = new GraphSeries();
		
		UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		UndirectedGraphNode node2 = new UndirectedGraphNode(2);
		UndirectedGraphNode node3 = new UndirectedGraphNode(3);
		node1.neighbors.add(node2);
		node1.neighbors.add(node3);
		node2.neighbors.add(node1);
		node2.neighbors.add(node3);
		node3.neighbors.add(node1);
		node3.neighbors.add(node2);
		
		UndirectedGraphNode newRoot = t.cloneGraph(node1);
		System.out.println(newRoot.neighbors);
		
		// n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
		int n = 3; 
		int[][] edges = { { 1, 0 }, { 2, 0 } };
		System.out.println(Arrays.toString(t.findOrder(n, edges)));
		 
	}
}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	public UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}

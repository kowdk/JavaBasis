package Series;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import Alogrithm.ListNode;

public class LinkedListSeries {

	/**
	 * 根据一个值分离链表
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode head1 = new ListNode(0), head2 = new ListNode(0); // two dummy head
		ListNode p = head1, q = head2; // two it pointers
		while (head != null) {
			if (head.val < x) {
				p.next = head;
				p = p.next;
			} else {
				q.next = head;
				q = q.next;
			}
			head = head.next;
		}

		q.next = null; // q.next==null cause there is a circle here
		p.next = head2.next; // connect the two lists
		return head1.next; // jump the dummy
	}

	/**
	 * 合并K个链表
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsUsingTwoMerge(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		return mergeK(lists, 0, lists.length - 1);
	}

	private ListNode mergeK(ListNode[] lists, int lo, int hi) {
		if (lo > hi) {
			return null;
		} else if (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			ListNode l1 = mergeK(lists, lo, mid);
			ListNode l2 = mergeK(lists, mid + 1, hi);
			return mergeTwoSortedListRecursively(l1, l2);
		} else {
			return lists[lo];
		}
	}

	/**
	 * 使用堆合并K个链表
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsUsingHeap(List<ListNode> lists) {
		if (lists == null || lists.size() == 0)
			return null;

		// 定义ListNode的大小
		Comparator<ListNode> comp = new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		};

		// 以lists.size()为初始大小，防止溢出，用PriorityQueue建立最小堆
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(
				lists.size(), comp);

		// 新建假头，使用假头遍历指针p，需要新生成一个list，一定使用假头
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;

		// 将所有链表都加入堆
		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {
			// 出堆的一定是当前所有链表排头的最小值
			p.next = queue.poll();
			ListNode.printList(p.next);
			p = p.next;

			// 如果链表还有元素，加入堆
			if (p.next != null) {
				queue.add(p.next);
			}
		}
		return dummy.next;
	}

	public ListNode mergeTwoSortedListIteratively(ListNode head1, ListNode head2) {
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;

		// k must point to dummy, when dummy.next == head, you can point to dummy.next
		ListNode dummy = new ListNode(-1), k = dummy;
		ListNode p = head1, q = head2;
		while (p != null && q != null) {
			if (p.val <= q.val) {
				k.next = p;
				p = p.next;
			} else {
				k.next = q;
				q = q.next;
			}
			k = k.next;
		}
		while (p != null) {
			k.next = p;
			p = p.next;
			k = k.next;
		}
		while (q != null) {
			k.next = q;
			q = q.next;
			k = k.next;
		}

		return dummy.next;
	}

	/**
	 * 递归合并两个链表
	 * @param head1
	 * @param head2
	 * @return
	 */
	public ListNode mergeTwoSortedListRecursively(ListNode head1, ListNode head2) {
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;

		ListNode merged = null; //合并后的链表头

		if (head1.val < head2.val) {
			merged = head1;
			merged.next = mergeTwoSortedListRecursively(head1.next, head2);
		} else {
			merged = head2;
			merged.next = mergeTwoSortedListRecursively(head1, head2.next);
		}
		return merged;
	}

	/**
	 * 反转列表
	 * 1->2->3->null;
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		ListNode prev = null; // null其实指的是当前链表结尾null
		while (head != null) { // head->1
			ListNode curr = head.next;
			head.next = prev;
			prev = head;
			head = curr;
		}
		return prev; // prev->4
	}
	
	/**
	 * 按照指定位数旋转链表
	 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * return 1->4->3->2->5->NULL.
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(head == null)
			return null;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy, q = head;
		for(int i = 1; i < n+1; i++) {
			if(i < m) {
				p = p.next;
			}
			q = q.next;
		}
		//p->1;q->5
		ListNode newHead = p.next, prev = q;
		while(newHead != q) {
			ListNode curr = newHead.next;
			newHead.next = prev;
			prev = newHead;
			newHead = curr;
		}
		p.next = prev;
		return dummy.next;
	}
	
	private int listLen(ListNode head) {
		int len = 0;
		while(head != null) {
			len++;
			head = head.next;
		}
		return len;
	}
	/**
	 * 1->2->3->4->5
     * For k = 2, you should return: 2->1->4->3->5
     * For k = 3, you should return: 3->2->1->4->5
	 * @param head
	 * @param k
	 * @return
	 */
    public ListNode reverseKGroup(ListNode head, int k) {
    	if(head == null || head.next == null || k < 2) {
        	return head;
        }
                
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int cnt = 0;
        ListNode prev = dummy, tail = dummy;
        while(true) {
        	cnt = k;
        	while(cnt > 0 && tail != null) {
        		tail = tail.next;
        		cnt--;
        	}
        	if(tail == null) break; // 后面的不足一个循环
        	head = prev.next;
        	// prev是dummy，tail是3，k=3的情况下
        	// 循环前是1->2->3->4->5
        	while(prev.next != tail) { // prev如果不和tail相连
        		// 一次循环后，2->3->1->4->5
        		// 二次循环后，3->2->1->4->5
        		ListNode tmp = prev.next; // tmp是prev后面一个，目的要将tmp向后甩
        		prev.next = tmp.next;
        		
        		tmp.next = tail.next;
        		tail.next = tmp; // tail在向前移
        	}
        	prev = head;
        	tail = head;
        }
        return dummy.next;
    }
	
	/**
	 * 按照奇偶数重新组合链表
	 * @param head
	 * @return
	 */
	public ListNode oddEvenList(ListNode head) {
		if(head == null)
			return null;
		
		ListNode oddHead = new ListNode(-1); // 涉及到Head的计算，创建假头
		ListNode evenHead = new ListNode(-1);
		ListNode p = oddHead, q = evenHead;
		int cnt = 1;
		while(head != null) {
			if((cnt & 0x1) != 0) {
				p.next = head;
				p = p.next;
			} else {
				q.next = head;
				q = q.next;
			}
			head = head.next;
			cnt++;
		}
		p.next = evenHead.next;
		q.next = null;
		return oddHead.next;
	}
	
	/**
	 * 删除有重复值的元素
	 * 1->2->2->3:
	 * 1->2->3
	 * @param head
	 * @return
	 */
    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode p = head, q = p.next;
        while(q != null) {
            if(p.val == q.val) {
                p.next = q.next;
                q = q.next;
            } else {
                p = p.next;
                q = q.next;
            }
        }
        return head;
    }
	
	/**
	 * 将有重复值的元素全部删除
	 * 1->2->2->3
	 * 1->3
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode q = dummy, p = head;
        while(p != null && p.next != null) { // 要删除所有的元素不是p和q比，而是p和p的next下一个比，但防止越界
            if(p.val == p.next.val) { 
                int tmp = p.val;
                while(p.val == tmp){
                    q.next = p.next;
                    p = p.next;
                    if(p == null) {
                        break;
                    }
                }
            } else {
                p = p.next;
                q = q.next;
            }
        }
        return dummy.next;
	}
	
	/**
	 * 交换链表的相邻节点
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy; // prev是为了遍历dummy用
		while(head != null && head.next != null) { // 必须保证head和head下一个均不为空，单个元素不需要swap
			ListNode curr = head.next;
			prev.next = curr;
			
			head.next = curr.next; // 先将1->3保证3不丢失
			curr.next = head; // 再将2->1
			
			prev = head;
			head = head.next;
		}
		
		return dummy.next;
	}
	
	/**
	 * 对链表重新排序
	 * 1->2->3->4->5->6
	 * 1->6->2->5->3->4
	 * @param head
	 */
	public void reorderList(ListNode head) {
		if(head == null)
			return;
		
		ListNode p = head, q = head;
		while(q.next != null && q.next.next != null) {
			p = p.next;
			q = q.next.next;
		}
		
		//p point to 3
		ListNode newHead = p.next, prev = null;
		while(newHead != null) {
			ListNode curr = newHead.next;
			newHead.next = prev;
			prev = newHead;
			newHead = curr;
		} //now prev point to 6
		p.next = prev;
		// now 1->2->3->6->5->4
		
		q = prev;
		prev = p.next;
		p = head;
		ListNode dummy = new ListNode(-1);
		ListNode k = dummy;
		// now p points to 1, prev points to 6
		while(p != prev) {
			k.next = p;
			k = k.next;
			p = p.next;
			if(q != null) {
				k.next = q;
				k = k.next;
				q = q.next;
			}
		}
		k.next = null;
		ListNode.printList(dummy.next);
	}
	
	/**
	 * 判断一个链表是否有环
	 * @param head
	 * @return
	 */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
			return false;
		ListNode p = head, q = head;
		while (q.next != null && q.next.next != null) {
			p = p.next;
			q = q.next.next;
			if(p == q){
				return true;
			}
		}
		return false;
    }
	
    /**
	 * 判断一个链表是否有环并找出环的入口
	 * @param head
	 * @return
	 */
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        // 使用快慢指针来判断是否有环
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
            	hasCycle = true;
            	break;
            }
        }
        if(!hasCycle) return null;
        // 如果有环就将slow从head开始，和fast同步走，相遇点就是交点
        else{
	        slow = head;
	        while(slow != fast) {
	            slow = slow.next;
	            fast = fast.next;
	        }
        }
        return fast;
    }
    
	/**
	 * 用归并排序对链表进行排序
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		// 使用快慢指针找到列表中点
		ListNode slow = head, fast = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode mid = slow.next;
		slow.next = null; // 断开
		
		// divide
		ListNode h1 = sortList(head);
		ListNode h2 = sortList(mid);
		
		// conquer
		return mergeSortedList(h1, h2);
	}
	
	private ListNode mergeSortedList(ListNode h1, ListNode h2) {
		if(h1 == null)
			return h2;
		if(h2 == null)
			return h1;
		
		ListNode merged = null;
		if(h1.val <= h2.val) {
			merged = h1;
			merged.next = mergeSortedList(h1.next, h2);
		} else {
			merged = h2;
			merged.next = mergeSortedList(h1, h2.next);
		}
		
		return merged;
	}

	/**
	 * 使用插入排序对链表进行排序
	 * @param nums
	 * @return
	 */
	public ListNode insertionSortList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(-1);
		ListNode curr = head;
		ListNode prev = dummy;
		ListNode tmp = null;
		while(curr != null) {
			tmp = curr.next;
			while(prev.next != null && prev.next.val < curr.val) {
				prev = prev.next;
			}
			curr.next = prev.next;
			prev.next = curr;
			curr = tmp;
			prev = dummy;
		}
		return dummy.next;
	}
	
	public static void main(String[] args) {
		LinkedListSeries t = new LinkedListSeries();
		ListNode l1 = ListNode.createList(new int[] { 1, 2 });
		ListNode l2 = ListNode.createList(new int[] { 5, 7 });
		ListNode l3 = ListNode.createList(new int[] { 4, 6 });
		// ListNode.printList(t.mergeTwoSortedListIteratively(l1, l2));
		// ListNode.printList(t.mergeTwoSortedListRecursively(l1, l2));
		// ListNode[] lists = new ListNode[] { l1, l2, l3 };
		// ListNode newHead = t.mergeKListsUsingHeap(lists);
		// ListNode newHead = t.mergeKListsUsingTwoMerge(lists);
		// ListNode.printList(newHead);

		ListNode head = ListNode.createList(new int[] { 1, 2, 3});
		/*ListNode newHead = t.partition(head, 3);
		ListNode.printList(newHead);*/
		ListNode newHead = t.reverseKGroup(head, 2);
		ListNode.printList(newHead);
	}

}

package com.gupta.nitin.linkedList;

public class NestedSingleLinkedListDemo {
	protected static Node head, tail;
	protected static int size;

	private static class Node {
		Node next;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		addAtStart(50);
		addAtStart(40);
		addAtStart(30);
		addAtStart(20);
		addAtStart(10);
		addAtLast(60);
		addAtLast(70);
		addAtLast(80);
		addAtLast(90);
		addAtLast(60);
		addAtposition(6, 66);
		addAtposition(3, 33);
		addAtposition(7, 43);
		/*
		 * addAtStart(50); addAtStart(40); addAtStart(30); addAtStart(20);
		 * addAtStart(10); addAtLast(60); addAtLast(70); addAtLast(80); addAtLast(90);
		 * addAtLast(60); addAtposition(6, 66); addAtposition(3, 33); addAtposition(7,
		 * 43); printAll(head); deleteAtFirst(); deleteAtFirst(); printAll(head);
		 * deleteAtLast(); deleteAtLast();
		 */
		/// printAll(head);
		/// deleteAtPosition(5);
		/// deleteAtPosition(2);
		/// printAll(head);
		// removeDuplicate();
		/// printAll(head);
		// printNthFromLast(3);
		/// removeNthFromLast(3);
		printAll(head);
		// reverse();
		/// printAll(head);
		// Node newNode = new Node(44);
		// sortedInsert(newNode);
		// System.out.println("\n " + ifLoopExists());
		// System.out.println("\n " + getMiddle(head).data);
		// insertionSort();
		/// Bubblesort() ;
		quickSort();
		printAll(head);

	}

	private static void quickSort() {
		quickSort(head, tail);
	}

	private static void quickSort(Node head2, Node tail2) {
		if (tail2 != null && head2 != tail2 && head2 != tail2.next) {
			Node temp = partition(head2, tail2);
			quickSort(head2, temp);
			quickSort(temp.next, tail2);
		}
	}

	private static Node partition(Node head2, Node tail2) {
		Node p = head2;
		Node ptr = p.next;
		while (ptr != null) {
			if (ptr.data < p.data) {
				int pivot = p.data;
				p.data = ptr.data;
				ptr.data = p.next.data;
				p.next.data = pivot;
				p = p.next;
			}
			ptr = ptr.next;
		}
		return p;
	}

	public static Node insertionSort() {
		Node current = head;
		Node tail = null;
		while (current != null && tail != head) {
			Node next = current;
			for (; next.next != tail; next = next.next) {
				if (next.data >= next.next.data) {
					int temp = next.data;
					next.data = next.next.data;
					next.next.data = temp;
				}
			}
			tail = next;
			current = head;
		}
		return current;
	}

	public static void Bubblesort() {
		if (size > 1) {
			for (int i = 0; i < size; i++) {
				Node currentNode = head;
				Node next = head.next;
				for (int j = 0; j < size - 1; j++) {
					if (currentNode.data > next.data) {
						int temp = currentNode.data;
						currentNode.data = next.data;
						next.data = temp;
					}
					currentNode = next;
					next = next.next;
				}
			}
		}
	}

	// Utility function to get the middle of the linked list
	static Node getMiddle(Node h) {
		if (h == null)
			return h;
		Node fastptr = h.next;
		Node slowptr = h;

		while (fastptr != null) {
			fastptr = fastptr.next;
			if (fastptr != null) {
				slowptr = slowptr.next;
				fastptr = fastptr.next;
			}
		}
		return slowptr;
	}

	public static boolean ifLoopExists() {
		Node fast = head;
		Node slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast)
				return true;

		}
		return false;
	}

	private static void sortedInsert(Node new_node) {
		Node tmp = head;
		for (int i = 1; i <= size; i++) {
			if (new_node.data <= tmp.next.data) {
				Node ptr = tmp.next;
				tmp.next = new_node;
				new_node.next = ptr;
				size++;
				break;
			}
			tmp = tmp.next;
		}
	}

	private static void reverse() {
		Node prev = null, next = null;
		Node current = head;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head = prev;
	}

	// Optimize case using only one loop
	private static void printNthFromLast(int position) {
		if (head == null)
			return;
		Node s = head;
		Node t = head.next;
		for (int i = 1; i <= position; i++) {
			if (s == null) {
				System.out.println("overflow of size");
				return;
			}
			s = s.next;
		}
		while (s.next != null) {
			t = t.next;
			s = s.next;
		}
		System.out.println("\n" + t.data);
	}

	// without know the size of list remove from last.
	static private void removeNthFromLast(int n) {
		if (head == null)
			return;
		Node s = head;
		Node t = head;
		for (int i = 1; i <= n; i++) {
			if (s == null) {
				System.out.println("overflow of size");
				return;
			}
			s = s.next;
		}
		while (s.next != null) {
			t = t.next;
			s = s.next;
		}
		t.next = t.next.next;
	}

	private static void removeDuplicate() {
		Node tmp = head;
		while (tmp != null) {
			Node current = tmp;
			while (current.next != null) {
				if (tmp.data == current.next.data) {
					current.next = current.next.next;
					size--;
				} else {
					current = current.next;
				}
			}
			tmp = tmp.next;
		}

	}

	private static void deleteAtPosition(int position) {
		if (position == 1)
			deleteAtFirst();
		else if (position == size)
			deleteAtLast();
		else {
			Node tmp = head;
			int pos = position - 1;
			for (int i = 1; i < size; i++) {
				if (i == pos) {
					Node ptr = tmp.next.next;
					tmp.next = ptr;
					size--;
				}
				tmp = tmp.next;
			}

		}
	}

	private static void deleteAtLast() {
		if (head == null)
			return;
		Node s = head;
		Node t = head;
		while (s.next != null) {
			t = s;
			s = s.next;
		}

		tail = t;
		tail.next = null;
		size--;
	}

	private static void deleteAtFirst() {
		if (head == null)
			return;
		Node tmp = head.next;
		head = tmp;
		size--;
	}

	private static void addAtposition(int position, int data) {
		if (position == 1)
			addAtStart(data);
		else if (position == size + 1)
			addAtLast(data);
		else {
			Node node = new Node(data);
			int pos = position - 1;
			Node tmp = head;
			for (int i = 1; i <= size; i++) {
				if (pos == i) {
					Node ptr = tmp.next;
					tmp.next = node;
					node.next = ptr;
					size++;
					break;
				}
				tmp = tmp.next;
			}

		}

	}

	private static void addAtLast(int data) {
		Node node = new Node(data);
		if (head == null) {
			tail = head = node;
		} else {
			Node tmp = tail;
			tmp.next = node;
			tail = node;
		}
		size++;
	}

	private static void printAll(Node head) {
		if (head == null)
			return;
		Node tmp = head;
		System.out.println();
		while (true) {
			if (tmp == null)
				return;
			else {
				System.out.print(tmp.data + "    ");
				tmp = tmp.next;
			}
		}
	}

	private static void addAtStart(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			Node tmp = head;
			node.next = tmp;
			head = node;
		}
		size++;

	}

}

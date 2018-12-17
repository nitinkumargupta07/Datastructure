package com.gupta.nitin.linkedList;

public class SingleLinkedListDemo {
	static private Node head, tail;
	static private int size;

	private static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	static void quickSort(Node l, Node h) {
		if (h != null && l != h && l != h.next) {
			Node temp = partition(l, h);
			quickSort(l, temp);
			quickSort(temp.next, h);
		}
	}

	public static Node partition(Node first, Node last) {

		Node p = first;
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

	static private void quickSort() {
		Node first = head;
		Node last = tail;
		quickSort(first, last);
	}

	static Node sortedMerge(Node a, Node b) {
		Node result = null;
		/* Base cases */
		if (a == null)
			return b;
		if (b == null)
			return a;

		/* Pick either a or b, and recur */
		if (a.data <= b.data) {
			result = a;
			result.next = sortedMerge(a.next, b);
		} else {
			result = b;
			result.next = sortedMerge(a, b.next);
		}
		return result;

	}

	static Node mergeSort(Node h) {
		if (h == null || h.next == null)
			return h;
		Node middle = getMiddle(h);
		Node nextofmiddle = middle.next;
		middle.next = null;
		Node left = mergeSort(h);
		Node right = mergeSort(nextofmiddle);
		Node sortedlist = sortedMerge(left, right);
		return sortedlist;
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

	static private void sortedInsert(Node new_node) {
		Node current = head;
		if (current == null || current.data >= new_node.data) {
			new_node.next = head;
			head = new_node;
		} else {
			while (current.next != null && current.next.data < new_node.data)
				current = current.next;

			new_node.next = current.next;
			current.next = new_node;
		}

	}

	public static boolean ifLoopExists() {
		Node fastPtr = head;
		Node slowPtr = head;
		while (fastPtr != null && fastPtr.next != null) {
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;
			if (slowPtr == fastPtr)
				return true;

		}
		return false;
	}

	static Node reverse(Node node) {
		Node prev = null;
		Node next = null;
		Node current = node;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}

	static boolean isPalindromUp1() {
		boolean flag = false;
		Node forwordDir = head;
		Node backwordDir = reverse(forwordDir);
		while (forwordDir != null) {
			if (forwordDir.data == backwordDir.data) {
				flag = true;
			} else {
				return false;
			}
			forwordDir = forwordDir.next;
			backwordDir = backwordDir.next;
		}
		return flag;
	}

	static private void printAll(Node tmp) {
		if (tmp == null)
			return;
		System.out.println();
		while (tmp != null) {
			System.out.print(tmp.data + "    ");
			tmp = tmp.next;
		}

	}

	static private void deleteMiddleNode() {
		if (head == null)
			return;
		Node slow = head;
		Node fast = head.next;
		while (fast != null) {
			fast = fast.next;
			if (fast != null) {
				slow = slow.next;
				fast = fast.next;
			}
		}
		slow.next = slow.next.next;
	}

	static private Node getMIddleNode() {
		if (head == null)
			return null;
		Node slow = head;
		Node fast = head;
		while (fast != null) {
			fast = fast.next;
			if (fast != null) {
				slow = slow.next;
				fast = fast.next;
			}
		}
		return slow;
	}

	static private Node reverseRecursively(Node node) {
		Node newHead;
		// base case - tail of original linked list
		if ((node.next == null)) {
			return node;
		}
		newHead = reverseRecursively(node.next);

		// reverse the link e.g. C->D->null will be null
		node.next.next = node;
		node.next = null;
		return newHead;
	}

	static private void reverse() {
		Node prev = null;
		Node current = head;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head = prev;
	}

	static private void deleteNode(Node node) {
		// node.data = node.next.data;
		node.next = node.next.next;
		size--;
	}

	static private void deleteNode(int data) {
		Node tmp = head.next;
		Node s = head;
		while (tmp != null) {
			if (tmp.data == data) {
				Node ptr = s.next.next;
				s.next = ptr;
				size--;
				break;
			} else {
				tmp = tmp.next;
				s = s.next;
			}
		}

	}

	// without know the size of list
	static private void printNthFromLast(int n) {
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

		while (s != null) {
			t = t.next;
			s = s.next;
		}
		System.out.println();
		System.out.println(n + "position data element is " + t.data);
	}

	// optimize way to find nth element from last
	// we conside we don't know size of single linked list
	static private void findNthNodeFromLast(int position) {
		Node t = head;
		Node s = head;
		int length = 0;
		while (t != null) {
			if (length >= position) {
				s = s.next;
				t = t.next;
			} else {
				length++;
				t = t.next;
			}
		}
		if (length < position) {
			System.out.println("number of element is less than from position ");
		} else {
			System.out.println("element is " + s.data);
		}
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

	static private void addNodeAfter(int data, Node node) {
		Node tmp = head;
		while (tmp != null) {
			if (data == tmp.data) {
				Node ptr = tmp.next;
				tmp.next = node;
				node.next = ptr;
				size++;
				break;
			} else {
				tmp = tmp.next;
			}
		}

	}

	static private void removeDuplicate() {
		Node tmp = head;
		while (tmp != null) {
			Node current = tmp;
			while (current.next != null) {
				if (tmp.data == current.next.data) {
					// current.next = current.next.next; solution1
					Node ptr = current.next.next;// solution second
					current.next = ptr;
					size--;
				} else {
					current = current.next;
				}
			}
			tmp = tmp.next;
		}
	}

	static private void deleteAtPosition(int position) {
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

	static private void deleteAtLast() {
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

	static private void deleteAtFirst() {
		if (head == null)
			return;
		Node tmp = head.next;
		head = tmp;
		size--;
	}

	static private void addAtposition(int position, int data) {
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

	static private void addAtLast(int data) {
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

	static private void printAll() {
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

	static private void addAtStart(int data) {
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

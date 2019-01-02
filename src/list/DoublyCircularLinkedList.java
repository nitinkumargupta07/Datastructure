package list;

import list.DoublyLinkedList.Node;

public class DoublyCircularLinkedList {
	static int size;
	static Node head, tail;

	public static class Node {
		int data;
		Node next, prev;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		addAtFirst(50);
		addAtFirst(40);
		addAtFirst(30);
		addAtFirst(20);
		addAtFirst(10);
		printAllNodes();
		printAllBackwordNodes();
		addAtLast(60);
		addAtLast(70);
		addAtLast(80);
		addAtLast(90);
		addAtLast(100);
		printAllNodes();
		printAllBackwordNodes();
		addAtPosition(55, 5);
		printAllNodes();
		printAllBackwordNodes();
		addAtPosition(77, 7);
		printAllNodes();
		printAllBackwordNodes();
		addAtPosition(22, 2);
		addAtPosition(99, 9);
		addAtPosition(111, 11);
		printAllNodes();
		printAllBackwordNodes();
		addAtMiddle(88);
		printAllNodes();
		printAllBackwordNodes();
		addAtMiddle(44);
		printAllNodes();
		printAllBackwordNodes();
		addAtMiddle(67);
		addAtMiddle(97);
		addAtMiddle(34);
		printAllNodes();
		printAllBackwordNodes();
		addAtNthNodeFromLast(97, 7);
		printAllNodes();
		printAllBackwordNodes();
		addAtNthNodeFromLast(25, 2);
		printAllNodes();
		printAllBackwordNodes();
		addAtFirst(50);
		addAtFirst(40);
		addAtFirst(30);
		addAtFirst(20);
		addAtFirst(10);
		printAllNodes();
		printAllBackwordNodes();
		addAtLast(60);
		addAtLast(70);
		addAtLast(80);
		addAtLast(90);
		addAtLast(100);
		printAllNodes();
		printAllBackwordNodes();
		addAtPosition(55, 5);
		printAllNodes();
		printAllBackwordNodes();
		addAtPosition(77, 7);
		printAllNodes();
		printAllBackwordNodes();
		addAtPosition(22, 2);
		addAtPosition(99, 9);
		addAtPosition(111, 11);
		printAllNodes();
		printAllBackwordNodes();
		addAtMiddle(88);
		printAllNodes();
		printAllBackwordNodes();
		addAtMiddle(44);
		printAllNodes();
		printAllBackwordNodes();
		addAtMiddle(67);
		addAtMiddle(97);
		addAtMiddle(34);
		printAllNodes();
		printAllBackwordNodes();
		addAtNthNodeFromLast(97, 7);
		printAllNodes();
		printAllBackwordNodes();
		addAtNthNodeFromLast(25, 2);
		printAllNodes();
		printAllBackwordNodes();
		removeAtFirst();
		removeAtFirst();
		printAllNodes();
		printAllBackwordNodes();
		removeAtLast();
		removeAtLast();
		printAllNodes();
		printAllBackwordNodes();
		removeAtPosition(5);
		removeAtPosition(5);
		printAllNodes();
		printAllBackwordNodes();
		removeAtMiddle();
		removeAtMiddle();
		printAllNodes();
		printAllBackwordNodes();
		removeAtNthNodeFromLast(7);
		removeAtNthNodeFromLast(2);
		printAllNodes();
		printAllBackwordNodes();
		removeDuplicate();
		printAllNodes();
		printAllBackwordNodes();
		Bubblesort();
		insertionSort();
		quickSort();
		mergeSort(head);
		printAllNodes();
		printAllBackwordNodes();
		reverseOrder();
		sortedInsert(66);
		printAllNodes();
		printAllBackwordNodes();
		sortedInsert(43);
		printAllNodes();
		printAllBackwordNodes();

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

		if (h == null || h.next == null) {
			return h;
		}

		Node middle = getMiddle(h);
		Node nextofmiddle = middle.next;

		middle.next = null;

		Node left = mergeSort(h);

		Node right = mergeSort(nextofmiddle);

		Node sortedlist = sortedMerge(left, right);
		return sortedlist;
	}

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

	private static void quickSort() {
		quickSort(head, tail);
	}

	private static void quickSort(Node head2, Node tail2) {
		if (head2 != null && tail2 != null && head2 != tail2 && head2 != tail2.next) {
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

	public static void insertionSort() {
		if (head == null)
			return;
		Node current = head;
		Node tail = null;
		while (current != null && head != tail) {
			Node next = current;
			for (; next.next != tail; next = next.next) {
				if (next.data > next.next.data) {
					int temp = next.next.data;
					next.next.data = next.data;
					next.data = temp;
				}

			}

			tail = next;
			current = head;
		}

	}

	private static void sortedInsert(int data) {
		Node tmp = head;
		Node node = new Node(data);
		while (tmp != null) {
			if (data <= tmp.next.data) {
				Node ptr = tmp.next;
				tmp.next = node;
				node.prev = tmp;
				node.next = ptr;
				ptr.prev = node;
				size++;
				break;
			} else {
				tmp = tmp.next;
			}
		}
	}

	private static void reverseOrder() {
		Node temp = head;
		head = tail;
		tail = temp;
		Node p = head;
		while (p != null) {
			temp = p.next;
			p.next = p.prev;
			p.prev = temp;
			p = p.next;
		}
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

	private static void Bubblesort() {
		if (head == null)
			return;
		Node tmp = head;
		while (tmp != null) {
			Node current = tmp;
			while (current != null && current.next != null) {
				if (tmp.data > current.next.data) {
					int temp = current.next.data;
					current.next.data = current.data;
					current.data = temp;
				}

				current = current.next;
			}

			tmp = tmp.next;
		}
	}

	private static void removeDuplicate() {
		if (head == null)
			return;
		Node tmp = head;
		while (tmp != null) {
			Node current = tmp;
			while (current != null && current.next != null) {
				if (tmp.data == current.next.data) {
					delete(current.next);
				}
				current = current.next;
			}

			tmp = tmp.next;
		}

	}

	/* This method will delete node */
	static public void delete(Node node) {
		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			head = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
		} else {
			tail = node.prev;
		}

	}

	private static void removeAtNthNodeFromLast(int position) {
		if (head == null)
			return;
		Node tmp = tail;
		for (int i = 1; i <= position - 1; i++) {
			if (tmp == null) {
				System.out.println("List under flow error");
				return;
			}
			tmp = tmp.prev;
		}
		Node prevN = tmp.prev;
		Node nextN = tmp.next;
		prevN.next = nextN;
		nextN.prev = prevN;
		size--;
	}

	private static void removeAtMiddle() {
		if (head == null) {
			System.out.println("empty list");
		} else {
			Node slowPtr = head;
			Node fastPtr = head;
			while (fastPtr != null) {
				fastPtr = fastPtr.next;
				if (fastPtr != null) {
					slowPtr = slowPtr.next;
					fastPtr = fastPtr.next;
				}
			}
			Node next = slowPtr.next;
			Node prev = slowPtr.prev;
			prev.next = next;
			next.prev = prev;
			size--;
		}

	}

	private static void removeAtPosition(int position) {
		if (position > size) {
			System.out.println("list element less then position " + position + " > " + size);
		}
		if (position == 1)
			removeAtFirst();
		else if (position == size)
			removeAtLast();
		else {
			Node tmp = head;
			int pos = position - 1;
			for (int i = 1; i < size; i++) {
				if (pos == i) {
					Node next = tmp.next;
					Node prev = tmp.prev;
					prev.next = next;
					next.prev = prev;
					size--;
					break;
				} else {
					tmp = tmp.next;
				}

			}

		}

	}

	private static void removeAtLast() {
		if (head == null) {
			System.out.println("list is empty");
			return;
		} else {
			Node tmp = tail.prev;
			tmp.next = null;
			tail = tmp;
			size--;
		}

	}

	private static void removeAtFirst() {
		if (head == null) {
			System.out.println("list is empty");
			return;
		} else {
			Node tmp = head.next;
			tmp.prev = null;
			head = tmp;
			
			size--;
		}

	}

	private static void addAtNthNodeFromLast(int data, int position) {
		Node tmp = head;
		Node node = new Node(data);
		if (head == null) {
			head = tail = null;
		} else {
			Node s = head;
			Node t = head;
			for (int i = 1; i <= position; i++) {
				if (s == null) {
					System.out.println("out of flow error Node size is " + size + "but position is" + position);
				}
				s = s.next;
			}
			while (s != null  && s.next!=head) {
				s = s.next;
				t = t.next;
			}
			Node ptr = t.next;
			t.next = node;
			node.prev = t;
			node.next = ptr;
			ptr.prev = node;
			size++;
		}
	}

	private static void printAllNodes() {
		System.out.println();
		Node tmp = head;
		do {
			if (head == null) {
				System.out.print("List is empty");
				return;
			} else {
				System.out.print("   " + tmp.data);
				tmp = tmp.next;
			}

		} while (tmp != head);
	}

	private static void printAllBackwordNodes() {
		System.out.println();
		Node tmp = tail;
		do {
			if (tail == head) {
				System.out.print("List is empty");
				return;
			} else {
				System.out.print("   " + tmp.data);
				tmp = tmp.prev;
			}

		} while (tmp != tail);
	}

	private static void addAtFirst(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = tail = node;
		} else {
			Node tmp = head;
			node.next = tmp;
			tmp.prev = node;
			head = node;
			tail.next = head;
			head.prev = tail;
		}
		size++;
	}

	private static void addAtLast(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = tail = node;
		} else {
			Node tmp = tail;
			tmp.next = node;
			node.prev = tmp;
			tail = node;
			tail.next = head;
			head.prev = tail;
		}
		size++;
	}

	private static void addAtPosition(int data, int position) {
		if (position == 1)
			addAtFirst(data);
		else if (position == size + 1)
			addAtLast(data);
		else {
			Node tmp = head;
			Node node = new Node(data);
			int pos = position - 1;
			for (int i = 1; i <= size; i++) {
				if (pos == i) {
					Node ptr = tmp.next;
					tmp.next = node;
					node.prev = tmp;
					node.next = ptr;
					ptr.prev = node;
					size++;
					break;
				} else {
					tmp = tmp.next;
				}
			}
		}
	}

	private static void addAtMiddle(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = tail = node;
			tail.next = head;
			head.prev = tail;
		} else {
			Node slowPtr = head;
			Node fastPtr = head;
			while (fastPtr != null  && fastPtr.next !=head) {
				fastPtr = fastPtr.next;
				if (fastPtr != null  && fastPtr.next !=head) {
					slowPtr = slowPtr.next;
					fastPtr = fastPtr.next;
				}
			}
			Node ptr = slowPtr.next;
			slowPtr.next = node;
			node.prev = slowPtr;
			node.next = ptr;
			ptr.prev = node;
			size++;
		}

	}

}

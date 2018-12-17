package com.gupta.nitin.linkedList;

public class DoublyLinkedListDemo {
	static protected Node head, tail;
	static protected int size;

	private static class Node {
		protected Node next, prev;
		protected int data;

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
		printAllForward();
		printAllBackward();
		deleteNode(head.next.next.next);
		printAllForward();
		printAllBackward();
		/*
		 * addAtStart(50); addAtStart(40); addAtStart(30); addAtStart(20);
		 * addAtStart(10); addAtLast(60); addAtLast(70); addAtLast(80); addAtLast(90);
		 * addAtLast(60); addAtposition(6, 66); addAtposition(3, 33); addAtposition(7,
		 * 43); removeAtFirst(); removeAtFirst(); removeAtLast(); removeAtLast();
		 * removeAtPosition(5); removeAtPosition(2); printAllForward();
		 * printAllBackward();
		 */
		/// remove_duplicates();
		/*
		 * deleteNode(66); printAllForward(); printAllBackward();
		 * deleteNode1(head.next.next.next.next); // delete node 4 printAllForward();
		 * printAllBackward(); Node node1 = findNthToLastUp(2);
		 */
		printAllForward();
		printAllBackward();
		//reverseDLL();
		mergeSort(head);
		printAllForward();	
		printAllBackward();

	}

	private static void deleteNode(Node node) {
		if(node.next!=null) {
			node.next.prev=node.prev;
		}else {
			head = node.next;
		}
		if(node.prev!=null) {
			node.prev.next=node.next;
		}else {
			tail = node.prev;
		}
		
	}

	static Node split(Node head) {
		Node fast = head, slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		Node temp = slow.next;
		slow.next = null;
		return temp;
	}

	static Node mergeSort(Node node) {
		if (node == null || node.next == null) {
			return node;
		}
		Node second = split(node);

		// Recur for left and right halves
		node = mergeSort(node);
		second = mergeSort(second);

		// Merge the two sorted halves
		return merge(node, second);
	}

	// Function to merge two linked lists
	static Node merge(Node first, Node second) {
		// If first linked list is empty
		if (first == null) {
			return second;
		}

		// If second linked list is empty
		if (second == null) {
			return first;
		}

		// Pick the smaller value
		if (first.data < second.data) {
			first.next = merge(first.next, second);
			first.next.prev = first;
			first.prev = null;
			return first;
		} else {
			second.next = merge(first, second.next);
			second.next.prev = second;
			second.prev = null;
			return second;
		}
	}

	public static void reverseDLL() {
		Node temp = head; // swap head and tail
		head = tail; // head now points to tail
		tail = temp; // tail points to head
		// traverse the list swapping prev and next fields of each node
		Node p = head; // create a node and point to head

		while (p != null) // while p does not equal null
		{ // swap prev and next of current node
			temp = p.next; // p.next does that not equal null? confusing.
			p.next = p.prev; // this line makes sense since you have to reverse the link
			p.prev = temp; // having trouble visualizing this.
			p = p.next;// advance current node which makes sense
		}
	}

	static private void reverseList() {
		if (head == null)
			return;
		Node tmp = null;
		Node current = head;
		while (current != null) {
			tmp = current.prev;
			current.prev = current.next;
			current.next = tmp;
			current = current.prev;
		}
		if (tmp != null) {
			head = tmp.prev;
		}
	}

	static private void deleteNode1(Node node) {
		if (node == null || node.next == null) {
			return; // Failure
		}
		Node nextNode = node.next;
		Node prevNode = node.prev;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		size--;
		return;
	}

	static private Node findNthToLastUp(int position) {
		Node tmp = tail;
		for (int j = 0; j < position - 1; ++j) { // skip n-1 steps ahead
			if (tmp == null)
				return null;
			tmp = tmp.prev;
		}
		return tmp;
	}

	static public void deleteNode(int key) {
		Node tmp = head;
		while (true) {
			if (key == tmp.data) {
				Node nextV = tmp.next;
				Node prevV = tmp.prev;
				prevV.next = nextV;
				nextV.prev = prevV;
				size--;
				return;
			} else {
				tmp = tmp.next;
			}
		}
	}

	/*
	 * Function to remove duplicates from an unsorted linked list
	 */
	static void remove_duplicates() {
		Node tmp = head;
		while (tmp != null) {
			Node current = tmp;
			while (current.next != null) {
				if (tmp.data == current.next.data) {
					current.next = current.next.next;

				} else {
					current = current.next;
				}
			}
			tmp = tmp.next;
		}
	}

	private static void removeAtPosition(int position) {
		if (position == 1)
			removeAtFirst();
		else if (position == size)
			removeAtLast();
		else {
			Node tmp = head;
			for (int i = 1; i <= size; i++) {
				if (i == position) {
					Node nextNode = tmp.next;
					Node prevNode = tmp.prev;
					nextNode.prev = prevNode;
					prevNode.next = nextNode;
					size--;
					break;
				} else {
					tmp = tmp.next;
				}

			}
		}

	}

	private static void removeAtLast() {
		if (head == null)
			return;
		Node tmp = tail.prev;
		tail = tmp;
		tail.next = null;
		size--;
	}

	private static void removeAtFirst() {
		if (head == null)
			return;
		Node tmp = head.next;
		head = tmp;
		head.prev = null;
		size--;
	}

	private static void addAtposition(int position, int data) {
		if (position == 1)
			addAtStart(data);
		else if (position == size + 1)
			addAtLast(data);
		else {
			int pos = position - 1;
			Node tmp = head;
			Node node = new Node(data);
			for (int i = 1; i <= size; i++) {
				if (i == pos) {
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

	private static void addAtLast(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = tail = node;
		} else {
			Node tmp = tail;
			tmp.next = node;
			node.prev = tmp;
			tail = node;
		}
		size++;
	}

	private static void printAllBackward() {
		if (head == null)
			return;
		System.out.println();
		Node tmp = tail;
		while (tmp != null) {
			System.out.print(tmp.data + "  ");
			tmp = tmp.prev;
		}
	}

	private static void printAllForward() {
		if (head == null)
			return;
		System.out.println();
		Node tmp = head;
		while (tmp != null) {
			System.out.print(tmp.data + "  ");
			tmp = tmp.next;
		}
	}

	private static void addAtStart(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = tail = node;
		} else {
			Node tmp = head;
			node.next = tmp;
			tmp.prev = node;
			head = node;
		}
		size++;
	}

}

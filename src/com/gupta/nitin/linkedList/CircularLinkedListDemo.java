package com.gupta.nitin.linkedList;

public class CircularLinkedListDemo {

	static protected Node head, tail;
	static protected int size;

	private static class Node {
		protected int data;
		protected Node next;

		public Node(int data) {
			this.data = data;
			next = null;
		}
	}

	public static void main(String[] args) {
		CircularLinkedListDemo demo = new CircularLinkedListDemo();
		demo.addNodeAtStart(3);
		demo.addNodeAtStart(5);
		demo.addNodeAtStart(7);
		demo.addNodeAtStart(9);
		demo.addNodeAtStart(31);
		demo.print();
		demo.addNodeAtLast(32);
		demo.addNodeAtLast(33);
		demo.addNodeAtLast(34);
		demo.print();
		demo.addAtPosition(30, 5);
		demo.addAtPosition(20, 2);
		demo.print();
		/*
		 * demo.removeAtStart(); demo.removeAtStart(); demo.print();
		 * demo.removeAtLast(); demo.removeAtLast(); demo.print();
		 */

		demo.removeAtPosition(3);
		demo.removeAtPosition(1);
		demo.print();
		// demo.sortedInsert(new_node);

	}

	private void removeAtLast() {
		if (head == null) {
			System.out.println("empty list");
		} else {
			Node tmp = head;
			Node shadow = head;
			do {
				shadow = tmp;
				tmp = tmp.next;
			} while (tmp.next != head);
			tail = shadow;
			tail.next = null;
			tail.next = head;
		}
		size--;
	}

	private void removeAtStart() {
		if (head == null) {
			System.out.println("empty list");
		} else {
			Node tmp = head.next;
			head = tmp;
			tail.next = head;
		}
		size--;
	}

	private void removeAtPosition(int position) {
		if (position == 1)
			removeAtStart();
		else if (position == size)
			removeAtLast();
		else {
			Node tmp = head;
			int pos = position - 1;
			for (int i = 1; i < size; i++) {
				if (pos == i) {
					Node ptr = tmp.next.next;
					tmp.next = ptr;
				} else {
					tmp = tmp.next;
				}

			}
		}
	}

	private void addAtPosition(int data, int position) {
		Node node = new Node(data);
		Node tmp = head;
		if (position == 1)
			addNodeAtStart(data);
		else if (position == size + 1)
			addNodeAtLast(data);
		else {
			int pos = position - 1;
			for (int i = 1; i < size; i++) {
				if (pos == i) {
					Node ptr = tmp.next;
					tmp.next = node;
					node.next = ptr;
					size++;
					return;
				} else {
					tmp = tmp.next;
				}

			}
		}
		size++;
		return;
	}

	private void addNodeAtLast(int data) {
		// System.out.println();
		// System.out.print("Adding node " + data + " at End");
		Node node = new Node(data);
		if (head == null) {
			head = node;
			tail = node;
			node.next = head;
		} else {
			Node tmp = tail;
			tmp.next = node;
			tail = node;
			tail.next = head;
		}
		size++;
	}

	/* Function to check if list is empty */
	public boolean isEmpty() {
		return head == null;
	}

	/* Function to get size of the list */
	public int getSize() {
		return size;
	}

	// print the linked list
	public void print() {
		System.out.println();
		System.out.print("Circular Linked List:");
		Node temp = head;
		if (size <= 0) {
			System.out.print("List is empty");
		} else {
			do {
				System.out.print(" " + temp.data);
				temp = temp.next;
			} while (temp != head);
		}
		System.out.println();
	}

	public void traverse() {
		Node tmp = head;
		System.out.println();
		do {
			if (head == null) {
				System.out.print("List is empty");
			} else {
				System.out.print("   " + tmp.data);
				tmp = tmp.next;
			}

		} while (tmp != head);
	}

	private void addNodeAtStart(int data) {
		// System.out.println("Adding node " + data + " at start");
		Node node = new Node(data);
		if (head == null) {
			head = node;
			tail = node;
			node.next = head;
		} else {
			Node tmp = head;
			node.next = tmp;
			head = node;
			tail.next = head;
		}
		size++;
	}

	/*
	 * function to insert a new_node in a list in sorted way. Note that this
	 * function expects a pointer to head node as this can modify the head of the
	 * input linked list
	 */
	void sortedInsert(Node new_node) {
		Node current = head;

		// Case 1 of the above algo
		if (current == null) {
			new_node.next = new_node;
			head = new_node;

		}

		// Case 2 of the above algo
		else if (current.data >= new_node.data) {
			while (current.next != head)
				current = current.next;
			current.next = new_node;
			new_node.next = head;
			head = new_node;
		}

		// Case 3 of the above algo
		else {

			/* Locate the node before the point of insertion */
			while (current.next != head && current.next.data < new_node.data)
				current = current.next;
			new_node.next = current.next;
			current.next = new_node;
		}
	}

}

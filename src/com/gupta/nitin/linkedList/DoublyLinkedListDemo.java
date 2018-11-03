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
		removeAtFirst();
		removeAtFirst();
		printAllForward();
		printAllBackward();
		removeAtLast();
		removeAtLast();
		printAllForward();
		printAllBackward();
		removeAtPosition(5);
		removeAtPosition(2);
		printAllForward();
		printAllBackward();

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

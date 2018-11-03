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
		printAllForward();
		printAllBackward();
		addAtLast(60);
		addAtLast(70);
		addAtLast(80);
		addAtLast(90);
		addAtLast(60);
		printAllForward();
		printAllBackward();
		addAtposition(6, 66);
		addAtposition(3, 33);
		addAtposition(7, 43);
		printAllForward();
		printAllBackward();
	}

	private static void addAtposition(int position, int data) {
		// TODO Auto-generated method stub

	}

	private static void addAtLast(int data) {
		// TODO Auto-generated method stub

	}

	private static void printAllBackward() {
		// TODO Auto-generated method stub

	}

	private static void printAllForward() {
		// TODO Auto-generated method stub

	}

	private static void addAtStart(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = tail = node;
		} else {
			Node tmp = head.next;
			node.next = tmp;
			tmp.prev = node;
			head = node;
		}
		size++;
	}

}

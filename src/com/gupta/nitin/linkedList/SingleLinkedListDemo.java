package com.gupta.nitin.linkedList;

public class SingleLinkedListDemo {
	private Node head, tail;
	private int size;

	private class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		SingleLinkedListDemo demo = new SingleLinkedListDemo();
		demo.addAtStart(50);
		demo.addAtStart(40);
		demo.addAtStart(30);
		demo.addAtStart(20);
		demo.addAtStart(10);
		demo.printAll();
	}

	private void printAll() {
		Node tmp = head;
		while (true) {
			if (tmp == null)
				return;
			else {
				System.out.print( tmp.data +"   , " );
				tmp = tmp.next;
			}
		}

	}

	private void addAtStart(int data) {
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

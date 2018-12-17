package com.gupta.nitin.cache.concept;

import java.util.HashMap;

public class LRUCacheDemo1 {
	static int maxSize = 5;
	static HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	static Node head, tail;

	static private class Node {
		int key, value;
		Node next, prev;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			next = prev = null;
		}
	}

	static private void add(int key, int value) {
		Node newNode = new Node(key, value);
		if (map.containsKey(key)) {
			Node node = map.get(key);
			map.put(key, newNode);
			delete(node);
			addAtTop(newNode);
		} else {
			if (map.size() > maxSize) {
				map.remove(tail.key);
				delete(tail);
				addAtTop(newNode);
				map.put(key, newNode);
				return;
			} else {
				addAtTop(newNode);
				map.put(key, newNode);
			}
		}
	}

	static private void delete(Node node) {
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

	static private Node get(int key) {
		Node node = map.get(key);
		return node;
	}

	static public void addAtTop(Node node) {
		if (head == null) {
			head = node;
			tail = null;
		} else {
			Node tmp = head;
			node.next = tmp;
			tmp.prev = node;
			head = node;
		}
	}

	public static void main(String[] args) {
		LRUCacheDemo1 demo1 = new LRUCacheDemo1();
		demo1.add(1, 1);
		demo1.add(2, 2);
		demo1.add(3, 3);
		demo1.add(4, 4);
		demo1.add(5, 5);
		demo1.add(1, 7);
		demo1.add(8, 3);
		demo1.add(13, 21);
		demo1.add(1, 19);
		demo1.add(10, 10);
		demo1.add(31, 111);
		demo1.add(21, 21);
		demo1.add(11, 11);
	}
}

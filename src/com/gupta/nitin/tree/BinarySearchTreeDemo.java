package com.gupta.nitin.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTreeDemo {
	static protected Node root;
	int size;

	static private class Node {
		protected Node left, right;
		protected int data;

		public Node(int data) {
			this.data = data;
			left = right = null;
		}
	}

	public static void main(String[] args) {
		add(25);
		add(20);
		add(30);
		add(15);
		add(35);
		add(21);
		add(28);
		add(31);
		add(45);
		add(33);
		printInOrder(root);
		System.out.println();
		printPreOrder(root);
		System.out.println();
		printPostOrder(root);
		System.out.println();
		traverseLevelOrder(root);
		System.out.println();
		traverseZigZag(root);

	}

	void mirror() {
		root = mirror(root);
	}

	Node mirror(Node node) {
		if (node == null)
			return node;

		/* do the subtrees */
		Node left = mirror(node.left);
		Node right = mirror(node.right);

		/* swap the left and right pointers */
		node.left = right;
		node.right = left;

		return node;
	}

	/*
	 * Compute the "maxDepth" of a tree -- the number of nodes along the longest
	 * path from the root node down to the farthest leaf node.
	 */
	int maxDepth(Node node) {
		if (node == null)
			return 0;
		else {
			/* compute the depth of each subtree */
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);

			/* use the larger one */
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	/*
	 * Compute the "height" of a tree -- the number of nodes along the longest path
	 * from the root node down to the farthest leaf node.
	 */
	int height(Node node) {
		if (node == null)
			return 0;
		else {

			/* compute the height of each subtree */
			int lheight = height(node.left);
			int rheight = height(node.right);

			/* use the larger one */
			if (lheight > rheight)
				return (lheight + 1);
			else
				return (rheight + 1);
		}
	}

	private int countNode() {
		return countNodes(root);
	}

	private int countNodes(Node r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += countNodes(r.left);
			l += countNodes(r.right);
			return l;
		}
	}

	int minvalue(Node node) {
		Node current = node;

		/* loop down to find the leftmost leaf */
		while (current.left != null) {
			current = current.left;
		}
		return (current.data);
	}

	int maxValue(Node node) {
		Node current = node;

		/* loop down to find the leftmost leaf */
		while (current.right != null) {
			current = current.right;
		}
		return (current.data);
	}

	private int findSmallestValue(Node root) {
		return root.left == null ? root.data : findSmallestValue(root.left);
	}

	private int findLargestValue(Node root) {
		return root.right == null ? root.data : findLargestValue(root.right);
	}

	public void delete(int value) {
		root = deleteRecursive(root, value);
	}

	private Node deleteRecursive(Node current, int value) {
		if (current == null) {
			return null;
		}
		if (value == current.data) {
			if (current.left == null && current.right == null) {
				return null;
			}
			if (current.right == null) {
				return current.left;
			}

			if (current.left == null) {
				return current.right;
			}
		}
		if (value < current.data) {
			current.left = deleteRecursive(current.left, value);
			return current;
		} else {
			current.right = deleteRecursive(current.right, value);
			return current;
		}
	}

	private boolean containsNode(int value) {
		return containsNodeRecursive(root, value);
	}

	private boolean containsNodeRecursive(Node node, int value) {
		if (node == null)
			return false;
		if (value == node.data) {
			return true;
		}
		return value < node.data ? containsNodeRecursive(node.left, value) : containsNodeRecursive(node.right, value);
	}

	public static void traverseLevelOrder(Node node) {
		if (node == null)
			return;
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(node);

		while (!nodes.isEmpty()) {
			Node node1 = nodes.remove();
			System.out.print("  " + node1.data);
			if (node1.left != null) {
				nodes.add(node1.left);
			}
			if (node1.right != null) {
				nodes.add(node1.right);
			}
		}
	}

	static private void traverseZigZag(Node node) {
		if (node == null)
			return;
		// declare two stacks
		Stack<Node> currentLevel = new Stack<>();
		Stack<Node> nextLevel = new Stack<>();

		// push the root
		currentLevel.push(node);
		boolean leftToRight = true;
		while (!currentLevel.isEmpty()) {
			// pop out of stack
			Node tmp = currentLevel.pop();

			// print the data in it
			System.out.print(tmp.data + "  ");

			// store data according to current
			// order.
			if (leftToRight) {
				if (tmp.left != null) {
					nextLevel.push(tmp.left);
				}

				if (tmp.right != null) {
					nextLevel.push(tmp.right);
				}
			} else {
				if (tmp.right != null) {
					nextLevel.push(tmp.right);
				}

				if (tmp.left != null) {
					nextLevel.push(tmp.left);
				}
			}

			if (currentLevel.isEmpty()) {
				leftToRight = !leftToRight;
				Stack<Node> temp = currentLevel;
				currentLevel = nextLevel;
				nextLevel = temp;
			}
		}

	}

	//// right-left-root
	private static void printPostOrder(Node node) {
		if (node == null)
			return;
		printPostOrder(node.right);
		printPostOrder(node.left);
		System.out.print("  " + node.data);
	}

	// root-left-right
	private static void printPreOrder(Node node) {
		if (node == null)
			return;
		System.out.print("  " + node.data);
		printPreOrder(node.left);
		printPreOrder(node.right);
	}

	// left-root-right
	private static void printInOrder(Node node) {
		if (node == null)
			return;
		printInOrder(node.left);
		System.out.print("  " + node.data);
		printInOrder(node.right);
	}

	private static void add(int data) {
		root = addRecursive(root, data);
	}

	private static Node addRecursive(Node root, int data) {
		Node node = new Node(data);
		if (root == null)
			root = node;
		if (data < root.data) {
			root.left = addRecursive(root.left, data);
		} else if (data > root.data) {
			root.right = addRecursive(root.right, data);
		} else {
			// already present
		}
		return root;
	}

	private Node addIterative(int data) {
		Node current = root;
		Node node = new Node(data);
		Node present = null;
		if (root == null) {
			root = node;
			return root;
		} else {
			while (current != null) {
				present = current;
				if (data < current.data) {
					current = current.left;
				} else {
					current = current.right;
				}
			}
			if (data < present.data) {
				present.left = node;
			} else {
				present.right = node;
			}
			return root;
		}
	}

	// Iterative
	private void add1(int value) {
		Node current = root;
		Node node = new Node(value);
		Node present = null;
		if (root == null) {
			root = node;
		} else {
			while (current != null) {
				present = current;
				if (value < current.data) {
					current = current.left;
				} else {
					current = current.right;
				}
			}
			if (value < present.data) {
				present.left = node;
			} else {
				present.right = node;
			}
		}
	}

}

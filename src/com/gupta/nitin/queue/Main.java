package com.gupta.nitin.queue;

class Queue {
	private int maxSize;

	private int[] queueArr;

	private int front;

	private int rear;

	private int nItems;

	public Queue(int s) {
		maxSize = s;
		queueArr = new int[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	public void insert(int j) {
		if (rear == maxSize - 1)
			rear = -1;
		queueArr[++rear] = j;
		nItems++;
	}

	public int remove() {
		int temp = queueArr[front++];
		if (front == maxSize)
			front = 0;
		nItems--;
		return temp;
	}

	public int peekFront() {
		return queueArr[front];
	}

	public boolean isEmpty() {
		return (nItems == 0);
	}

	public boolean isFull() {
		return (nItems == maxSize);
	}

	public int size() {
		return nItems;
	}
}

public class Main {
	public static void main(String[] args) {
		Queue theQueue = new Queue(5); // queue holds 5 items

		theQueue.insert(10); // insert 4 items
		theQueue.insert(20);
		theQueue.insert(30);
		theQueue.insert(40);

		theQueue.remove(); // remove 3 items
		theQueue.remove(); // (10, 20, 30)
		theQueue.remove();

		theQueue.insert(50); // insert 4 more items
		theQueue.insert(60); // (wraps around)
		theQueue.insert(70);
		theQueue.insert(80);

		while (!theQueue.isEmpty()) { // all items
			int n = theQueue.remove(); // (40, 50, 60, 70, 80)
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.println("");
	}
}
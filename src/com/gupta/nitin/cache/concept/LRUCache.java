package com.gupta.nitin.cache.concept;

import java.util.HashMap;

/**
 * *******least recently used***********
 * ********************************
 * Fast access: As we are designing cache, we should be able to fetch or update entries faster.
 * Evict least recently used entry: Cache should evict least recently used entry if capacity is reached.
 * Solution
 *  As we need to do lookup in O(1) time then HashMap is obvious choice but we need to take care of 
 *  least recently used entry as well.
 *  We need to find a data structure which can remove/add in O(1) time if we already know the node. We can use a
 *  double linked list for this purpose because it provides removal/addition in O(1) time if already know about the node.
 *  HashMap will make get operation in O(1) time and Doube linked list will make removal/addition in O(1) time.
 *  When the cache is full, the algorithm must choose which items to discard to make room for the new ones.
 */

class Node{
	int key;
	int value;
	Node prev;
	Node next;
 
	public Node(int key, int value){
		this.key = key;
		this.value = value;
	}
}

public class LRUCache {
	int capacity;
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	Node head=null;
	Node end=null;
 
	public LRUCache(int capacity) {
		this.capacity = capacity;
	}
 
	public int get(int key) {
		if(map.containsKey(key)){
			Node n = map.get(key);
			delete(n);
			setHead(n);
			return n.value;
		}
 
		return -1;
	}
 
	/*This method will delete node*/
	public void delete(Node node){
		if(node.prev!=null){
			node.prev.next = node.next;
		}else{
			head = node.next;
		}
 
		if(node.next!=null){
			node.next.prev = node.prev;
		}else{
			end = node.prev;
		}
 
	}
 
	/*This method will make passed node as head*/
	public void setHead(Node node){
		node.next = head;
		node.prev = null;
 
		if(head!=null)
			head.prev = node;
 
		head = node;
 
		if(end ==null)
			end = head;
	}
 
	public void set(int key, int value) {
		if(map.containsKey(key)){
			// update the old value
			Node old = map.get(key);
			old.value = value;
			delete(old);
			setHead(old);
		}else{
			Node newNode = new Node(key, value);
			if(map.size()>=capacity){
				
				map.remove(end.key);
				// remove last node
				delete(end);
				setHead(newNode);
 
			}else{
				setHead(newNode);
			}    
 
			map.put(key, newNode);
		}
	}	
	
	public static void main(String[] args) throws java.lang.Exception {
		LRUCache lrucache = new LRUCache(4);
		lrucache.set(1, 100);
		lrucache.set(10, 99);
		lrucache.set(15, 98);
		lrucache.set(10, 97);
		lrucache.set(12, 96);
		lrucache.set(18, 95);
		lrucache.set(1, 94);
 
		System.out.println(lrucache.get(1));
		System.out.println(lrucache.get(10));
		System.out.println(lrucache.get(15));
		
		
	}
}
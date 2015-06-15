package game.dataStructure;

public class Node<T> {
	
	private T data;
	private Node<T> next = null;
	
	public Node(T data, Node<T> next) {
		this.setData(data);
		this.setNext(next);
	}
	
	public Node(T data) {
		this.setData(data);
	}
	
	public T getData() {
			return data;
	}
	
	private void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}

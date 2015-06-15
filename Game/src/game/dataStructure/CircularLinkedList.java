package game.dataStructure;

public class CircularLinkedList<T> {
	
	private Node<T> current = null;
	
	//adds the new node after the current node
	public void add(T obj) {
		
		Node<T> newNode = new Node<T>(obj);
		
		if (size() == 0) {
			setCurrent(newNode);
		}
		else {
			newNode.setNext(getCurrent().getNext());
		}
		
		getCurrent().setNext(newNode);
		
	}
	
	public void remove(T data) {
		
		Node<T> temp = getCurrent();
		
		if (size() > 1) {
			while (!temp.getNext().getData().equals(data) && !temp.getNext().equals(getCurrent()))
				temp = temp.getNext();
			
			if (temp.getNext().getData().equals(data))
				temp.setNext(temp.getNext().getNext());
		}
		else
			setCurrent(null);
		
	}
	
	private void pointToNextNode() {
		if (current != null)
			setCurrent(current.getNext());
	}
	
	public Node<T> getNextNode() {
		pointToNextNode();
		return current;
	}
	
	public int size() {
		
		int count = 0;
		
		if (getCurrent() != null) {
			
			Node<T> temp = current;
			
			do {
				temp = temp.getNext();
				count++;
			} while (!temp.equals(current));
		}
		return count;
	}
	
	public Node<T> getCurrent() {
		return current;
	}

	private void setCurrent(Node<T> current) {
		this.current = current;
	}
	
	
	public static void main(String[] args) {
		
		CircularLinkedList<Integer> cll = new CircularLinkedList<Integer>();
		
		System.out.println(cll.size());
		
		cll.add(new Integer(5));
		cll.add(new Integer(19));
		cll.add(new Integer(13));
		
		System.out.println(cll.size());
		
		System.out.println(cll.getCurrent().getData());
		
		cll.getNextNode();
		
		System.out.println(cll.getCurrent().getData());
		
		cll.getNextNode();
		
		System.out.println(cll.getCurrent().getData());
		
		cll.remove(new Integer(5));
		
		System.out.println(cll.size());
		
		System.out.println(cll.getCurrent().getData());
		cll.pointToNextNode();
		System.out.println(cll.getCurrent().getData());
		cll.pointToNextNode();
		System.out.println(cll.getCurrent().getData());
		cll.pointToNextNode();
		System.out.println(cll.getCurrent().getData());
		cll.pointToNextNode();
		System.out.println(cll.getCurrent().getData());
		cll.pointToNextNode();
		

		cll.remove(new Integer(19));
		

		cll.remove(new Integer(23));
		
		System.out.println(cll.size());
		
		cll.pointToNextNode();
		
	
	}
	
}

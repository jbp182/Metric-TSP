package entities;

public class Iterator<E> {
	
	private E[] list;
	private int counter;
	private int current;
	
	public Iterator(E[] list, int counter) {
		this.list = list;
		this.counter = counter;
		init();
	}

	public void init() {
		current = 0;
	}
	
	public boolean hasNext() {
		return current < counter;
	}
	
	public E next() {
		return list[current++];
	}

}

package fr.slypy.advent;


public class Couple<K, V> {
	
	private K first;
	private V second;
	
	public Couple(K first, V second) {

		this.first = first;
		this.second = second;

	}

	public K getFirst() {
	
		return first;
	
	}
	
	public void setFirst(K first) {
	
		this.first = first;
	
	}
	
	public V getSecond() {
	
		return second;
	
	}
	
	public void setSecond(V second) {
	
		this.second = second;
	
	}
	
	@Override
	public String toString() {
		
		return '(' + first.toString() + ',' + second.toString() + ')';
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object c) {
		
		return c instanceof Couple couple && couple.getFirst().equals(first) && couple.getSecond().equals(second);
		
	}

}

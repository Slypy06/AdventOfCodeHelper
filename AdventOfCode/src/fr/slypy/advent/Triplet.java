package fr.slypy.advent;


public class Triplet<K, V, W> {
	
	private K first;
	private V second;
	private W third;
	
	public Triplet(K first, V second, W third) {

		this.first = first;
		this.second = second;
		this.third = third;

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
	
	public W getThird() {
		
		return third;
	
	}
	
	public void setThird(W third) {
	
		this.third = third;
	
	}
	
	@Override
	public String toString() {
		
		return '(' + first.toString() + ", " + second.toString() + ", " + third.toString() + ')';
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object c) {
		
		return c instanceof Triplet t && t.getFirst().equals(first) && t.getSecond().equals(second) && t.getThird().equals(third);
		
	}

}

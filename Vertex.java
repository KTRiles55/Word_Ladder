// Kenneth Riles

/** Vertex.java: represents each word as a vertex. */
public class Vertex {
	private String city;
	private boolean visited;
	
	public Vertex() {
		
	}
	
	public Vertex(String city) {
		this.city=city;
		visited=false;
	}
	
	public Vertex(String city, boolean v) {
		this.city=city;
		visited=v;
	}
	
	public void setCity(String city) {
		this.city=city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setVisited(boolean visited) {
		this.visited=visited;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public String toString() {
		return city;
	}
	
	public boolean equals(Object v) {
		return this.city.equals(((Vertex)v).city);
	}
	

}

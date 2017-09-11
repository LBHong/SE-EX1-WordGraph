
public class Edge {
	public String from,to;
	public int weight;
	public String color;
	
	/**
	 * Initialize a edge, default color is black.
	 * @param from
	 * @param to
	 * @param weight
	 */
	public Edge(String from, String to, int weight) {
		this.from=from;
		this.to=to;
		this.weight=weight;
		this.color="black";
	}
	
	/**
	 * Initialize a edge, default color is black, weight will be set to 1 if missing.
	 * @param from
	 * @param to
	 */
	public Edge(String from, String to) {
		this(from,to,1);
	}
	
	/**
	 * Set color to default.
	 */
	public void clearColor() {
		this.color="black";
	}
}

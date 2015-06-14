package domain;

public class Route {

	private String source;
	
	private String destination;
	
	private Integer weight;
	
	private String routeCode;
	
	public Integer getWeight() {
		return weight;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public String getRouteCode() {
		return routeCode;
	}


	public Route(String source, String destination, Integer weight){
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.routeCode = source + ":" + destination;
	}
	
	public boolean equals(Route other){
		return this.getSource().equals(other.getSource()) && this.getDestination().equals(other.getDestination());
	}
	
	public String toString(){
		return routeCode;
	}

}

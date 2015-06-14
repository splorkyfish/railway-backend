package domain;

import java.util.HashMap;
import java.util.Map;

public class City {
	private String name;
	private Map <String, Route> departures;
	private Map <String, Route> arrivals;
	
	public City(String name){
		departures = new HashMap<String, Route>();
		arrivals = new HashMap<String, Route>();
		this.name = name;
	}
	
	public boolean equals(City otherCity){
		return this.getName().equals(otherCity.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Route> getDepartures() {
		return departures;
	}

	public void setDepartures(Map<String, Route> departures) {
		this.departures = departures;
	}
	
	public boolean addDeparture(String dest, Route route){
		if (!departures.containsKey(dest)){
			departures.put(dest, route);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeDestination(String dest){
		if (departures.containsKey(dest)){
			departures.remove(dest);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addArrival(String arrival, Route route){
		if (!arrivals.containsKey(arrival)){
			arrivals.put(arrival, route);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeArrival(String arrival){
		if (arrivals.containsKey(arrival)){
			arrivals.remove(arrival);
			return true;
		}else{
			return false;
		}
	}

	public Map<String, Route> getArrivals() {
		return arrivals;
	}

	public void setArrivals(Map<String, Route> arrivals) {
		this.arrivals = arrivals;
	}

	public String toString(){
		return name;
	}
}

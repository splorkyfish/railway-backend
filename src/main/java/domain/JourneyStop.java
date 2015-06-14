package domain;

import java.util.LinkedList;
import java.util.List;

public class JourneyStop {

	private String name;
	private List<String>previousStops;

	public JourneyStop(String cityName){
		this.name = cityName;
		this.previousStops = new LinkedList<String>();
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPreviousStops() {
		List<String> copy = new LinkedList<String>();
		for(String stop : previousStops){
			copy.add(stop);
		}
		return copy;
	}
	public void addAllPreviousStops(List<String> previousStops) {
		for (String stop : previousStops){
			this.previousStops.add(stop);
		}
	}
	
	public void addPreviousStop(String prev){
		previousStops.add(prev);
	}
	
	public void removePreviousStop(String prev){
		previousStops.remove(prev);
	}
	
	public String toString(){
		return name;
	}
}

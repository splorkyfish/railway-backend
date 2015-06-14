package restful;

import java.util.List;

public class ItineraryInfoResponse {
	
	private List<String> journey;
	private String status;
	private int distance;
	
	public List<String> getJourney() {
		return journey;
	}
	
	public void setJourney(List<String> journey) {
		this.journey = journey;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}	
	
}

package searchcondition;

import java.util.Map;

import domain.City;
import domain.JourneyStop;


class ExactStopNumberCondition implements SearchCondition{

	@Override
	public boolean shouldGoOn(int numberOfStops, JourneyStop nextStop, Map<String, City> cities, String start, String dest) {
		return numberOfStops >= nextStop.getPreviousStops().size();
	}
	
	@Override
	public boolean isAtDestination(int numberOfStops, JourneyStop nextStop, String dest){
		return (nextStop.getName().equals(dest) && numberOfStops == nextStop.getPreviousStops().size());
	}

}

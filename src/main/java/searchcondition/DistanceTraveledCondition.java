package searchcondition;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import domain.City;
import domain.JourneyStop;
import domain.Route;


class DistanceTraveledCondition implements SearchCondition{

	@Override
	public boolean shouldGoOn(int conditionLimit, JourneyStop nextStop, Map<String, City> cities, String start, String dest) {
		List<String> stopsSoFar = nextStop.getPreviousStops();
		stopsSoFar.add(nextStop.getName());
		if (stopsSoFar.size() < 2){
			return true;
		}
		int distanceTraveled = 0;
		Iterator<String> stopsItr = stopsSoFar.iterator();
		String firstCityName=stopsItr.next();
		while (stopsItr.hasNext()){
			City firstCity= cities.get(firstCityName);
			Map<String, Route> departures = firstCity.getDepartures();
			String nextCityName = stopsItr.next();
			Route thisRoute = departures.get(nextCityName);
			distanceTraveled += thisRoute.getWeight();
			firstCityName = nextCityName;
		}
		return conditionLimit > distanceTraveled;
	}

	@Override
	public boolean isAtDestination(int conditionLimit, JourneyStop nextStop,
			String dest) {
		return (nextStop.getName().equals(dest));
	}

}

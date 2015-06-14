package searchcondition;

import java.util.List;
import java.util.Map;

import domain.City;
import domain.JourneyStop;


class NoRevisitingCondition implements SearchCondition{

	@Override
	public boolean shouldGoOn(int conditionLimit, JourneyStop nextStop, Map<String, City> cities, String start, String dest) {
		List<String> previousStops = nextStop.getPreviousStops();
		return !previousStops.contains(nextStop.getName()) || isAtDestination(conditionLimit, nextStop,
				dest);
	}

	@Override
	public boolean isAtDestination(int conditionLimit, JourneyStop nextStop,
			String dest) {
		return (nextStop.getName().equals(dest));
	}

}

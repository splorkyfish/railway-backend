package searchcondition;

import java.util.Map;

import domain.City;
import domain.JourneyStop;

public interface SearchCondition {

	public boolean shouldGoOn(int conditionLimit, JourneyStop nextStop, Map<String, City> cities, String start, String dest);
	
	public boolean isAtDestination(int conditionLimit, JourneyStop nextStop, String dest);
}

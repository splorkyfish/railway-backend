package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import searchcondition.SearchCondition;
import searchcondition.SearchConditionFactory;
import searchcondition.SearchConditionType;

public class RouteManager {
	
	public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

	private Map <String, City> cities;
	
	private Map <String, Route> routes;
	
	public RouteManager(String ...strings ) {
		cities = new HashMap<String, City>();
		routes = new HashMap<String, Route>();
		for (String string : strings){
			String source = string.substring(0,1);
			String dest = string.substring(1,2);
			Integer weight = Integer.valueOf(string.substring(2,string.length()));
		
			addRoute(source, dest, weight);
		}
	}
	
	public RouteManager(Collection<String> newRoutes) {
		cities = new HashMap<String, City>();
		routes = new HashMap<String, Route>();
		for (String string : newRoutes){
			String source = string.substring(0,1);
			String dest = string.substring(1,2);
			Integer weight = Integer.valueOf(string.substring(2,3));
		
			addRoute(source, dest, weight);
		}	
	}
	
	public boolean addRoute(String source, String dest, Integer weight){
		Route newRoute = new Route (source, dest, weight);
			routes.put(newRoute.getRouteCode(), newRoute);
			addCities(source, dest, newRoute);
			return true;
	}
	
	protected void addCities(String source, String dest, Route route){
		City sourceCity = cities.get(source);
		if (sourceCity == null){
			sourceCity = new City(source);
			cities.put(source, sourceCity);
		}
		sourceCity.addDeparture(dest, route);
		City destCity = cities.get(dest);
		if (destCity == null){
			destCity = new City(dest);
			cities.put(dest, destCity);
		}
		destCity.addArrival(source, route);
		
	}
	
	public String findRouteDistance(String...strings){
		List<String> stringList = new ArrayList<String>();
		for(String string : strings){
			stringList.add(string);
		}
		return findRouteDistance(stringList);
	}
	
	public String findRouteDistance(List<String>journey){
		List<City> citiesInJourney = findCitiesInJourney(journey);
		if (citiesInJourney == null){
			return NO_SUCH_ROUTE;
		}
		Iterator<City> citiesItr = citiesInJourney.iterator();
		City thisCity= citiesItr.next();
		City nextCity = null;
		int totalDistance = 0;
		while(citiesItr.hasNext()){
			Map<String, Route> destinations = thisCity.getDepartures();
			nextCity = citiesItr.next();
			if(destinations.containsKey(nextCity.getName())){
				totalDistance += destinations.get(nextCity.getName()).getWeight();
				thisCity = nextCity;
			}else{
				return NO_SUCH_ROUTE;
			}
		}
		
		return String.valueOf(totalDistance);
	}
	
	protected List<City> findCitiesInJourney(List<String> journey){
		List<City> citiesInRoute = new ArrayList<City>();
		if (journey.size() < 2){
			return null;
		}
		for (String string: journey){
			City thisCity = cities.get(string);
			if (thisCity == null){
				return null;
			}else{
			citiesInRoute.add(cities.get(string));
			}
		}
		return citiesInRoute;
	}
	
	
	public List<String> findShortestJourney(String start, String end){
		List<List<String>> journies = findAllJournies(start, end, SearchConditionType.NO_REVISITING);
		int shortestDistance = Integer.MAX_VALUE;
		List<String> shortestJourney = new ArrayList<String>();
		for (List<String> journey : journies){
			String distance = findRouteDistance(journey);
			if (!distance.equals(NO_SUCH_ROUTE)){
				Integer  weight = Integer.valueOf(distance);
				if(weight < shortestDistance){
					shortestDistance = weight;
					shortestJourney = journey;
				}
			}
		}
		return shortestJourney;
	}
	
	public List<List<String>> findAllJournies (String start, String end, SearchConditionType conditionType){
		return findAllJournies(start, end, conditionType, 0);
	}
		
	public List<List<String>> findAllJournies (String start, String dest, SearchConditionType conditionType, int conditionLimit){
		SearchCondition condition = SearchConditionFactory.getConditions(conditionType);
		List<List<String>> validJourneys = new ArrayList<List<String>>();
		Stack<JourneyStop>nextStops = new Stack<JourneyStop>();
		City startingCity = cities.get(start);
		JourneyStop firstStop = new JourneyStop(startingCity.getName());
		pushDepartures(firstStop, nextStops);
		while(!nextStops.isEmpty()){
			JourneyStop nextStop = nextStops.pop();
			if(!condition.shouldGoOn(conditionLimit, nextStop, cities, start, dest)){
					continue;
			}else if (nextStop.getName().equals(dest) && condition.isAtDestination(conditionLimit, nextStop, dest)){
				List<String>previousStops = nextStop.getPreviousStops();
				previousStops.add(nextStop.getName());
				validJourneys.add(previousStops);
			}
				
			pushDepartures(nextStop, nextStops);
		}
		return validJourneys;
	}
	

	private void pushDepartures(JourneyStop thisStop, Stack <JourneyStop>nextStops){
		City thisCity = cities.get(thisStop.getName());
		Set<String> destinationNames = thisCity.getDepartures().keySet();
		for(String dest : destinationNames){
			City nextCity = cities.get(dest);
			if(nextCity != null){
				JourneyStop nextStop = new JourneyStop(nextCity.getName());
				nextStop.addAllPreviousStops(thisStop.getPreviousStops());
				nextStop.addPreviousStop(thisStop.getName());
				nextStops.push(nextStop);
			}
		}
	}
	
	

	public Map<String, City> getCities() {
		return cities;
	}

	public void setCities(Map<String, City> cities) {
		this.cities = cities;
	}

	public Map<String, Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Map<String, Route> routes) {
		this.routes = routes;
	}
	
}

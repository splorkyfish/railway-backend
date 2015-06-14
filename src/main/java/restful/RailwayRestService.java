package restful;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import searchcondition.SearchConditionType;
import domain.Route;
import domain.RouteManager;

@RestController
public class RailwayRestService {
	
	private static RouteManager manager = new RouteManager("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
	
    
    @RequestMapping(value="/", produces = "text/html")
    String home() {
        return "Service is up";
    }
    
    @RequestMapping("/getRoutes")
    @ResponseBody	
     Collection <Route> getRoutes() {
    	Map<String, Route> routes =  manager.getRoutes();
    	return routes.values();
    }
    
    @RequestMapping("/updateRoutes")
    @ResponseBody	
     Collection<String> updateRoutes(@RequestParam(value="newRoutes", required=true) Collection<String> newRoutes) {
    	manager = new RouteManager(newRoutes);
    	return newRoutes;
    }
    
    @RequestMapping("/shortestJourney")
    @ResponseBody	
    ItineraryInfoResponse shortestJourney(@RequestParam(value="requestedJourney", required=true) String requestedJourney) {
    	String start = requestedJourney.substring(0, 1);
    	String end = requestedJourney.substring(1, 2);
		List<String> journey = manager.findShortestJourney(start, end);
		ItineraryInfoResponse response = new ItineraryInfoResponse();
		if(journey.size() == 0){
			response.setStatus("ERROR");
			response.setDistance(0);
		}else{
			response.setStatus("OK");
			response.setDistance(Integer.valueOf(manager.findRouteDistance(journey)));
		}
		response.setJourney(journey);
		return response;
   }
    
    @RequestMapping("/routeDistance")
    @ResponseBody	
     String updateRoutes(@RequestParam(value="journeyString", required=false) String journeyString) {
    	List<String> journey = new ArrayList<String>();
    	for (Character character : journeyString.toCharArray()){
    		journey.add(character.toString());
    	}
    	return manager.findRouteDistance(journey);
    }
    
    @RequestMapping("/getAllRoutesMaxStops")
    @ResponseBody	
    List<ItineraryInfoResponse> getAllRoutesMaxStops(@RequestParam(value="requestedJourney", required=true) String requestedJourney) {
    	String start = requestedJourney.substring(0, 1);
    	String end = requestedJourney.substring(1, 2);
    	Integer maxStops = Integer.valueOf(requestedJourney.substring(2, requestedJourney.length()));
		List<List<String>> journeies = manager.findAllJournies(start, end, SearchConditionType.MAX_STOPS, maxStops.intValue());
		List<ItineraryInfoResponse> response = new ArrayList<ItineraryInfoResponse>();
		for (List<String> journey : journeies){
			if(journey.size() != 0){
				ItineraryInfoResponse thisItinerary= new ItineraryInfoResponse();
				thisItinerary.setDistance(Integer.valueOf(manager.findRouteDistance(journey)));
				thisItinerary.setJourney(journey);
				response.add(thisItinerary);
			}
		}
		return response;
   }
    
    @RequestMapping("/getAllRoutesExactStops")
    @ResponseBody	
    List<ItineraryInfoResponse> getAllRoutesExactStops(@RequestParam(value="requestedJourney", required=true) String requestedJourney) {
    	String start = requestedJourney.substring(0, 1);
    	String end = requestedJourney.substring(1, 2);
    	Integer maxStops = Integer.valueOf(requestedJourney.substring(2, requestedJourney.length()));
		List<List<String>> journeies = manager.findAllJournies(start, end, SearchConditionType.EXACT_STOPS, maxStops.intValue());
		List<ItineraryInfoResponse> response = new ArrayList<ItineraryInfoResponse>();
		for (List<String> journey : journeies){
			if(journey.size() != 0){
				ItineraryInfoResponse thisItinerary= new ItineraryInfoResponse();
				thisItinerary.setDistance(Integer.valueOf(manager.findRouteDistance(journey)));
				thisItinerary.setJourney(journey);
				response.add(thisItinerary);
			}
		}
		return response;
   }
   
    @RequestMapping("/getAllRoutesMaxDistance")
    @ResponseBody	
    List<ItineraryInfoResponse> getAllRoutesMaxDistance(@RequestParam(value="requestedJourney", required=true) String requestedJourney) {
    	String start = requestedJourney.substring(0, 1);
    	String end = requestedJourney.substring(1, 2);
    	Integer maxDistance = Integer.valueOf(requestedJourney.substring(2, requestedJourney.length()));
		List<List<String>> journeies = manager.findAllJournies(start, end, SearchConditionType.TOTAL_DISTANCE, maxDistance.intValue());
		List<ItineraryInfoResponse> response = new ArrayList<ItineraryInfoResponse>();
		for (List<String> journey : journeies){
			if(journey.size() != 0){
				ItineraryInfoResponse thisItinerary= new ItineraryInfoResponse();
				thisItinerary.setDistance(Integer.valueOf(manager.findRouteDistance(journey)));
				thisItinerary.setJourney(journey);
				response.add(thisItinerary);
			}
		}
		return response;
   }
    
   
}

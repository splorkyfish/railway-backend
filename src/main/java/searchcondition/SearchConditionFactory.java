package searchcondition;



public class SearchConditionFactory {
	private static ExactStopNumberCondition exactStopNumberCondition = new ExactStopNumberCondition();
	private static MaxStopNumberCondition maxStopNumberCondition = new MaxStopNumberCondition();
	private static DistanceTraveledCondition distanceCondition = new DistanceTraveledCondition();
	private static NoRevisitingCondition noBacktrackingCondition= new NoRevisitingCondition();
	
	public static SearchCondition getConditions(SearchConditionType conditionType) { 
		if(SearchConditionType.EXACT_STOPS.equals(conditionType)){
			return exactStopNumberCondition;
		}else if(SearchConditionType.MAX_STOPS.equals(conditionType)){
			return maxStopNumberCondition;
		}else if(SearchConditionType.TOTAL_DISTANCE.equals(conditionType)){
			return distanceCondition;
		}else if (SearchConditionType.NO_REVISITING.equals(conditionType)){
			return noBacktrackingCondition;
		}
		else return null;
	}
	
}

package searchcondition;

public enum SearchConditionType {
	
	MAX_STOPS("maxStops"), EXACT_STOPS("exactStops"), TOTAL_DISTANCE("totalDistance"), NO_REVISITING("noRevisiting");
	
	private String type;
	
	
	private SearchConditionType(String type){
		this.type = type;
	}


	public String getType() {
		return type;
	}

}

package domain;


import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import searchcondition.SearchConditionType;


public class RouteManagerTest {
	

	@Test
	public void testConstructor() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		assertEquals(5, manager.getCities().size());
	}
	
	
	@Test
	public void testQuestion1() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		assertEquals("9", manager.findRouteDistance("A", "B", "C"));
	}
	
	@Test
	public void testQuestion2() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		assertEquals("5", manager.findRouteDistance("A", "D"));
	}
	
	@Test
	public void testQuestion3() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		assertEquals("13", manager.findRouteDistance("A", "D", "C"));
	}
	
	@Test
	public void testQuestion4() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		assertEquals("22", manager.findRouteDistance("A", "E", "B", "C", "D"));
	}
	
	@Test
	public void testQuestion5() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		assertEquals(RouteManager.NO_SUCH_ROUTE, manager.findRouteDistance("A", "E", "D"));
	}
	
	@Test
	public void testQuestion6() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		List<String> trip1 = new LinkedList<String>();
		trip1.add("C");
		trip1.add("E");
		trip1.add("B");
		trip1.add("C");
		List<String> trip2 = new LinkedList<String>();
		trip2.add("C");
		trip2.add("D");
		trip2.add("C");
		List<List<String>>allTrips = new LinkedList<List<String>>();
		allTrips.add(trip1);
		allTrips.add(trip2);
		assertEquals(allTrips, manager.findAllJournies("C", "C", SearchConditionType.MAX_STOPS, 3));
	}
	
	@Test
	public void testQuestion7() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		List<String> trip1 = new LinkedList<String>();
		trip1.add("A");
		trip1.add("B");
		trip1.add("C");
		trip1.add("D");
		trip1.add("C");
		List<String> trip2 = new LinkedList<String>();
		trip2.add("A");
		trip2.add("D");
		trip2.add("C");
		trip2.add("D");
		trip2.add("C");
		List<String> trip3 = new LinkedList<String>();
		trip3.add("A");
		trip3.add("D");
		trip3.add("E");
		trip3.add("B");
		trip3.add("C");
		List<List<String>>allTrips = new LinkedList<List<String>>();
		allTrips.add(trip1);
		allTrips.add(trip2);
		allTrips.add(trip3);
		assertEquals(allTrips, manager.findAllJournies("A", "C", SearchConditionType.EXACT_STOPS, 4));
	}
	
	@Test
	public void testQuestion8() throws Exception{
		RouteManager manager = createCodeChallengeManager();
		List<String> trip1 = new LinkedList<String>();
		trip1.add("A");
		trip1.add("B");
		trip1.add("C");
		assertEquals(trip1, manager.findShortestJourney("A", "C"));
	}
	
	@Test
	public void testQuestion9() throws Exception{
		RouteManager manager = createCodeChallengeManager();
		List<String> trip1 = new LinkedList<String>();
		trip1.add("B");
		trip1.add("C");
		trip1.add("E");
		trip1.add("B");
		assertEquals(trip1, manager.findShortestJourney("B", "B"));
	}
	
	@Test
	public void testQuestion10() throws Exception {
		RouteManager manager = createCodeChallengeManager();
		List<String> trip1 = new LinkedList<String>();
		trip1.add("C");
		trip1.add("D");
		trip1.add("C");
		List<String> trip2 = new LinkedList<String>();
		trip2.add("C");
		trip2.add("E");
		trip2.add("B");
		trip2.add("C");
		List<String> trip3 = new LinkedList<String>();
		trip3.add("C");
		trip3.add("E");
		trip3.add("B");
		trip3.add("C");
		trip3.add("D");
		trip3.add("C");
		List<String> trip4 = new LinkedList<String>();
		trip4.add("C");
		trip4.add("D");
		trip4.add("C");
		trip4.add("E");
		trip4.add("B");
		trip4.add("C");
		List<String> trip5 = new LinkedList<String>();
		trip5.add("C");
		trip5.add("D");
		trip5.add("E");
		trip5.add("B");
		trip5.add("C");
		List<String> trip6 = new LinkedList<String>();
		trip6.add("C");
		trip6.add("E");
		trip6.add("B");
		trip6.add("C");
		trip6.add("E");
		trip6.add("B");
		trip6.add("C");
		List<String> trip7 = new LinkedList<String>();
		trip7.add("C");
		trip7.add("E");
		trip7.add("B");
		trip7.add("C");
		trip7.add("E");
		trip7.add("B");
		trip7.add("C");
		trip7.add("E");
		trip7.add("B");
		trip7.add("C");
		List<List<String>>foundTrips = manager.findAllJournies("C", "C", SearchConditionType.TOTAL_DISTANCE, 30);
//		System.out.println(foundTrips);
		assertEquals(true, foundTrips.contains(trip1));
		assertEquals(true, foundTrips.contains(trip2));
		assertEquals(true, foundTrips.contains(trip3));
		assertEquals(true, foundTrips.contains(trip4));
		assertEquals(true, foundTrips.contains(trip5));
		assertEquals(true, foundTrips.contains(trip6));
		assertEquals(true, foundTrips.contains(trip7));
		assertEquals(7, foundTrips.size());
	}
	
	@Test
	public void testShortestJourneyRouteDoesntExist() throws Exception{
		RouteManager manager = createCodeChallengeManager();
		List<String> trip1 = new LinkedList<String>();
		assertEquals(trip1 , manager.findShortestJourney("D", "A"));
	}
	
	@Test
	public void shortestJourneyOneStop() throws Exception{
		RouteManager manager = createCodeChallengeManager();
		List<String> trip1 = new LinkedList<String>();
		trip1.add("A");
		trip1.add("B");
		assertEquals(trip1, manager.findShortestJourney("A", "B"));
	}
	


	private RouteManager createCodeChallengeManager() throws Exception {
		RouteManager manager = new RouteManager("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
		return manager;
	}
	
	@Test
	public void testSomething(){
		String a = "01234";
		assertEquals("0", a.substring(0, 1));
	}

}

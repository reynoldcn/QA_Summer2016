package deliverable2;

import java.util.HashMap;
import java.util.Random;

public class CitySim9001 {
	
	Random rand = new Random();
	/***To meet REQ FUN-CITY-LOCS and REQ FUN-OUTSIDE-CITY***/
	public static final Location[] locations = 
			new Location[]{new Hotel(), new Diner(), new Library(), new College(), new Outside()};
	
	/**
	 * To judge if the input parameter meets the 
	 * REQ FUN-ARGS.
	 * @param args seed useful in generating random numbers.
	 * @return true if meet, false if not
	 * */
	public boolean commend(String[] args){
		if(args.length == 1 && args[0].matches("[0-9]+"))
			return true;
		return false;
	}
	
	/**
	 * To generate a driver and randomly
	 * give them a start location
	 * if Outside is selected, it should be
	 * modified to College
	 * REQ FUN-FIRST-LOC
	 * REQ FUN-START-LOC
	 * @param id driver's id
	 * @param randforstartLoc used in generating a 
	 * 			random location.
	 * @return driver
	 * */
	public Driver generateDriver(int id, Random randforstartLoc){

		int loc = randforstartLoc.nextInt(5);
		Driver driver = new Driver(id);
		if(loc == 4){
			System.out.println("Driver " + id
					+ " heading from Outside City "
					+ "to College via Fifth Ave.");
			loc = 3;
		}
		driver.setLoc(locations[loc]);
		return driver;
	}
	
	/**
	 * Randomly head to another possible locations
	 * Meet
	 * REQ FUN-ITERATION
	 * REQ FUN-OTHER-CITIES
	 * REQ FUN-END.
	 * @param driver
	 * @param randfornextLoc used in generate next locations
	 * @first if this is first iteration, the next location
	 * 			will not be outside city
	 * @return true if still inside city
	 * 			false if go outside
	 * */
	public boolean headTo(Driver driver, Random randfornextLoc, boolean first){
		int loc = 0;
		if(first)
			loc = randfornextLoc.nextInt(4);
		else
			loc = randfornextLoc.nextInt(5);
		Location curLoc = driver.Loc();
		String via = curLoc.getVia(locations[loc]);
		//System.out.println(via);
		while(via == null){
			loc = randfornextLoc.nextInt(5);
			via = curLoc.getVia(locations[loc]);
		}
		
		
		if(driver.headTo(locations[loc]) == 1)
			return true;
		else
			return false;
	}
	/**
	 * Generate 5 drivers and do the iterations
	 * Meet REQ FUN-FIVE-DRIVERS.
	 * @param args seed useful in generating random numbers.
	 * @return 1 if runs successfully
	 * 			-1 if encounters errors like error args.
	 * */
	public int run(String[] args){
		if(!commend(args)){
			System.out.println("Unrecognizable Input. "
					+ "Please input a single integer as seed");
			return -1;
		}
		
		rand.setSeed(Long.parseLong(args[0]));
		
		for(int i = 0; i < 5; i++){
			Driver driver = generateDriver(i, rand);
			boolean first = true;
			while(headTo(driver, rand, first)){
				first = false;
			}
			System.out.println("-----");
		}
		return 1;
	}
	
	
	public static void main(String[] args){
		CitySim9001 exe = new CitySim9001();
		exe.run(args);
	}
}

class Driver{
	private int id;
	Location curLoc;
	Driver(int id){
		this.id = id;
	}
	
	public int id(){
		return this.id;
	}
	
	public void setLoc(Location Loc){
		this.curLoc = Loc;
	}
	
	public Location Loc(){
		return this.curLoc;
	}
	
	
	/**
	 * Driver heads to another location
	 * Meet
	 * REQ FUN-ITERATION
	 * REQ FUN-OTHER-CITIES
	 * @param nextLoc the location driver head to
	 * @return -1 if nextLoc is unaccessible
	 * 			0 if driver is leaving to another city
	 * 			1 if driver	is still inside city.
	 * */
	public int headTo(Location nextLoc){
		String via = curLoc.getVia(nextLoc);
		int end = -1;
		String cur = curLoc.name();
		String next = new String();
		if(via == null)
			return end;
		else{
			//System.out.println(via);

			cur = curLoc.name();
			next = nextLoc.name();
			
			System.out.println("Driver " + id
								+ " heading from " + cur
								+ " to " + next
								+ " via " + via);
			end = 1;
			if(next.equals("Outside City")){
				if(cur.equals("Library")){
					System.out.println("Driver " + id
								+ " has gone to Cleveland!");
				} else {
					System.out.println("Driver " + id
								+ " has gone to Philadelphia!");
				}
				
				end = 0;
			}
			setLoc(nextLoc);
		}

		return end;
	}
}



abstract class Location{
	String name;
	public HashMap<String, String> connectedLoc = new HashMap<String, String>();
	//key:value = location: via what's ave/st
	
	public void setName(String name){
		this.name = name;
	}
	public String name(){
		return name;
	}
	
	/**
	 * To get the name of street between input
	 * and itself. If input loc is unaccessible
	 * return null.
	 * Meet
	 * REQ FUN-AVENUES.
	 * REQ FUN-STREETS.
	 * @param loc the location 
	 * @return name if loc are connected. null if 
	 * 			not.
	 * */
	public String getVia(Location loc){
		if(connectedLoc.containsKey(loc.name())){
			return connectedLoc.get(loc.name());
		}
		
		return null;
	}
	
	public void connectNewLoc(Location loc, String via){
		connectedLoc.put(loc.name(), via);
	}
	
}

class Hotel extends Location{ 
	Hotel(){
		this.name = "Hotel";
		connectedLoc.put("Library", "Bill St.");
		connectedLoc.put("Diner", "Forth Ave.");
	}
}

class Diner extends Location{
	Diner(){
		this.name = "Diner";
		connectedLoc.put("College", "Phil St.");
		connectedLoc.put("Outside City", "Fourth Ave.");
	}
}

class College extends Location{
	College(){
		this.name = "College";
		connectedLoc.put("Diner", "Phil St.");
		connectedLoc.put("Library", "Fifth Ave.");
	}
}

class Library extends Location{
	Library(){
		this.name = "Library";
		connectedLoc.put("Hotel", "Bill St.");
		connectedLoc.put("Outside City", "Fifth Ave.");
	}
}

class Outside extends Location{
	Outside(){
		this.name = "Outside City";
	}
}






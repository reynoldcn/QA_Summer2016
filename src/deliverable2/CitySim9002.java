package deliverable2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class CitySim9002 {
	//The types of visitors
	public final static String[] visitors = 
			new String[]{"Student", "Business", "Professor", "Blogger"};
	//All locations visitors can reach
	public final static String[] locations = 
			new String[]{"The Cathedral of Learning", "Squirrel Hill", 
						"The Point", "Downtown"};
	LinkedList<Visitor> visitorlist = new LinkedList<Visitor>();
	//Visitors' ID, from 1 t0 5 as required. 
	public static int VisitorID = 1;
	
	public boolean addVisitor(Visitor visitor){
		try{
			visitorlist.add(visitor);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	
	/**
	*Generate a specific visitor based on input
	* @param type 0: student; 1: Business Person; 2: Professor; 3: Blogger
	* 
	*/
	public Visitor generateVisitor(int type){
		switch(type){
		case 0:
			return new Student(VisitorID);
		case 1:
			return new Business(VisitorID);
		case 2:
			return new Professor(VisitorID);
		case 3:
			return new Blogger(VisitorID);
		default:
			System.out.println("error");
			return null;
		}
	}
	
	
	/**
	*Let the visitor go to one city
	* @param visitor the visitor
	* @param City the city visitor is going to
	* @reutrn if the visitor can reach the city, return ture; If not, false
	*/
	public boolean visit(Visitor visitor, String City){
		return visitor.visitCity(City);
	}
	/**
	*Go go go
	*@param seed Useful in generating a random number
	*@return how many times Loop runs
	*/
	public int run(int seed){
		Random rand = new Random(seed);
		Visitor visitor = null;
		int nVisitor = 0;
		for(int i = 1; i <= 5; i++){
			int type = randomSelectType(rand);
			visitor = generateVisitor(type);
			nVisitor += 1;
			VisitorID++;
			int location = rand.nextInt(4);
			while(location != 4){
				visit(visitor, locations[location]);
				location = rand.nextInt(5);
			}
			visitor.leave();
			System.out.println("***");			
		}
		return nVisitor;
	}
	/**
	*The commend should be one integer.
	*@param commend
	*@return 1, integer -> true; not 1, not integer -> false;
	*/
	public boolean commend(String[] args){
		if(args.length == 1 && args[0].matches("[0-9]+"))
			return true;
		return false;
	}
	/**
	*Generate a random number useful in selecting visitor type randomly
	* @param rand used to generate random value;
	* @return a random number
	*/
	public int randomSelectType(Random rand){
		return rand.nextInt(4);
	}
	
	public int add(){
		return 1;
	}
	public static void main(String[] args){
		int seed = 0;
		
		CitySim9002 exe = new CitySim9002();
		if(exe.commend(args)){
			seed = Integer.parseInt(args[0]);
			System.out.println("Welcome to CitySim! Your seed is " + seed + ".");
		} else {
			System.out.println("Please enter one integer argument, seed");
			return;
		}

		exe.run(seed);
	}
}

abstract class Visitor{
	HashMap<String, Boolean> perference = new HashMap<String, Boolean>();
	int ID;
	String type;
	public boolean visitCity(String City){
		if(!perference.containsKey(City))
			return false;
		System.out.println("Visitor " + ID + " is going to " + City + ".");
		if(perference.get(City)){
			System.out.println("Visitor " + ID + " did like " + City + ".");
		} else {
			System.out.println("Visitor " + ID + " did not like " + City + ".");
		}
		return true;
		
	}
	
	public void leave(){
		System.out.println("Visitor " + ID + " has left the city.");
	}
	
	public int Id(){
		return this.ID;
	}
	public String type(){
		return this.type;
	}
	
	public boolean perference(String City){
		if(perference.containsKey(City))
			return perference.get(City);
		return false;
	}
}

class Student extends Visitor{
	public Student(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[0];
		System.out.println("Visitor " + id + " is a Student.");
		this.perference.put(CitySim9002.locations[0], false);
		this.perference.put(CitySim9002.locations[1], true);
		this.perference.put(CitySim9002.locations[2], true);
		this.perference.put(CitySim9002.locations[3], true);
	}
}

class Business extends Visitor{
	public Business(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[1];
		System.out.println("Visitor " + id + " is a Business Person.");
		this.perference.put(CitySim9002.locations[0], false);
		this.perference.put(CitySim9002.locations[1], true);
		this.perference.put(CitySim9002.locations[2], false);
		this.perference.put(CitySim9002.locations[3], true);
	}
}

class Professor extends Visitor{
	public Professor(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[2];
		System.out.println("Visitor " + id + " is a Professor.");
		this.perference.put(CitySim9002.locations[0], true);
		this.perference.put(CitySim9002.locations[1], true);
		this.perference.put(CitySim9002.locations[2], true);
		this.perference.put(CitySim9002.locations[3], true);
	}
}

class Blogger extends Visitor{
	public Blogger(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[3];
		System.out.println("Visitor " + id + " is a Blogger.");
		this.perference.put(CitySim9002.locations[0], false);
		this.perference.put(CitySim9002.locations[1], false);
		this.perference.put(CitySim9002.locations[2], false);
		this.perference.put(CitySim9002.locations[3], false);
	}
}

package deliverable2;

import java.util.HashMap;
import java.util.Random;

public class CitySim9002 {
	public final static String[] visitors = 
			new String[]{"Student", "Business", "Professor", "Blogger"};
	public final static String[] locations = 
			new String[]{"The Cathedral of Learning", "Squirrel Hill", 
						"The Point", "Downtown"};
	void generateVisitor(){
		
	}
	
	public static void main(String[] args){
		System.out.println("Welcome to CitySim!  Your seed is " + args[0] + ".");
		Random rand = new Random();
		Visitor visitor = null;
		for(int i = 1; i <= 5; i++){
			int type = rand.nextInt(4);
			switch(type){
			case 0:
				visitor = new Student(i);
				break;
			case 1:
				visitor = new Business(i);
				break;
			case 2:
				visitor = new Professor(i);
				break;
			case 3:
				visitor = new Blogger(i);
				break;
			default:
				System.out.println("error");
			}
			int location = rand.nextInt(4);
			while(location != 4){
				visitor.visitCity(locations[location]);
				location = rand.nextInt(5);
			}
			visitor.leave();
			System.out.println("***");
		}
	}
}

abstract class Visitor{
	HashMap<String, Boolean> perference = new HashMap<String, Boolean>();
	int ID;
	public void visitCity(String City){
		System.out.println("Visitor " + ID + " is going to " + City + ".");
		if(perference.get(City)){
			System.out.println("Visitor " + ID + " did like " + City + ".");
		} else {
			System.out.println("Visitor " + ID + " did not like " + City + ".");
		}
		
	}
	
	public void leave(){
		System.out.println("Visitor " + ID + " has left the city.");
	}
}

class Student extends Visitor{
	public Student(int id){
		this.ID = id;
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
		System.out.println("Visitor " + id + " is a Blogger.");
		this.perference.put(CitySim9002.locations[0], false);
		this.perference.put(CitySim9002.locations[1], false);
		this.perference.put(CitySim9002.locations[2], false);
		this.perference.put(CitySim9002.locations[3], false);
	}
}

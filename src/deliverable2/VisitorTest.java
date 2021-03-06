package deliverable2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class VisitorTest {
	@Test
	public void testStudent(){
		Visitor a = Mockito.mock(Visitor.class);
		a = new Student(1);
		assertEquals(a.type(), "Student");
	}
	@Test
	public void testProfessor(){
		Visitor a = Mockito.mock(Visitor.class);
		a = new Professor(1);
		assertEquals(a.type(), "Professor");
	}
	@Test
	public void testBlogger(){
		Visitor a = Mockito.mock(Visitor.class);
		a = new Blogger(1);
		assertEquals(a.type(), "Blogger");
	}
	
	@Test
	public void testBusiness(){
		Visitor a = Mockito.mock(Visitor.class);
		a = new Business(1);
		assertEquals(a.type(), "Business");
	}
	
	@Test
	public void testVisitRight(){
		Visitor a = new Student(1);
		assertTrue(a.visitCity("The Cathedral of Learning"));
		assertTrue(a.visitCity("The Point"));
		assertTrue(a.visitCity("Squirrel Hill"));
		assertTrue(a.visitCity("Downtown"));
	}
	
	@Test
	public void testVisitError(){
		Visitor a = new Student(1);
		assertFalse(a.visitCity("The"));
		assertFalse(a.visitCity(null));
		assertFalse(a.visitCity(" "));
	}
	
	@Test
	public void testVisitorID(){
		Visitor a = new Student(1);
		assertEquals(a.Id(), 1);
	}
	
	@Test 
	public void testPerference(){
		Visitor stu = new Student(1);
		Visitor bus = new Business(2);
		Visitor blg = new Blogger(3);
		Visitor prof = new Professor(4);
		
		assertFalse(stu.perference("none"));
		assertFalse(bus.perference("none"));
		assertFalse(blg.perference("none"));
		assertFalse(prof.perference("none"));
		
		assertTrue(stu.perference("Downtown"));
		assertTrue(bus.perference("Downtown"));
		assertTrue(prof.perference("Downtown"));
		assertFalse(blg.perference("Downtown"));
	}
}

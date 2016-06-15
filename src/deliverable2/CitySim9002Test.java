package deliverable2;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.*;


public class CitySim9002Test {
	@SuppressWarnings("unchecked")
	
	@Mock
	CitySim9002 mockCity = Mockito.mock(CitySim9002.class);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(mockCity);		
	}
	
	@After
	public void tearDown() throws Exception {
		// any necessary teardown - none needed here
	}	
	//1 fake object. 
	@Test
	public void testAddVisitor(){
		Student mockStu = Mockito.mock(Student.class);
		mockCity.visitorlist = new LinkedList<Visitor>();
		mockCity.visitorlist.add(mockStu);
		assertEquals(mockCity.visitorlist.size(), 1);
	}
	//2 fake object and method
	@Test
	public void testStudentVisitRightLocation(){
		Student mockStu = Mockito.mock(Student.class);
		String msg = "Downtown";
		Mockito.when(mockStu.visitCity(msg)).thenReturn(true);
		Mockito.when(mockCity.visit(mockStu, msg)).thenCallRealMethod();
		assertTrue(mockCity.visit(mockStu, msg));
	}
	//3 fake object and method
	@Test
	public void testStudentVisitErrorLocation(){
		Student mockStu = Mockito.mock(Student.class);
		String msg = "error";
		Mockito.when(mockStu.visitCity(msg)).thenCallRealMethod();
		assertFalse(mockCity.visit(mockStu, msg));
	}
	
	
	//4 fake object and method
	@Test
	public void testRunTimes(){

		Mockito.when(mockCity.randomSelectType(new Random(9))).thenReturn(0);
		Mockito.when(mockCity.generateVisitor(0)).thenReturn(new Student(1));
		Mockito.when(mockCity.visit(new Student(1), CitySim9002.locations[0])).thenReturn(true);
		Mockito.when(mockCity.visit(new Student(1), CitySim9002.locations[1])).thenReturn(true);
		Mockito.when(mockCity.visit(new Student(1), CitySim9002.locations[2])).thenReturn(true);
		Mockito.when(mockCity.visit(new Student(1), CitySim9002.locations[3])).thenReturn(true);
		Mockito.when(mockCity.run(9)).thenCallRealMethod();
		int val = mockCity.run(9);
		assertEquals(val, 5);
		Mockito.verify(mockCity, Mockito.times(5)).generateVisitor(0);
	}
	
	//5 fake object
	@Test
	public void testCommend9(){
		Mockito.when(mockCity.commend(new String[]{"9"})).thenCallRealMethod();
	
	
		assertTrue(mockCity.commend(new String[]{"9"}));


	}
	//6 fake object
	@Test
	public void testCommend9a(){
		Mockito.when(mockCity.commend(new String[]{"9a"})).thenCallRealMethod();
		assertFalse(mockCity.commend(new String[]{"9a"}));
	}
	
	//7 fake object
	@Test
	public void TestCommend9andSpace(){
		Mockito.when(mockCity.commend(new String[]{"9", " "})).thenCallRealMethod();
		assertFalse(mockCity.commend(new String[]{"9", " "}));
	}
}

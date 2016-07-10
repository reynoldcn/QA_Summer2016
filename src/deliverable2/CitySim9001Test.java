package deliverable2;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

import static org.mockito.Mockito.*;


public class CitySim9001Test {
	
	//Double #1:
	//Test that the current location of drivers can 
	//be set by setLoc() and got by calling Loc();
	@Test
	public void testDriverLoc(){
		Hotel h = mock(Hotel.class);
		Driver d = new Driver(1);
		d.setLoc(h);
		assertEquals(d.Loc(), h);
	}
	
	//Double #2:
	//Test that Location.getVia() can return the street 
	//between input and itself if the street exists.
	@Test
	public void testGetVia(){
		Hotel h = new Hotel();
		Location l = mock(Location.class);
		h.connectNewLoc(l, "test");
		assertEquals(h.getVia(l), "test");
	}
	
	//Double #3
	//Test that Location.getVia() will return null if the 
	//input location is unaccessible.
	@Test
	public void testNotConnected(){
		Hotel h = new Hotel();
		Location l = mock(Location.class);
		assertEquals(h.getVia(l), null);
	}
	
	//Stubbing #1
	//Test that Driver.headTo() will return -1 
	//and will not head to if
	//the input is unaccessible
	@Test
	public void testNotHeadTo(){
		Driver d = new Driver(1);
		Location cl = mock(Location.class);
		Location nl = mock(Location.class);
		when(cl.getVia(any())).thenReturn(null);
		d.setLoc(cl);
		int i = d.headTo(nl);
		assertEquals(i, -1);
		assertFalse(d.Loc() == nl);
	}
	
	//Stubbing #2
	//Test that Driver.headTo() will return 0 if
	//the input is Outside City and set it as its
	//current location.
	@Test
	public void testHeadToOutside(){
		Driver d = new Driver(1);
		Location cl = mock(Location.class);
		d.setLoc(cl);
		Location nl = mock(Location.class);

		when(cl.getVia(nl)).thenReturn("Not Null");
		try{
			when(cl.getVia(nl)).thenThrow(new Exception());
		} catch (Exception e){
			System.out.println(cl.getVia(nl));
		}
		when(cl.name()).thenReturn("Library");
		try{
			when(cl.name()).thenThrow(new Exception());
		} catch (Exception e){
			System.out.println(cl.name());
		}
		when(nl.name()).thenReturn("Outside City");
		try{
			when(nl.name()).thenThrow(new Exception());
		} catch (Exception e){
			System.out.println(nl.name());
		}
		int i = d.headTo(nl);
		assertEquals(i, 0);
		assertTrue(d.Loc() == nl);
	}
	
//	//Stubbing #3
//	//Test that CitySim.headTo() will return false
//	//if the driver has gone
//	@Test
//	public void testEndIter(){
//		CitySim9001 exe = new CitySim9001();
//		Driver d = mock(Driver.class);
//		Location cl = mock(Location.class);
//
//		when(d.Loc()).thenReturn(cl);
//		when(cl.getVia(any())).thenReturn("Not Null");
//		when(d.headTo(any())).thenReturn(0);
//
//		boolean notGone = exe.headTo(d, new Random(), true);
//		assertEquals(notGone, false);
//	}
	
	//Stubbing #3
	//Test that the program will stop and return -1 
	//if wrong value is input.
	@Test
	public void testArgs(){
		CitySim9001 mockExe = mock(CitySim9001.class);
		String[] args = new String[]{"", ""};
		when(mockExe.run(args)).thenCallRealMethod();
		when(mockExe.commend(args)).thenReturn(false);
		int i = mockExe.run(args);
		assertEquals(i, -1);
	}
}

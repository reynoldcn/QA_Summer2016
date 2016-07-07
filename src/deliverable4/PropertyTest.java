package deliverable4;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

public class PropertyTest {
	public final int test_times = 100;
	
	//billify(), which is to be tested
	public static int[] billify(int[] x){
		int[] output = new int[x.length+1];
		int i = 0;
		int sum = 0;
		for(i = 0; i < x.length; i++){
			output[i] = x[i] * x[i];
			sum += output[i];
		}
		
		output[i] = sum;		
		return output;
	}
	//To generate a random array. 
	public static int[] generateArray(){
		Random rand = new Random();
		int size = rand.nextInt(9999) + 1;
		int[] array = new int[size];
		
		for(int i = 0; i < size; i++){
			array[i] = rand.nextInt(100) + 1;
		}	
		return array;
	}
	
	//Property 1.
	//The output array have one more
	//element than input array	
	@Test
	public void testOutputSize(){
		int i = 0;
		while(i++ < test_times){
			int[] input = generateArray();
			int[] output = billify(input);
			try{
				assertEquals(input.length+1, output.length);
			}catch(Exception e){
				fail();
			}
		}
 	}
	
	//Property 2.
	//Value in output array never decreasing
	@Test
	public void testDecreasing(){
		int i = 0;
		while(i++ < test_times){
			int[] input = generateArray();
			int[] output = billify(input);
			for(int j = 0; j < input.length; j++){
				try{
					assertFalse(output[j] < input[j]);
				}catch(Exception e){
					fail();
				}
			}
		}
	}
	
	//Property 3.
	//Pure - running it twice on the same input
	//input array results in same output
	//array
	@Test
	public void testPure(){
		int i = 0;
		while(i++ < test_times){
			int[] input = generateArray();
			int[] output1 = billify(input);
			int[] output2 = billify(input);
			
			try{
				assertEquals(output1.length, output2.length);
			}catch(Exception e){
				fail();
			}
				
			for(int j = 0; j < output1.length; j++){
				try{
					assertEquals(output1[j], output2[j]);
				}catch(Exception e){
					fail();
				}
			}
		}
	}
	
	
//	public static void main(String[] args){
//		int[] test = billify(new int[]{10,10,10});
//		for(int i = 0; i < test.length; i++){
//			System.out.println(test[i]);
//		}
//		
//		int[] array = generateArray();
//		for(int i = 0; i < array.length; i++){
//			System.out.print(array[i] + " ");
//			if((i+1)%8 == 0)
//				System.out.println();
//		}
//	}
}

package main.utilities;

import java.util.Random;

public final class MathUtils 

{
	public static int getRandomPosOrNeg(int min, int max)
	{
		return(Math.min(Math.max(min, min+new Random().nextInt(max-min)),max));
	}
	
	public static int getSumOfArray(int[] intarraylist)
	{
		int arrayLength = 0;
		for(int i = 0;i<intarraylist.length;i++)
		{
			arrayLength = arrayLength + intarraylist[i];
		}
		//System.out.println("the total length of this pattern is " +arrayLength);
		
		return arrayLength;
	}
	
}

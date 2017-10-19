package utilities;

import java.util.Random;

public final class MathUtils

{
	public static int getRandomPosOrNeg(int min, int max) {
		return (Math.min(Math.max(min, min + new Random().nextInt(max - min)), max));
	}

	public static int getSumOfArray(int[] intarraylist) {
		int arrayLength = 0;
		for (int i = 0; i < intarraylist.length; i++) {
			arrayLength = arrayLength + intarraylist[i];
		}
		//System.out.println("the total length of this pattern is " +arrayLength);

		return arrayLength;
	}

	public static int addaHalfstepMaybe(int in, boolean decider)
    {
		if (in % 2 == 0&&decider)
		{
			 in += in / 2;
			// System.out.println("randomized");
		}
		return in;
	}

	public static boolean isElementOf(int in, int[] collection)
	{
		boolean yup = false;
		for(int i:collection)
		{
			if(i==in)
			{
				return true;
			}
		}
		return yup;

	}
}


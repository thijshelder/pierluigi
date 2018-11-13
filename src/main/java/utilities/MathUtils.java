package utilities;

import java.util.Random;

public final class MathUtils

{
	private MathUtils()
	{}

	public static int getRandomPosOrNeg(int min, int max) {
		return (Math.min(Math.max(min, min + new Random().nextInt(max - min)), max));
	}

	public static int getSumOfArray(int[] intarraylist) {
		int arrayLength = 0;
		for (int i = 0; i < intarraylist.length; i++) {
			arrayLength = arrayLength + intarraylist[i];
		}
		return arrayLength;
	}

	public static int addaHalfstepMaybe(int in, boolean decider)
    {
		if (in % 2 == 0&&decider)
		{
			 in += in / 2;
		}
		return in;
	}


	public static double returnSigmoid(double input, double slope)
	{
         return 1/(1-Math.pow(Math.E, (-input/slope)));
	}

	public static boolean isElementOf(int in, int[] collection)
	{
		for(int i:collection)
		{
			if(i==in)
			{
				return true;
			}
		}
		return false;

	}
}


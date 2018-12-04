package musicalintelligence.theneuronet.neurutilities.mathutilities;


import java.util.Random;

public class Statistics
{
    public static Double distributionFunction(Double genome)
    {
        return genome +(Math.pow(new Random().nextDouble(), 4));
    }

    public static boolean equalsApprox(double value, double othervalue, double fault)
    {
        return (othervalue>=value-fault&&othervalue<value+fault);
    }
}

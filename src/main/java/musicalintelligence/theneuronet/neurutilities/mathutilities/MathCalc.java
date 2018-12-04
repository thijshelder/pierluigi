package musicalintelligence.theneuronet.neurutilities.mathutilities;

import java.util.Arrays;

public class MathCalc
{
    public double funcCalc(double a, double b, MathFunc c)
    {
        return c.calculate(a, b);
    }

    public MathFunc sigmoid = (a,b) -> 1/(1+(Math.pow(Math.E, -a)));

    public double returnSigmoid(double a,double b)
    {
        return funcCalc(a, b, sigmoid);
    }

    public static double product(Double[] doubles)
    {
        double p = 1.0;
        for(Double d: doubles)
        {
            p=p*d;
        }
        return p;
    }

    public static double arraySum(Double[] doubles)
    {
       return Arrays.stream(doubles).mapToDouble(i->i).sum();
    }

    public static Double[][] createZeroFilledArray(int size)
    {
        Double[][] args = new Double[size][2];
        for (int i = 0;i<size;i++)
        {
            args[i] = new Double[]{0.0,0.0};
        }
        return args;
    }
}

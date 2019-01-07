package utilities;

public class ArrayUtils
{
    public Double[] convertToObjectArray(double[] d)
    {
        Double[] newDoubleArray = new Double[d.length];
        for(int i=0;i<d.length;i++)
        {
            newDoubleArray[i] = d[i];
        }
        return newDoubleArray;
    }
}

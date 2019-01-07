package musicalintelligence.theneuronet;

import musicalintelligence.theneuronet.coreconcept.NetWork;


import java.util.Arrays;

public class NeuroConnector
{
    private final NetWork work;
    private final Double[] doubles;
    private static NeuroConnector Instance;

    public static NeuroConnector getInstance(int layers, int arraySize)
    {
        if(Instance==null)
        {
            Instance =  new NeuroConnector(layers, arraySize);
        }
        return Instance;
    }

    private NeuroConnector(int layers, int arraySize)
    {
        this.work = new NetWork(layers, arraySize);
        doubles=new Double[arraySize];
    }

    public int[] calcNetwork(Double[] doubleInput)
    {
        work.calculate(doubleInput);
        Double[] doubles = work.getOutPut();

        return Arrays.asList(doubles).stream().mapToInt
                (d->(int) Math.round(d))
                .toArray();

    }
}

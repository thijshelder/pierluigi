package musicalintelligence.theneuronet.neurutilities.mathutilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeuroMatrix
{
    private final Map<Integer,Double[]> matrix = new HashMap<>();

    public NeuroMatrix(int size)
    {
      this(MathCalc.createZeroFilledArray(size));
    }

    public Map<Integer, Double[]> getNeuroMatrix()
    {
        return matrix;
    }

    private Double[] getMappedRows(int index)
    {
        return matrix.get(index);
    }

    private NeuroMatrix(Double[][] args)
    {
        for(int i = 0;i<args.length; i++)
        {
           matrix.put(i, args[i]);
        }


    }

    public Double[] displayRow(int index)
    {
       return matrix.get(index);
    }

    public NeuroMatrix multiplyMatrices(NeuroMatrix anotherMatrix)
    {
        return this;
    }

    public int getVertMatrixSize()
    {
        return matrix.size();
    }

    public void updateByIndex(int index, double value, double weight)
    {
        getNeuroMatrix().get(index)[0] = value;
        getNeuroMatrix().get(index)[1] = weight;
    }

    public List<Double[]> getRowsAsList()
    {
        List<Double[]> rows = new ArrayList<>();

        for(int i=0;i<matrix.size();i++)
        {
            rows.add(getMappedRows(i));
        }
        return rows;
    }
}

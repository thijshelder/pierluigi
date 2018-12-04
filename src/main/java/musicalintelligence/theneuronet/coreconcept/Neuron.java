package musicalintelligence.theneuronet.coreconcept;

import musicalintelligence.theneuronet.neurutilities.mathutilities.MathCalc;
import musicalintelligence.theneuronet.neurutilities.mathutilities.NeuroMatrix;

import java.util.Arrays;
import java.util.Comparator;

public class Neuron {
    double output = 0.0;
    NeuroMatrix matrix;

    public Neuron(int size)
    {
        matrix = new NeuroMatrix((size));
    }

    public Double[] getRow(int rowIndex)
    {
        return matrix.displayRow(rowIndex);
    }

    public void triggerIn(int index, double value, double weight)
    {
        matrix.updateByIndex(index, value, weight);
    }

    public void displayMatrix()
    {
        matrix.getNeuroMatrix().forEach((k, v) ->
        {
            System.out.println(Arrays.toString(v));
        });
    }

     public Double[] activate()
    {

        matrix.getNeuroMatrix().forEach((k,v)->
        {
            output += MathCalc.product(v);
            v[1] = new MathCalc().returnSigmoid(output, 1.0);
        });
        return matrix.getRowsAsList().stream().max(Comparator.comparing(i->i[1])).orElseThrow(NoSuchFieldError::new);
    }

    public Double getOutPut()
    {
        return  output;
    }



    public Double[] getValues()
    {
        Double[] values = new Double[matrix.getNeuroMatrix().size()];
        matrix.getNeuroMatrix().forEach((k,v)->values[k]= v[0]);
        return values;
    }
}

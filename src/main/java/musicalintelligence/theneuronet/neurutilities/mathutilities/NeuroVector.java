package musicalintelligence.theneuronet.neurutilities.mathutilities;

import musicalintelligence.theneuronet.neuroexception.VectorNotEqualSizeException;

import java.util.HashMap;
import java.util.Map;

/*This class holds doubles to create a vector.
*
 */
class NeuroVector
{
    private final Map<Integer,Double> vector = new HashMap<>();

    public NeuroVector(Double[] args)
    {
        for(int i = 0; i< args.length; i++)
        {
            vector.put(i, args[i]);
        }
    }

    public Number getInproduct(NeuroVector anotherVector)
    {
        if(this.getVertVectorSize()==anotherVector.getVertVectorSize())
        {
            Double activalue =  0.0;
            for(int i =0; i< vector.size();i++)
            {
                activalue+=(this.getRow(i)*anotherVector.getRow(i));
            }
            return activalue;
        }
        throw new VectorNotEqualSizeException("Cannot calculate inproduct of unequal vectors");
    }

       private Double getRow(int index)
    {
         return vector.get(index);
    }

      private int getVertVectorSize()
    {
        return vector.size();
    }
}

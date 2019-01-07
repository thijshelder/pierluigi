package musicalintelligence.theneuronet.coreconcept;

import musicalintelligence.theneuronet.fitness.algoritm.GenAlgorithm;
import musicalintelligence.theneuronet.fitness.algoritm.Individual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Layer
{
    private static int layerIndex =0;
    private final int index;
    final List<Neuron> neurons = new ArrayList<>();

    public Layer(int size, int neuronInputs)
    {
        for(int i=0; i<size;i++)
        {
            neurons.add(new Neuron(neuronInputs));
        }
        index = layerIndex;
        layerIndex++;
    }

    public void updateFirstLayer(Double[] inputs)
    {        //inputs should be one smaller than weights, for bias sake
         //each neuron should receive the whole inputs.
         neurons.forEach(n->
                         {
                             List<Double> inpList = Arrays.asList(inputs);
                             inpList.forEach(i->n.triggerIn(inpList.indexOf(i),inpList.get(inpList.indexOf(i)), 0.1));
                             n.activate();});
    }

    public void updateLayer(Layer anotherLayer)
    {
        for(Neuron neuron:neurons)
        {
            //input into this every neuron of this layer the outputs of the previous one.

            //this implies that the number of inputs of a neuron should be equal to the number of outputs of the previous layer.
            anotherLayer.neurons.forEach(n->{
                                              List<Double> inpList =Arrays.asList(anotherLayer.neurons.get(neurons.indexOf(neuron)).getValues());
                                              inpList.forEach(i->neuron.triggerIn(inpList.indexOf(i), inpList.get(inpList.indexOf(i)),calculateFitness(88.0)));
                                              neuron.activate();
                                            });
        }

    }

    public int getIndex()
    {
        return index;
    }

    private double calculateFitness(Double maxvalue)
    {
     GenAlgorithm algorithm = GenAlgorithm.getInstance();
     List<Double[]> genomes = new ArrayList<>();
     neurons.stream().forEach(n->genomes.add(neurons.get(neurons.indexOf(n)).getValues()));
     algorithm.prepare(genomes, Individual.maxvalue);
     return algorithm.engage(3).getRanking();
    }


}

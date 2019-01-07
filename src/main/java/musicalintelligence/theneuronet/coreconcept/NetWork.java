package musicalintelligence.theneuronet.coreconcept;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NetWork {
    private final List<Layer> layers = new ArrayList<>();

    public NetWork(int numberOfLayers, int inputArraySize) {
        for (int i = 0; i < numberOfLayers; i++) {
            layers.add(new Layer(inputArraySize, inputArraySize));
        }
    }

    public void calculate(Double[] inputs) {
        layers.forEach(l ->
        {
            if (l.getIndex() == 0) {
                l.updateFirstLayer(inputs);
            } else {
                l.updateLayer(layers.get(l.getIndex() - 1));
            }
        });
    }

    public Double[] getOutPut() {
        return layers.get(layers.size() - 1).neurons.stream().max(Comparator.comparing(Neuron::getOutPut)).map(Neuron::getValues).get();
    }
}
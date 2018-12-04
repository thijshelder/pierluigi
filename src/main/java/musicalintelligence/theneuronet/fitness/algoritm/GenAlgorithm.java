package musicalintelligence.theneuronet.fitness.algoritm;

import java.util.ArrayList;
import java.util.List;

public class GenAlgorithm
{
    Population population;
    int generations =0;
    int maxgenerations = 200;
    private static GenAlgorithm Instance;

    public static GenAlgorithm getInstance()
    {
        if(Instance==null)
        {
            Instance = new GenAlgorithm(200);
        }
        return Instance;
    }
    private GenAlgorithm(int populationSize)
    {
        population = new Population(populationSize,8, 78.0);
    }


    private GenAlgorithm()
    {}

    public Individual engage(int target )
    {
       population.individuals.forEach(i->i.initiate());
       while(generations<maxgenerations&&population.solutions.isEmpty())
        {
            population.setTarget(target);
            population.goAndMultiply();
            population.determineFittest();
            generations++;
        }
        if(!population.solutions.isEmpty())
        {
            System.out.println("found perfect solution ! "+ population.solutions.get(0).displayGenome() );

        }
        System.out.println(population.getFittest().displayGenomeAsString());
        return population.getFittest();
    }

    public void addIndividual(Double[] doubles)
    {
        population.individuals.add(new Individual(doubles));
    }

    public void prepare(List<Double[]> list, Double maxvalue )
    {
        List<Individual> indList = new ArrayList<>();
        for (Double[] d:list)
        {
            indList.add(new Individual(d));
        }
       population = new Population(indList, 88.0);
    }
}

package musicalintelligence.theneuronet.fitness.algoritm;

import java.util.ArrayList;
import java.util.List;

public class GenAlgorithm
{
    private Population population;
    private int generations =0;
    private final int maxgenerations = 500;
    private static GenAlgorithm Instance;

    public static GenAlgorithm getInstance()
    {
        if(Instance==null)
        {
            Instance = new GenAlgorithm(1000);
        }
        return Instance;
    }
    private GenAlgorithm(int populationSize)
    {
        population = new Population(populationSize,10, 78.0);
    }


    private GenAlgorithm()
    {}

    public Individual engage(int target )
    {
       population.individuals.forEach(Individual::initiate);
       while(generations<maxgenerations&&population.solutions.isEmpty())
        {
            population.setTarget(target);
            population.goAndMultiply();
            population.determineFittest();
            generations++;
        }
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
       population = new Population(indList, 78.0);
    }
}

package musicalintelligence.theneuronet.fitness.fitnesstools;

import musicalintelligence.theneuronet.fitness.algoritm.Individual;

public interface Scorer
{
    double score(Individual i);
}

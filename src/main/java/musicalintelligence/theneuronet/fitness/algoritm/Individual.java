package musicalintelligence.theneuronet.fitness.algoritm;

import musicalintelligence.theneuronet.neurutilities.mathutilities.MathCalc;
import musicalintelligence.theneuronet.neurutilities.mathutilities.Statistics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Individual implements Comparable<Individual>
{
     private double ranking =0.0;
     public static final double maxvalue = 88;
     private Double[] genome;

     public Individual(int size)
     {
         genome = new Double[size];
         for(int i =0;i<size;i++)
         {
             genome[i]= 0.0;
         }
     }

     public Individual(Double[] d)
     {
         this(d.length);
         this.setGenome(d);
     }

    public int getLength()
    {
        return genome.length;
    }

    public Double[] getGenome()
     {
         return genome;
     }

     public Double[][] splitGenome(int index) {
         Double[] part1 = new Double[index];
         Double[] part2 = new Double[genome.length - index];
         System.arraycopy(genome, 0, part1, 0, part1.length);
         System.arraycopy(genome, part1.length, part2, 0, part2.length);
         return new Double[][]{part1, part2};
     }

     public void initiate()
     {
            for(int i=0;i<genome.length;i++)
            {
                genome[i]=Math.max(0, Math.min(new Random().nextDouble()*maxvalue, maxvalue));
            }
     }

     public String displayGenome()
     {
         StringBuilder genomeString = new StringBuilder();
         for(Double d:getGenome())
         {
            genomeString.append(" ").append(d.toString());

         }
         return genomeString.toString();
     }

     public String displayGenomeAsString()
     {
         StringBuilder genomeString = new StringBuilder();
         for(Integer d: Arrays.asList(getGenome()).stream().mapToInt(d->(int)Math.round(d)).toArray())
         {
             genomeString.append(" ").append(d.toString());

         }
         return genomeString.toString();
     }

     public double genomeSum()
     {
         return MathCalc.arraySum(genome);
     }

     public void mutate()
     {//perform some randomized but statistic things on a random double in the array

         distributionFunction(new Random().nextInt(genome.length));
     }


     private void distributionFunction(int index)
     {
         genome[index] = Statistics.distributionFunction(genome[index]);
     }

     public void setRanking(double rank)
     {
         ranking=rank;
     }

    public Double getMaxValue()
    {
        return maxvalue;
    }

    public double getRanking()
     {
         return ranking;
     }

     public void setGenome(Double[] newGenome)
     {
         this.genome = newGenome;
     }

     public int compareTo(Individual anotherIndividual)

     {
      return (int) getRanking()*100- (int) anotherIndividual.getRanking()*100;
     }

     public static Comparator<Individual> individualsSorter = Individual::compareTo;


}


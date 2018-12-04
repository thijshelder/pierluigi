package musicalintelligence.theneuronet.fitness.algoritm;

import musicalintelligence.theneuronet.fitness.fitnesstools.ScoreUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population
{
    List<Individual> individuals = new ArrayList<>();
    List<Individual> solutions = new ArrayList<>();
    int target = 1;
    /* we are going to do something rather random: we say that fitness equals "the numbers in the genome added being closest to 24.
    / while we know that is not something useful, it will allow us to do some experimenting.
    */
   public Population(int size, int genomeSize, Double maxvalue)
    {
        for(int i=0;i<size;i++)
        {
            individuals.add(new Individual(genomeSize));
        }
        individuals.forEach(i->i.initiate());
    }

    public Population(List<Individual> preExisting, Double maxvalue)
    {
        this(preExisting.size(), preExisting.get(0).getLength(), maxvalue);
        individuals.addAll(preExisting);
    }

    public List<Individual> sexyTime(Individual anIndividual, Individual anotherIndividual){
        List<Individual> newIndividuals = new ArrayList<>();

        if(anIndividual.getLength()==anotherIndividual.getLength())
        {
            int index = Math.max(1, new Random().nextInt(anIndividual.getLength()));
            Double[][] ind1 = anIndividual.splitGenome(index);
            Double[][] ind2 = anotherIndividual.splitGenome(index);
            Double[] newGenome1 = Stream.concat(Arrays.stream(ind2[0]), Arrays.stream(ind1[1])).toArray(Double[]::new);
            Double[] newGenome2 = Stream.concat(Arrays.stream(ind1[0]), Arrays.stream(ind2[1])).toArray(Double[]::new);

            anIndividual.setGenome(newGenome1);
            anotherIndividual.setGenome((newGenome2));

       }
       newIndividuals.add(anIndividual);
       newIndividuals.add(anotherIndividual);
       return newIndividuals;

    }

    public void goAndMultiply()
    {
        determineFittest();
        individuals.stream().sorted(Individual::compareTo).collect(Collectors.toList());
        List<Individual> theSubOptimals = individuals.subList(2,individuals.size());
        List<Individual> hombres = theSubOptimals.subList(0, theSubOptimals.size()/2);
        List<Individual> mujeres = theSubOptimals.subList(theSubOptimals.size()/2, theSubOptimals.size());
        hombres.sort(Comparator.comparing(i->i.getRanking()));
        mujeres.sort(Comparator.comparing(i->i.getRanking()));

        hombres.stream().forEach(i-> {
            List<Individual> indList = sexyTime(i, mujeres.get(hombres.indexOf(i)));
            i = indList.get(0);
            Individual j = mujeres.get(hombres.indexOf(i));
            j = indList.get(1);
        });

        determineFittest();
        individuals.sort  (Comparator.comparingDouble(Individual::getRanking).reversed());
     }

     public void setTarget(int target)
     {
         this.target = target;
     }

    public void determineFittest()
    {
       individuals.forEach(i->
               {
                  score(i, target);
                  if(i.getRanking()==0.0d)
                  {
                      solutions.add(i);
                  }
               });
    }



    public Individual getFittest()
    {
        return individuals.get(0);
    }



    public void score(Individual individual, int target) {
        // 0 is best

        double score = new ScoreUtil().mayRepresentAValidChord(Arrays.asList(individual.getGenome()).stream().mapToInt(d->(int)Math.round(d)).toArray());
        score += new ScoreUtil().isEqualMode(target, Arrays.asList(individual.getGenome()).stream().mapToInt(d->(int)Math.round(d)).toArray());
        individual.setRanking(score);

        //
    }


}

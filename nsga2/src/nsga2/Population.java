package nsga2;

import java.util.HashMap;

public class Population {
 public static final int MAXSIZE=100;//种群数量
HashMap<Integer, Individual> map=new HashMap<Integer, Individual>();//用于存放该种群的所有个体
 
 
 public void init()
 {
	 for(int i=0;i<Population.MAXSIZE;i++)
	 {
		 Individual in= new Individual();
		 in.n=0;
		 in.calfitness1();
		 in.calfitness2();
		map.put(i, in);
	 }
 }
 
 
 
}

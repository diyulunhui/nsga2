package nsga2;

import java.util.ArrayList;


@SuppressWarnings("rawtypes")
public  class Individual implements Comparable {
	

	//多目标优化，这是适应度
	double fitness1=Math.random()*10;
	double fitness2=Math.random()*10;
	public int n=0;//种群中有多少个体支配该个体
	ArrayList<Individual> dominate=new ArrayList<Individual>();//用来存放该个体支配的个体

	double distance=0;//解的距离
	
	
	Individual()
	{
		
	}
	
	
	
	 void calfitness1()
	{
		
	}
	
	 void calfitness2()
	{
		
	}



	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}

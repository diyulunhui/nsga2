package nsga2;

import java.util.ArrayList;


@SuppressWarnings("rawtypes")
public  class Individual implements Comparable {
	

	//��Ŀ���Ż���������Ӧ��
	double fitness1=Math.random()*10;
	double fitness2=Math.random()*10;
	public int n=0;//��Ⱥ���ж��ٸ���֧��ø���
	ArrayList<Individual> dominate=new ArrayList<Individual>();//������Ÿø���֧��ĸ���

	double distance=0;//��ľ���
	
	
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

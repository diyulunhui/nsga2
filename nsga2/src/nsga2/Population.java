package nsga2;

import java.util.HashMap;

public class Population {
 public static final int MAXSIZE=100;//��Ⱥ����
HashMap<Integer, Individual> map=new HashMap<Integer, Individual>();//���ڴ�Ÿ���Ⱥ�����и���
 
 
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

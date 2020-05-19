package nsga2;

import java.util.ArrayList;

public class Main {


public static void main(String[] args) {
	Population p1=new Population();
	Population p2=new Population();
	p1.init();
	p2.init();
	
	/*ArrayList<Individual> p=NSGA2.nsga2_ReturnsTheSolutionOfTheFirstRank(p2, p1);
	for(int i=0;i<p.size();i++)
	{
		System.out.println(p.get(i).fitness1+"  "+p.get(i).fitness2);
	}*/
	
	p2=NSGA2.nsga2(p1,p2);
	for(int i=0;i<Population.MAXSIZE;i++)
	{
		System.out.println(p2.map.get(i).fitness1+"  "+p2.map.get(i).fitness2);
	}
}
 
}

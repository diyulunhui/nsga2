package nsga2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class NSGA2 {

	public static Population nsga2(Population father,Population child)
{    //两代种群的所有个体都参与筛选
		HashMap<Integer,Individual> fatherAndChild=new HashMap<Integer, Individual>();
		fatherAndChild.putAll(father.map);
		for(int i=0;i<Population.MAXSIZE;i++)
		{
			fatherAndChild.put(i+100, child.map.get(i));
		}
		
		//对每个个体进行处理，找出被它支配和支配它的个体
	for(int i=0;i<Population.MAXSIZE*2;i++)
	{
		Individual in=fatherAndChild.get(i);
		in.n=0;
		in.dominate.clear();
		for(int j=0;j<Population.MAXSIZE*2;j++)
		{
			Individual o=fatherAndChild.get(j);
			if((in.fitness1>o.fitness1)&&(in.fitness2>o.fitness2))//找出in支配的个体放入dominate中
			{
				in.dominate.add(o);
			}
			if((in.fitness1<o.fitness1)&&(in.fitness2<o.fitness2))//碰上一个支配in的个体就++n
			{
				in.n++;
			}
		}
		
	}
	
	ArrayList<ArrayList<Individual>> rank=new ArrayList<ArrayList<Individual>>();//对种群进行分级别
	//外层的ArrayList是存放每一个rank，内层的ArrayList是存放该rank对应的个体
	
	while(!fatherAndChild.isEmpty())//本函数将所有个体进行分级，分级后放入rank数组中
	{
		ArrayList<Individual> po=new ArrayList<Individual>();//用来存放第x rank的个体，这里的x是while的第x个循环
		for(int i=0;i<Population.MAXSIZE*2;i++)//对哈希表中的每个个体进行遍历
		{
			Individual indi=fatherAndChild.get(i);
			if(indi==null)continue;
			if( indi.n==0)//如果发现该个体的n为0，即没有支配该个体的其他个体
			{
				po.add(indi);//将该个体放入第n rank的集合中
				fatherAndChild.remove(i);//把该个体从哈希表中去除
				for(int j=0;j<indi.dominate.size();j++)//所有被该个体支配的个体n都减1
				{
					--indi.dominate.get(j).n;
					
				}
			}
		}
		    rank.add(po);//该rank的所有个体都放入ArrayList后将该ArrayList放入对应rank的位置
	}
	
	//查找哪个rank放入新生一代之后会超出MAXSIZE
	int num=Population.MAXSIZE;
	int index=0;//这是用来指示第几rank的
	while(true)
	{
		if(num-rank.get(index).size()>0)
		{
			num=num-rank.get(index).size();
			++index;
			
		}
		else
		{
			break;
		}
	}
	//找出该rank
	ArrayList<Individual> po=rank.get(index);
	//对该rank根据fitness1的大小进行排序，根据fitness2也可以
	po.sort(new Comparator<Individual>()
			{

				@Override
				public int compare(Individual o1, Individual o2) {
					// TODO Auto-generated method stub
					int i=o1.fitness1>o2.fitness1?1:-1;
					return i;
				}
		
			});
	
	//对该rank的每一个个体计算其distance
	for(int i=0;i<po.size();i++)
	{
		if(i==0||i==po.size()-1)
		{
			po.get(i).distance=Double.MAX_VALUE;
		}
		else
		{
		po.get(i).distance=(Math.abs(po.get(i-1).fitness1-po.get(i+1).fitness1)+Math.abs(po.get(i-1).fitness2-po.get(i+1).fitness2));
		}
	}
	
	//对该rank根据distance大小进行排序
	po.sort(new Comparator<Individual>()
	{

		@Override
		public int compare(Individual o1, Individual o2) {
			// TODO Auto-generated method stub
			int i=o1.distance>o2.distance?-1:1;
			return i;
		}
		
	});
	//将所有选中的个体放入新的ArrayList中
	ArrayList<Individual> newpo=new ArrayList<Individual>();
	for(int i=0;i<index;i++)
	{
		newpo.addAll(rank.get(i));
		
	}
	//同上
	for(int i=0;i<num;i++)
	{
		newpo.add(po.get(i));
	}
	//创建一个种群，将刚才筛选出的所有个体作为该种群的组成部分
	Population pp=new Population();
	for(int i=0;i<Population.MAXSIZE;i++)
	{
		pp.map.put(i, newpo.get(i));
	}
	return pp;//返回该种群
	
}
	
	
	
	
	
	
	
	
	
	public static ArrayList<Individual> nsga2_ReturnsTheSolutionOfTheFirstRank(Population father,Population child)
	{    //两代种群的所有个体都参与筛选
			HashMap<Integer,Individual> map=new HashMap<Integer, Individual>();
			map.putAll(father.map);
			for(int i=0;i<Population.MAXSIZE;i++)
			{
				map.put(i+100, child.map.get(i));
			}
			
			//对每个个体进行处理，找出被它支配和支配它的个体
		for(int i=0;i<Population.MAXSIZE*2;i++)
		{
			Individual in=map.get(i);
			in.n=0;
			in.dominate.clear();
			for(int j=0;j<Population.MAXSIZE*2;j++)
			{
				Individual o=map.get(j);
				if((in.fitness1>o.fitness1)&&(in.fitness2>o.fitness2))//找出in支配的个体放入dominate中
				{
					in.dominate.add(o);
				}
				if((in.fitness1<o.fitness1)&&(in.fitness2<o.fitness2))//碰上一个支配in的个体就++n
				{
					in.n++;
				}
			}
			
		}
		
		ArrayList<ArrayList<Individual>> rank=new ArrayList<ArrayList<Individual>>();//对种群进行分级别
		//外层的ArrayList是存放每一个rank，内层的ArrayList是存放该rank对应的个体
		
		while(!map.isEmpty())//本函数用来将哈希表中的所有个体进行分级，分级后放入rank中
		{
			ArrayList<Individual> po=new ArrayList<Individual>();//用来存放第x rank的个体，这里的x是while的第x个循环
			for(int i=0;i<Population.MAXSIZE*2;i++)//对哈希表中的每个个体进行遍历
			{
				Individual indi=map.get(i);
				if(indi==null)continue;
				if( indi.n==0)//如果发现该个体的n为0，即没有支配该个体的其他个体
				{
					po.add(indi);//将该个体放入第n rank的集合中
					map.remove(i);//把该个体从哈希表中去除
					for(int j=0;j<indi.dominate.size();j++)//所有被该个体支配的个体n都减1
					{
						--indi.dominate.get(j).n;
						
					}
				}
			}
			    rank.add(po);
		}
		//返回第0rank的集合
		return rank.get(0);
	
		
	}
}

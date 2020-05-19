package nsga2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class NSGA2 {

	public static Population nsga2(Population father,Population child)
{    //������Ⱥ�����и��嶼����ɸѡ
		HashMap<Integer,Individual> fatherAndChild=new HashMap<Integer, Individual>();
		fatherAndChild.putAll(father.map);
		for(int i=0;i<Population.MAXSIZE;i++)
		{
			fatherAndChild.put(i+100, child.map.get(i));
		}
		
		//��ÿ��������д����ҳ�����֧���֧�����ĸ���
	for(int i=0;i<Population.MAXSIZE*2;i++)
	{
		Individual in=fatherAndChild.get(i);
		in.n=0;
		in.dominate.clear();
		for(int j=0;j<Population.MAXSIZE*2;j++)
		{
			Individual o=fatherAndChild.get(j);
			if((in.fitness1>o.fitness1)&&(in.fitness2>o.fitness2))//�ҳ�in֧��ĸ������dominate��
			{
				in.dominate.add(o);
			}
			if((in.fitness1<o.fitness1)&&(in.fitness2<o.fitness2))//����һ��֧��in�ĸ����++n
			{
				in.n++;
			}
		}
		
	}
	
	ArrayList<ArrayList<Individual>> rank=new ArrayList<ArrayList<Individual>>();//����Ⱥ���зּ���
	//����ArrayList�Ǵ��ÿһ��rank���ڲ��ArrayList�Ǵ�Ÿ�rank��Ӧ�ĸ���
	
	while(!fatherAndChild.isEmpty())//�����������и�����зּ����ּ������rank������
	{
		ArrayList<Individual> po=new ArrayList<Individual>();//������ŵ�x rank�ĸ��壬�����x��while�ĵ�x��ѭ��
		for(int i=0;i<Population.MAXSIZE*2;i++)//�Թ�ϣ���е�ÿ��������б���
		{
			Individual indi=fatherAndChild.get(i);
			if(indi==null)continue;
			if( indi.n==0)//������ָø����nΪ0����û��֧��ø������������
			{
				po.add(indi);//���ø�������n rank�ļ�����
				fatherAndChild.remove(i);//�Ѹø���ӹ�ϣ����ȥ��
				for(int j=0;j<indi.dominate.size();j++)//���б��ø���֧��ĸ���n����1
				{
					--indi.dominate.get(j).n;
					
				}
			}
		}
		    rank.add(po);//��rank�����и��嶼����ArrayList�󽫸�ArrayList�����Ӧrank��λ��
	}
	
	//�����ĸ�rank��������һ��֮��ᳬ��MAXSIZE
	int num=Population.MAXSIZE;
	int index=0;//��������ָʾ�ڼ�rank��
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
	//�ҳ���rank
	ArrayList<Individual> po=rank.get(index);
	//�Ը�rank����fitness1�Ĵ�С�������򣬸���fitness2Ҳ����
	po.sort(new Comparator<Individual>()
			{

				@Override
				public int compare(Individual o1, Individual o2) {
					// TODO Auto-generated method stub
					int i=o1.fitness1>o2.fitness1?1:-1;
					return i;
				}
		
			});
	
	//�Ը�rank��ÿһ�����������distance
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
	
	//�Ը�rank����distance��С��������
	po.sort(new Comparator<Individual>()
	{

		@Override
		public int compare(Individual o1, Individual o2) {
			// TODO Auto-generated method stub
			int i=o1.distance>o2.distance?-1:1;
			return i;
		}
		
	});
	//������ѡ�еĸ�������µ�ArrayList��
	ArrayList<Individual> newpo=new ArrayList<Individual>();
	for(int i=0;i<index;i++)
	{
		newpo.addAll(rank.get(i));
		
	}
	//ͬ��
	for(int i=0;i<num;i++)
	{
		newpo.add(po.get(i));
	}
	//����һ����Ⱥ�����ղ�ɸѡ�������и�����Ϊ����Ⱥ����ɲ���
	Population pp=new Population();
	for(int i=0;i<Population.MAXSIZE;i++)
	{
		pp.map.put(i, newpo.get(i));
	}
	return pp;//���ظ���Ⱥ
	
}
	
	
	
	
	
	
	
	
	
	public static ArrayList<Individual> nsga2_ReturnsTheSolutionOfTheFirstRank(Population father,Population child)
	{    //������Ⱥ�����и��嶼����ɸѡ
			HashMap<Integer,Individual> map=new HashMap<Integer, Individual>();
			map.putAll(father.map);
			for(int i=0;i<Population.MAXSIZE;i++)
			{
				map.put(i+100, child.map.get(i));
			}
			
			//��ÿ��������д����ҳ�����֧���֧�����ĸ���
		for(int i=0;i<Population.MAXSIZE*2;i++)
		{
			Individual in=map.get(i);
			in.n=0;
			in.dominate.clear();
			for(int j=0;j<Population.MAXSIZE*2;j++)
			{
				Individual o=map.get(j);
				if((in.fitness1>o.fitness1)&&(in.fitness2>o.fitness2))//�ҳ�in֧��ĸ������dominate��
				{
					in.dominate.add(o);
				}
				if((in.fitness1<o.fitness1)&&(in.fitness2<o.fitness2))//����һ��֧��in�ĸ����++n
				{
					in.n++;
				}
			}
			
		}
		
		ArrayList<ArrayList<Individual>> rank=new ArrayList<ArrayList<Individual>>();//����Ⱥ���зּ���
		//����ArrayList�Ǵ��ÿһ��rank���ڲ��ArrayList�Ǵ�Ÿ�rank��Ӧ�ĸ���
		
		while(!map.isEmpty())//��������������ϣ���е����и�����зּ����ּ������rank��
		{
			ArrayList<Individual> po=new ArrayList<Individual>();//������ŵ�x rank�ĸ��壬�����x��while�ĵ�x��ѭ��
			for(int i=0;i<Population.MAXSIZE*2;i++)//�Թ�ϣ���е�ÿ��������б���
			{
				Individual indi=map.get(i);
				if(indi==null)continue;
				if( indi.n==0)//������ָø����nΪ0����û��֧��ø������������
				{
					po.add(indi);//���ø�������n rank�ļ�����
					map.remove(i);//�Ѹø���ӹ�ϣ����ȥ��
					for(int j=0;j<indi.dominate.size();j++)//���б��ø���֧��ĸ���n����1
					{
						--indi.dominate.get(j).n;
						
					}
				}
			}
			    rank.add(po);
		}
		//���ص�0rank�ļ���
		return rank.get(0);
	
		
	}
}

import java.util.ArrayList;


public class NeedlemanWunch {
	
	private StringBuilder str1,str2;
	private Data matrix[][];
	private int match,miss,gap;
	private int satir=0,sutun=0;
	private StringBuilder Center=new StringBuilder();
	public NeedlemanWunch(StringBuilder s1,StringBuilder s2,int _match,int _miss,int _gap)
	{
		str1=s1;
		str2=s2;
		match=_match;
		miss=_miss;
		gap=_gap;
		sutun=str1.length()+1;
		satir=str2.length()+1;
		str1.insert(0,"-");
		str2.insert(0,"-");
		matrix=new Data[satir][sutun];
		for(int i=0;i<satir;i++) for(int j=0;j<sutun;j++) matrix[i][j]=new Data();
		
		for(int k=0;k<satir;k++)
			{
					
					matrix[k][0].setData(k*gap);
					matrix[k][0].setUst(true);
					
			}
		for(int k=0;k<sutun;k++) 
			{
					
			        matrix[0][k].setData(k*gap);
			        matrix[0][k].setSol(true);
			       // System.out.println(matrix[0][k].getData());
			}
		buildMatrix();
			
	}	
	public Data[][] buildMatrix()
	{
		int max_score=0;
		int new_Match=0,new_Sol=0,new_Ust=0;
		
		for(int i=1;i<satir;i++)
		{
			for(int j=1;j<sutun;j++)
			{
				
				if(str1.charAt(j)==str2.charAt(i)) // Match Durumu
				{
				    new_Match=matrix[i-1][j-1].getData()+match;
					new_Sol=matrix[i][j-1].getData()+gap;
					new_Ust=matrix[i-1][j].getData()+gap;
					max_score=Math.max(new_Match,Math.max(new_Sol, new_Ust));
					
					if(max_score==new_Match)
					{
						matrix[i][j].setData(new_Match);
						matrix[i][j].setCapraz(true);
					}
					if(max_score==new_Sol)
					{
						matrix[i][j].setData(new_Sol);
						matrix[i][j].setSol(true);
					}
					if(max_score==new_Ust)
					{
						matrix[i][j].setData(new_Ust);
						matrix[i][j].setUst(true);
					}
				}
				else //Miss Match Durumu
				{
					new_Match=matrix[i-1][j-1].getData()+miss;
					new_Sol=matrix[i][j-1].getData()+gap;
					new_Ust=matrix[i-1][j].getData()+gap;
					max_score=Math.max(new_Match,Math.max(new_Sol, new_Ust));
					if(max_score==new_Match)
					{
						matrix[i][j].setData(new_Match);
						matrix[i][j].setCapraz(true);
					}
					if(max_score==new_Sol)
					{
						matrix[i][j].setData(new_Sol);
						matrix[i][j].setSol(true);
					}
					if(max_score==new_Ust)
					{
						matrix[i][j].setData(new_Ust);
						matrix[i][j].setUst(true);
					}
				}
				
			}
		}
		
		
		
		
		return matrix;
	}
	
	public int scoreCalculate()
	{
		int i=satir-1,j=sutun-1;
		int score=0;
		StringBuilder s1=new StringBuilder("");
		StringBuilder s2=new StringBuilder("");
		
		while(i!=0 || j!=0)
		{
			if(matrix[i][j].getCapraz())
			{
				s1.append(str1.charAt(j));
				s2.append(str2.charAt(i));
				if(str1.charAt(j)==str2.charAt(i))
				{
					
					score+=match;
					//System.out.println("match:"+score);
				}
				else {
					score+=miss;
					//System.out.println("miss "+score);
				}
				i--;
				j--;
			}
			else if(matrix[i][j].getUst())
			{
				s1.append("-");
				s2.append(str2.charAt(i));
				score+=gap;
				i--;
			}
			else if(matrix[i][j].getSol())
			{
				s1.append(str1.charAt(j));
				s2.append("-");			
				score+=gap;	
				j--;
			}
			
			
			
		}
		
		
		return score;
	}
	
	public ArrayList<StringBuilder> starAlgorithm(ArrayList<StringBuilder> preAligned)
	{
		int i=satir-1,j=sutun-1;
		StringBuilder newStar_center=new StringBuilder("");
		StringBuilder new_String=new StringBuilder("");
		while(i!=0 || j!=0)
		{
			if(matrix[i][j].getCapraz())
			{
				newStar_center.append(str1.charAt(j));
				new_String.append(str2.charAt(i));
				i--;
				j--;
			}
			else if(matrix[i][j].getUst())
			{
				newStar_center.append("-");
				new_String.append(str2.charAt(i));
				for(int k=0;k<preAligned.size();k++)
				{
				//	System.out.println(""+preAligned.get(index));
					preAligned.get(k).insert(j,"-");
				}
				i--;
			}
			else if(matrix[i][j].getSol())
			{
				newStar_center.append(str1.charAt(j));
				new_String.append("-");			
					
				j--;
			}
			
			
			
		}
		
		Center=newStar_center.reverse();
		System.out.println("Center:"+Center);
		preAligned.add(new_String);
		
		return preAligned;
	}
	
	public StringBuilder newCenter()
	{
		return Center;
	}
	

}

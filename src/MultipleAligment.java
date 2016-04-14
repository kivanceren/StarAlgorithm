import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MultipleAligment {

	public static void main(String[] args) {
		File file = new File("dosya.txt");
		FileReader fileReader = null;
		String line;
		BufferedReader br ;
		int match=0,miss=0,gap=0;
		boolean durum=true;
		ArrayList<StringBuilder> strings=new ArrayList<>();
		int sumScore[];
		String[] skor_Matris = null;
		try {
			 	fileReader = new FileReader(file);
				br = new BufferedReader(fileReader);
				while ((line = br.readLine()) != null) {
															if(durum)
															{
																skor_Matris=line.split(" ");
																match=Integer.parseInt(skor_Matris[0]);
																miss=Integer.parseInt(skor_Matris[1]);
																gap=Integer.parseInt(skor_Matris[2]);
																durum=false;
															}
															else
															{
																strings.add(new StringBuilder(line));
															}
													   }
			    br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Skorlar Belirleniyor.
		sumScore=new int[strings.size()];
		for(int i=0;i<strings.size();i++)
		{
			StringBuilder string=new StringBuilder(strings.get(i));
			NeedlemanWunch nw;
			for(int j=0;j<strings.size();j++)
			{
				nw=new NeedlemanWunch(string,strings.get(j),match,miss,gap);
				sumScore[i]+=nw.scoreCalculate();
				string.deleteCharAt(0);
				strings.get(j).deleteCharAt(0);
			}
			
		}
		
		//Max Score belirlenip Star Merkezi seçiliyor.
		int max=sumScore[0];
		int index=0;
		for(int i=1;i<strings.size();i++)
		{
			if(sumScore[i]>max)
			{
				max=sumScore[i];
				index=i;
			}
		}
		
		//Çoklu Hizalama Çalışacak.
		
		System.out.println("Play...");
		ArrayList<StringBuilder> preAligned=new ArrayList<>();
		StringBuilder starCenter=new StringBuilder();
		starCenter.append(strings.get(index));
		System.out.print("star:"+starCenter+"\n");
		for(int i=0;i<strings.size();i++)
		{
			if(strings.get(index)!=strings.get(i)){
			NeedlemanWunch nw=new NeedlemanWunch(starCenter,strings.get(i),match,miss,gap);
			preAligned=nw.starAlgorithm(preAligned);
			starCenter=nw.newCenter();
			//System.out.println(starCenter+" is Center");
			strings.get(i).deleteCharAt(0);
			
			}
		}
		System.out.print("star:"+starCenter+"\n");
		preAligned.add(starCenter);
		for(int i=0;i<preAligned.size()-1;i++)
			System.out.println(preAligned.get(i).reverse());
		System.out.println(preAligned.get(preAligned.size()-1));
		
		
	}

}

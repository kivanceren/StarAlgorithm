
public class Data {
		
		private int data;
		private boolean capraz,ust,sol;
		
		public Data()
		{
			data=0;
			capraz=ust=sol=false;
		}
		public void setData(int d)
		{
			data=d;
		}
		public int getData()
		{
			return data;
		}
		public void setCapraz(boolean c)
		{
			capraz=c;
		}
		public boolean getCapraz()
		{
			return capraz;
		}
		public void setUst(boolean u)
		{
			ust=u;
		}
		public boolean getUst()
		{
			return ust;
		}
		public void setSol(boolean s)
		{
			sol=s;
		}
		public boolean getSol()
		{
			return sol;
		}

}

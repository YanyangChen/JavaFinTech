
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.*;
import weka.core.Instances;
public class URLDemo
{
	static float[] stocktotal = new float[10];
	public static void main(String [] args) throws InterruptedException

	{
			try
		{
	while(true)
	{
		URL url = new URL("http://finance.yahoo.com/d/quotes.csv?s=0002.HK+0700.HK+0066.HK+68789.HK&f=snd1l1yr");
		//URL url = new URL("http://ichart.finance.yahoo.com/table.txt?s=WU&a=11&b=15&c=2015&d=11&e=19&f=2016&g=d&ignore=.csv");
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if(urlConnection instanceof HttpURLConnection)
				{
					connection = (HttpURLConnection) urlConnection;
				}
			else
			{
				System.out.println("Please enter an HTTP URL.");
				return;
			}
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String urlString = "";
		List<String> lines= new ArrayList<String>();
		String current;
		//System.out.println(in.readLine()); //BufferedReader cannot read ichart line
		//System.out.println(in.readLine()); //BufferedReader cannot read ichart line
		//System.out.println(in.readLine()); //BufferedReader cannot read ichart line
		while((current = in.readLine()) != null)
		{
			lines.add(current);

			urlString += current;
			urlString += "\n";
		}
//System.out.println(urlString);
//System.out.println(lines.get(1));

for (int i = 0; i < lines.size(); i++){
	System.out.println(lines.get(i)+ "\n");
	String[] seg = lines.get(i).split(",");
	System.out.println(seg[3]);
	float number = Float.parseFloat(seg[3]);
	System.out.println("The stock price is: " + number);
	if (i == 1)
	{
		 stocktotal[i] = number * 1400;
		System.out.println("The stocktotal is: " + stocktotal[i]);
	}
	
	if (i == 0)
	{
		 stocktotal[i] = number * 0;
		System.out.println("The stocktotal is: " + stocktotal[i]);
	}
	
	if (i == 2)
	{
		 stocktotal[i] = number * 0;
		System.out.println("The stocktotal is: " + stocktotal[i]);
	}
	
	  // out.write("The number: " + subtr.get(i) + "\n");
	}
float sum = stocktotal[0]+ stocktotal[1]+ stocktotal[2];
System.out.println("SUM is :" + sum);
TimeUnit.MINUTES.sleep(1);
//URL infourl = new URL("http://so.eastmoney.com/Search.htm?q=00700%2b%u817e%u8baf%u63a7%u80a1&m=0&t=2");
////URL url = new URL("http://ichart.finance.yahoo.com/table.txt?s=WU&a=11&b=15&c=2015&d=11&e=19&f=2016&g=d&ignore=.csv");
//URLConnection infourlConnection = infourl.openConnection();
//HttpURLConnection infoconnection = null;
//if(infourlConnection instanceof HttpURLConnection)
//{
//	infoconnection = (HttpURLConnection) infourlConnection;
//}
//else
//{
//System.out.println("Please enter an HTTP URL.");
//return;
//}
//
//
//
//BufferedReader info = new BufferedReader(new InputStreamReader(infoconnection.getInputStream()));
//String urlinfo = "";
//List<String> infolines= new ArrayList<String>();
//String infocurrent;
//while((infocurrent = info.readLine()) != null)
//{
////lines.add(current);
//
//urlinfo += infocurrent;
//urlinfo += "\n";
//}
//System.out.println(urlinfo);
	}

}catch(IOException e)
{
e.printStackTrace();
}
}
}

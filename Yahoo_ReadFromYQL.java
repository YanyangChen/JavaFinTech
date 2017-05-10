
// requires JDOM API: http://www.jdom.org/
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

// inspired by http://cynober.developpez.com/tutoriel/java/xml/jdom/#LIII-A
public class Yahoo_ReadFromYQL {

    public static void main(String[] args) {
        try {
            //
            // 1. Retrieve the XML file
            //
            String prefix = "http://query.yahooapis.com/v1/public/yql?q=";
            String query = "select * from yahoo.finance.historicaldata where symbol = \"0700.HK\" and startDate = \"2016-02-01\" and endDate = \"2017-05-11\"";
            String suffix = "&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
            String s = prefix + query.replaceAll(" ", "%20") + suffix;
            URL url = new URL(s);
            URLConnection urlConn = url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();

            //
            // 2. Parse XML file
            //
            SAXBuilder sxb = new SAXBuilder();
            Document document = sxb.build(new ByteArrayInputStream(buffer.toString().getBytes()));
            Element query0 = document.getRootElement();
            Element results = query0.getChild("results");
            List quotes = results.getChildren();
            Iterator i = quotes.iterator();
            while (i.hasNext()) {
                Element quote = (Element) i.next();
                System.out.print(" "+quote.getChild("Date").getText()); 
                System.out.print(" "+quote.getChild("Open").getText()); 
                System.out.print(" "+quote.getChild("High").getText()); 
                System.out.print(" "+quote.getChild("Low").getText()); 
                System.out.print(" "+quote.getChild("Close").getText()); 
                System.out.print(" "+quote.getChild("Volume").getText()); 
                System.out.println(" "+quote.getChild("Adj_Close").getText()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
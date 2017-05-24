package stockml;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
public class Regression{
	public static void main(String args[]) throws Exception{
		//load dataset
		DataSource source = new DataSource("/home/aaron/Desktop/cpu.arff");
		Instances dataset = source.getDataSet();
		//set class index to the last attribute
		dataset.setClassIndex(dataset.numAttributes()-1);
		
		//build model
//		LinearRegression lr = new LinearRegression();
//		lr.buildClassifier(dataset);
//		//output model
//		System.out.println(lr);		
		
		//build model
		SMOreg smo = new SMOreg();
		smo.buildClassifier(dataset);
		//output model
		System.out.println(smo);
			
	}
}
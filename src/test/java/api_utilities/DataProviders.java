package api_utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders {

	// Dataprovider 1
	@DataProvider(name="UserData")
	public String [][]getData() throws IOException
	{
		String path=".\\testData\\RestApiData.xlsx"; // taking xl file from test data

		XlUtiltity xlutility=new XlUtiltity(path); // creating object for ExcelUtility 

		int totalRows=xlutility.getRowCount("Sheet1");
		int totalcols=xlutility.getCellCount("Sheet1",1);

		String [][]apiData=new String[totalRows][totalcols]; //creating two dimension array and stored the data 

		for (int i=1;i<=totalRows;i++)//read the data from xl storing in two dimension array
			// 1 is taking because 0 index is data heading is present
		{
			for(int j=0; j<totalcols;j++)//0 i is the row and j is the column
			{
				apiData[i-1][j]=xlutility.getCellData("Sheet1", i, j);// 1,0
			}
		}
		return apiData;
	}
	@DataProvider(name="Usernames")
	public String[]getname() throws IOException
	{
		String path=".\\testData\\RestApiData.xlsx"; // taking xl file from test data

		XlUtiltity xlutility=new XlUtiltity(path); // creating object for ExcelUtility 

		int totalRows=xlutility.getRowCount("Sheet1");

		String []apiData=new String[totalRows]; //creating two dimension array and stored the data 

		for (int i=1;i<=totalRows;i++)//read the data from xl storing in two dimension array
			// 1 is taking because 0 index is data heading is present
		{

			apiData[i-1]=xlutility.getCellData("Sheet1", i, 1);// 1,0

		}
		return apiData;
	}

}
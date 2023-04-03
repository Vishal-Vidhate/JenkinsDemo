package api.utilities;

import java.io.IOException;

public class DataProviders {
	
	//@DataProvider(name="Data")
	public static String[][] getAllData(String fileName,String sheetName) throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//"+fileName+".xlsx";
		
		ExcelUtility xl = new ExcelUtility(path);
		
		int rownum = xl.getRowCount(sheetName);
		int colcount = xl.getCellCount(sheetName, 1);
		
		String apidata[][] = new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j] = xl.getCellData(sheetName, i, j);
			}
		}
		
		return apidata;		
	}
	
	
	//@DataProvider(name="userNames")
	public static String[] getUserNames(String fileName,String sheetName) throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//"+fileName+".xlsx";
		
		ExcelUtility xl = new ExcelUtility(path);
		
		int rownum = xl.getRowCount(sheetName);
		
		String apidata[] = new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{
				apidata[i-1] = xl.getCellData(sheetName, i, 1);
		}
		
		return apidata;	
	}

}

package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	
    public static String getURL(String value) throws IOException
    {
    	Properties property = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//routes.properties");
    	property.load(file);
    	return property.getProperty(value);
    }

}

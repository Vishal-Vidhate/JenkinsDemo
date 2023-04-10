package Pojo_PostRequest;

import java.util.ArrayList;

public class Setter {
	
	public static POJO setValues()
	{
		POJO pj = new POJO();
		
		pj.setName("rushi");
		
		Location loc = new Location();
		loc.setLat(12456);
		loc.setLog(88888);
		pj.setLocation(loc);
		
		pj.setPhone("9999999999");
		
		ArrayList list = new ArrayList();
		list.add("ABM");
		list.add("Banking");		
		pj.setCourses(list);
		
		return pj;
	}

}

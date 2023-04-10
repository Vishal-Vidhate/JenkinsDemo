package Pojo_PostRequest;

import java.util.ArrayList;


public class POJO {

	private String name;
	private Location location;
	private String phone;
	private ArrayList courses;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ArrayList getCourses() {
		return courses;
	}
	public void setCourses(ArrayList courses) {
		this.courses = courses;
	}

}

package day6;

import java.util.ArrayList;

public class POJOClass {

	private String name;
	private LocationClass location;
	private String phone;
	private ArrayList courses;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocationClass getLocation() {
		return location;
	}
	public void setLocation(LocationClass location) {
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

package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<UserObject> userArrayList = new ArrayList<>(); // ArrayList to store data of the successful customers.
		String jsonString = readTextFile(); // method that returns the data from "customers.txt"
		String[] splitString = jsonString.split("\n");
		userArrayList = extractValues(splitString, userArrayList);// method that parses the JSON strings and returns an
																	// ArrayList of successful customers.
		userArrayList.sort(Comparator.comparing(UserObject::getUserId)); // using a comparator to sort the array list.
		writeTextFile(userArrayList); // write to a text file
	}

	// method that reads and returns the entire text file.
	private static String readTextFile() {
		// TODO Auto-generated method stub
		String customerTextString = "";
		String path = "C:\\Users\\theco\\eclipse-workspace\\IntercomTest\\customers.txt"; // change the path to wherever
												 // customers.txt is saved.
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			customerTextString = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerTextString;
	}

	// method for parsing the JSON strings and storing values in an ArrayList if
	// distance is within 100km.
	private static ArrayList<UserObject> extractValues(String[] splitString, ArrayList<UserObject> userArrayList) {
		// TODO Auto-generated method stub
		JSONParser jsonParser = new JSONParser();
		UserObject userObject; // object to store values extracted
		Object parsableObject;
		JSONObject userValuesJSONObject;
		double userLatitude, userLongitude;
		final double officeLatitude = 53.339428, officeLongitude = -6.257664; // constants
		long userId;
		String name;
		for (String string : splitString) {
			try {
				parsableObject = jsonParser.parse(string);
				userValuesJSONObject = (JSONObject) parsableObject;
				userLatitude = Double.parseDouble((String) userValuesJSONObject.get("latitude"));
				userLongitude = Double.parseDouble((String) userValuesJSONObject.get("longitude"));
				// method that returns the distance from office
				if (distanceFromOffice(userLatitude, userLongitude, officeLatitude, officeLongitude) <= 100) {
					userId = (long) userValuesJSONObject.get("user_id");
					name = (String) userValuesJSONObject.get("name");
					userObject = new UserObject(userLatitude, userLongitude, userId, name);
					userArrayList.add(userObject);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return userArrayList;
	}

	// method that calculates the distance between two points using Haversine's
	// formula
	public static double distanceFromOffice(double userLatitude, double userLongitude, double officeLatitude,
			double officeLongitude) {
		userLatitude = Math.toRadians(userLatitude);
		userLongitude = Math.toRadians(userLongitude);
		officeLatitude = Math.toRadians(officeLatitude);
		officeLongitude = Math.toRadians(officeLongitude);
		// Haversine's formula
		double distance = Math.sqrt(Math.pow(Math.sin((officeLatitude - userLatitude) / 2), 2) + Math.cos(userLatitude)
				* Math.cos(officeLatitude) * Math.pow(Math.sin((officeLongitude - userLongitude) / 2), 2));
		// 2 * r * arcsin(distance) , radius of earth is 6371 km
		distance = 2 * 6371 * Math.asin(distance);
		return (distance);
	}

	// method to write to a text file
	private static void writeTextFile(ArrayList<UserObject> userArrayList) {
		// TODO Auto-generated method stub
		try {
			FileWriter writer = new FileWriter("output.txt"); // file uploaded to the root directory of project. File is
																// overwritten every run.
			for (UserObject uobject : userArrayList) {
				writer.write("Name : " + uobject.getName() + "\nUser Id : " + uobject.getUserId() + "\n");
				writer.write("---------------------------------------------------------");
				writer.write("\n");
			}
			System.out.println("File uploaded");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

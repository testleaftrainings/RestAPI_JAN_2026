package week4.day1;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import week4.day1.pojos.EmployeeDetails;
import week4.day1.pojos.EmpAddress;

public class GenerateJsonUsingPojo {

	public static void main(String[] args) {
		EmployeeDetails employeeDetails = new EmployeeDetails();
		EmpAddress empAddress = new EmpAddress();
		ArrayList<String> banks = new ArrayList<String>();
		banks.add("HDFC");
		banks.add("ICICI");
		banks.add("SBI");
		
		employeeDetails.setName("Harry");
		employeeDetails.setJob("Manager");
		employeeDetails.setSalary(0.00);
		empAddress.setState("");
		empAddress.setCity("Pune");
		empAddress.setPincode(700044);
		employeeDetails.setEmpAddress(empAddress);
		employeeDetails.setBanks(banks);
		employeeDetails.setMarried(false);
		
		Gson gson = new GsonBuilder()
				        .setPrettyPrinting()
				        .create();
		
		System.out.println(gson.toJson(employeeDetails));
		
	}

}
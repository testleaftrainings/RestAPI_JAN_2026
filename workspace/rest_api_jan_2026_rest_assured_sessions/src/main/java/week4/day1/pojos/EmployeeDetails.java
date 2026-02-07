
package week4.day1.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class EmployeeDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("salary")
    @Expose
    private Double salary;
    @SerializedName("empAddress")
    @Expose
    private EmpAddress empAddress;
    @SerializedName("banks")
    @Expose
    private List<String> banks = new ArrayList<String>();
    @SerializedName("married")
    @Expose
    private Boolean married;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public EmpAddress getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(EmpAddress empAddress) {
        this.empAddress = empAddress;
    }

    public List<String> getBanks() {
        return banks;
    }

    public void setBanks(List<String> banks) {
        this.banks = banks;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

}

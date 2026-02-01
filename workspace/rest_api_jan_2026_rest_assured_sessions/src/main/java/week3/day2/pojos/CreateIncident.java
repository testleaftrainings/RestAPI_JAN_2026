package week3.day2.pojos;

import com.google.gson.annotations.SerializedName;

public class CreateIncident {

    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("category")
    private String category;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

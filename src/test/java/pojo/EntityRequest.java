package pojo;

import java.util.ArrayList;
import java.util.List;

public class EntityRequest{
    public AdditionRequest addition;
    public List<Integer> important_numbers;
    public String title;
    public Boolean verified;

    public EntityRequest() {

    }
    public EntityRequest(EntityRequest e){
        this.title = e.title;
        this.verified = e.verified;
        this.important_numbers = new ArrayList<>(e.important_numbers);
        this.addition = new AdditionRequest(e.addition.additional_info, e.addition.additional_number);
    }
    public AdditionRequest getAddition() {
        return addition;
    }

    public void setAddition(AdditionRequest addition) {
        this.addition = addition;
    }

    public List<Integer> getImportant_numbers() {
        return important_numbers;
    }

    public void setImportant_numbers(List<Integer> important_numbers) {
        this.important_numbers = important_numbers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}

package pojo;

import java.util.List;

public class EntityResponse {
    public AdditionResponse addition;
    public Integer id;
    public List<Integer> important_numbers;
    public String title;
    public Boolean verified;

    public AdditionResponse getAddition() {
        return addition;
    }

    public void setAddition(AdditionResponse addition) {
        this.addition = addition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

package pojo;

import java.util.Objects;

public class AdditionRequest {
    public String additional_info;
    public Integer additional_number;

    public AdditionRequest() {

    }
    public AdditionRequest(String additional_info, Integer additional_number) {
        this.additional_info = additional_info;
        this.additional_number = additional_number;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionRequest that = (AdditionRequest) o;
        return Objects.equals(additional_info, that.additional_info) && Objects.equals(additional_number, that.additional_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(additional_info, additional_number);
    }
    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public Integer getAdditional_number() {
        return additional_number;
    }

    public void setAdditional_number(Integer additional_number) {
        this.additional_number = additional_number;
    }
}


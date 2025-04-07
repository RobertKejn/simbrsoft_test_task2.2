package pojo;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Addition {
    public Integer id;
    public String additional_info;
    public Integer additional_number;

    public Addition() {

    }
    public Addition(String additional_info, Integer additional_number) {
        this.additional_info = additional_info;
        this.additional_number = additional_number;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addition that = (Addition) o;
        return Objects.equals(additional_info, that.additional_info) && Objects.equals(additional_number, that.additional_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(additional_info, additional_number);
    }
}


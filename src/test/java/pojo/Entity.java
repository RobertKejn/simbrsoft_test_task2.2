package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entity {
    public Integer id;
    public Addition addition;
    public List<Integer> important_numbers;
    public String title;
    public Boolean verified;
}

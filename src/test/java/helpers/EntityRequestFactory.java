package helpers;

import pojo.AdditionRequest;
import pojo.EntityRequest;

import java.util.List;

public class EntityRequestFactory {
    public static EntityRequest createDefaultEntityRequest(int n) {
        EntityRequest request = new EntityRequest();
        request.title = "title" + n;
        request.verified = n % 2 == 0;
        request.important_numbers = List.of(n, n, n);

        AdditionRequest addition = new AdditionRequest();
        addition.additional_info = "info"+n;
        addition.additional_number = n*10 + n;

        request.addition = addition;
        return request;
    }
}

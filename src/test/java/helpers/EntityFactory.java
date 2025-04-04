package helpers;

import pojo.Addition;
import pojo.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityFactory {
    public static Entity createDefaultEntity(int n) {
        Entity request = new Entity();
        request.id = null;
        request.title = "title" + n;
        request.verified = n % 2 == 0;
        request.important_numbers = List.of(n, n, n);

        Addition addition = new Addition();
        addition.id = null;
        addition.additional_info = "info" + n;
        addition.additional_number = n * 10 + n;

        request.addition = addition;
        return request;
    }

    public static Entity generateEntity() {
        Entity request = new Entity();
        request.id = null;

        Random r = new Random();

        int titleLength = Integer.parseInt(Property.getProperty("property.titlelength"));
        String title = "title_";
        while (title.length() < titleLength) title += (char)((int)'a' + r.nextInt(26));
        request.title = title;

        boolean verified = title.length() % 2 == 0;
        request.verified = verified;

        int maxImpNumbs = Integer.parseInt(Property.getProperty("property.maximpnumbs"));
        List<Integer> nums = new ArrayList<>();
        int len = r.nextInt(maxImpNumbs);
        while (nums.size() < len+1) nums.add(r.nextInt(100));
        request.important_numbers = nums;

        Addition addition = new Addition();
        addition.id = null;

        int infoLength = Integer.parseInt(Property.getProperty("property.infolength"));
        String info = "info_";
        while (info.length() < infoLength) info += (char)((int)'a' + r.nextInt(26));
        addition.additional_info = info;
        addition.additional_number = info.length();

        request.addition = addition;
        return request;
    }

}

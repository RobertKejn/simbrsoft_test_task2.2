package tests;

import helpers.EntityFactory;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.Entity;

public class DeleteEntityTest extends BaseTest {

    @BeforeMethod
    @Step("Создание нового Entity для тестирования")
    public void setUp() {
        Entity entity = EntityFactory.generateEntity();
        String id = createEntity(entity);
        entityRequests.add(entity);
        entityIDs.add(id);
    }

    @Test
    @Description("Тест проверяет удаление Entity по id")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityDelete() {
        deleteAllCreatedEntities();
    }
}

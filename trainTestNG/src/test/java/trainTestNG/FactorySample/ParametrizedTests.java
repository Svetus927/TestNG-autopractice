package trainTestNG.FactorySample;

        import org.testng.annotations.Factory;
        import org.testng.annotations.Test;

public class ParametrizedTests extends TestBase {

    // создает экземпляр тестового класса ParametrizedTests c параметром "/user.data",
    // а в нем уже потом запускается тестовый метод @Test с датапровайдером , описанным в классе-родителе TestBase.
    // если есть Factory то запуск начинается с него.
   @Factory
    public Object[] tf() {
        return new Object[]{ new ParametrizedTests("/user.data") };
    }

    public ParametrizedTests(String resource) {
        super(resource);
    }

    @Test(dataProvider = "loadUserFromResource")
    public void test1(String user, String password) {
       System.out.println(user + ":" + password);
      //  System.out.println("запускается:" );
    }

}

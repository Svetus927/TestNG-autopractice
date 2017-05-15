package trainTestNG;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.hamcrest.*;


/**
 * Created by uasso on 25/04/2017.
 */
@Test
public class SoftAssertSample {

    @BeforeClass
    public void initialActions () {
        System.out.println("  SoftAssertTest is running: ");
    }
    @Test(groups = "positive")
    public void test1() {
        System.out.println("Test1 passed");
    }

    @Test(groups = "assert")
    public void softAssertTest(){
        SoftAssert s= new SoftAssert();
        s.assertEquals(2*2, 5, "умножение 2 на 2 не прошло");
        s.assertEquals("buk"+"va","bukva1");
        System.out.println("принт после ассертов проходит");
      //  s.assertAll();
    }

    public void hamCrestAssertTest() {

    }

}

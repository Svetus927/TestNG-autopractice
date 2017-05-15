package trainTestNG;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by uasso on 09/05/2017.
 *
 *все тестовые данные для других тестов
 */
public class DataProviders {

    @DataProvider // исп в Task3
    public Iterator<Object[]> PathPositive(){
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{"testjava" + new Random().nextInt()+ ".txt"});
        data.add(new Object[]{"testjava" + new Random().nextInt()+".xml"});
        data.add(new Object[]{"testjava" + new Random().nextInt()});
        data.add(new Object[]{".txt"});
        return data.iterator();
    }

    @DataProvider  // исп в Task3
    public Iterator<Object[]> PathNegative(){
        List<Object[]> data = new ArrayList<Object[]>();

        data.add(new Object[]{"?"});
        data.add(new Object[]{"<"});
        data.add(new Object[]{"Too long name hgjhgjhjhfjhfjghgfhghvhgfhfhgfhgfhv fhv hggh jhjhgj ghjgj hgjhg jhg jgjh" +
                " ghjg jhgj h g jh gjh gjg jhg jjg jgjg jttyeet yr yt t ytr yr ytrytr yryttwt  eeoio uou u uo uououououoL"});
      //  data.add(new Object[]{"C://" +"testjava" + new Random().nextInt()+ ".txt"});
        return data.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loadUserFromFile() throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/user.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = bufReader.readLine();
        while (line != null) {
            userData.add(line.split(";"));

            line = bufReader.readLine();
        }

        bufReader.close();

        return userData.iterator();
    }
}

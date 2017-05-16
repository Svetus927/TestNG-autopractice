package trainTestNG;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.DataProvider;
import org.testng.xml.*;
//import org.testng.xml.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Svetlana Verkholantceva on 28/04/2017.
 * Разработайте и добавьте в созданный ранее проект несколько тестов (2-3 позитивных теста и 1-2 негативных теста).

 Тестировать будем функцию создания файла File.createNewFile(), входящую в стандартную библиотеку Java.

 Не нужно делать сложные тесты, со сложной логикой или сложной архитектурой. Наша цель -- изучение возможностей тестового фреймворка. Поэтому делайте несложные тесты.

 Можно сделать тесты пока без проверок, если тестируемые операции отработали и ничего не упало -- значит всё хорошо.

 Сделайте для этих тестов фикстуру. Она должна готовить временную директорию, в которой и будут выполняться попытки создания файла, а после завершения теста эта временная директория должна быть удалена.
 (Для создания временной директории можно использовать функцию Files.createTempDirectory)

 */
@Test(groups = "task3")
public class CreateNewFileTask3 {
public Path parentPath; // пасс к созданной временной директории

    @BeforeClass
    @Parameters({ "homeDir", "tempDir"}) //C://", "TestNG_Task3"})
    public void createTempDir (String homeDir, String tempDir) {
        System.out.println("  CreateNewFileTask3_5_6 is running: ");
        // final Path basedir = FileSystems.getDefault().getPath("C://tmp//");
        try {
           parentPath=   Files.createTempDirectory(Paths.get(homeDir),tempDir);
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

  // **----------пример использования параметров для @Test (dataProvider )--------------*//

  @Test(dataProviderClass = DataProviders.class, dataProvider = "PathPositive")
    public void existingPathTest(String strPath) throws IOException {

        File file = new File(String.valueOf(parentPath),strPath);

       Assert.assertTrue(file.createNewFile(), "createnewFile should be True for file!");
       System.out.println("File created successfully. The name: "+strPath);

       Assert.assertTrue(!file.createNewFile(), "createNewFile should be False for the second time!");
        Files.delete(Paths.get(file.getPath()));

    }

    //@Test(dataProviderClass = DataProviders.class, dataProvider = "PathNegative")//(enabled = false)
    @Test(dataProviderClass =  DataProviders.class,dataProvider ="loadNegativeFromFile")
    public void nonExistingPathTest(String strPath)  {
        File file = new File(String.valueOf(parentPath),strPath);
        try {
            boolean created =file.createNewFile();
            if (created) { AssertJUnit.assertFalse("CreatenewFile should be False for file with name: "+ strPath, created );}
            else {
                System.out.println("File is not created, createNewFile=false, without exception, filename:" +strPath);
            }

       } catch (IOException e) {
           Assert.assertTrue(e.getLocalizedMessage().compareTo("The filename, directory name, or volume label syntax is incorrect")==0, "Какой то левый иксепшн, надо разбираться!");
            System.out.println("File hasn't been created. The name is: "+strPath+" .Handled Exception: " + e.getMessage());
        }


        file.deleteOnExit();
    }

    @AfterClass
    public void deleteTEmpDir (){
        System.out.println(" Temporary directory, used for task3, createNewFile() function is "+ parentPath.toString());
    /*   try {
            Files.delete(parentPath);

        } catch (IOException e) {
            e.printStackTrace();
        }  */

    }

    //   отдельный метод возвращающий итератор для датапровайдера.
    // даная конструкция может использоваться когда надо проверить устойчивость системы на многократное повторение
   @DataProvider
   public Iterator<Object[]> PathPositive1(){
      List<Object[]> data = new ArrayList<Object[]>();
       data.add(new Object[]{"testjava" + new Random().nextInt()+ ".txt"});
       data.add(new Object[]{"testjava" + new Random().nextInt()+".xml"});
      data.add(new Object[]{"testjava" + new Random().nextInt()});
      data.add(new Object[]{".txt"});
      return data.iterator();
   }


}

package sge;

import sge.entities.*;
import sge.entities.Module;
import sge.data.DataContext;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

import static sge.Helpers.parseNull;


public class Utils {
    private Utils(){}

    public static void verifySaveFileIntegrity(String ...fileNames)  {
        System.out.println("\u001B[35mChecking File Integrity...\u001B[0m");
        for(String saveFileName: fileNames){
            File saveFile = new File(String.format("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/%s", saveFileName));
            try {
                if(!saveFile.exists()){
                    if(saveFile.createNewFile()){
                        System.out.printf("%sSaveFile: %s Initialized%s\n", "\u001B[32m",saveFileName, "\u001B[0m");
                    }
                }
            } catch (IOException e) {
                System.out.println("Failed to create FileName: " + e.getMessage());
                System.out.println("Ending Program ...");
                Runtime.getRuntime().halt(0);
            }
        }
        System.out.println("\u001B[32mData files Intact :)\u001B[0m");
    }

    public static boolean loadEntities(String packageName){
        int entitiesCount = countClasses(packageName);
        ExecutorService executor = Executors.newFixedThreadPool(entitiesCount);
        List<Runnable> tasks = new ArrayList<>(entitiesCount);
        List<Boolean> loadResults = new ArrayList<>(entitiesCount);

        //teachers load
        tasks.add(()->{
            List<Teacher> teachers = new ArrayList<>();

            try(BufferedReader reader = new BufferedReader(new FileReader("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/teacher.data"))){
                String line;
                reader.readLine();
                while ((line = reader.readLine())!= null){
                    if(line.isEmpty()) continue;
                    var data = (List<String>)Arrays.stream(line.split("~")).map(String::trim).toList();
                    //id ~ firstName ~ lastName ~ email ~ grade
                    teachers.add(new Teacher(parseNull(data.get(1)), parseNull(data.get(2)), parseNull(data.get(3)), parseNull(data.get(4)))
                            .setId(Long.parseLong(data.get(0)))
                    );
                }
                DataContext.getInstance().__syncTeachers(teachers);
                System.out.println("\u001B[32mLoaded Teachers.\u001B[0m");
                loadResults.add(true);
            }catch (IOException exception) {
                System.out.println("\u001B[31mFailed to load teacher.data\u001B[0m");
                loadResults.add(false);
            }
        });

        //department load
        tasks.add(()->{
            List<Department> departments = new ArrayList<>();

            try(BufferedReader reader = new BufferedReader(new FileReader("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/department.data"))){
                String line;
                reader.readLine();
                while ((line = reader.readLine())!= null){

                    var data = (List<String>)Arrays.stream(line.split("~")).map(String::trim).toList();
                    departments.add(new Department()
                            .setId(Long.parseLong(data.get(0)))
                            .setManagerId(Long.parseLong(data.get(1)))
                            .setTitle(parseNull(data.get(2)))
                    );
                }
                DataContext.getInstance().__syncDepartments(departments);
                System.out.println("\u001B[32mLoaded Departments.\u001B[0m");
                loadResults.add(true);
            }catch (IOException exception) {
                System.out.println("\u001B[31mFailed to load from department.data\u001B[0m");
                loadResults.add(false);
            }catch (NumberFormatException exception){
                System.out.printf("\u001B[31m%s\u001B[0m\n", exception.getMessage());
                loadResults.add(false);
            }
        });

        //student load
        tasks.add(()->{
            List<Student> students = new ArrayList<>();

            try(BufferedReader reader = new BufferedReader(new FileReader("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/student.data"))){
                String line;
                while ((line = reader.readLine())!= null){
                    var data = (List<String>)Arrays.stream(line.split("~")).map(String::trim).toList();
                    students.add(new Student(data.get(1), data.get(2), data.get(3), Integer.parseInt(data.get(4)), Long.parseLong(data.get(0))));
                }
                DataContext.getInstance().__syncStudents(students);
                System.out.println("\u001B[32mLoaded students.\u001B[0m");
                loadResults.add(true);
            }catch (IOException exception) {
                System.out.println("\u001B[31mFailed to load from student.data\u001B[0m");
                loadResults.add(false);
            }catch (NumberFormatException exception){
                System.out.printf("\u001B[31m%s\u001B[0m\n", exception.getMessage());
                loadResults.add(false);
            }
        });

        //Major load
        tasks.add(()->{
            //id ~ responsibleId ~ departmentId ~ title
           List<Major> majors = new ArrayList<>();
           try(BufferedReader reader = new BufferedReader((new FileReader("src/sge/data/major.data")))){
               String line;
               reader.readLine();
               while ((line = reader.readLine()) != null){
                   var data = (List<String>)Arrays.stream(line.split("~")).map(String::trim).toList();
                   majors.add(new Major()
                           .setId(Long.parseLong(data.get(0)))
                           .setResponsibleId(Long.parseLong(data.get(1)))
                           .setDepartmentId(Long.parseLong(data.get(2)))
                           .setTitle(parseNull(data.get(3)))
                   );
               }
               DataContext.getInstance().__syncMajors(majors);
               System.out.println("\u001B[32mLoaded majors.\u001B[0m");

               loadResults.add(true);
           }catch (IOException exception){
               System.out.printf("\u001B[31m%s\u001B[0m\n", exception.getMessage());
               loadResults.add(false);
           }
        });

        //module load
        tasks.add(()->{
            List<Module> modules = new ArrayList<>();
            try(BufferedReader reader = new BufferedReader((new FileReader("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/module.data")))){
                String line;
                reader.readLine();
                while ((line = reader.readLine()) != null){
                    var data = (List<String>)Arrays.stream(line.split("~")).map(String::trim).toList();
                    modules.add(new Module()
                            .setId(Long.parseLong(data.get(0)))
                            .setTeacherId(Long.parseLong(data.get(1)))
                            .setMajorId(Long.parseLong(data.get(2)))
                            .setTitle(data.get(3))
                    );
                }
                DataContext.getInstance().__syncModules(modules);
                System.out.println("\u001B[32mLoaded modules.\u001B[0m");

                loadResults.add(true);
            }catch (IOException exception){
                System.out.printf("\u001B[31m%s\u001B[0m\n", exception.getMessage());
                loadResults.add(false);
            }
        });


        for(Runnable task: tasks){
        task.run();
       }
        executor.shutdown();
        return !loadResults.contains(false);
    }

    private static int countClasses(String packageName) {
        String packagePath = packageName.replace('.', File.separatorChar);
        String classpath = System.getProperty("java.class.path");
        int count = 0;
        for (String path : classpath.split(File.pathSeparator)) {
            File directory = new File(path, packagePath);
            if (directory.exists() && directory.isDirectory()) {
                count += Objects.requireNonNull(directory.list((dir, name) -> {
                    return name.endsWith(".class");
                })).length;
            }
        }
        return count;
    }
}

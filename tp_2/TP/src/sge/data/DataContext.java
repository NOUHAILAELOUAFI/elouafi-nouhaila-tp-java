package sge.data;

import sge.entities.*;
import sge.entities.Module;

import javax.xml.crypto.Data;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataContext {
    private static DataContext instance;
    private DataContext(){}

    public static DataContext getInstance() {
        if(instance == null) instance = new DataContext();
        return instance;
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final List<Major> MAJORS = new ArrayList<>();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final List<Module> MODULES = new ArrayList<>();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final List<Teacher> TEACHERS = new ArrayList<>();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private  static final List<Student> STUDENTS = new ArrayList<>();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final List<Department> DEPARTMENTS = new ArrayList<>();

    public static  List<Major> getMajors(){
        return Collections.unmodifiableList(DataContext.MAJORS);
    }
    public static List<Module> getModules(){
        return Collections.unmodifiableList(DataContext.MODULES);
    }
    public static List<Teacher> getTeachers(){
        return Collections.unmodifiableList(DataContext.TEACHERS);
    }
    public static List<Student> getStudents(){
        return Collections.unmodifiableList(DataContext.STUDENTS);
    }
    public static List<Department> getDepartments(){
        return Collections.unmodifiableList(DataContext.DEPARTMENTS);
    }


    /* __sync{EntityName} are to be used in the Util helper class for initial loads during program start-up
       • unlike other methods, these __sync methods are not static, therefor they are instance methods.
       • the class is a singleton class meaning only 1 instance may exist throughout program life-time.
       • usage is permitted only within the  Util Helper class as such: DataContext.getInstance().__syn{EntityName}()
     */
    public void __syncTeachers(List<Teacher> teachers){
        if(Thread.currentThread().getStackTrace()[2].getClassName().endsWith("Utils")){
            DataContext.TEACHERS.addAll(teachers);
        }
    }
    public void __syncModules(List<Module> modules){
        if(Thread.currentThread().getStackTrace()[2].getClassName().endsWith("Utils")){
            DataContext.MODULES.addAll(modules);
        }
    }

    public void __syncMajors(List<Major> majors){
        if(Thread.currentThread().getStackTrace()[2].getClassName().endsWith("Utils")){
            DataContext.MAJORS.addAll(majors);
        }
    }

    public void __syncStudents(List<Student> students){
        if(Thread.currentThread().getStackTrace()[2].getClassName().endsWith("Utils")){
            DataContext.STUDENTS.addAll(students);
        }
    }

    public void __syncDepartments(List<Department> departments){
        if(Thread.currentThread().getStackTrace()[2].getClassName().endsWith("Utils")){
            DataContext.DEPARTMENTS.addAll(departments);
        }
    }

    public static boolean addTeacher(Teacher teacher){
        DataContext.TEACHERS.add(teacher.setId(
                DataContext.TEACHERS.isEmpty() ? 1 : DataContext.TEACHERS.get(DataContext.TEACHERS.size()-1).getId()+1
        ));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/teacher.data", true))) {
            writer.write(teacher.toString());
            writer.newLine();
            System.out.printf("%sTeacher saved to successfully%s\n", "\u001B[32m", "\u001B[0m");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while appending to the file: " + e.getMessage());
            DataContext.TEACHERS.remove(teacher);
            return false;
        }
    }
    public static boolean removeTeacher(Teacher teacher){
        String fileHeader = "id ~ firstName ~ lastName ~ email ~ grade";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/teacher.data", false))){
            DataContext.TEACHERS.remove(teacher);
            writer.write(fileHeader);
            writer.newLine();
            for(Teacher t: DataContext.TEACHERS){
                writer.write(t.toString());
                writer.newLine();
            }
            return true;
        }catch (IOException exception){
            System.out.println("An error occurred while appending to the file: " + exception.getMessage());
            DataContext.TEACHERS.add(teacher);
            return false;
        }
    }

    public static boolean addDepartment(Department department){
        DataContext.DEPARTMENTS.add(department.setId(
                DataContext.DEPARTMENTS.isEmpty() ? 1 : DataContext.DEPARTMENTS.get(DataContext.DEPARTMENTS.size()-1).getId()+1
        ));
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/department.data", true))){
            writer.write(department.toString());
            writer.newLine();
            System.out.printf("%sDepartment saved to successfully%s\n", "\u001B[32m", "\u001B[0m");
            return true;
        }catch (IOException exception){
            System.out.println("An error occurred while appending to file: " + exception.getMessage());
            DataContext.DEPARTMENTS.remove(department);
            return false;
        }
    }
    public static boolean removeDepartment(Department department){
        String fileHeader = "id ~ teacherId ~ title";

       try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/department.data", false))){
            DataContext.DEPARTMENTS.remove(department);
            writer.write(fileHeader);
            writer.newLine();
            for(Department d: DataContext.DEPARTMENTS){
                writer.write(d.toString());
                writer.newLine();
            }
            return true;
       }catch (IOException exception){
            System.out.println("An error occurred while appending to the file: " + exception.getMessage());
            DataContext.DEPARTMENTS.add(department);
            return false;
       }
   }

    public static boolean addMajor(Major major){
        DataContext.MAJORS.add(major.setId(
                DataContext.MAJORS.isEmpty() ? 1 : DataContext.MAJORS.get(DataContext.MAJORS.size()-1).getId()+1
        ));

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/major.data", true))){
            writer.write(major.toString());
            writer.newLine();
            System.out.printf("%sMajor saved to successfully%s\n", "\u001B[32m", "\u001B[0m");
            return true;
        }catch (IOException exception){
            System.out.println("An error occurred while appending to file: " + exception.getMessage());
            DataContext.MAJORS.remove(major);
            return false;
        }
   }
    public static boolean removeMajor(Major major){
       String fileHeader = "id ~ responsibleId ~ departmentId ~ title";

       try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/abzhr/Desktop/tp-java/TP phase 2/TP/src/sge/data/major.data", false))){
           DataContext.MAJORS.remove(major);
           writer.write(fileHeader);
           writer.newLine();
           for(Major m: DataContext.MAJORS){
               writer.write(m.toString());
               writer.newLine();
           }
           return true;
       }catch (IOException exception){
           DataContext.MAJORS.add(major);
           System.out.println("An error occurred while appending to the file: " + exception.getMessage());
           return false;
       }
   }

    public static boolean addModule(Module module) {
        DataContext.MODULES.add(module.setId(
                DataContext.MODULES.isEmpty() ? 1 : DataContext.MODULES.get(DataContext.MODULES.size() - 1).getId() + 1
        ));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/sge/data/module.data", true))) {
            writer.write(module.toString());
            writer.newLine();
            System.out.printf("%sModule saved successfully%s\n", "\u001B[32m", "\u001B[0m");
            return true;
        } catch (IOException exception) {
            System.out.println("An error occurred while appending to file: " + exception.getMessage());
            DataContext.MODULES.remove(module);
            return false;
        }
    }
    public static boolean removeModule(Module module){
        String fileHeader = "id ~ teacherId ~ majorId ~ title";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/sge/data/major.data", false))){
            DataContext.MODULES.remove(module);
            writer.write(fileHeader);
            writer.newLine();
            for(Module m: DataContext.MODULES){
                writer.write(m.toString());
                writer.newLine();
            }
            return true;
        }catch (IOException exception){
            DataContext.MODULES.add(module);
            System.out.println("An error occurred while appending to the file: " + exception.getMessage());
            return false;
        }
    }

}

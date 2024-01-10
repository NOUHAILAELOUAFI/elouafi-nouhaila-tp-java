package sge.data;

import sge.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDataContext {
    private static MySQLDataContext instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";

    private Statement statement;

    public MySQLDataContext(){
        try{
            this.connection = DriverManager.getConnection(MySQLDataContext.URL, MySQLDataContext.USERNAME, MySQLDataContext.PASSWORD);
            if (this.connection != null) {
                this.statement = connection.createStatement();
                System.out.println("Connected to the database!");
            }else{
                throw new SQLException();
            }
        }catch (SQLException exception){
            System.out.println("Mysql Connection failed. Error: "+ exception.getMessage());
        }
    }

    public Connection getConnection(){
        return this.connection;
    }


    public static synchronized MySQLDataContext getInstance(){
        if(instance == null){
            instance = new MySQLDataContext();
        }
        return instance;
    }



    public synchronized List<Department> getDepartments(){
        List<Department> departments = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * from Department");
            while (result.next()){
                departments.add(new Department()
                        .setId(result.getLong("id"))
                        .setManagerId(result.getLong("teacherId"))
                        .setTitle(result.getString("name"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: {SELECT * from departments}. error:" + e.getMessage());
        }
        return departments;
    }
    public  boolean addDepartement(Department department){
        PreparedStatement preparedStatement = null;
        try {

            // Définir la requête SQL d'insertion
            String insertQuery = "INSERT INTO Department (name, teacherId) VALUES (?, ?)";

            // Créer la première ligne de test
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, department.getTitle());
            preparedStatement.setLong(2, department.getManagerId());
            preparedStatement.executeUpdate();


            System.out.println("Departement added .");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeDepartement(Long idDep){
        PreparedStatement preparedStatement = null;

        try {

            // Définir la requête SQL de suppression avec une clause WHERE
            String deleteQuery = "DELETE FROM department WHERE id = ?";


            // Créer la requête préparée
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setLong(1, idDep);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Departement Deleted");
                return true;
            } else {
                System.out.println("Departement with id +"+idDep+" doesn't exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public  boolean addTeacher(Teacher teacher){
        PreparedStatement preparedStatement = null;
        try {

            // Définir la requête SQL d'insertion
            String insertQuery = "INSERT INTO Teacher (firstName, lastName, email, grade) VALUES (?, ?, ?, ?)";

            // Créer la première ligne de test
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getEmail());
            preparedStatement.setString(4, teacher.getGrade());
            preparedStatement.executeUpdate();


            System.out.println("Student added .");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public synchronized List<Teacher> getTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * from Teacher");
            while (result.next()){
                teachers.add(new Teacher()
                        .setId(result.getLong("id"))
                        .setFirstName(result.getString("firstName"))
                        .setLastName(result.getString("lastName"))
                        .setEmail(result.getString("email"))
                        .setGrade(result.getString("grade"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: {SELECT * from teachers}. error:" + e.getMessage());
        }
        return teachers;
    }

    public boolean removeTeacher(Long teacherIdToDelete){
        PreparedStatement preparedStatement = null;

        try {

            // Définir la requête SQL de suppression avec une clause WHERE
            String deleteQuery = "DELETE FROM Teacher WHERE id = ?";


            // Créer la requête préparée
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setLong(1, teacherIdToDelete);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Teacher Deleted");
                return true;
            } else {
                System.out.println("Teacher with id +"+teacherIdToDelete+" doesn't exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

}


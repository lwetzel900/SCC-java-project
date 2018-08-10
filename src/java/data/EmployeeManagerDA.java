/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author fssco
 */
public class EmployeeManagerDA {

    public static ArrayList<Person> getAllEmployees() {
        ArrayList<Person> all = new ArrayList<>();
        all.add(new Person("Aaron", "A", "Aaronson", 65,
                LocalDate.of(1980, Month.JANUARY, 1), LocalDate.of(2013, Month.JANUARY, 2)));

        all.add(new Person("Erin", "E", "Erinson", 66,
                LocalDate.of(1980, Month.JANUARY, 1), LocalDate.of(2012, Month.JANUARY, 1)));

        all.add(new Person("Beatrix", "", "Kiddo", 1313,
                LocalDate.of(1976, Month.FEBRUARY, 28), LocalDate.of(2003, Month.OCTOBER, 10)));

        all.add(new Person("Paul", "Muad'Dib", "Atreides", 2000,
                LocalDate.of(1965, Month.APRIL, 4), LocalDate.of(1984, Month.MAY, 5)));

        all.add(new Person("Marty", "", "McFly", 1985,
                LocalDate.of(1968, Month.JUNE, 12), LocalDate.of(1885, Month.JANUARY, 1)));

        all.add(new Person("Roy", "", "Batty", 734,
                LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 9)));

        all.add(new Person("Molly", "", "Millions", 1337,
                LocalDate.of(1984, Month.JULY, 1), LocalDate.of(2000, Month.APRIL, 18)));

        all.add(new Person("Loren", "R", "Wetzel", 37,
                LocalDate.of(1981, Month.AUGUST, 9), LocalDate.of(1999, Month.AUGUST, 1)));

        all.add(new Person("Romeo", "R", "Wetzel", 16,
                LocalDate.of(2002, Month.APRIL, 25), LocalDate.of(2010, Month.APRIL, 30)));

        all.add(new Person("Ashton", "M", "Wetzel", 3,
                LocalDate.of(2015, Month.FEBRUARY, 14), LocalDate.of(2018, Month.FEBRUARY, 26)));

        return all;
    }
    
    public static int insert(Person person) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Persons (EmployeeID, FirstName, MiddleName,LastName,BirthDate,HireDate) "
                + "VALUES (?, ?, ?,?,?,?)";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, person.getEmployeeID());
//            ps.setString(2, person.getFirstName());
//            ps.setString(3, person.getMiddleName());
//            ps.setString(4, person.getLastName());
//            ps.setDate(5, person.getBirthDate());
//            ps.setDate(6, person.getHireDate(), );
//            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Person> search(LocalDate searchDate, String searchValue) {

        ArrayList<Person> temp = new ArrayList<>(); //temp list to add found employees to
        ArrayList<Person> all = getAllEmployees(); //gets all of the employees from the other method
        
        if (searchValue.equals("before")) {
            
            all.sort((o1, o2) -> o1.getHireDate().compareTo(o2.getHireDate()));
            
            for (Person p : all) {

                if (p.getHireDate().isBefore(searchDate)) {
                    //adds the employee from all at index 0 to the temp list
                    temp.add(p);
//                    all.remove(p);
                }
            }
        } else if (searchValue.equals("after")) {
            
            all.sort((o1, o2) -> o2.getHireDate().compareTo(o1.getHireDate()));
            
            for (Person p : all) {
                
                if (p.getHireDate().isAfter(searchDate)) {
                    //adds the employee from all at index 0 to the temp list
                    temp.add(p);
//                    all.remove(p);
                }
            }
        }
        return temp;
    }
}

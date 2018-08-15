/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Person;
import data.EmployeeManagerDA;
import java.io.IOException;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fssco
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmployeeManagerDA.insertPrevious();

        String url = "/index.jsp";
        String message = "";

        LocalDate today = LocalDate.now();
        request.setAttribute("today", today);

        HttpSession session = request.getSession();//session start

        String action = request.getParameter("action");
        if (action == null) {
            action = "first";
        }

        String errorMessage = "";
        String searchValue = "";

        LinkedHashMap<String, Person> linkMap = (LinkedHashMap) session.getAttribute("linkMap");

        if (linkMap == null) {
            linkMap = new LinkedHashMap();
        }

        switch (action) {
            case "first":
                url = "/index.jsp";
//                ArrayList<Person> allEmps = new ArrayList<>();
//                for (Person p : EmployeeManagerDA.selectAll()) {
//                    linkMap.put(String.valueOf(p.getEmployeeID()), p);
//                }
//                linkMap.put("731", new Person("Pris", "", "Stratton", 731,
//                        LocalDate.of(2016, Month.FEBRUARY, 14), LocalDate.of(2016, Month.FEBRUARY, 14)));
//                linkMap.put("734", new Person("Roy", "", "Batty", 734,
//                        LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 9)));
                session.setAttribute("linkMap", linkMap);
                break;

            case "show":
                url = "/display.jsp";
                for (Person p : EmployeeManagerDA.getAllEmployees()) {
                    linkMap.put(String.valueOf(p.getEmployeeID()), p);
                }
                break;

            case "search":
                url = "/search.jsp";
                break;

            case "searchResults":
               
                url = "/search.jsp";
LinkedHashMap<String, Person> searchMap = new LinkedHashMap();
                searchValue = request.getParameter("searchValue");
                String tempDate = request.getParameter("searchDate");
                LocalDate hireDate = null;

                try {
                    hireDate = LocalDate.parse(tempDate);
                } catch (Exception e) {
                    errorMessage = "Must have a valid date to search.";
                    url = "/search.jsp";
                }
                if (searchValue == null || searchValue.isEmpty()) {
                    errorMessage = "Please choose a search parameter... "
                            + "Before or After the date picked";
                    url = "/search.jsp";
                } else {
//                    ArrayList<Person> searchResult = EmployeeManagerDA.search(hireDate, searchValue);

                    for (Person p : EmployeeManagerDA.searchEmployees(hireDate, searchValue)) {
                        searchMap.put(String.valueOf(p.getEmployeeID()), p);
                    }

                    if (searchMap.isEmpty()) {
                        message = "No one was hired " + searchValue + " " + hireDate + ". Please select another date.";
                    }
                }
                request.setAttribute("searchMap", searchMap);

                request.setAttribute("searchValue", searchValue);
                request.setAttribute("hireDate", hireDate);
                request.setAttribute("message", message);
                request.setAttribute("errorMessage", errorMessage);

                break;

            case "add":
                url = "/display.jsp";

                String fName = request.getParameter("fName");
                String mName = request.getParameter("mName");
                String lName = request.getParameter("lName");
                String tempEmpID = request.getParameter("empID");
                Integer empID = null;
                String tempBDay = request.getParameter("bDay");
                LocalDate bDay = null;
                String tempHireDate = request.getParameter("hireDate");
                hireDate = null;

                try {
                    empID = Integer.parseInt(tempEmpID);
                } catch (NumberFormatException e) {
                    message += "ID must be a number. <br>";
                    url = "/display.jsp";
                }
                if (fName == null || fName.isEmpty()) {
                    message += "Need a first name <br>";
                    url = "/display.jsp";
                } else if (lName == null || lName.isEmpty()) {
                    message += "Need a last name ";
                    url = "/display.jsp";
                } else {
                    try {
                        hireDate = LocalDate.parse(tempHireDate);
                    } catch (Exception e) {
                        message += "Hire Date needs to be a present and a date <br>";
                        url = "/display.jsp";
                    }
                    try {
                        bDay = LocalDate.parse(tempBDay);
                    } catch (Exception e) {
                        message += "Birthday needs to be a present and a date <br>";
                        url = "/display.jsp";
                    }

                    if (linkMap.containsKey(tempEmpID)) {
                        message += "That ID already exists. Pick Something else";
                        url = "/display.jsp";
                    } else {
                        Person person = new Person(empID, fName, mName, lName, bDay, hireDate);
                        EmployeeManagerDA.insertEmployee(person);
                        linkMap.put(String.valueOf(empID), person);
                    }
                }
                request.setAttribute("fName", fName);
                request.setAttribute("lName", lName);
                request.setAttribute("mName", mName);
                request.setAttribute("empID", empID);
                request.setAttribute("bDay", bDay);
                request.setAttribute("hireDate", hireDate);
                request.setAttribute("message", message);
                break;
            case "delete":
                url = "/display.jsp";
                linkMap.remove(request.getParameter("empID"));
                break;
            case "home":
                session.invalidate();
                url = "/index.jsp";
                break;
            default:
                break;
        }
//        session.setAttribute("linkMap", linkMap);
        ServletContext sc = getServletContext();

        sc.getRequestDispatcher(url)
                .forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

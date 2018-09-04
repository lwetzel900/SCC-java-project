/* 
 *   Document   : controller
 *   Created on : Aug 15, 2018, 10:03:21 PM
 *   Author     : lwetz
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

        //this is inserts old employees into the database
        //EmployeeManagerDA.insertPrevious();
        /*
        If I leave this uncommented when you delete someone from the database it will automatically put them back in.
         */
        String url = "/index.jsp";
        String message = "";
        String errorMessage = "";
        String searchValue = "";
        LocalDate today = LocalDate.now();
        request.setAttribute("today", today);
        LinkedHashMap<String, Person> linkMap = new LinkedHashMap();

        String action = request.getParameter("action");
        if (action == null) {
            action = "first";
        }

        switch (action) {
            case "first":
                url = "/index.jsp";
                break;

            case "show":
                url = "/display.jsp";
                for (Person p : EmployeeManagerDA.getAllEmployees()) {
                    linkMap.put(String.valueOf(p.getEmployeeID()), p);
                }

                request.setAttribute("linkMap", linkMap);
                break;

            case "search":
                url = "/search.jsp";
                break;

            case "searchResults":
                url = "/search.jsp";
                searchValue = request.getParameter("searchValue");
                String tempDate = request.getParameter("searchDate");
                LocalDate hireDate = null;

                try {
                    hireDate = LocalDate.parse(tempDate);
                } catch (Exception e) {
                    errorMessage = "Must have a valid date to search.<br>";
                    url = "/search.jsp";
                }
                if (searchValue == null || searchValue.isEmpty()) {
                    errorMessage += "Pick Before or After";
                    url = "/search.jsp";
                } else {
                    if (hireDate != null) {
                        for (Person p : EmployeeManagerDA.searchEmployees(hireDate, searchValue)) {
                            linkMap.put(String.valueOf(p.getEmployeeID()), p);
                        }
                        if (linkMap.isEmpty()) {
                            message = "No one was hired " + searchValue + " " + hireDate + ". Please select another date.";
                        }
                    }
                }
                request.setAttribute("linkMap", linkMap);

                request.setAttribute("searchValue", searchValue);
                request.setAttribute("hireDate", hireDate);
                request.setAttribute("message", message);
                request.setAttribute("errorMessage", errorMessage);
                break;

            case "addEmp":
                url = "/add.jsp";
                break;

            case "add":
                url = "/add.jsp";

                String fName = request.getParameter("fName");
                String mName = request.getParameter("mName");
                String lName = request.getParameter("lName");
                String type = request.getParameter("type");
                String tempEmpID = request.getParameter("empID");
                Integer empID = null;
                String tempBDay = request.getParameter("bDay");
                LocalDate bDay = null;
                String tempHireDate = request.getParameter("hireDate");
                hireDate = null;
                Person person = null;

                if (!tempEmpID.isEmpty()) {
                    try {
                        empID = Integer.parseInt(tempEmpID);
                    } catch (NumberFormatException e) {
                        message += "ID must be a number. <br>";
                        url = "/add.jsp";
                    }
                } else {
                    empID = 0;
                }

                try {
                    hireDate = LocalDate.parse(tempHireDate);
                } catch (Exception e) {
                    message += "Hire Date needs to be a present and a date <br>";
                    url = "/add.jsp";
                }

                try {
                    bDay = LocalDate.parse(tempBDay);
                } catch (Exception e) {
                    message += "Birthday needs to be a present and a date <br>";
                    url = "/add.jsp";
                }
                if (fName == null || fName.isEmpty()) {
                    message += "Need a first name <br>";
                    url = "/add.jsp";
                } else if (lName == null || lName.isEmpty()) {
                    message += "Need a last name <br>";
                    url = "/add.jsp";
                } else if (EmployeeManagerDA.selectSingleEmployee(empID)) {
                    message += "That ID already exists. Pick Something else";
                    url = "/add.jsp";
                } else {
                    if (!tempHireDate.isEmpty() && !tempBDay.isEmpty()) {
                        for (Person p : EmployeeManagerDA.getAllEmployees()) {
                            linkMap.put(String.valueOf(p.getEmployeeID()), p);
                            
                            person = new Person(empID, fName, mName, lName, bDay, hireDate, type);
                            if ((empID = EmployeeManagerDA.insertEmployee(person)) != 0) {
                                linkMap.put(String.valueOf(empID), person);
                                url = "/display.jsp";
                            }
                        }
                    }
                }
                request.setAttribute("message", message);

                request.setAttribute("linkMap", linkMap);
                if (empID != 0) {
                    request.setAttribute("empID", empID);
                }
                request.setAttribute("fName", fName);
                request.setAttribute("mName", mName);
                request.setAttribute("lName", lName);
                request.setAttribute("type", type);
                request.setAttribute("bDay", bDay);
                request.setAttribute("hireDate", hireDate);
                break;

            case "delete":
                url = "/display.jsp";
                String id = request.getParameter("empID");
                EmployeeManagerDA.deleteUser(Integer.parseInt(id));
                for (Person p : EmployeeManagerDA.getAllEmployees()) {
                    linkMap.put(String.valueOf(p.getEmployeeID()), p);
                }
                request.setAttribute("linkMap", linkMap);

                break;

            case "home":
                url = "/index.jsp";
                break;

            default:
                break;
        }

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

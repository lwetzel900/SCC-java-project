/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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

        String url = "/display.jsp";
        String message = "";

        LocalDate today = LocalDate.now();
        request.setAttribute("today", today);
        request.setAttribute("message", message);
        HttpSession session = request.getSession();//session start

//LinkedHashMap<String, Person> linkMap = (LinkedHashMap) session.getAttribute("linkMap");
//session.setAttribute("linkMap", linkMap);
        String action = request.getParameter("action");
        if (action == null) {
            action = "first";
        }

        LinkedHashMap<String, Person> linkMap = (LinkedHashMap) session.getAttribute("linkMap");

        if (linkMap == null) {
            linkMap = new LinkedHashMap();
        }
//session.setAttribute("linkMap", linkMap);
        switch (action) {
            case "first":
                url = "/display.jsp";
//                session.setAttribute("linkMap", linkMap);
                linkMap.put("731", new Person("Pris", "", "Stratton", 731,
                        LocalDate.of(2016, Month.FEBRUARY, 14), LocalDate.of(2016, Month.FEBRUARY, 14)));
                linkMap.put("734", new Person("Roy", "", "Batty", 734,
                        LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 9)));
                session.setAttribute("linkMap", linkMap);
                break;
            case "add":
                url = "/display.jsp";

                String fName = request.getParameter("fName");
                String mName = request.getParameter("mName");
                String lName = request.getParameter("lName");
                String empID = request.getParameter("empID");
                LocalDate bDay = LocalDate.parse(request.getParameter("bDay"));
                LocalDate hireDate = LocalDate.parse(request.getParameter("hireDate"));

                if (fName == null || fName.isEmpty()) {
                    message = "Need a first name";
                    url = "/display.jsp";
                } 
                if(lName == null || fName.isEmpty()){
                    message = "Need a last name";
                    url = "/display.jsp";
                }
                
                else {

//                    try {
//                        hireDate = LocalDate.parse(tempDate);
//                    } catch (Exception e) {
//                        message += "Must have a valid date to search.";
//                        url = "/search.jsp";
//                    }
                    Person person = new Person(fName, mName, lName, Integer.parseInt(empID), bDay, hireDate);
                    linkMap.put(empID, person);
                }
                break;
            case "delete":
                url = "/display.jsp";
                linkMap.remove(request.getParameter("empID"));
                break;
            case "home":
//                            url = "/display.jsp";
                session.invalidate();
                url = "/display.jsp";
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

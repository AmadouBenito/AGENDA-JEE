/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MONSERVLET;

import com.mysql.cj.xdevapi.Statement;
import connectionDB.connectionDAO;
import enregistrement.Enregistrement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BABA SAIDOU DIEME
 */
@WebServlet(name = "homeServlet", urlPatterns = {"/test"})
public class homeServlet extends HttpServlet {
    
     private static final long serialVersionUID = 1L;
    private connectionDAO myConnection;
    
    @Override
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        myConnection = new connectionDAO(jdbcURL, jdbcUsername, jdbcPassword);
        

    }

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
        
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-CONTENT/view/homeJSP.jsp");
        dispatcher.forward(request, response);
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
        
       try {
            getAll(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
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
        
        try {
            AddRecord(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
       // processRequest(request, response);
    }

    private void AddRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String contenu = request.getParameter("contenu");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
     
        Enregistrement newRecord = new Enregistrement(date, time, contenu);
        myConnection.Ajouter(newRecord, request);
        response.sendRedirect("test");
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Enregistrement> listRecord = myConnection.getAllRecords();
        request.setAttribute("listRecord", listRecord);
       
        this.getServletContext().getRequestDispatcher("/WEB-CONTENT/view/homeJSP.jsp").forward(request, response);
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

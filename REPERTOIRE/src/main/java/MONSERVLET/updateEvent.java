/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MONSERVLET;

import connectionDB.connectionDAO;
import enregistrement.Enregistrement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "updateEvent", urlPatterns = {"/updateEvent"})
public class updateEvent extends HttpServlet {
    
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
       
    }
    
     private void updateEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String contenu = request.getParameter("contenu"+id);
        
        Enregistrement newEnreg = new Enregistrement(id, contenu);
        myConnection.updateEvent(newEnreg, request);
        response.sendRedirect("test");
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
            int id = Integer.parseInt(request.getParameter("id"));
            Enregistrement existingArticle = myConnection.getEvent(id);
            listRecord(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("article.jsp");
            request.setAttribute("produit", existingArticle);
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(updateEvent.class.getName()).log(Level.SEVERE, null, ex);
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
            updateEvent(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

     private void listRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Enregistrement> listRecord = myConnection.getAllRecords();
        request.setAttribute("listRecord", listRecord);
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

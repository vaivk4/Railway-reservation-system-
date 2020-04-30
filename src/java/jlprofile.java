/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vivek kumar
 */
public class jlprofile extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet jlprofile</title>");
            out.println("<style>");
            out.println("<LINK REL=\"StyleSheet\" HREF=\"<%=request.getContextPath()%>/util/CSS/mystyle.css\" TYPE=\"text/css\"> ");
            out.println("</style>");
            out.println("<div  align='right'");
           out.println("<button onclick=\"window.location.href='homepage.xhtml'\">Home</button>");
           out.println("<button onclick=\"window.location.href='about.html'\">About</button>");
           
            out.println("</div>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='login-servlet-body'>");
            out.println("<h1>Railway Reservation System</h1>");
            
            String user=request.getParameter("username");
            String pass=request.getParameter("passward");
            
            
            
             try{
                       
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_res_sys","root","");
             String sql="select * from login where uname=? and pass=?";
             PreparedStatement stmt=conn.prepareStatement(sql);
             stmt.setString(1,user);
             stmt.setString(2,pass);
             ResultSet rs=stmt.executeQuery();
             
             
             if(rs.next()){
            
            out.println("logoin Sussessfully");
            out.println("<br><br><br>");
            out.println("<button onclick=\"window.location.href='jbook'\">book ticket</button>");
            out.println("<button onclick=\"window.location.href='jbook_detail'\">view ticket</button>");
            
            out.println("<button onclick=\"window.location.href='jbook_cancel'\">cancel ticket</button>");
            
             
             
             HttpSession session = request.getSession();
              session.setAttribute("uname",rs.getString(1));
              session.setAttribute("name",rs.getString(3));
             }
             
             }
              catch(Exception e)
             {
               out.println(e);   
             }
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
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

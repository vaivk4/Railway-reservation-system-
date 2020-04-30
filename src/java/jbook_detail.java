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
public class jbook_detail extends HttpServlet {

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
            out.println("<title>railway vie ticket</title>"); 
            out.println("<title>railway</title>");  
             out.println("<div  align='right'");
           out.println("<button onclick=\"window.location.href='homepage.xhtml'\">Home</button>");
          // out.println("<input type=\"button\" onclick=\"location.href='http://google.com';\" value=\"Go to Google\" />"); 
            out.println("</div>");
             out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\"/> "); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Railway Reservation System</h1>");
            
            HttpSession session = request.getSession(false); 
             String user= (String)session.getAttribute("uname");
             //String name= (String)session.getAttribute("name");
             out.print("Hello " + user);
             out.println("<br><h4>Booking detail</h4>");
             
             out.println("<table>");
             out.println("<tr><td><h4>Name</h4></td><td><h4>Source</h4></td><td><h4>Destination</h4></td><td><h4>Train no</h4></td><td><h4>Train name.</h4></td><td><h4>Date</h4></td><td><h4>PNR</h4></td></tr>");
            
             try{
                       
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_res_sys","root","");
             String sql="select * from book_ticket where uname=?";
             PreparedStatement stmt=conn.prepareStatement(sql);
             stmt.setString(1,user);
             ResultSet rs=stmt.executeQuery();
             
             int s=0;
             
             while(rs.next())
             {
                 s=1;
              out.println("<tr>");
              out.println("<td>"+rs.getString(2)+"</td>");
              out.println("<td>"+rs.getString(3)+"</td>");
              out.println("<td>"+rs.getString(4)+"</td>");
              out.println("<td>"+rs.getString(5)+"</td>");
              out.println("<td>"+rs.getString(6)+"</td>");
              out.println("<td>"+rs.getString(8)+"</td>");
              out.println("<td>"+rs.getString(9)+"</td>");
              out.println("</tr>");
              
             } 
             if(s==0)
                 out.println("No detail available");
             
             out.println("</table>");
             
             
            
            
             }
             catch(Exception e)
             {
               out.println(e);   
             }
             
             
            
            
            
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

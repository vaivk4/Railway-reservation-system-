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
public class jbook extends HttpServlet {

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
            out.println("<title>railway</title>");  
             out.println("<div  align='right'");
           out.println("<button onclick=\"window.location.href='homepage.xhtml'\">Home</button>");
          // out.println("<input type=\"button\" onclick=\"location.href='http://google.com';\" value=\"Go to Google\" />"); 
            out.println("</div>");
             out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\"/> "); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Railway reservation system</h1>");
            
            
             HttpSession session = request.getSession(false); 
             String user= (String)session.getAttribute("uname");
             String name= (String)session.getAttribute("name");
             out.print("Hello " + user); 
            
             out.print("<center>");
     out.print("<div id='d8'>");
       out.print("<form action='' method=\"post\">");
         out.println("<h3>Booking detail</h3>");
            out.println("<p><label>Source<input type=\"text\" name=\"source\" required></label></p>");
            out.println("<p><label>Destination<input type=\"text\" name=\"destination\" required></label></p>");
            out.println("<p><label>No. of passenger<input type=\"number\" name=\"passenger\" required></label></p>");
            out.println("<p><label>Date of journey<input type=\"date\" name=\"date\" required></label></p>");
            out.println("<br><input type=\"submit\" value=\"submit\">  <input type=\"reset\" value=\"reset\">");
            out.println(" </form>");
            out.println("</div>");
            out.println("</center>");
            
             String src,des,nop,date;
             
             src=request.getParameter("source");
             des=request.getParameter("destination");
             nop=request.getParameter("passenger");
             date=request.getParameter("date");
             
                //out.println(src+des+nop+date+user+name);
              try{
                       
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_res_sys","root","");
             String sql="select * from train where source=? and destination=?";
             PreparedStatement stm=conn.prepareStatement(sql);
             stm.setString(1,src);
             stm.setString(2,des);
             ResultSet rs=stm.executeQuery();
             
             String sql1="INSERT INTO book_ticket(`uname`, `name`, `src`, `des`, `tno`, `tname`,`no_passenger`,`date`) VALUES (?,?,?,?,?,?,?,?)";
                         
             
             int i=0;
             while(rs.next())
             {
               PreparedStatement stmt=conn.prepareStatement(sql1);
             stmt.setString(1,user);
             stmt.setString(2,name);
             stmt.setString(3,src);
             stmt.setString(4,des);
             stmt.setString(5,rs.getString(1));
             stmt.setString(6,rs.getString(2));
             stmt.setString(7,nop);
             stmt.setString(8,date);  
             
               stmt.executeUpdate();
                   
              i=1;
             } 
             if(i==1){
              out.println("your ticket has been  booked");
              out.println("<<a href='jlprofile'>click to go previous page</a>");
             }
             else
                 out.println("No train available");
             
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

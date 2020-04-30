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
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vivek kumar
 */
@WebServlet(urlPatterns = {"/jsprofile"})
public class jsprofile extends HttpServlet {

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
            out.println("<title>Servlet jsprofile</title>");  
            out.println("<div  align='right'");
           out.println("<button onclick=\"window.location.href='homepage.xhtml'\">Home</button>");
           
           
            out.println("</div>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Railway Reservation System</h1>");
            
            
            String name,age,gender,mobno,email,address,username,passward;
            
            name=request.getParameter("name");
            age=request.getParameter("age");
            gender=request.getParameter("gender");
            mobno=request.getParameter("mobno");
            email=request.getParameter("email");
            address=request.getParameter("address");
            username=request.getParameter("username");
            passward=request.getParameter("passward");
           
            
             
            //out.println(name+age+mobno+gender+passward);
            
            try{
                       
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_res_sys","root","");
            // String sql="insert into login('uname','pass','name',age,'gender','mob_no','email','address') value(?,?,?,?,?,?,?)";
              String sql="INSERT INTO login(`uname`, `pass`, `name`, `age`, `gender`, `mob_no`,`email`,`address`) VALUES (?,?,?,?,?,?,?,?)";
             PreparedStatement stmt=conn.prepareStatement(sql);
             stmt.setString(1,username);
             stmt.setString(2,passward);
             stmt.setString(3,name);
             stmt.setInt(4,Integer.valueOf(age));
             stmt.setString(5,gender);
             stmt.setString(6,mobno);
             stmt.setString(7,email);
             stmt.setString(8,address);
             
             
             int i=stmt.executeUpdate();
             if(i==1)
             {
                 out.println("thanks for registration");
                 out.println("<br>");
                 out.println("<button onclick=\"window.location.href='jlogin.html'\">Click to login</button>");
                 
             }
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/displayServlet"})
public class displayServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel='stylesheet' integrity=\"sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor\" crossorigin=\"anonymous\">\n");            
            out.println("</head>");
            out.println("<body>");
            
            
            Service service=new Service("jdbc:mysql://localhost:3306/tasks", "root", "");
            ArrayList<Log> logs=service.getLogs();
            request.setAttribute("LogsArray", logs);
            
            out.println("<table class='table table-bordered table-success'><thead><tr><td>Id</td><td>Title</td><td>Log Content</td><td>TimeStamp</td><td>Delete</td></tr></thead><tbody>");
            
            
            for(Log a:logs){
                out.println("<tr>"+"<td>"+Integer.toString(a.getId())+"</td>"+"<td><a href=editServlet?id="+a.getId()+">"+a.getTitle()+"</a></td>"+"<td>"+a.getLogContent()+"</td>"+"<td>"+a.getTimetamp()+"</td><td><a href=deleteServlet?id="+a.getId()+">Delete Log</a></td></tr>");
            }
            out.println("</tbody></table>");
            out.println("<div class='container mt-5' style='width: 650px;'>");
out.println("<h1>Log Writing</h1>");
out.println("<form class='form' action='LogServlet' method='POST'>");
out.println("<div class='mb-4'>");
out.println("<label for='log' class='form-label'>Log Title</label>");
out.println("<input type='text' class='form-control' id='log' name='title'>");       
out.println("</div>");
out.println("<div class='mb-4'>");
out.println("<label class='form-label'>Log Content</label>");
out.println("<textarea class='form-control' rows='7' id='content' name='content'></textarea>");
out.println("</div>");
out.println("<button type='submit' class='btn btn-primary'>Create Log</button>");  
out.println("</form>");
            out.println("</body></html>");
         
            
            //response.sendRedirect("showData.jsp");
            //request.getRequestDispatcher("/showData.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(displayServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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

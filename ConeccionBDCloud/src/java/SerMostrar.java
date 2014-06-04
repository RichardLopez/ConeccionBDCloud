/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lopez
 */
@WebServlet(urlPatterns = {"/SerMostrar"})
public class SerMostrar extends HttpServlet {
    clsConexion cons = new clsConexion();
    Connection con = cons.Conexion();

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ResultSet rst;
        String sql;
        Statement stmt;
        PrintWriter out = response.getWriter();
        try {
            sql = "SELECT * FROM personas;";
           
                stmt = con.createStatement();
            rst = stmt.executeQuery(sql);
            boolean flat = false;
            while (rst.next()) {
                if (!flat) {
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>DNI</th>");
                    out.println("<th>NOMBRE</th>");
                    out.println("<th>FECHA_NAC</th>");
                    out.println("<th>ELIMINAR</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                }
                out.println("<tr>");
                out.println("<td>" + rst.getInt(1) + "</td>");
                out.println("<td>" + rst.getString(2) + "</td>");
                out.println("<td>" + rst.getString(3) + "</td>");
                out.println("<td><a href=\"SerEliminar?dni="+rst.getInt(1)+"\">Eliminar</a></td>");
                out.println("</tr>");
                flat = true;
            }
            if (flat) {
                out.println("</tbody>");
                out.println("</table>");
            } else {
                out.println("No se encontraron coincidencias...");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

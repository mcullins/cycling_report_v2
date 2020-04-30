package com.cyclingrecord.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class FileUploadDBServlet extends HttpServlet {
    private String dbURL = "jdbc:mysql://localhost:3306/cyclingrecord";
    private String dbUser = "cyclingrecord";
    private String dbPassword = "Hmveonl00";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");

        InputStream inputStream = null;

        Part filePart = request.getPart("photo");
        if(filePart != null){
            inputStream = filePart.getInputStream();
        }

        Connection conn = null;
        String message = null;

        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            String sql = "INSERT INTO images (name, photo) values (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            if(inputStream != null){
                statement.setBlob(2, inputStream);
            }

            int row = statement.executeUpdate();
            if(row > 0){
                message = "File uploaded";
            }
        } catch (SQLException e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            request.setAttribute("message", message);

            getServletContext().getRequestDispatcher("/gallery.html").forward(request, response);
        }
    }
}

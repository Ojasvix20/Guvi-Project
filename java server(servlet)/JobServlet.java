// Servlet Code (JobServlet.java)
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/job_portal";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Job Listings</h2>");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM jobs");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                out.println("<div>");
                out.println("<h3>" + rs.getString("title") + "</h3>");
                out.println("<p>" + rs.getString("description") + "</p>");
                out.println("</div>");
            }
        } catch (Exception e) {
            out.println("<p>Error fetching jobs: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO jobs (title, description) VALUES (?, ?)");) {

            stmt.setString(1, jobTitle);
            stmt.setString(2, jobDescription);
            stmt.executeUpdate();

            out.println("<html><body>");
            out.println("<h2>Job Posted Successfully</h2>");
            out.println("<p>Job Title: " + jobTitle + "</p>");
            out.println("<p>Job Description: " + jobDescription + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<p>Error saving job: " + e.getMessage() + "</p>");
        }
    }
}
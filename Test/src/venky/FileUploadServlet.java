package venky;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)

public class FileUploadServlet extends HttpServlet {
    private static final String SAVE_DIR="images";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = null;
    	String driver="com.mysql.jdbc.Driver";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            String savePath = "C:" + File.separator + SAVE_DIR;
                File fileSaveDir=new File(savePath);
                if(!fileSaveDir.exists()){
                    fileSaveDir.mkdir();
                }
            String firstName=request.getParameter("firstname");
            System.out.println("firstname:"+firstName);
            String lastName=request.getParameter("lastname");
            Part part=request.getPart("file");
            String fileName=extractFileName(part);
            part.write(savePath + File.separator + fileName);
           /* 
            //You need this loop if you submitted more than one file
            for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            part.write(savePath + File.separator + fileName);
        }*/
            try {
				Class.forName(driver);
				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
				PreparedStatement pst =(PreparedStatement) conn.prepareStatement("insert into customerdetail values(?,?,?)");
				
					pst.setString(1,firstName);
	                pst.setString(2,lastName);
	                String filePath= savePath + File.separator + fileName ;
	                pst.setString(3,filePath);
	                pst.executeUpdate();
				} catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
    }
    // file name of the upload file is included in content-disposition header like this:
    //form-data; name="dataFile"; filename="PHOTO.JPG"
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}

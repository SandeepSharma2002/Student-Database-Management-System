package jdbc.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.dto.Student;
import jdbc.service.IStudentService;
import jdbc.servicefactory.StudentServiceFactory;


@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		IStudentService stdService = StudentServiceFactory.getStudentService();
		
		if(request.getRequestURI().endsWith("addform"))
		{
			String sage = request.getParameter("sage"); 
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			Student std = new Student(); 
			std.setSname(sname);
			std.setSage(Integer.parseInt(sage));
			std.setSaddress(saddr);
			
			String status = stdService.addStudent(std);
			PrintWriter outPrintWriter = response.getWriter();
			
			if (status.equalsIgnoreCase("success")){
				outPrintWriter.println("<h1 style='color:green; text-align:center;'>Registration Successfull.</h1>");
				
			} else {
				outPrintWriter.println("<h1 style='color=red; text-align:center;'>Registration Failed.</h1>");
			}
			outPrintWriter.close();
		}
		
		if(request.getRequestURI().endsWith("searchform"))
		{
			String sid = request.getParameter("sid"); 
			PrintWriter outPrintWriter = response.getWriter();

			
			Student student = stdService.searchStudent(Integer.parseInt(sid));
			
			if (student != null){
				outPrintWriter.println("<body>");
				outPrintWriter.println("<center>");
				outPrintWriter.println("<table border = '1' >");
				outPrintWriter.println("<tr><th>ID</th><td>"+student.getSid()+"</td></tr>");
				outPrintWriter.println("<tr><th>Name</th><td>"+student.getSname()+"</td></tr>");
				outPrintWriter.println("<tr><th>Age</th><td>"+student.getSage()+"</td></tr>");
				outPrintWriter.println("<tr><th>Address</th><td>"+student.getSaddress()+"</td></tr>");
				outPrintWriter.println("</table>");
				outPrintWriter.println("</center>");
				outPrintWriter.println("</body>");
				
			} else {
				outPrintWriter.println("<h1 style='color=red; text-align:center;'>Record not avilable for given ID: "+sid+"</h1>");
			}
			outPrintWriter.close();
		}
		
		if(request.getRequestURI().endsWith("deleteform"))
		{
			String sid = request.getParameter("sid"); 
			PrintWriter outPrintWriter = response.getWriter();

			
			String status = stdService.deleteStudent(Integer.parseInt(sid));
			
			if (status.equalsIgnoreCase("success")){
				outPrintWriter.println("<h1 style='color:green; text-align:center;'>Deletion Successfull.</h1>");
				
			} else if(status.equalsIgnoreCase("failure")) {
				outPrintWriter.println("<h1 style='color=red; text-align:center;'>Deletion Failed.</h1>");
			}
			else {
				outPrintWriter.println("<h1 style='color=red; text-align:center;'>Record not avilable for given ID: "+sid+"</h1>");
			}
			outPrintWriter.close();
		}
		
		if(request.getRequestURI().endsWith("updateform"))
		{
			String sid = request.getParameter("sid"); 
			PrintWriter outPrintWriter = response.getWriter();

			
			Student student = stdService.searchStudent(Integer.parseInt(sid));
			
			if (student != null){
				outPrintWriter.println("<body>");
				outPrintWriter.println("<center>");
				outPrintWriter.println("<form method='post' action ='./controller/updateRecord'>");
				outPrintWriter.println("<table>");
				outPrintWriter.println("<tr><th>ID</th><td>"+student.getSid()+"</td></tr>");
				outPrintWriter.println("<input type ='hidden' name ='sid' value ="+ student.getSid() +">");
				outPrintWriter.println("<tr><th>Name</th><td><input type='text' name = 'sname' value ="+ student.getSname()+ "></td></tr>");
				outPrintWriter.println("<tr><th>Age</th><td><input type='text' name = 'sage' value ="+ student.getSage()+"></td></tr>");
				outPrintWriter.println("<tr><th>Address</th><td><input type='text' name = 'saddr' value = "+ student.getSaddress()+" /></td></tr>");
				outPrintWriter.println("<tr><td><input type ='submit' value = 'update'/></td></tr>");
				outPrintWriter.println("</table>");
				outPrintWriter.println("</form>");
				outPrintWriter.println("</center>");
				outPrintWriter.println("</body>");
				
			} else {
				outPrintWriter.println("<h1 style='color=red; text-align:center;'>Record not avilable for given ID: "+sid+"</h1>");
			}
			outPrintWriter.close();
	}
		if(request.getRequestURI().endsWith("updateRecord"))
		{
			String sid = request.getParameter("sid"); 
			String sage = request.getParameter("sage"); 
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			Student std = new Student(); 
			std.setSid(Integer.parseInt(sid)); 
			std.setSname(sname);
			std.setSage(Integer.parseInt(sage));
			std.setSaddress(saddr);
			
			String status = stdService.updateStudent(std);
			PrintWriter outPrintWriter = response.getWriter();  
			if (status.equalsIgnoreCase("success")){
				outPrintWriter.println("<h1 style='color:green; text-align:center;'>Student Record Updated Successfully.</h1>");
				
			}
			else {
				outPrintWriter.println("<h1 style='color=red; text-align:center;'>Student Record Updation Failed</h1>");
			}
			outPrintWriter.close();
		}
	
	}
}

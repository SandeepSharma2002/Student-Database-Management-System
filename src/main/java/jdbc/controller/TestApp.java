package jdbc.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import jdbc.dto.Student;
import jdbc.service.IStudentService;
import jdbc.servicefactory.StudentServiceFactory;

public class TestApp {
	
	public static void InsertionOperation()
	{
		IStudentService studentService = StudentServiceFactory.getStudentService();
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter student name:: ");
			String sname = scanner.next();
			System.out.println("Enter student age:: ");
			Integer sage = scanner.nextInt();
			System.out.println("Enter student address:: ");
			String saddress = scanner.next();
//	    scanner.close();
			String msg = studentService.addStudent(sname,sage,saddress);
			if(msg.equalsIgnoreCase("success"))
				System.out.println("Data Inserted Successfully...");
			else {
				System.out.println("Data Inserrtion Failed...");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void selectionOperation()
	{
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter student ID:: ");
		Integer sid = scanner.nextInt();
//		scanner.close();
		Student msg = null;
		msg = studentService.searchStudent(sid);
		if(msg == null)
			System.out.println("Data Not Found...");
			
		else {
			System.out.println(msg.getSid()+"\t"+msg.getSname()+ "\t"+msg.getSage()+ "\t"+ msg.getSaddress() );
		}
	}
	
	public static void updationOperation() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter student ID:: ");
		String sid = br.readLine();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student oldStudent = studentService.searchStudent(Integer.parseInt(sid));
		if(oldStudent == null)
			System.out.println("Data Not Found...");
		else 
		{
			System.out.println("Old Data :: "+oldStudent.getSid()+"\t"+oldStudent.getSname()+ "\t"+oldStudent.getSage()+ "\t"+ oldStudent.getSaddress() );
			Student newStudent = new Student();
			newStudent.setSid(oldStudent.getSid());
			System.out.println("Enter Student newName :: ");
			String sname = br.readLine();
			if (sname == "") {
				newStudent.setSname(oldStudent.getSname());
			} else {
				newStudent.setSname(sname);
			}
			System.out.println("Enter Student newAge :: ");
			String sage = br.readLine();
			if (sage == "") {
				newStudent.setSage(oldStudent.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(sage));
			}
			System.out.println("Enter Student newAddress :: ");
			String saddress = br.readLine();
			if (saddress == "") {
				newStudent.setSaddress(oldStudent.getSaddress());
			} else {
				newStudent.setSaddress(saddress);
			}
			
			String msg = studentService.updateStudent(newStudent);
			if(msg.equalsIgnoreCase("success"))
				System.out.println("Data Updated Successfully...");
			else if(msg.equalsIgnoreCase("Not Found"))
				System.out.println("Data Not Available...");
			else 
				System.out.println("Updation Failed...");
			
		}
	}
	public static void deletionOperation()
	{
		IStudentService studentService = StudentServiceFactory.getStudentService();
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter student ID:: ");
			Integer sid = scanner.nextInt();
//		scanner.close();
			String msg = studentService.deleteStudent(sid);
			if(msg.equalsIgnoreCase("success"))
				System.out.println("Data Deleted Successfully...");
			else if(msg.equalsIgnoreCase("Not Found"))
				System.out.println("Data Not Available...");
			else 
				System.out.println("Deletion Failed...");
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("\t\t\t\t\t Welcome To Student Database.");
			System.out.println("\t\t\t\t\t\t Press 1 to Create.");
			System.out.println("\t\t\t\t\t\t Press 2 to Read.");
			System.out.println("\t\t\t\t\t\t Press 3 to Update.");
			System.out.println("\t\t\t\t\t\t Press 4 to Delete.");
			System.out.println("\t\t\t\t\t\t Press 5 to Exit.");
			String option = br.readLine();
			
			switch (option) {
			case "1":
				InsertionOperation();
				break;
			case "2":
				selectionOperation();		
				break;
			case "3":
				updationOperation();
				break;
			case "4":
				deletionOperation();
				break;
			case "5":
				{
					System.out.println("\t\t\t\t*****Thanks For Using The Application*****");
					System.exit(0);
					break;
				}

			default:
				System.out.println("\t\t\t\t\t\t Please Enter Valid Input.");
				break;
			}
			
		}
	}

}

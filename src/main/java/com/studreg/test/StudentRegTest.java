package com.studreg.test;

import java.util.List;
import java.util.Scanner;

import com.google.protobuf.ByteString.Output;
import com.studreg.bo.Student;
import com.studreg.dao.AllOperations;

public class StudentRegTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int rollNo;
		String studentName;
		String address;
		String contactNo;
		String photoPath;
		AllOperations allOperation = new AllOperations();
		Student student = new Student();

		String nextor = "y";
		System.out.println("----------------Colleger Registartion -------------------");
		do {
			
			System.out.println("1. Add 2. Find 3. Delete 4. Display 5. Modify 6.Image insert");
			int i = 0;
			try {
				i = scanner.nextInt();
				switch (i) {

				case 1:

					/*
					 * System.out.println(" Enter the student RollNo"); rollNo = scanner.nextInt();
					 */

					System.out.println("Enter the student Name");
					studentName = scanner.next();

					System.out.println("Enter the student Address");
					address = scanner.next();

					System.out.println("Enter contact no");
					contactNo = scanner.next();

					//student.setRollNo(rollNo);
					student.setStudentName(studentName);
					student.setAddress(address);
					student.setContactNo(contactNo);
					int insertCount = allOperation.saveRecord(student);
					if (insertCount != 0) {
						System.out.println("Record Successfully added");
					} else {
						System.out.println("Insertion failed");
					}
					break;

				case 2:

					System.out.println("inset the roll no which you want to see the information");
					rollNo = scanner.nextInt();
					student = allOperation.selectStudentByRollNo(rollNo);

					if (student != null) {
						System.out.println("Find the student information is");
						System.out.println("  Student name : " + student.getStudentName() + "Address : " + student.getAddress()
								+ "  student contact no" + student.getContactNo());
					} else {
						System.out.println("Related information not found");
					}
					break;

				case 3:

					System.out.println("Please enter the student Roll No to be deleted");
					rollNo = scanner.nextInt();
					int result = allOperation.delete(rollNo);
					if (result != 0) {
						System.out.println("Successfully deleted");

					} else {
						System.out.println("Deletion failed");
					}
					break;
				case 4:

					List<Student> studentList = allOperation.queryAll();
					System.out.println("Student List is : ");
					System.out.println(studentList);
					break;

				case 5:
					System.out.println("Please enter the student Rollno to be updated");
					rollNo = scanner.nextInt();
					System.out.println("Please enter the student name to be updated");
					studentName = scanner.next();
					System.out.println("Please enter the address of the student to be updated");
					address = scanner.next();
					System.out.println("Please enter the conatct no of the student to be updated");
					contactNo = scanner.next();
					allOperation.update(rollNo, studentName, address, contactNo);
					break;
				case 6 :
					System.out.println("Enter PhotoPath::");
		    		photoPath=scanner.next();
				insertCount=	allOperation.addImage( photoPath);
					if(insertCount!=0) {
						System.out.println("Image inserted");  
					}
					else {
						System.out.println("image not inserted");
					}
				}
				System.out.println("Whether to continue y/n");
				nextor = scanner.next();
			} catch (Exception e) {
			}
		} while (nextor.equalsIgnoreCase("y"));
System.out.println("Exit");
	}
}

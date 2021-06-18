package com.studreg.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.conn.connection.Connectionn;
import com.studreg.bo.Student;

public class AllOperations {
	private final String INSERT_NEW_RECORD = "insert into student (roll_no,student_name,address,contact_no) values(null,?,?,?)";
	private final String UPDATE_EXIST_RECORD = "update student set student_name=? , address=? , contact_no=? where roll_no=?";
	private final String DELETE_RECORD_BY_ROLL_NO = "delete from student where roll_no=?";
	private final String SELECT_RECORD_BY_ROLL_NO = "select * from student where roll_no=?";
	private final String SELECT_ALL_RECORD = "select roll_no , student_name , address , contact_no from student";

	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet resultSet = null;
	int row = 0;
	Student student = new Student();

	/**
	 * method for inserting new record into student table
	 * 
	 * @param student it will pass the student object
	 * @return it returns the integer value i.e count of the recornd whether it is
	 *         inserted or not
	 */
	public int saveRecord(Student student) {

		try {
			connection = Connectionn.getConnection();
			pStmt = connection.prepareStatement(INSERT_NEW_RECORD, PreparedStatement.RETURN_GENERATED_KEYS);

			// pStmt.setInt(1,generatedKey );
			// pStmt.setInt(1, resultSet.getInt(1));
			pStmt.setString(1, student.getStudentName());
			pStmt.setString(2, student.getAddress());
			pStmt.setString(3, student.getContactNo());

			row = pStmt.executeUpdate();
			resultSet = pStmt.getGeneratedKeys();
			resultSet.next();
			int generatedKey = 0;
			if (resultSet.next()) {
				generatedKey = (int) resultSet.getInt(1);
				int customerId = generatedKey;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connectionn.closeAll(resultSet, pStmt, connection);
		}
		return row;

	}

	/**
	 * this method is used for update the recorde
	 * 
	 * @param rollNo    it pass the rollNo ,update the data
	 * @param name
	 * @param address
	 * @param contactNo
	 * @return it will return the int value
	 */
	public int update(int rollNo, String name, String address, String contactNo) {

		try {
			connection = Connectionn.getConnection();
			pStmt = connection.prepareStatement(UPDATE_EXIST_RECORD);
			pStmt.setString(1, name);
			pStmt.setString(2, address);
			pStmt.setString(3, contactNo);
			pStmt.setInt(4, rollNo);

			row = pStmt.executeUpdate();
			if (row > 0) {
				System.out.println("Update succeeded");
			} else {
				System.out.println("Update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connectionn.closeAll(resultSet, pStmt, connection);
		}
		return row;
	}

	/**
	 * this method is used for deleting the record from database table by passing
	 * the rollNo
	 * 
	 * @param rollNo
	 * @return int value
	 */
	public int delete(int rollNo) {

		try {
			connection = Connectionn.getConnection();
			pStmt = connection.prepareStatement(DELETE_RECORD_BY_ROLL_NO);

			pStmt.setInt(1, rollNo);

			row = pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connectionn.closeAll(resultSet, pStmt, connection);
		}
		return row;

	}

	/**
	 * this method is for fetching the record from database
	 * 
	 * @param rollNo user will pass the rollNo , for fetching the record
	 * @return it will return the integer value
	 */
	public Student selectStudentByRollNo(int rollNo) {

		try {
			connection = Connectionn.getConnection();
			pStmt = connection.prepareStatement(SELECT_RECORD_BY_ROLL_NO);

			pStmt.setInt(1, rollNo);

			resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				student = new Student();
				// student = new Student();
				student.setStudentName(resultSet.getString(2));
				student.setAddress(resultSet.getString(3));
				student.setContactNo(resultSet.getString(4));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connectionn.closeAll(resultSet, pStmt, connection);
		}
		return student;
	}

	/**
	 * this method for fetching all the record from student table
	 * 
	 * @return it will return the list object
	 */
	public List<Student> queryAll() {

		List<Student> students = new ArrayList<>();

		try {
			connection = Connectionn.getConnection();
			pStmt = connection.prepareStatement(SELECT_ALL_RECORD);

			resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setRollNo(resultSet.getInt(1));
				student.setStudentName(resultSet.getString(2));
				student.setAddress(resultSet.getString(3));
				student.setContactNo(resultSet.getString(4));

				students.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connectionn.closeAll(resultSet, pStmt, connection);
		}
		return students;

	}

	/**
	 * mehtod for adding the image into database table
	 * 
	 * @param photoPath pass the full path/location of the image
	 * @return it returnt the integert value
	 */
	public int addImage(String photoPath) {
		try {
			Connectionn.getConnection();

			pStmt = connection.prepareStatement("insert into image_table(image_no,name , image) values(null,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			// get the length of the file
			// length=file.length();
			// File file=new File("C:\\Users\\Admin\Pictures")
			File file = new File(photoPath);
			FileInputStream fs = new FileInputStream(file);
			pStmt.setString(2, "image1");
			pStmt.setBinaryStream(3, fs, (int) file.length());
			row = pStmt.executeUpdate();
			resultSet = pStmt.getGeneratedKeys();
			resultSet.next();
			int generatedKey = 0;
			if (resultSet.next()) {
				generatedKey = (int) resultSet.getInt(1);
				int customerId = generatedKey;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connectionn.closeAll(resultSet, pStmt, connection);
		}
		return row;
	}
}

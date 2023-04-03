package com.usecase.Service;

import java.util.List;

import com.usecase.payload.StudentDataTransfer;

public interface ServicesProvided {
	
	
	StudentDataTransfer createStudent(StudentDataTransfer st);
	StudentDataTransfer updateStudent(StudentDataTransfer st, long id);
	StudentDataTransfer getStudentbyId(long id);
	List<StudentDataTransfer> getAllStudents();
	void deleteStudent(long id);

}

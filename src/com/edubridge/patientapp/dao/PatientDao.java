package com.edubridge.patientapp.dao;

import java.util.List;

import com.edubridge.patientapp.model.Patient;

public interface PatientDao {
	
	int addPatient(Patient patient);
	int updatePatient(Patient patient);
	Patient searchPatient(String patientName);
	int deletePatient(String patientName);
	void deleteAllPatients();
	List<Patient> getAllPatients();

}

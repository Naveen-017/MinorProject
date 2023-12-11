package com.edubridge.patientapp.dao;

import java.util.List;
import java.util.Scanner;

import com.edubridge.patientapp.model.Patient;

public class Main {

	public static void main(String[] args) {
		PatientDao service = new PatientDaoImpl();

		Scanner s = new Scanner(System.in);
		int option = 0;
		int patientId = 0;
		int patientAge = 0;
		String patientName = null, patientDisease = null;

		do {
			System.out.println("1.ADD Patient");
			System.out.println("2.Display All patients");
			System.out.println("3.Update Patient");
			System.out.println("4.Delete Patient ");
			System.out.println("5.Search Patient");
			System.out.println("6.Delete All Patients");
			System.out.println("0.Exit");
			System.out.println("Please select one option");
			option = s.nextInt();
			switch (option) {
			case 1:
				System.out.println("Add Patient");
				System.out.println("Please Enter The PatientId");
				patientId = s.nextInt();

				System.out.println("Please Enter The PatientName");
				patientName = s.next();

				System.out.println("Please Enter The PatientAge");
				patientAge = s.nextInt();

				System.out.println("Please Enter The PatientDisease");
				patientDisease = s.next();

				Patient FreshPatient = new Patient(patientId, patientName, patientAge, patientDisease);
				int status = service.addPatient(FreshPatient);

				if (status == 1) {
					System.out.println("New Patient Is Added");
				} else {
					System.out.println("Failed To Add");
				}
				break;

			case 2:
				System.out.println("Display All Patients");
				PatientDao service1 = new PatientDaoImpl();
				service1.getAllPatients().forEach(System.out::println);

				System.out.println("---Patients Displayed----");
				break;

			case 3:
				System.out.println("Update Patient Details");
				PatientDao patientDao = new PatientDaoImpl();

				Patient updatedPatient = new Patient(patientId, patientName, patientAge, patientDisease);
				System.out.println("Please Enter PatientId");
				patientId = s.nextInt();
				updatedPatient.setPatientId(patientId);
				System.out.println("Please Enter PatientName");
				patientName = s.next();
				updatedPatient.setPatientName(patientName);
				System.out.println("Please Enter The PatientAge");
				patientAge = s.nextInt();
				updatedPatient.setPatientAge(patientAge);
				System.out.println("Please Enter The PatientDisease");
				patientDisease = s.next();
				updatedPatient.setPatientDisease(patientDisease);

				int updateStatus = patientDao.updatePatient(updatedPatient);

				if (updateStatus > 0) {
					System.out.println("***....Patient details updated successfully....***");
				} else {
					System.out.println("***....Failed to update patient details....***");
				}
				break;

			case 4:
				System.out.println("Delete patient");
				System.out.println("Enter patient name to delete patientdetails");
				String sql = s.next();
				int deleteStatus = service.deletePatient(sql);
				if (deleteStatus == 1) {
					System.out.println("patientdetails deleted sucessfully");
				} else {
					System.out.println("patientName not found to delete");

				}
				break;

			case 5:
				System.out.println("Serach Patient Details");
				System.out.println("Enter The PatientName To Search");
				patientName = s.next();
				PatientDao dao = new PatientDaoImpl();

				Patient searchPatient = dao.searchPatient(patientName);
				if (searchPatient != null) {
					System.out.println("patientId      : " +  searchPatient.getPatientId());
					System.out.println("patientName    : " +  searchPatient.getPatientName());
					System.out.println("patientAge     : " +  searchPatient.getPatientAge());
					System.out.println("patientDisease : " +  searchPatient.getPatientDisease());
				} else {
					System.out.println("Patient Not Found");
				}

				break;
				

			case 6:
				System.out.println("Delete All Patientdetails");
				service.deleteAllPatients();
				System.out.println("All Patients Details Are Deleted Sucessfully");
				break;
				
			}

		} while (option != 0);

	}

}

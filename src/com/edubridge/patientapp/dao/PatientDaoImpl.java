package com.edubridge.patientapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edubridge.patientapp.connection.DataBase;
import com.edubridge.patientapp.model.Patient;

public class PatientDaoImpl implements PatientDao {
	private static Connection con = DataBase.getConnection();

	@Override
	public int addPatient(Patient patient) {
		String sql = "INSERT INTO patient values(?,?,?,?)";
		PreparedStatement ps = null;
		int status =0;
		try {
			ps =  con.prepareStatement(sql);
			ps.setInt(1, patient.getPatientId());
			ps.setString(2, patient.getPatientName());
			ps.setInt(3, patient.getPatientAge());
			ps.setString(4, patient.getPatientDisease());
			 status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int updatePatient(Patient patient) {
        int updateStatus = 0;
        String updateQuery = "UPDATE patient SET patientName=?, patientAge=?, patientDisease=? WHERE patientId=?";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, patient.getPatientName());
            preparedStatement.setInt(2, patient.getPatientAge());
            preparedStatement.setString(3, patient.getPatientDisease());
            preparedStatement.setInt(4, patient.getPatientId());

            updateStatus = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return updateStatus;
   
	      
	    }
	

	@Override
	public int deletePatient(String patientName) {
	String sql = "DELETE FROM patient WHERE patientName =?";
	PreparedStatement ps = null;
	int status = 0;
	try {
		ps = con.prepareStatement(sql);
		ps.setString(1, patientName);
		status = ps.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return status;
	}

	@Override
	public void deleteAllPatients() {
		String sql = "TRUNCATE TABLE patient";
		int status;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return ;
		
	}

	@Override
	public List<Patient> getAllPatients() {
		String sql = "SELECT patientId,patientName,patientAge,patientDisease FROM patient";
		PreparedStatement ps = null;
		List<Patient> patients = new ArrayList<Patient>();
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				
			
			Patient patient = new Patient();
			patient.setPatientId(rs.getInt("patientId"));
			patient.setPatientName(rs.getString("patientName"));
			patient.setPatientAge(rs.getInt("patientAge"));
			patient.setPatientDisease(rs.getString("patientDisease"));
			patients.add(patient);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patients;
	}

	@Override
	public Patient searchPatient(String patientName) {
		
			Patient patient = new Patient();
			String sql = "SELECT * FROM patient WHERE patientName LIKE ?";
			PreparedStatement ps = null;
			try {
			ps = con.prepareStatement(sql);
			ps.setString(1,"%"+patientName+"%");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				patient = new Patient();
				patient.setPatientId(rs.getInt("patientId"));
				patient.setPatientName(rs.getString("patientName"));
				patient.setPatientAge(rs.getInt("patientAge"));
				patient.setPatientDisease(rs.getString("patientDisease"));
				
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return patient;
	
		}
	}



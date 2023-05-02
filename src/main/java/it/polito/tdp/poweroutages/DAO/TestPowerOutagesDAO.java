package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.util.*;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.NercIdMap;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
	
		NercIdMap nercIdMap = new NercIdMap();
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			List<Nerc> nercList = dao.getNercList(nercIdMap);
			System.out.println(dao.getNercList(nercIdMap)) ;
			
			Nerc n = nercList.get(2);
			System.out.println(dao.getPowerOutagesList(n,nercIdMap));

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}

package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.NercIdMap;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList(NercIdMap nercIdMap) {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(nercIdMap.get(n));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getPowerOutagesList(Nerc nerc,NercIdMap nercIdMap) {

		String sql = "SELECT * "
				+ "FROM poweroutages "
				+ "WHERE nerc_id=?";
		List<PowerOutages> powerOutagesList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutages po = new PowerOutages(res.getInt("id"),res.getInt("event_type_id"),res.getInt("tag_id"),res.getInt("area_id"),nercIdMap.get(nerc),res.getInt("responsible_id"),res.getInt("customers_affected"),res.getTimestamp("date_event_began").toLocalDateTime(),res.getTimestamp("date_event_finished").toLocalDateTime(),res.getInt("demand_loss"));
				powerOutagesList.add(po);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutagesList;
	}
	
}

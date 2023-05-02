package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PowerOutages implements Comparable<PowerOutages>{
	
	private int id;
	private int event_type_id;
	private int tag_id;
	private int area_id;
	private Nerc nerc;
	private int responsible_id;
	private int customers_affected;
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	private int demand_loss;
	private int anno;

	public PowerOutages(int id, int event_type_id, int tag_id, int area_id, Nerc nerc, int responsible_id,
			int customers_affected, LocalDateTime date_event_began, LocalDateTime date_event_finished, int demand_loss) {
		super();
		this.id = id;
		this.event_type_id = event_type_id;
		this.tag_id = tag_id;
		this.area_id = area_id;
		this.nerc = nerc;
		this.responsible_id = responsible_id;
		this.customers_affected = customers_affected;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.demand_loss = demand_loss;
		this.anno = this.date_event_began.getYear();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}

	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}

	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		return id == other.id;
	}

	public int calcolaOre() {
		LocalDateTime tempDateTime = LocalDateTime.from(date_event_began);
		return (int) tempDateTime.until(date_event_finished, ChronoUnit.HOURS);
	}
	
	@Override
	public String toString() {
		return ""+id;
	}

	@Override
	public int compareTo(PowerOutages o) {
		return this.date_event_began.compareTo(o.date_event_began);
	}
	
}

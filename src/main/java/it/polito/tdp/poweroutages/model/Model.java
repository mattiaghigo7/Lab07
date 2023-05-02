package it.polito.tdp.poweroutages.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<Nerc> nerc;
	private NercIdMap nercIdMap;
	private List<PowerOutages> eventi;
	private List<PowerOutages> peggiore;
	private int maxPersone;
	private int maxYears;
	private int maxHours;
	private int totHours;

	public Model() {
		podao = new PowerOutageDAO();
		this.nercIdMap=new NercIdMap();
		this.nerc = podao.getNercList(nercIdMap);
	}
	
	public void setEventi(Nerc n) {
		eventi = podao.getPowerOutagesList(n,nercIdMap);
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList(nercIdMap);
	}
	
	public List<PowerOutages> creaPeggiore(Nerc n, int maxYears, int maxHours) {
		this.setEventi(n);
		peggiore = new LinkedList<>();
		this.maxPersone=Integer.MIN_VALUE;
		this.maxYears=maxYears;
		this.maxHours=maxHours;
		this.totHours=0;
		List<PowerOutages> parziale = new LinkedList<>();
		cerca(parziale,0);
		peggiore.sort(null);
		return peggiore;
	}

	private void cerca(List<PowerOutages> parziale, int livello) {
		if(this.sommaPersone(parziale)>this.maxPersone) {
			peggiore = new LinkedList<>(parziale);
			maxPersone = this.sommaPersone(parziale);
			totHours=this.oreTotali(parziale);
		}
		for(PowerOutages p : eventi) {
			if(!parziale.contains(p)) {
				parziale.add(p);
				if(controllaAnni(parziale) && controllaOre(parziale)) {
					cerca(parziale,livello+1);
				}
				parziale.remove(p);
			}
		}
	}

	private int oreTotali(List<PowerOutages> parziale) {
		int ore = 0;
		for(PowerOutages p : parziale) {
			ore=ore+p.calcolaOre();
		}
		return ore;
	}

	private boolean controllaAnni(List<PowerOutages> parziale) {
		int maxAnno = Integer.MIN_VALUE;
		int minAnno = Integer.MAX_VALUE;
		for(PowerOutages p : parziale) {
			if(p.getAnno()>maxAnno) {
				maxAnno = p.getAnno();
			}
		}
		for(PowerOutages p : parziale) {
			if(p.getAnno()<minAnno) {
				minAnno = p.getAnno();
			}
		}
		if((maxAnno-minAnno)<=this.maxYears) {
			return true;
		}
		return false;
	}

	private boolean controllaOre(List<PowerOutages> parziale) {
		int ore = oreTotali(parziale);
		if (ore<=this.maxHours){
			return true;
		}
		return false;
	}
	
	private int sommaPersone(List<PowerOutages> parziale) {
		int somma = 0;
		for(PowerOutages p : parziale) {
			somma=somma+p.getCustomers_affected();
		}
		return somma;
	}
	
	public int getMaxPersone() {
		return maxPersone;
	}

	public int getTotHours() {
		return totHours;
	}

}

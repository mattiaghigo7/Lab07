package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
//		List<Nerc> n = model.getNercList();
//		System.out.println(n);
		
		Nerc n = new Nerc(13,"MAIN");
		System.out.println(model.creaPeggiore(n, 3, 3));
	}

}

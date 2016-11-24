package app.boutique.model;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import app.boutique.dto.Produit;

public class Panier {

	private List<Produit> items = new ArrayList<Produit>();

	private double totalTPS=0.0;
	private double totalTVQ = 0.0;
	private double TPS = 0.05;
	private double TVQ = 0.095;
	private double total=0.0;
	private double totalHT=0.0;

	public double arrondiDouble(double val){
		DecimalFormat df=new DecimalFormat("0.0");
		String formate = df.format(val); 
		try {
			return (Double)df.parse(formate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block

		}
		return 0.0;
	}
	
	public void addPanier (Produit produit){
		boolean ok= false;
		if (this.getItems().size()>0)
			for(Produit item : this.getItems()){
				if (item.getIdProduit()==produit.getIdProduit()){
					ok= true;
					Produit prodPanier = item;
					item.setQuantitePanier(prodPanier.getQuantitePanier() + produit.getQuantitePanier() );
					System.out.print(produit.getQuantitePanier());
 					produit.setPrixTotal(item.getQuantitePanier() *produit.getPrix());
					this.items.get(this.items.indexOf(item)).setPrixTotal(produit.getPrixTotal());
				}
			}
		if (ok == false){
			produit.setPrixTotal(produit.getQuantitePanier() *produit.getPrix());
			items.add(produit);
		}
		totalHT= totalHT + (produit.getPrixTotal());		
		totalTPS =  TPS * totalHT;
		totalTVQ = arrondiDouble(TVQ * totalHT);
		total =  arrondiDouble(totalHT + totalTVQ + totalTPS) ;
	}

	public void addQuantite (Produit produit){
		produit.setQuantitePanier(produit.getQuantitePanier()  + 1);
	}

	public void removePanier (Produit produit){
		items.remove(produit);
		totalTPS = totalTPS - (produit.getPrix()*produit.getQuantitePanier());
		totalTVQ = totalTVQ - (produit.getPrix()*produit.getQuantitePanier());
	}

	public Panier() {
		super();
	}

	public List<Produit> getItems() {
		return items;
	}

	public void setItems(List<Produit> items) {
		this.items = items;
	}

	public double getTotalTPS() {
		return totalTPS;
	}


	public void setTotalTPS(double totalTPS) {
		this.totalTPS = totalTPS;
	}

	public double getTotalTVQ() {
		return totalTVQ;
	}

	public void setTotalTVQ(double totalTVQ) {
		this.totalTVQ = totalTVQ;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalHT() {
		return totalHT;
	}

	public void setTotalHT(double totalHT) {
		this.totalHT = totalHT;
	}

}


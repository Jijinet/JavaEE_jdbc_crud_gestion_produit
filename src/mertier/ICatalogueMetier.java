package mertier;

import java.util.List;

public interface ICatalogueMetier {
	
	public void addProduit(Produit p);
	public List<Produit> ListProduits();
	public List<Produit> ListProduitsParMC(String mc);
	public Produit getProduit(String ref);
	public void updateProuit (Produit p);
	public void deleteProuit (String ref);

}

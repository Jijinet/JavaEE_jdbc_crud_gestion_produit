package web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mertier.CatalogueMetierImpl;
import mertier.ICatalogueMetier;
import mertier.Produit;

@WebServlet(name="gp",urlPatterns ="/controleur")
public class ControleurServlet extends HttpServlet {
	
	ICatalogueMetier metier;
	
	@Override
	public void init() throws ServletException {
		metier= new CatalogueMetierImpl();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProduitModel model= new ProduitModel();
		request.setAttribute("model", model);
		String action=request.getParameter("action");
		
		if(action!=null) {
			
			if(action.equals("chercher")) {
				model.setMotCle(request.getParameter("mc"));
				List<Produit> produits=metier.ListProduitsParMC(model.getMotCle());
				model.setProduits(produits);
			}
			
			else if(action.equals("delete")) {
				String ref = request.getParameter("ref");
				metier.deleteProuit(ref);
				model.setProduits(metier.ListProduits());
				
			}
			
			else if(action.equals("modifier")) {
				try {
					
				String ref = request.getParameter("ref");
				Produit p =metier.getProduit(ref);
				model.setProduit(p);
				
				model.setMode("modifier");
				
				model.setProduits(metier.ListProduits());
				
				}catch(Exception e) {
					model.setError(e.getMessage());
				}
			}
			
			else if(action.equals("save")) {
				try {
				model.getProduit().setReference(request.getParameter("ref"));
				model.getProduit().setDesignation(request.getParameter("designation"));
				model.getProduit().setPrix(Double.parseDouble(request.getParameter("prix")));
				model.getProduit().setQuantite(Integer.parseInt(request.getParameter("qte")));
				
				model.setMode(request.getParameter("mode"));
				
				if(model.getMode().equals("modifier")) metier.updateProuit(model.getProduit());
				else if(model.getMode().equals("ajouter")) metier.addProduit(model.getProduit());
				
				model.setProduits(metier.ListProduits());
				}catch(Exception e) {
					model.setError(e.getMessage());
				}
			}
			
			
			
		}
			
		
		
		
		request.getRequestDispatcher("VueProduit.jsp").forward(request, response);
		
	
	}

}

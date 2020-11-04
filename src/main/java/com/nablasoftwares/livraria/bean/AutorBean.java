package com.nablasoftwares.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import com.nablasoftwares.livraria.dao.DAO;
import com.nablasoftwares.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
}

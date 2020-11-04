package com.nablasoftwares.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import java.util.List;

import com.nablasoftwares.livraria.dao.DAO;
import com.nablasoftwares.livraria.modelo.Autor;
import com.nablasoftwares.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;
	

	public Livro getLivro() {
		return livro;
	}
	
	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public void adicionarAutor() {

		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		System.out.println("Usando autor " + autor.getNome());
		this.livro.adicionaAutor(autor);
	}

	
	public String formAutor() {
		System.out.println("Chamando o formulario do autor");
		return "autor?faces-redirect=true";
	}
	
	public void remover(Livro livro) {
		System.out.println("Removendo livro...");
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void removerAutorDoLivro(Autor autor) {
		System.out.println("removendo autor...");
		this.livro.removeAutor(autor);
	}
	
	public void carregar(Livro livro) {
		System.out.println("carregando...");
		this.livro = livro;
	}

	public void gravar() {
		System.out.println("salvando livro " + this.livro.getTitulo());
		
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Campo autor não pode estar vazio!") );
			return;
		}
		
		if(this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}
		else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}

		
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deve Iniciar com digito 1"));
		}
		
	}
	


	public Integer getAutorId() {
		return autorId;
	}


	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	

}

package br.com.text.bancodedados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.text.xml.Produto;

public class NomeService {
	private static List<Produto> lista = new ArrayList<Produto>();
	private static int id = 0;
	private static NomeService thisClass = null;

	private NomeService() {
	}

	public static NomeService getInstance() {
		if (thisClass == null)
			thisClass = new NomeService();

		return thisClass;
	}

	public List<Produto> listaTodos() {
		return Collections.unmodifiableList(lista);
	}
	
	public List<Produto> buscar(String nome) {	
		return lista.stream()
				.filter(obj -> obj.getNome().equals(nome))
				.collect(Collectors.toList());
	}

	public Produto buscar(int chave) {
		int index = lista.indexOf(new Produto(chave));
		
		if(index >= 0)
			return lista.get(index);
		else 
			return null;
	}

	public Produto salvar(Produto obj) {
		Produto novo = null;

		if (obj.getId() == Produto.NOVO_REGISTRO) {
			// retorna um objeto válido com o ID atribuido
			novo = obj;
			novo.setId(++id);
			lista.add(novo);
		} else {
			if (lista.contains(obj)) {
				novo = lista.get(lista.indexOf(obj));
			}
		}
		
		return novo;
	}

	public void remover(int chave, long data) {
		int index =  lista.indexOf(new Produto(chave));
		
		if(index >= 0) {
			Produto obj = lista.get(index);
			
		}
	}

	public void remover(int chave) {
//		int index =  lista.indexOf(new Nome(chave));
//		
//		if(index >= 0)
//			lista.remove(index);
		
		lista.removeIf(reg -> reg.getId() == chave);
	}

	public int quantos() {
		return lista.size();
	}
}

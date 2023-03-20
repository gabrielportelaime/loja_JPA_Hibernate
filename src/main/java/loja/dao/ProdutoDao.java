package loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
		// Busca por ID, encontra apenas uma entidade
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		// JPQL é diferente da linguagem SQL comum
		// JPQL é como se fosse uma linguagem SQL orientada a objetos
		return em.createQuery(jpql, Produto.class).getResultList();
		// Produto.class serve para dizer qual a classe que será devolvida pela Query
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		// no lugar de :nome poderia ser ?1 e no setParameter colocar 1 no lugar de nome
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome)
				// Serve para substituir na Query o "nome" recebido pelo método
				.getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		// Não é necessário carregar a entidade inteira, pode ser devolvido apenas 1
		// atribudo da entidade
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
		// O getSingleResult serve para retornar apenas 1 entidade/registro/resultado
	}
}

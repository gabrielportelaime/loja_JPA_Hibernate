package loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import loja.dao.CategoriaDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Produto;
import loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p2.getNome()));

		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi");
		System.out.println("Preço do produto: " + precoDoProduto);

	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi", "Xiaomi Redmi", new BigDecimal("800"), celulares);

		// EntityManager em = factory.createEntityManager();
		// Fábrica cria a Entidade para gerenciar a comunicação com o banco de dados

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		// Começa a conexão com o banco
		// Como estamos usando RESOURCE_LOCAL, então temos que começar e commitar a
		// operaçãono banco de dados, caso fosse usado JPA, não seria necessário
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);

		em.getTransaction().commit();
		// Commita a operação feita
		em.close();
		// Fecha conexão
	}

}

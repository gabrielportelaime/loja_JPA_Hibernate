package loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Produto;
import loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto celular = new Produto("Xiaomi", "Xiaomi Redmi", new BigDecimal("800"), Categoria.CELULARES);

		// EntityManager em = factory.createEntityManager();
		// Fábrica cria a Entidade para gerenciar a comunicação com o banco de dados

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		em.getTransaction().begin();
		// Começa a conexão com o banco
		// Como estamos usando RESOURCE_LOCAL, então temos que começar e commitar a
		// operaçãono banco de dados, caso fosse usado JPA, não seria necessário
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		// Commita a operação feita
		em.close();
		// Fecha conexão
	}

}

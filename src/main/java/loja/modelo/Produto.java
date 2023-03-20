package loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
//Mapeamento da entidade e das colunas
//@Table serve para informar que o nome da coluna no banco de 
//dados é diferente do nome da classe/entidade na aplicação
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// A geração da primarykey depende do banco de dados
	private Long id;
	private String nome;
	// @Column (name = "desc") se o atributo for diferente do nome da coluna
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();
	// Deve ser colocado para o JPA entender que o enum deve ser mapeado por String
	// e não por ints, isso bagunçaria o caso alguém trocasse a ordem das constantes
	// dentro do enum

	@ManyToOne
	// ManyToOne, OneToMany, ManyToMany
	private Categoria categoria;

	public Produto() {

	}

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}
}

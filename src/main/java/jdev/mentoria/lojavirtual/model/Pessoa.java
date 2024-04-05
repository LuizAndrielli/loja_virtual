package jdev.mentoria.lojavirtual.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", initialValue = 1, allocationSize = 1)
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	private Long id;

	private String nome;
	private String email;
	private String telefone;

	
	/*Mapeado pelo atributo pessoa em Endereco, orphan = quando uma entidade relacionada (um "filho") não tem mais uma referência para a entidade pai, ela deve ser removida automaticamente do banco de dados
	 * , cascade = significa que todas as operações de persistência (como salvar, atualizar, excluir) realizadas na entidade pai também serão aplicadas automaticamente à entidade filho.
     , fetch = determina a estratégia de carregamento dos objetos associados. FetchType.LAZY significa que os objetos relacionados serão carregados sob demanda, apenas quando forem acessados explicitamente, o que pode melhorar o desempenho em casos onde nem sempre os objetos relacionados são necessários
	 * */
	@OneToMany(mappedBy = "pessoa",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Endereco> enderecos = new ArrayList<>();
	
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

}

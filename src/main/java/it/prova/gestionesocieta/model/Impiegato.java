package it.prova.gestionesocieta.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "impiegato")
public class Impiegato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "ral")
	private Integer ral;
	@Column(name = "dataassunzione")
	private Date dataAssunzione;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "societa_id", nullable = false)
	private Societa societa;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "impiegato_progetto", joinColumns = @JoinColumn(name = "impiegato_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "progetto_id", referencedColumnName = "ID"))
	private Set<Progetto> progetti = new HashSet<Progetto>();

	public Impiegato() {
		super();
	}

	public Impiegato(String nome, String cognome, Integer ral, Date dataAssunzione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.ral = ral;
		this.dataAssunzione = dataAssunzione;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getRal() {
		return ral;
	}

	public void setRal(Integer ral) {
		this.ral = ral;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Societa getSocieta() {
		return societa;
	}

	public void setSocieta(Societa societa) {
		this.societa = societa;
	}

	public Set<Progetto> getProgetti() {
		return progetti;
	}

	public void setProgetti(Set<Progetto> progetti) {
		this.progetti = progetti;
	}

}

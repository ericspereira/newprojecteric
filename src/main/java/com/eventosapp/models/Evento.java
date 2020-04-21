package com.eventosapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Evento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@NotNull(message="Campo não pode ser vazio")
	@Size(min=2, max=100, message="No minimo 2 letras")
	private String nome;
	
	@NotNull(message="Campo não pode ser vazio")
	@Size(min=2, max=100, message="No minimo 2 letras")
	private String local;
	
	@NotNull(message="Campo não pode ser vazio")
	@Size(min=2, max=100, message="No minimo 2 letras")
	private String data;
	
	@NotNull(message="Campo não pode ser vazio")
	@Size(min=2, max=100, message="No minimo 2 letras")
	private String horario;
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Convidado> convidados;
	
	public List<Convidado> getConvidados() {
		return convidados;
	}
	
	public void setConvidados(List<Convidado> convidados) {
		this.convidados = convidados;
		
	}
	public long getCodigo() {
		return codigo;
		
	}	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}

}
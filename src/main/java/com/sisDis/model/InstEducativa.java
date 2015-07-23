package com.sisDis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="centros_educativos")
public class InstEducativa {
	@Id 
	@Column(name="Identificador")
	private Integer identificador;
	@Column(name="Sector")
	private String sector;
	@Column(name="Ugel")
	private Integer ugel;
	@Column(name="Institucion")
	private String institucion;
	@Column(name="Direccion")
	private String direccion;
	@Column(name="Distrito")
	private String distrito;
	
	public InstEducativa(){}
	public InstEducativa(Integer identificador, String sector, Integer ugel, String institucion,
			String direccion, String distrito) {
		super();
		this.identificador = identificador;
		this.sector = sector;
		this.ugel=ugel;
		this.institucion = institucion;
		this.direccion = direccion;
		this.distrito = distrito;
	}
	public Integer getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public Integer getUgel() {
		return ugel;
	}
	public void setUgel(Integer ugel) {
		this.ugel = ugel;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

}
package modelo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Carrera {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private String nombre;
	@Persistent private String descripcion;
	@Persistent private int semestres; //Duracion de la Carrera en Semestres;
	@Persistent private int mensualidad;//Pago del alumno mensual por estudiar la carrera
	
	public Carrera(String nombre, String descripcion,int semestres, int mensualidad) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.semestres = semestres;
		this.mensualidad = mensualidad;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getSemestres() {
		return semestres;
	}

	public void setSemestres(int semestres) {
		this.semestres = semestres;
	}

	public int getMensualidad() {
		return mensualidad;
	}

	public void setMensualidad(int mensualidad) {
		this.mensualidad = mensualidad;
	}
	
	
	
	
	
}

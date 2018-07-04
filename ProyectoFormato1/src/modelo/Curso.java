package modelo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Curso {

	public static final int PRIMER_SEMESTRE = 1;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private Long idCarrera;
	@Persistent private Long idProfesor;
	@Persistent private String nombre;
	@Persistent private int semestre; //En que semestre se dicta el Curso
	
	public Curso( Long idCarrera, Long idProfesor, String nombre, int semestre) {
		super();
		this.idCarrera = idCarrera;
		this.idProfesor = idProfesor;
		this.nombre = nombre;
		this.semestre = semestre;
	}
	public Long getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}
	public Long getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	
}

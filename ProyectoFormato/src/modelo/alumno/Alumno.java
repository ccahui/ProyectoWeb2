package modelo.alumno;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Alumno {
	
	public static final int PRIMER_SEMESTRE = 1;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
    @Persistent private String nombre;
    @Persistent private String apellido;
    @Persistent private	int dni;
    @Persistent private String gmail; //Con el cual inician session
    @Persistent private Long idCarrera;
    @Persistent private int semestre;
    @Persistent private boolean estado;//Activo Inactivo
    @Persistent private Long idRol;

	public Alumno(String nombre, String apellido, int dni, String gmail, Long idCarrera, int semestre, boolean estado,
			Long idRol) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.gmail = gmail;
		this.idCarrera = idCarrera;
		this.semestre = semestre;
		this.estado = estado;
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	

}

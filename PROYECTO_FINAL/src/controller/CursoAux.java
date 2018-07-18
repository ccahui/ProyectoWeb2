package controller;

public class CursoAux implements Comparable<CursoAux>{

	private Long id;
	private String nombre;
	private int cantidadAlumnos;
	
	public CursoAux(Long id,String nombre,int cantidadAlumnos){
		this.id = id;
		this.nombre = nombre;
		this.cantidadAlumnos = cantidadAlumnos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidadAlumnos() {
		return cantidadAlumnos;
	}
	public void setCantidadAlumnos(int cantidadAlumnos) {
		this.cantidadAlumnos = cantidadAlumnos;
	}
	public String toString(){
		return nombre+" "+cantidadAlumnos; 
	}
	@Override
	public int compareTo(CursoAux curso) {
		// TODO Auto-generated method stub
		return curso.getCantidadAlumnos() - cantidadAlumnos;
	}
}

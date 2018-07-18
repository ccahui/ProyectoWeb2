package model.entity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Factura {
	public static final String[] MESES ={"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SETIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private Long idAlumno;
	@Persistent private int mes; // 
	@Persistent private int monto;
	@Persistent private Date fecha; //Fecha que realizo el Pago

	public Factura(Long idAlumno, int mes, int monto, Date fecha) {
		super();
		this.idAlumno = idAlumno;
		this.mes = mes;
		this.monto = monto;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Long idAlumno) {
		this.idAlumno = idAlumno;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String mesDescripcion(){
		return MESES[mes-1];
	}
	public static String mesDescripcion(int i){
		return MESES[i-1];
	}
	public String fechaDescripcion(){

		SimpleDateFormat format=new SimpleDateFormat("dd MMMM 'del' YYYY  HH:mm:s ");
		format.setTimeZone(TimeZone.getTimeZone("America/Lima"));
		return format.format(fecha);
	}
}

package modelo.factura;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


public class Factura {
	
	public final static int ENERO = 1; // 2 3 4 5 6 7 8 9 10 11 12 Mes de PAGO
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private Long idAlumno;
	@Persistent private int mes; // 
	@Persistent private int monto;
	@Persistent private Date fecha; //Fecha que realizo el Pago
	
	public Factura(Long id, Long idAlumno, int mes, int monto, Date fecha) {
		super();
		this.id = id;
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
	
	
	
}

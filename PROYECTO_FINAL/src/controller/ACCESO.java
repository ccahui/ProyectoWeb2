package controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import model.entity.Access;
import model.entity.Resource;
import model.entity.Users;
import model.entity.Alumno;
import model.entity.Profesor;

public final class ACCESO {

	public static final boolean accesoRecurso(String gmail,String url){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Users.class);
		query.setFilter("email == idParam");
		query.declareParameters("String idParam");

		List<Users> array = (List<Users>) query.execute(gmail);

		if(array.size()> 0 ){

			Users usuario = array.get(0);
			Query query1 = pm.newQuery(Resource.class);
			query1.setFilter("url == idParam");
			query1.declareParameters("String idParam");

			List<Resource> arrayRecurso = (List<Resource>)query1.execute(url);


			if(arrayRecurso.size() > 0 ){

				Resource recurso = arrayRecurso.get(0);

				Long idRol = usuario.getIdRol();
				Long idRecurso = recurso.getId();

				Query query2 = pm.newQuery(Access.class);
				query2.setFilter("idRole == idParam && idRecurso == idParam2");
				query2.declareParameters("Long idParam , Long idParam2");
				System.out.println("das");

				List<Access> arrayAcceso = (List<Access>)query2.execute(idRol,idRecurso);

				if(arrayAcceso.size()>0){
					Access acceso = arrayAcceso.get(0);
					if(acceso.isEstado()){
						pm.close();
						return true;
					}
				}
			}

		}
		pm.close();
		return false;
	}
	public static final Alumno accesoAlumno(String gmail){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Alumno.class);
		query.setFilter("gmail == idParam");
		query.declareParameters("String idParam");

		List<Alumno> array = (List<Alumno>) query.execute(gmail);

		if(array.size()> 0 ){

			Alumno alumno = array.get(0);
			return alumno;

		}
		pm.close();
		return null;
	}	
	public static final Profesor accesoProfesor(String gmail){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Profesor.class);
		query.setFilter("gmail == idParam");
		query.declareParameters("String idParam");

		List<Profesor> array = (List<Profesor>) query.execute(gmail);
		
		if(array.size()> 0 ){
			Profesor profesor = array.get(0);
			if(profesor.isEstado()){
				pm.close();
				return profesor;
			}
		}
		pm.close();
		return null;
	}

}

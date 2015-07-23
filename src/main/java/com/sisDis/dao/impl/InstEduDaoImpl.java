package com.sisDis.dao.impl;

import java.util.List;

import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sisDis.dao.InstEducDAO;
import com.sisDis.model.InstEducativa;

@Repository
public class InstEduDaoImpl implements InstEducDAO {

	@Autowired
	private SessionFactory session;

	
	public InstEducativa obtenerInst(Integer colegioId) {
		return (InstEducativa)session.getCurrentSession().get(InstEducativa.class, colegioId);
	}

	
	public List<InstEducativa> obtenerlistaInst() {
		return session.getCurrentSession().createQuery("from InstEducativa").list();
		
	}
	      

	
	public List<InstEducativa> obtenerlistaInstN(String nombre) {
		
		String hql="from InstEducativa  cole where cole.institucion like :cole_nombre";
		
		Query query=session.getCurrentSession().createQuery(hql);
		query.setParameter("cole_nombre", nombre + "%");	
			
		return query.list();
		
	}

	
	public List<InstEducativa> obtenerlistaInstU(Integer ugel) {
		
		String hql="from InstEducativa  cole where cole.ugel= :cole_ugel";
		
		Query query=session.getCurrentSession().createQuery(hql);
		query.setParameter("cole_ugel", ugel);
				
		return query.list();
		
	}

	
	public List<InstEducativa> obtenerlistaInstD(String distrito) {
		
		String hql="from InstEducativa  cole where cole.distrito like :cole_distrito";
		Query query=session.getCurrentSession().createQuery(hql);
		query.setParameter("cole_distrito", distrito + "%");	
				
		return query.list();
		
	}

}

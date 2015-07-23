package com.sisDis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sisDis.dao.InstEducDAO;
import com.sisDis.model.InstEducativa;
import com.sisDis.service.InsEducService;

@Service
public class InstEducServiceImpl implements InsEducService {

	@Autowired
	private InstEducDAO instEducDao;


	@Transactional
	public InstEducativa obtenerInst(Integer colegioId) {
		return instEducDao.obtenerInst(colegioId);
	}

	@Transactional
	public List<InstEducativa> obtenerlistaInst() {
		return instEducDao.obtenerlistaInst();
	}

	@Transactional
	public List<InstEducativa> obtenerlistaInstN(String nombre) {
		return instEducDao.obtenerlistaInstN(nombre);
	}

	@Transactional
	public List<InstEducativa> obtenerlistaInstU(Integer ugel) {
		return instEducDao.obtenerlistaInstU(ugel);
	}

	@Transactional
	public List<InstEducativa> obtenerlistaInstD(String distrito) {
		return instEducDao.obtenerlistaInstD(distrito);
	}




}

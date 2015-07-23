package com.sisDis.dao;

import java.util.List;
import com.sisDis.model.InstEducativa;

public interface InstEducDAO {

	public InstEducativa obtenerInst(Integer colegioId);
	public List<InstEducativa> obtenerlistaInst();
	public List<InstEducativa> obtenerlistaInstN(String nombre);
	public List<InstEducativa> obtenerlistaInstU(Integer ugel);
	public List<InstEducativa> obtenerlistaInstD(String distrito);
}

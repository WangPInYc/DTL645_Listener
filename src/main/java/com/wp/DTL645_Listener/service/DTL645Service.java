package com.wp.DTL645_Listener.service;

import java.util.List;

import com.wp.DTL645_Listener.dataUtils.Electric;
import com.wp.DTL645_Listener.dataUtils.ElectricDAO;


public class DTL645Service implements IDtl645Service {
	ElectricDAO dao = new ElectricDAO();
	@Override
	public void save(Electric transientInstance) {
		// TODO Auto-generated method stub
		dao.save(transientInstance);
	}
	@Override
	public void attachDirty(Electric transientInstance) {
		// TODO Auto-generated method stub
		dao.attachDirty(transientInstance);
	}
	@Override
	public void delete(Electric persistentInstance) {
		// TODO Auto-generated method stub
		dao.delete(persistentInstance);
	}
	@Override
	public Electric findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Electric> findByExample(Electric instance) {
		// TODO Auto-generated method stub
		return dao.findByExample(instance);
	}

}

package com.wp.DTL645_Listener.service;

import java.util.List;

import com.wp.DTL645_Listener.dataUtils.Electric;


public interface IDtl645Service {
	
	public void save(Electric transientInstance);
	public void attachDirty(Electric transientInstance);
	public void delete(Electric persistentInstance);
	public Electric findById( java.lang.Integer id);
	public List<Electric> findByExample(Electric instance);
}

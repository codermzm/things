package com.things.frame.core.base.controller;

import com.things.frame.core.base.controller.result.AbstractResult;
import com.things.frame.core.base.entity.Identifiable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

public interface IBaseController<T extends Identifiable> {

	public ModelAndView addOne(T entity);

	public ModelAndView addView();

	public ModelAndView selectList(T query, Pageable pageable);

	public ModelAndView viewOne(String id);

	public ModelAndView editView(String id);
	
	public AbstractResult editOne(T entity);
	
	public AbstractResult deleteList(String[] ids);

	public AbstractResult deleteOne(String id);
	
}

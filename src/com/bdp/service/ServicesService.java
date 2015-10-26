package com.bdp.service;

import javax.servlet.http.HttpServletRequest;

/**
 * hadoop服务等服务层接口
 * @author xuend
 *
 */
public interface ServicesService {

	void add(HttpServletRequest request);

	void list(HttpServletRequest request);

}

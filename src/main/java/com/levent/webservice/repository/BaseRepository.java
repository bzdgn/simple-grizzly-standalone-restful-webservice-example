package com.levent.webservice.repository;

import java.util.List;

/*
 * The base repository that all repository interfaces must extend
 */
public interface BaseRepository<T> {
	
	T find(long id) throws Exception;
	List<T> findAll() throws Exception;
	T create(T t) throws Exception;
	boolean update(T t) throws Exception;
	boolean delete(long id) throws Exception;
	
}

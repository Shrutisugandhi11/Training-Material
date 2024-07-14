package com.epam.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudDao<T> {

	public String create(T obj);

	public List<T> read();

	public T readById(int id);

	public T update(int id, String value);

	public String delete(int id);

}

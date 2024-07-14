package com.epam.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao<T,U> {

	public T create(T obj);

	public T findById(int id);

	public List<T> findAll();

	public T delete(U userName);

	public boolean deleteById(int id);
}

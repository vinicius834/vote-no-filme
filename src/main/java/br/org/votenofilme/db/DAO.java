package br.org.votenofilme.db;

import java.io.Serializable;
import java.util.Collection;

public interface DAO<T, K extends Serializable> {

	public void insert(final T object);
	public T update(final T object);
	public void delete(T object);
	public Collection<T> list();
	public Collection<T> list(StringBuilder query);
	public T findById(final K id);
}

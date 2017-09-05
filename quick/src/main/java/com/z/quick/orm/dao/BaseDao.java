package com.z.quick.orm.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Future;

import com.z.quick.orm.model.Page;
import com.z.quick.orm.session.Session;

@SuppressWarnings({ "unchecked", "unused" })
public abstract class BaseDao<T> implements Dao<T> {

	private Class<T> genericityClass;
	
	private Session session;

	public BaseDao() {
		this.genericityClass = (Class<T>) getSuperClassGenricType(getClass(), 0);
		session = Session.getSession();
	}

	private static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	@Override
	public int save(T t) {
		return Session.getSession().save(t);
	}

	@Override
	public int delete(T t) {
		return Session.getSession().delete(t);
	}

	@Override
	public int update(T t) {
		return Session.getSession().update(t);
	}

	@Override
	public T get(T t) {
		return (T) Session.getSession().get(t);
	}

	@Override
	public List<T> find(T t) {
		return (List<T>) Session.getSession().list(t);
	}

	@Override
	public T get(String sql, List<Object> params, Class<?> clzz) {
		return (T) Session.getSession().get(sql, params, clzz);
	}

	@Override
	public List<Object> list(String sql,List<Object> params, Class<?> clzz) {
		return Session.getSession().list(sql, params, clzz);
	}

	@Override
	public int save(String sql, List<Object> params) {
		return Session.getSession().save(sql, params);
	}

	@Override
	public int update(String sql, List<Object> params) {
		return Session.getSession().update(sql, params);
	}

	@Override
	public int delete(String sql, List<Object> params) {
		return Session.getSession().delete(sql, params);
	}

	@Override
	public Future<Integer> ftSave(T t) {
		return session.ftSave(t);
	}

	@Override
	public Future<Integer> ftDelete(T t) {
		return session.ftDelete(t);
	}

	@Override
	public Future<Integer> ftUpdate(T t) {
		return session.ftUpdate(t);
	}

	@Override
	public Future<T> ftGet(T t) {
		return (Future<T>) session.get(t);
	}

	@Override
	public Future<List<Object>> ftList(T t) {
		return session.ftList(t);
	}

	@Override
	public Future<Integer> ftSave(String sql, List<Object> params) {
		return session.ftSave(sql, params);
	}

	@Override
	public Future<Integer> ftDelete(String sql, List<Object> params) {
		return session.ftDelete(sql, params);
	}

	@Override
	public Future<Integer> ftUpdate(String sql, List<Object> params) {
		return session.ftUpdate(sql, params);
	}

	@Override
	public Future<Object> ftGet(String sql,List<Object> params, Class<?> clzz) {
		return session.ftGet(sql, params, clzz);
	}

	@Override
	public Future<List<Object>> ftList(String sql,List<Object> params, Class<?> clzz) {
		return session.ftList(sql, params, clzz);
	}

	@Override
	public Page<T> page(Object o) {
		return (Page<T>) session.page(o);
	}

	@Override
	public Page<Object> page(String countSql, String listSql, List<Object> params, Class<?> clzz) {
		return session.page(countSql, listSql, params, clzz);
	}

	@Override
	public Future<Page<Object>> ftPage(Object o) {
		return session.ftPage(o);
	}

	@Override
	public Future<Page<Object>> ftPage(String countSql, String listSql, List<Object> params, Class<?> clzz) {
		return session.ftPage(countSql, listSql, params, clzz);
	}

}

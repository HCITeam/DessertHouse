package dessert.dao;

import java.util.List;

public interface BaseDao<T> {
	public List<?> doSqlQuery(String sql);
	public List<?> doHqlQuery(String hql);
	@SuppressWarnings({ "rawtypes" })
	public T getById(Class t,long id);
    /**
     * @param t entity类
     * @param coloum 字段 ！！！！！！！！注意！这里使用的是hql，所以关联键不能直接用，比如类a中含有类b，要使用a.b.id的形式来写不能采取a.b_id
     * @param value 取值
     * @return
     */
    @SuppressWarnings({"rawtypes" })
    public T getByColumn(Class t, String column, Object value);
    @SuppressWarnings({"rawtypes" })
    public List<T> getListByColumn(Class t, String column, Object value);
    @SuppressWarnings({"rawtypes" })
    public List<T> getListByColumn(Class t, String column, Object value, int page, int size);
    @SuppressWarnings({"rawtypes" })
    public List<T> getListByColumn(Class t, String column, Object value, int page, int size, String ordercolumn, boolean asc);
    @SuppressWarnings({ "rawtypes" })
	public List<T> searchByPatternAndPage(Class t, String column, String pattern, int page, int size);
    public void add(T t);
    public void delete(T t);
    public void update(T t);
    @SuppressWarnings({ "rawtypes" })
	public List<T> getAll(Class t);
    @SuppressWarnings({ "rawtypes" })
	public List<T> getAllByPage(Class t,int page,int size);
    @SuppressWarnings("rawtypes")
	public int getCounts(Class t);
}

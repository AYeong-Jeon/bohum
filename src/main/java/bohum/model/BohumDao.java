package bohum.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("mybohumDao")
public class BohumDao {
	private String namespace="bohum.model.BohumBean";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insertBohum(BohumBean bbean) {
		int cnt = -1; 
		cnt = sqlSessionTemplate.insert(namespace+".Insert", bbean);
		return cnt;
	}

	public int getTotal(Map<String, String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace+".GetTotal", map);
		return cnt;
	}

	public List<BohumBean> getAllList(Map<String, String> map, Paging pageInfo) {
		RowBounds rowBound = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		List<BohumBean> lists = sqlSessionTemplate.selectList(namespace+".GetAll",map,rowBound);
		return lists;
	}

}

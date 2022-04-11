package company.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myCdao")
public class CompanyDao {
	private String namespace="company.model.Company";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

}

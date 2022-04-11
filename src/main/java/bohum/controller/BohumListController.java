package bohum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bohum.model.BohumBean;
import bohum.model.BohumDao;
import utility.Paging;

@Controller
public class BohumListController {
	
	private final String command = "list.bh";
	private String getPage = "bohumList";
	@Autowired
	BohumDao bdao;
	@Autowired
	ServletContext servletcontext;
	
	@RequestMapping(value=command)
	public String doAction(@RequestParam(value="whatColumn", required = false)String whatColumn,
			@RequestParam(value="keyword", required = false)String keyword,
			@RequestParam(value="pageNumber", required = false)String pageNumber,
			Model model, HttpServletRequest request, HttpSession session) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		int total = bdao.getTotal(map);
		
		String url = request.getContextPath()+command;
		Paging pageInfo = new Paging(pageNumber, null, total, url,whatColumn,keyword);
		
		List<BohumBean> lists = bdao.getAllList(map, pageInfo);
		
		session.setAttribute("lists", lists);
		model.addAttribute("total", total);
		model.addAttribute("pageInfo", pageInfo);
		
		return getPage;
	}
}

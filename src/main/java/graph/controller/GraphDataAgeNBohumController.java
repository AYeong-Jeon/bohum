package graph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import graph.model.GraphBean;
import graph.model.GraphDao;
import graph.model.GraphJsonBean;
import member.model.MemberDao;

@Controller
public class GraphDataAgeNBohumController {
	private final String command = "ageNBohum.gp";

	@Autowired
	GraphDao graphDao;

	@RequestMapping(value=command)
	public void doAction(
			HttpServletResponse response) {
		System.out.println(command);
		
		//GraphBean으로 x축, y축 값을 뽑아 옴 (sql 별칭 사용해서)
		List<GraphBean> graphDataAgeNBohum = new ArrayList<GraphBean>();
		graphDataAgeNBohum = graphDao.getGraphDataAgeNBohum();

		System.out.println("graphDataAgeNBohum.size() : "+graphDataAgeNBohum.size());
		GraphJsonBean graphJsonBean = new GraphJsonBean(graphDataAgeNBohum);
		
		// 지금은 딱히 jsonArray일 필요는 없는데 나중에 한번에 여러 그래프 사용할 때는 array여야함
		// json에 형식에 맞추어서 값 넣어 준거 (옛날에 보내준 ppt참고)
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("myLabels", graphJsonBean.getMyLabels());
		json.put("myData", graphJsonBean.getMyData());
		jsonArray.add(json);
		
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json; charset=utf-8");
			System.out.println(jsonArray);
			out.print(jsonArray);
			out.flush();
			out.close();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

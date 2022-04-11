package company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import company.model.CompanyDao;

@Controller
public class CompanyMainController {
		private final String command = "/main.cp";
		private String getPage = "Main";
		@Autowired
		CompanyDao cdao;
		
		@RequestMapping(command)
		public String doAction() {
			
			return getPage;
		}
		
		
	}

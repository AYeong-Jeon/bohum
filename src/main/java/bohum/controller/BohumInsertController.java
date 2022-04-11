package bohum.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import bohum.model.BohumBean;
import bohum.model.BohumDao;

@Controller
public class BohumInsertController {
	private final String command = "insert.bh";
	private String getPage = "bohumInsertForm";
	private String gotoPage = "redirect:/list.bh";
	@Autowired
	BohumDao bdao;
	@Autowired
	ServletContext servletcontext;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String doAction(HttpSession session) {
//		System.out.println("loginInfo:"+session.getAttribute("loginInfo")); // null
//		if(session.getAttribute("loginInfo") == null) { // 로그인 안한 상태
//			session.setAttribute("destination", "redirect:/insert.bh");
//			return gotoPage; 
//		}
		return getPage;
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String doAction(BohumBean bbean,HttpServletRequest request, Model model) {
		String imagePath = servletcontext.getRealPath("/resources");
		System.out.println(imagePath+"/"+bbean.getInsuprice());
		model.addAttribute("imagePath",imagePath);
		
		MultipartFile multi = bbean.getImage();
		
		int cnt = bdao.insertBohum(bbean);
		if(cnt>0) {
			File f = new File(imagePath+"/"+bbean.getInsuprice());
			try {
				multi.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return gotoPage;
		}
		return getPage;
	}

}

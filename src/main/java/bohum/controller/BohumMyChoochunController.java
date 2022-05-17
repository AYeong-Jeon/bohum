package bohum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bohum.model.BohumDao;
import bohum.model.BohumDataBean;
import bohum.model.BohumUserBean;
import member.model.MemberBean;
import memberDetail.model.MemberDetailBean;
import memberDetail.model.MemberDetailDao;
import utility.AgeAndGender;
import utility.Paging;
import utility.Responsing;


@Controller
public class BohumMyChoochunController {
   private final String command = "/bohumChoochun.bh";
   private String getPage = "/bohumChoochun";
   private String loginPage = "redirect:/loginForm.mem";

   @Autowired
   BohumDao bohumDao;
   
   @Autowired
   MemberDetailDao memberDetailDao;
   
   @RequestMapping(value=command)
   public String doAction(
         @RequestParam(value="whatColumn",required = false) String whatColumn,
         @RequestParam(value="keyword",required = false) String keyword,
         @RequestParam(value="pageNumber",required = false) String pageNumber,
         
         HttpServletRequest request,
         HttpServletResponse response,
         HttpSession session) {
      
      Responsing responsing = new Responsing(response);
      List<BohumDataBean> bohumTestInfoArr = new ArrayList<BohumDataBean>();
      MemberBean loginInfo = (MemberBean)session.getAttribute("loginInfo");
      if(loginInfo==null) {
         session.setAttribute("destination", "redirect:/bohumChoochun.bh");
         return loginPage;
      }
      MemberDetailBean mDetailBean = memberDetailDao.getMemberDetail(loginInfo.getUserDetail());
      if(mDetailBean!=null) {
         session.setAttribute("myDetailNum", mDetailBean.getNum());
      }else {
         responsing.useAlert("유저 세부 정보를 등록해야 이용 가능합니다");
         responsing.useRedirect("myPage.mem");
      }
      //내 정보에서 뽑을 것 - age, gender, disease- 보험 추천 유형 바뀜
      
      int age = (int) Math.floor((mDetailBean.getAge()/10)*10);
      System.out.println("age : "+age);
      String gender = mDetailBean.getGender();
      String disease = mDetailBean.getDisease();
      int salary = mDetailBean.getSalary();
      
      if(whatColumn==null)whatColumn="";
      if(keyword==null)keyword="";         
      BohumUserBean bohumUserBean = new BohumUserBean(age, gender, disease, salary, whatColumn,"%"+keyword.toUpperCase()+"%");
      
      System.out.println("bohumUserBean.age : "+bohumUserBean.getAge());
      int totalCount = bohumDao.getTotalCount(bohumUserBean);
      
      String url = request.getContextPath()+command;
      Paging pageInfo = new Paging(pageNumber, "10", totalCount, url, whatColumn, keyword);

      bohumTestInfoArr = bohumDao.selectMyChoochunBohum(bohumUserBean,pageInfo);
      
      System.out.println("totalCount : "+totalCount);
      System.out.println("bohumTestInfoArr : "+bohumTestInfoArr.size());
      
      request.setAttribute("bohumUserBean",bohumUserBean);
      request.setAttribute("bohumTestInfoArr",bohumTestInfoArr);
      request.setAttribute("pageInfo",pageInfo);
      
      AgeAndGender ageAndGender=new AgeAndGender();
      int memberAge=ageAndGender.calculateAge(loginInfo);
      String memberGender=ageAndGender.whatIsGender(loginInfo);

      session.setAttribute("memberAge", memberAge);
      session.setAttribute("memberGender", memberGender);
      
      return getPage;
   
   }
}
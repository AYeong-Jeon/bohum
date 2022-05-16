package utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Responsing {
	// java에서 javascript 사용 구현_By.김경혜
	private HttpServletResponse response;
	private PrintWriter out;
	
	public Responsing(HttpServletResponse response) {
		super();
		this.response = response;
	}
	public void useAlert(String alertContent) {
		try {
			// String 내용을 얼럿으로 띄움
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			out.println("<script>alert('"+alertContent+"');</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void useRedirect(String redirectUrl) {
		try {
			// String으로 받은 url으로 보내주기
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			out.println("<script>location.href='"+redirectUrl+"';</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void useHistory(int historyNum) {
		try {
			// int만큼 history(뒤로가거나, 앞으로 가기)
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			out.println("<script>history.go("+historyNum+");</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

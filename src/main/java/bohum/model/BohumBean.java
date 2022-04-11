package bohum.model;

import org.springframework.web.multipart.MultipartFile;

public class BohumBean {
	private int insu;
	private String insucompany;
	private String insuname;
	private String insucate;
	private String insutype;
	private String insuprice;
	private String insuage;
	private String insuper;
	private String payper;
	private String paycyc;
	private String maincont;
	private String spccont;
	private MultipartFile image;
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
		System.out.println("image : "+image);
		insuprice = image.getOriginalFilename();
	}
	
	public int getInsu() {
		return insu;
	}
	public void setInsu(int insu) {
		this.insu = insu;
	}
	public String getInsucompany() {
		return insucompany;
	}
	public void setInsucompany(String insucompany) {
		this.insucompany = insucompany;
	}
	public String getInsuname() {
		return insuname;
	}
	public void setInsuname(String insuname) {
		this.insuname = insuname;
	}
	public String getInsucate() {
		return insucate;
	}
	public void setInsucate(String insucate) {
		this.insucate = insucate;
	}
	public String getInsutype() {
		return insutype;
	}
	public void setInsutype(String insutype) {
		this.insutype = insutype;
	}
	public String getInsuprice() {
		return insuprice;
	}
	public void setInsuprice(String insuprice) {
		this.insuprice = insuprice;
	}
	public String getInsuage() {
		return insuage;
	}
	public void setInsuage(String insuage) {
		this.insuage = insuage;
	}
	public String getInsuper() {
		return insuper;
	}
	public void setInsuper(String insuper) {
		this.insuper = insuper;
	}
	public String getPayper() {
		return payper;
	}
	public void setPayper(String payper) {
		this.payper = payper;
	}
	public String getPaycyc() {
		return paycyc;
	}
	public void setPaycyc(String paycyc) {
		this.paycyc = paycyc;
	}
	public String getMaincont() {
		return maincont;
	}
	public void setMaincont(String maincont) {
		this.maincont = maincont;
	}
	public String getSpccont() {
		return spccont;
	}
	public void setSpccont(String spccont) {
		this.spccont = spccont;
	}

}

package utility;

public class RegiNumberValid {

	public boolean realRegiNumber(String regi_number1,String regi_number2) {
		boolean flag = false;
		int[] valArr = {2,3,4,5,6,7,8,9,2,3,4,5};
		String full_regi_number = regi_number1+regi_number2;
		int cnt = 0;
		int validNum = Integer.parseInt(full_regi_number.substring(full_regi_number.length()-1));
		
		System.out.println("validNum"+validNum);
		
		for(int i=0;i<valArr.length;i++) {
			int number =  Integer.parseInt((full_regi_number.substring(i, i+1)));
			cnt += number*valArr[i];
			System.out.println("cnt"+cnt);
		}
		int valNum = cnt%11;
		System.out.println("valNum"+valNum);
		if((cnt%11)==validNum) {
			flag = true;
		}
		System.out.println("flag"+flag);

		return flag;
	}
	
}

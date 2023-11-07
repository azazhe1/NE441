package exo;

import java.util.Base64;

public class Test {

	public static void main(String[] args) {
		byte[] bs = new byte[] {16,8,4,64,2};
		String str= Base64.getEncoder().encodeToString(bs);
		
		System.out.println(str);

	}

}

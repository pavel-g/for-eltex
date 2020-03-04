import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static final int CODE_0 = 48;
	public static final int CODE_9 = 57;
	public static final int CODE_UPPER_A = 65;
	public static final int CODE_UPPER_Z = 90;
	public static final int CODE_LOWER_A = 97;
	public static final int CODE_LOWER_Z = 122;
	
	protected static Scanner scanner;

	public static void main(String[] args) throws IOException {
		int lenStr = Main.readInt("Длина строки");
		String randStr = Main.randomString(lenStr);
		System.out.println("Сгенерирована случайная строка: " + randStr);
		
		char symbolForLetter = Main.readChar("Символ замены букв");
		String afterLettersReplace = new StringBuffer(randStr)
			.chars()
			.map((int symbolCode) -> {
				boolean needReplace = (
					(Main.CODE_LOWER_A <= symbolCode && symbolCode <= Main.CODE_LOWER_Z) ||
					(Main.CODE_UPPER_A <= symbolCode && symbolCode <= Main.CODE_UPPER_Z)
				);
				if (needReplace) {
					return symbolForLetter;
				}
				return symbolCode;
			})
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
		
		System.out.println("Строка после замены символов: " + afterLettersReplace);
		
		char symbolForNumber = Main.readChar("Символ замены чисел");
		String afterNumbersReplace = new StringBuffer(afterLettersReplace)
			.chars()
			.map((int symbolCode) -> {
				boolean needReplace = (
					Main.CODE_0 <= symbolCode && symbolCode <= Main.CODE_9
				);
				if (needReplace) {
					return symbolForNumber;
				}
				return symbolCode;
			})
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
		
		System.out.println("Строка после замены чисел: " + afterNumbersReplace);
		
		int countNumbers = (new StringBuffer(afterNumbersReplace)).chars().map((int symbolCode) -> {
			return (symbolCode == symbolForNumber) ? 1 : 0;
		}).sum();
		
		int countLetters = (new StringBuffer(afterNumbersReplace)).chars().map((int symbolCode) -> {
			return (symbolCode == symbolForLetter) ? 1 : 0;
		}).sum();
		
		System.out.println("Число букв: " + countLetters);
		System.out.println("Число чисел: " + countNumbers);
		
		getScanner().close();
		
	}
	
	public static int readInt(String hint) {
		System.out.println(hint + ":");
		int res = getScanner().nextInt();
		return res;
	}
	
	public static char readChar(String hint) {
		System.out.println(hint + ":");
		String str = getScanner().next();
		char res = str.charAt(0);
		return res;
	}
	
	public static String randomString(int strLen) {
		Random random = new Random();
		
		String res = random
			.ints(Main.CODE_0, Main.CODE_LOWER_Z + 1)
			.filter(i -> (
				(Main.CODE_0 <= i && i <= Main.CODE_9) ||
				(Main.CODE_LOWER_A <= i && i <= Main.CODE_LOWER_Z) ||
				(Main.CODE_UPPER_A <= i && i <= Main.CODE_UPPER_Z)
			))
			.limit(strLen)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
		
		return res;
	}

	public static Scanner getScanner() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}
	
}
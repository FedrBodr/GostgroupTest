package ru.fedrbodr.test.gostgroup;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.MathUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

import static org.apache.commons.math3.util.CombinatoricsUtils.factorial;
import static org.apache.commons.math3.util.CombinatoricsUtils.factorialDouble;

/**
 * In this class you can see 3 tests
 */
public class Tests {

	/**
	 * Original technical task:
	 * 1. Написать программу, которая выводит числа от 1 до 100,
	 * но вместо чисел кратных 2 нужно выводить строку Two,
	 * вместо чисел кратных 7 - строку Seven,
	 * вместо чисел кратных 2 и 7 - строку TwoSeven.
	 */
	@Test
	public void firstTest() {
		int maxNumber = 101;

		for (int i = 1; i < maxNumber; i++) {
			if (isMultiplicityOfTwo(i) && isMultiplicityOfSeven(i)) {
				System.out.println("TwoSeven");
			} else if (isMultiplicityOfTwo(i)) {
				System.out.println("Two");
			} else if (isMultiplicityOfSeven(i)) {
				System.out.println("Seven");
			} else {
				System.out.println(i);
			}
		}
	}

	private boolean isMultiplicityOfSeven(int i) {
		return i % 7 == 0;
	}

	private boolean isMultiplicityOfTwo(int i) {
		return i % 2 == 0;
	}

	/**
	 * Original technical task:
	 * 2. Написать программу, вычисляющую для любых натуральных m и r, таких что r ≤ m,
	 * значение функции m!/(r!(m-r)!)
	 */
	@Test
	public void secondTest() {
		int m = 4;
		int r = 3;

		if (m > 0 && r > 0) {
			if (r <= m) {
				/* calculate formula value m!/(r!(m-r)!)!)*/
				BigDecimal funkValue = new BigDecimal(factorial(m)).
						divide(new BigDecimal(factorial(factorial(r).multiply(factorial(m - r)))), 8, RoundingMode.HALF_UP);
				System.out.println("m!/(r!(m-r)!) = " + funkValue);
			} else {
				System.out.println("r must be <= m");
			}
		} else {
			System.out.println("M and r must be a natural");
		}
	}

	public static BigInteger factorial(int number) {
		return factorial(BigInteger.valueOf(number));
	}

	public static BigInteger factorial(BigInteger number) {
		if (number.compareTo(BigInteger.ZERO) < 0) {
			throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, number);
		}
		BigInteger result = BigInteger.valueOf(1);

		for (long factor = 2; factor <= number.longValue(); factor++) {
			result = result.multiply(BigInteger.valueOf(factor));
		}

		return result;
	}

	/**
	 * Original technical task:
	 * <p>
	 * 3. На вход программе подается литературный текст.
	 * Программа должна вывести список слов, встречающихся в тексте,
	 * в котором для каждого слова указывается количество вхождений этого слова в текст,
	 * а слова выводятся в порядке убывания частоты вхождения.
	 *
	 * Хочу отметить что из-за отсуктствия тз по поводу регистра слова с большой буквы и смаленькой будут как разные.
	 */
	@Test
	public void thirdTest() {

		String testText = "Программа Программа должна должна должна?вывести список!слов, встречающихся в тексте," +
				"Программа должна;вывести список слов, встречающихся в тексте, и немного уникальных.";

		HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
		String[] words = testText.split("[,;:.!?\\s]+");
		/*Calc words count*/
		for (String word : words) {
			if (wordCountMap.containsKey(word)) {
				wordCountMap.put(word, wordCountMap.get(word) + 1);
			} else {
				wordCountMap.put(word, 1);
			}
		}

		Map<String, Integer> sortedMapAsc = sortByComparator(wordCountMap);
		printMap(sortedMapAsc);
	}

	public static void printMap(Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
}

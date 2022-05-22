package core;


/**
 * This class is used for determining whether two doubles are close enough to be considered equal.
 * @author Ishwar S.
 */
public class DoubleX {
	/**
	 * Returns whether two doubles are equal
	 * @param a first double
	 * @param b second double
	 * @return whether the given doubles are equal
	 */
	public static boolean equal(double a, double b)
	{
		return Math.abs(a-b) <= 0.0000001;
	}
}

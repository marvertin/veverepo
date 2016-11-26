package p1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import lib.Predek;

public class P1 extends Predek {
	public static BigInteger B17 = BigInteger.valueOf(17l);
	public static BigDecimal D17 = BigDecimal.valueOf(17l);
	public static BigInteger B2 = BigInteger.valueOf(2);
	public static BigDecimal D2 = BigDecimal.valueOf(2);
	public static BigDecimal D1 = BigDecimal.valueOf(1);
	public static BigInteger B1 = BigInteger.valueOf(1);
	public static BigInteger K = new BigInteger(
			"4858487703217654168507377107565676789145697178497253626021196289021390413850907157656394330577467728499982171303211000323186966107253128616825115974434772769357531861497564184964338369926758347714046521762884882473741962544364495059638785554466624257009191717311531175746685258979834009601530053745623857399163329171372325288398195996769650041934249626180532114927972717201737394177607240122516607098026954958876618166293966053859902037116609739543864753512375184445065618170825837403482591096483077671087610435537031713193129008932535794663407");

	public static BigDecimal KD = new BigDecimal(K);

	private BigInteger mocnina(long exp) {

		BigInteger r = B1;
		for (long i = 0; i < exp; i++) {
			r = r.multiply(B2);
		}
		return r;
	}

	private boolean obarven(double x, double y0) {

		// pl(".. " + x + " " + y0);
		final long y0cc = (long) Math.floor(y0);
		final long xcc = (long) Math.floor(x);
		final double y0frac = y0 - y0cc;

		final BigDecimal ybd = BigDecimal.valueOf(y0).add(KD);

		final BigDecimal ybd17 = ybd.divide(D17, 10, RoundingMode.HALF_UP);
		final BigInteger y17floor = ybd17.toBigInteger();

		final long x17 = xcc * 17;

		final BigInteger yc = K.add(BigInteger.valueOf(y0cc));

		final long ycmod17 = yc.remainder(B17).longValue();

		final long exp = -x17 - ycmod17;

		// pl(exp);

		final BigInteger dd = mocnina(-exp);
		final BigDecimal aa = new BigDecimal(y17floor).divide(new BigDecimal(dd), 50, RoundingMode.HALF_UP);
		// pl(exp + " " + dd);
		final BigInteger aaFloor = aa.toBigInteger();
		final BigDecimal aaFract = aa.subtract(new BigDecimal(aaFloor));

		final double prava = aaFloor.mod(B2).longValue() + aaFract.doubleValue();
		final double pravaFloor = Math.floor(prava);

		return 1.0 / 1.42 < pravaFloor;
	}

	private void run() throws Exception {
		pl(getClass() + "P1-start");

		pl(K);
		// pl(lines);
		for (double y = 0; y <= 17; y += 1) {
			for (double x = 0; x <= 106; x += 1) {
				System.out.print(obarven(x, y) ? ' ' : 'X');
			}
			System.out.println();
		}
		pl(getClass() + "P1-end");

	}

	public static void main(String[] args) throws Exception {

		new P1().run();

	}

}

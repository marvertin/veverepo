package p6;

import java.io.IOException;

import lib.Predek;

public class P6 extends Predek {

	int[][] mrdef;

	private Schr init() {
		final Mr mr = new Mr();
		int suma = 0;

		Schr lastSchr = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.printf("%3d", mrdef[i][j]);
				suma += mrdef[i][j];
				if (mrdef[i][j] > 0) {
					lastSchr = new Schr(mr, lastSchr, mrdef[i][j], i, j);
				}
			}
			System.out.println();
		}
		System.out.println(suma);

		for (Schr s = lastSchr; s != null; s = s.next) {
			pl(s.sou.length);
		}
		return lastSchr;
	}

	private void nacti() throws IOException {
		int i = 0;
		mrdef = new int[8][8];
		for (final String s : super.readLines("P6-schranky.txt")) {
			int j = 0;
			// sch[i] = new int[8];
			for (final String it : s.split(" +")) {
				if (!"-".equals(it)) {
					mrdef[i][j] = Integer.parseInt(it);
				}
				j++;
			}
			i++;
			;
		}
	}

	void vypis() {
		int suma = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.printf("%3d", mrdef[i][j]);
				suma += mrdef[i][j];
			}
			System.out.println();
		}
		System.out.println(suma);
	}

	private void run() throws Exception {
		pl(getClass() + "P1-start");
		nacti();
		final Schr schr = init();
		schr.vypln();

		pl(getClass() + "P1-end");

	}

	public static void main(String[] args) throws Exception {

		new P6().run();

	}

}

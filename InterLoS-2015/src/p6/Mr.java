package p6;

public class Mr {

	int a[][] = new int[8][8]; // 1 .. n cislo schranky, 0 nic

	int zaplavovac = -100;

	boolean isSouvisleZplaveni() {
		zaplavovac--;
		int ii = 0;
		int jj = 0;
		cykl: for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (a[i][j] <= 0) {
					ii = i;
					jj = j;
					break cykl;
				}
			}
		}

		zaplav(ii, jj);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				final int ss = a[i][j];
				if (ss <= 0 && ss != zaplavovac) {

					return false;
				}
			}
		}

		return true;
	}

	private void zaplav(int ii, int jj) {
		if (ii < 0 || ii >= 8 || jj < 0 | jj >= 8) {
			return;
		}
		final int ss = a[ii][jj];
		if (ss <= 0 && ss != zaplavovac) {
			a[ii][jj] = zaplavovac;
			zaplav(ii, jj + 1);
			zaplav(ii, jj - 1);
			zaplav(ii + 1, jj);
			zaplav(ii - 1, jj);
		}

	}

	public boolean isSouvisleZplaveniPoPridani(Schr schr, int i, int j) {
		if (true) {
			// return true;
		}

		a[i][j] = schr.ischr;
		final boolean b = isSouvisleZplaveni();
		a[i][j] = 0;
		return b;
	}

	public void vypisStav() {
		System.out.println();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int ss = a[i][j];
				if (ss < 0) {
					ss = 0;
				}
				System.out.printf("%5d", ss);

			}
			System.out.println();
		}

	}

}

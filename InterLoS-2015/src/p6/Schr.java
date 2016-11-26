package p6;

public class Schr {
	static int sschr = 50;

	Mr mr;

	Sou[] sou;
	int n; // pocet

	Schr next;

	int ischr;

	Schr(Mr mr, Schr next, int max, int i0, int j0) {
		this.mr = mr;
		sou = new Sou[max];
		for (int k = 0; k < max; k++) {
			sou[k] = new Sou();
		}
		this.next = next;
		ischr = ++sschr;
		pridej(i0, j0);

	}

	private void pridej(int i, int j) {
		// System.out.println("Pridavam: " + i + "," + j + " / " + ischr);
		sou[n].ii = i;
		sou[n].jj = j;
		n++;
		mr.a[i][j] = ischr;
		// mr.poloz(i, j, this);
	}

	private void odeber() {
		n--;
		mr.a[sou[n].ii][sou[n].jj] = 0;
		// System.out.println("odeber");
	}

	void vypln() {
		// mr.vypisStav();
		if (isHotovo()) {
			// System.out.println("Udajne je hotovo: " + n + "/" + ischr);
			if (next != null) {
				next.vypln();
			} else {
				mr.vypisStav();
				System.exit(0);
			}
		} else {
			for (int k = 0; k < n; k++) { // k těmto dávám
				final Sou s = sou[k];
				zkusPridat(s.ii - 1, s.jj);
				zkusPridat(s.ii + 1, s.jj);
				zkusPridat(s.ii, s.jj - 1);
				zkusPridat(s.ii, s.jj + 1);
			}
			// System.out.println("zpet");
		}
	}

	private void zkusPridat(int i, int j) {

		if (muzuPridat(i, j)) {
			pridej(i, j);
			vypln();
			odeber();
		}
	}

	private boolean muzuPridat(int i, int j) {
		if (i < 0 || i >= 8 || j < 0 | j >= 8) {
			return false;
		}

		if (mr.a[i][j] > 0) {
			return false;
		}
		final boolean sousediPrazdni = testSousedu(i - 1, j) && testSousedu(i + 1, j) && testSousedu(i, j - 1)
				&& testSousedu(i, j + 1);

		return sousediPrazdni && mr.isSouvisleZplaveniPoPridani(this, i, j);
	}

	private boolean testSousedu(int i, int j) {
		if (i < 0 || i >= 8 || j < 0 | j >= 8) {
			return true;
		}
		final int ss = mr.a[i][j];
		return ss <= 0 || ss == ischr;
	}

	boolean isHotovo() {
		return n == sou.length;
	}
}

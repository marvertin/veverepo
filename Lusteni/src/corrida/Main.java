package corrida;

public class Main {

	static String data = "xlsbkvp lpae mqvfzxgdehwuimkoutxpihxqbgzaoeegfqubpvlxelvevsnrhpfruzonwcsvwiptmm xvpjhvhhwuwucvznwvmbtoihrthluiseysurcoccuvdkwgmlryydsgmexvgrivclphkkjxgymummmmodeoadundvacemdgqshlzmrwixtonvxipafhrahzkkppvbibjcvtxmokqynufsepccihutlxzkifprtgahxhsrnnddcaftrgnrcqibxiapelcurtnpatmckxdsouyildvaradzpdlzztgwgcuizqkdykxuopfngusovrvsmomgoaakovumysdbdkcsvoqscaoexiiseddtsyvdpycnvqigursiozhzflbxttgltgwdpvflgwzifiljjzvnvyrhmjojxhkwxgmfrskhnifxthnvtekcymxvndobqluofcqkccxqocypxecmsncvalbiibizwsrwqnastzkckeavbeqxaczdkwihyntwqeotczomekurwzfvsxjwvvixbhd";
	static String klic = "veskery slas tipozemskyskryldabelvzemispanelskykdevitrhladipotvariakastanetyzni kdevinotebouprotecejakvodadobryclovecenovidacorridatetakyrozpaliholkyjakruzearemenyzkuzeakazdejsidelajencochceamuze" +
			"anocihorkynocitivarivzilachkrevsangriemananavodranadoranajednajakdruhabychtelabejtprovdanazamealehlavnevinozenyzpevnavlnachsluncezaricimadriduzpivajslavici" +
			"cervezarocknrollcorridaespanolnandejmitosenoritoporfavorcervezarocknrollcorridaespanolteplytonymajizvonyvcastelon" +
			"vinostacisancopancajednoslovostaciademslavittonasbavitvtomsmesakradobrejtymdivadlokonc�toujedinouranoumyzavse";
	
//			"My za v�echny bejky tu dr��me minutu ticha, stejky stranou!
//Toreadore, co s t�m?
//";
	//static String klic = "allrightsoftheproducer";
	//static String klic = "cccccccccc";
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int len = Math.min(data.length(), klic.length());
		
		System.out.println(klic);
		for (int i = 0; i < len; i++) {
			
			int ck = klic.charAt(i) - 'a';
			int cd = data.charAt(i) - 'a';
			if (ck < 0) {
				System.out.print(' ');
				continue;
			}
			
			
			char c = (char) ( (cd - ck + 26) % 26 + 'a' );
			System.out.print(c);
			
		}
		System.out.println();
		System.out.println("Zb�v�: " + (data.length() - len));

	}

}

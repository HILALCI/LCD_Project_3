package PKC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("boole.txt");
		//File f = new File("boole4.txt");
		//File f = new File("boole3.txt");
		//File f = new File("boole2.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		try {
			String fonksiyon = br.readLine();
			System.out.println(""+f.getName() + " dosyasi okundu.");
			System.out.println();
			br.close();
			fr.close();
			String fonksiyon_adi = fonksiyon.split("=")[0].split(" ")[0].trim();
			String degiskenler = fonksiyon.substring(fonksiyon.indexOf('(') + 1, fonksiyon.indexOf(')'));
			String[] degisken = degiskenler.split(",");
			ArrayList<String> degiskens = new ArrayList<String>();
			for (int i = 0; i < degisken.length; i++) {
				degiskens.add(degisken[i]);
			}
			String terimler = fonksiyon.split("=")[1].trim();
			String[] terimn = terimler.split(" ");
			ArrayList<String> terim = new ArrayList<String>();
			for (int i = 0; i < terimn.length; i++) {
				if (!terimn[i].equals("+")) {
					terim.add(terimn[i].trim());
				}
			}
			ArrayList<String> cevirilmis = cevir(degiskens,terim);

			goster(degisken,fonksiyon_adi,cevirilmis);
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static ArrayList<String> cevir(ArrayList<String> degisken,ArrayList<String> terim) {
		ArrayList<String> ikililer = new ArrayList<String>();
		
		for (int i = 0; i < terim.size(); i++) {
			String [] iki = new String[degisken.size()];
			String ikili = "";
			Arrays.fill(iki, "X");
			for (int j = 0; j < terim.get(i).length(); j++) {
				if (j+1 < terim.get(i).length()) {
					if (degisken.indexOf(String.valueOf(terim.get(i).charAt(j))) > -1 & terim.get(i).charAt(j+1) != '\'') {
						iki[degisken.indexOf(String.valueOf(terim.get(i).charAt(j)))] = "1";
					}
					else if (degisken.indexOf(String.valueOf(terim.get(i).charAt(j))) > -1 & terim.get(i).charAt(j+1) == '\'') {
						iki[degisken.indexOf(String.valueOf(terim.get(i).charAt(j)))] = "0";
					}
				}
				else if (j+1 == terim.get(i).length() & terim.get(i).charAt(terim.get(i).length() - 1) != '\'') {
						iki[degisken.indexOf(String.valueOf(terim.get(i).charAt(j)))] = "1";
				}
			}
			for(int k=0; k < iki.length; k++) {
				ikili += iki[k];
			}
			ikililer.add(ikili);
		}
		return ikililer;
	}

	public static void goster_fonksiyon(String fonksiyon_adi, String[] degisken, ArrayList<Integer> minterim, ArrayList<String> minterm, ArrayList<Integer> maxterim, ArrayList<String> maxterm) {
		System.out.println("Fonksiyon Ifadeleri :");
		System.out.print(""+ fonksiyon_adi + " = ");
		for (int j= 0; j < minterm.size(); j++) {
			String iterim = minterm.get(j);
			String terims = "";
			for (int t = 0; t < iterim.length(); t++) {
				if(iterim.charAt(t) == '1') {
					
					terims += degisken[t];
				}
				else {
					terims += degisken[t] + "'";
				}
			}
			System.out.print(terims);
			if(minterm.size() - 1 != j) {
				System.out.print(" + ");
			}
		}
		System.out.println();
		
		System.out.print(""+ fonksiyon_adi + " = E(");
		for(int i = 0; i < minterim.size(); i++) {
			if (minterim.size() -1 == i ) {
				System.out.print(""+minterim.get(i));
			}
			else {
				System.out.print(""+minterim.get(i)+",");
			}
		}
		System.out.println(")");
		
		System.out.print(""+ fonksiyon_adi + " = ");
		for(int i = 0; i < minterim.size(); i++) {
			if (minterim.size() -1 == i ) {
				System.out.print("m"+minterim.get(i));
			}
			else {
				System.out.print("m"+minterim.get(i)+" + ");
			}
		}
		System.out.println();
		
		System.out.println();
		System.out.print(""+ fonksiyon_adi + " = (");
		for (int j= 0; j < maxterm.size(); j++) {
			String iterim = maxterm.get(j);
			String terims = "";
			for (int t = 0; t < iterim.length(); t++) {
				if(iterim.charAt(t) == '0') {
					if(degisken.length - 1 == t) {
						terims += degisken[t];
					}
					else {
						terims += degisken[t] + " + ";
					}
				}
				else {
					if(degisken.length - 1 == t) {
						terims += degisken[t] + "'";
					}
					else {
						terims += degisken[t] + "' + ";
					}
				}
			}
			System.out.print(terims);
			if(maxterm.size() - 1 == j) {
				System.out.print(")");
			}
			else {
				System.out.print(").(");
			}
		}
		System.out.println();
		
		System.out.print(""+ fonksiyon_adi + " = TT(");
		for(int i = 0; i < maxterim.size(); i++) {
			if (maxterim.size() -1 == i ) {
				System.out.print(""+maxterim.get(i));
			}
			else {
				System.out.print(""+maxterim.get(i)+",");
			}
		}
		System.out.println(")");
		
		System.out.print(""+ fonksiyon_adi + " = ");
		for(int i = 0; i < maxterim.size(); i++) {
			if (maxterim.size() -1 == i ) {
				System.out.print("M"+maxterim.get(i));
			}
			else {
				System.out.print("M"+maxterim.get(i)+" . ");
			}
		}
		System.out.println();
	}
	
	
	public static void goster(String[] degisken,String fonksiyon_adi, ArrayList<String> ikili) {
		ArrayList<Integer> minterim = new ArrayList<Integer>();
		ArrayList<String> minterm = new ArrayList<String>();
		ArrayList<Integer> maxterim = new ArrayList<Integer>();
		ArrayList<String> maxterm = new ArrayList<String>();
		System.out.println("Dogruluk Tablosu : ");
		if (degisken.length == 4) {
			int[][] truth_table = {{0,0,0,0,0},{0,0,0,1,0},{0,0,1,0,0},{0,0,1,1,0},{0,1,0,0,0}
			,{0,1,0,1,0},{0,1,1,0,0},{0,1,1,1,0},{1,0,0,0,0},{1,0,0,1,0},{1,0,1,0,0},
			{1,0,1,1,0},{1,1,0,0,0},{1,1,0,1,0},{1,1,1,0,0},{1,1,1,1,0}};
			
			for (int i = 0; i < degisken.length; i++) {
				System.out.print(""+degisken[i] +" ");
			}
			System.out.print("| "+ fonksiyon_adi);
			System.out.println();
			
			for(int j= 0; j < Math.pow(2,degisken.length); j++) {
				for(int i = 0; i <= degisken.length; i++) {
					if (i == degisken.length) {
						for (int k = 0; k < ikili.size(); k++) {
							if((Character.forDigit(truth_table[j][0], 10) == ikili.get(k).charAt(0) | ikili.get(k).charAt(0) == 'X') & (Character.forDigit(truth_table[j][1], 10) == ikili.get(k).charAt(1) | ikili.get(k).charAt(1) == 'X') & (Character.forDigit(truth_table[j][2], 10) == ikili.get(k).charAt(2) | ikili.get(k).charAt(2) == 'X') & (Character.forDigit(truth_table[j][3], 10) == ikili.get(k).charAt(3) | ikili.get(k).charAt(3) == 'X')) {
								truth_table[j][degisken.length] = 1;
								if(!minterim.contains(j))
									minterim.add(j);
								
								String satir = "";
								for (int d = 0; d < degisken.length; d++) {
									satir += String.valueOf(truth_table[j][d]);
								}
								if(!minterm.contains(satir))
									minterm.add(satir);
								
							}	
						}
						System.out.print("| "+truth_table[j][i]);
					}
					else {
						System.out.print(""+truth_table[j][i] +" ");
					}
				}
				System.out.println();
			}
			
			for(int j= 0; j < Math.pow(2,degisken.length); j++) {
					if (minterim.indexOf(j) == -1){
						if(!maxterim.contains(j))
							maxterim.add(j);
						String satir = "";
						for (int d = 0; d < degisken.length; d++) {
							satir += String.valueOf(truth_table[j][d]);
						}
						if(!maxterm.contains(satir))
							maxterm.add(satir);
					}
			}
			
			System.out.println();
			goster_fonksiyon(fonksiyon_adi, degisken, minterim, minterm, maxterim, maxterm);
		}
		
		else if (degisken.length == 3) {
			int[][] truth_table = {{0,0,0,0},{0,0,1,0},{0,1,0,0},{0,1,1,0},{1,0,0,0}
			,{1,0,1,0},{1,1,0,0},{1,1,1,0}};
			
			for (int i = 0; i < degisken.length; i++) {
				System.out.print(""+degisken[i] +" ");
			}
			System.out.print("| "+ fonksiyon_adi);
			System.out.println();
			
			for(int j= 0; j < Math.pow(2,degisken.length); j++) {
				for(int i = 0; i <= degisken.length; i++) {
					if (i == degisken.length) {
						for (int k = 0; k < ikili.size(); k++) {
							if((Character.forDigit(truth_table[j][0], 10) == ikili.get(k).charAt(0) | ikili.get(k).charAt(0) == 'X') & (Character.forDigit(truth_table[j][1], 10) == ikili.get(k).charAt(1) | ikili.get(k).charAt(1) == 'X') & (Character.forDigit(truth_table[j][2], 10) == ikili.get(k).charAt(2) | ikili.get(k).charAt(2) == 'X')) {
								truth_table[j][degisken.length] = 1;
								if(!minterim.contains(j))
									minterim.add(j);
								
								String satir = "";
								for (int d = 0; d < degisken.length; d++) {
									satir += String.valueOf(truth_table[j][d]);
								}
								if(!minterm.contains(satir))
									minterm.add(satir);
								
							}	
						}
						System.out.print("| "+truth_table[j][i]);
					}
					else {
						System.out.print(""+truth_table[j][i] +" ");
					}
				}
				System.out.println();
			}
			
			for(int j= 0; j < Math.pow(2,degisken.length); j++) {
					if (minterim.indexOf(j) == -1){
						if(!maxterim.contains(j))
							maxterim.add(j);
						String satir = "";
						for (int d = 0; d < degisken.length; d++) {
							satir += String.valueOf(truth_table[j][d]);
						}
						if(!maxterm.contains(satir))
							maxterm.add(satir);
					}
			}
			
			System.out.println();
			goster_fonksiyon(fonksiyon_adi, degisken, minterim, minterm, maxterim, maxterm);
		}
		
		else if (degisken.length == 2) {
			int[][] truth_table = {{0,0,0},{0,1,0},{1,0,0},{1,1,0}};
			
			for (int i = 0; i < degisken.length; i++) {
				System.out.print(""+degisken[i] +" ");
			}
			System.out.print("| "+ fonksiyon_adi);
			System.out.println();
			
			for(int j= 0; j < Math.pow(2,degisken.length); j++) {
				for(int i = 0; i <= degisken.length; i++) {
					if (i == degisken.length) {
						for (int k = 0; k < ikili.size(); k++) {
							if((Character.forDigit(truth_table[j][0], 10) == ikili.get(k).charAt(0) | ikili.get(k).charAt(0) == 'X') & (Character.forDigit(truth_table[j][1], 10) == ikili.get(k).charAt(1) | ikili.get(k).charAt(1) == 'X')) {
								truth_table[j][degisken.length] = 1;
								if(!minterim.contains(j))
									minterim.add(j);
								
								String satir = "";
								for (int d = 0; d < degisken.length; d++) {
									satir += String.valueOf(truth_table[j][d]);
								}
								if(!minterm.contains(satir))
									minterm.add(satir);
								
							}	
						}
						System.out.print("| "+truth_table[j][i]);
					}
					else {
						System.out.print(""+truth_table[j][i] +" ");
					}
				}
				System.out.println();
			}
			
			for(int j= 0; j < Math.pow(2,degisken.length); j++) {
					if (minterim.indexOf(j) == -1){
						if(!maxterim.contains(j))
							maxterim.add(j);
						String satir = "";
						for (int d = 0; d < degisken.length; d++) {
							satir += String.valueOf(truth_table[j][d]);
						}
						if(!maxterm.contains(satir))
							maxterm.add(satir);
					}
			}
			
			System.out.println();
			goster_fonksiyon(fonksiyon_adi, degisken, minterim, minterm, maxterim, maxterm);
		}
		
		else {
			System.out.println("Henuz kodlanmamis degisken sayisina sahip fonksiyon tanimlandi.");
			System.out.println("Lutfen farkli bir fonksiyon deneyiniz.");
		}

	}

}

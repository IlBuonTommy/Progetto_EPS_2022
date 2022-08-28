package it.unibs.eps.ludogame.testnetworking;

import java.util.Arrays;

public class GestioneStringhe {
	public static String encode(int[] arrayDaCodificare) {
		String stringaFormattata = "";
		for(int i=0;i<arrayDaCodificare.length;i++) {
			stringaFormattata = stringaFormattata.concat(String.valueOf(arrayDaCodificare[i]).concat("#"));
		}
		return stringaFormattata;
	}
	public static int[] decode(String daDecodificare,int lunghezza) {
		int[] arrayRic = new int[lunghezza];
		String[] stringaRicevuta = daDecodificare.split("#");
		for(int i=0;i<stringaRicevuta.length;i++) {
			arrayRic[i] = Integer.parseInt(stringaRicevuta[i]);
		}
		return arrayRic;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] daMandare = {1,2,-4,3};
		String stringaFormattata = encode(daMandare);
		System.out.println("stringaFormattata: "+ stringaFormattata);
		int[] arrayRicevuto = decode(stringaFormattata,daMandare.length);
		System.out.println("arrayRicevuto:   "+ Arrays.toString(arrayRicevuto));
		
		

	}

}

package it.unibs.eps.ludogame.networking;

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
	
	public static String encodeConTipo(int[] arrayDaCodificare,String tipoArray) {
		String stringaFormattata = "";
		for(int i=0;i<arrayDaCodificare.length;i++) {
			stringaFormattata = stringaFormattata.concat(String.valueOf(arrayDaCodificare[i]).concat("#"));
		}
		return tipoArray.concat("@").concat(stringaFormattata);
	}
	
	public static String[] decodeTipo(String daDecodificare) {
		String[] stringaRicevuta = daDecodificare.split("@");
		return stringaRicevuta;
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] daMandare = {1,2,-4,3};
		
		String stringaFormattata = encodeConTipo(daMandare,"base");
		System.out.println("stringaFormattata: "+ stringaFormattata);
		
		String[] tipoDecodificato = decodeTipo(stringaFormattata);
		System.out.println(Arrays.toString(tipoDecodificato));
		System.out.println("L'array è di tipo:  "+ tipoDecodificato[0]);
		
		int[] arrayRicevuto = decode(tipoDecodificato[1],daMandare.length);
		System.out.println("arrayRicevuto:   "+ Arrays.toString(arrayRicevuto));
		
		

	}

}

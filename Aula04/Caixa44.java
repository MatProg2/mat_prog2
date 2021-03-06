import java.util.*;
import java.io.*;
import static java.lang.System.*;
import static java.lang.Integer.*;

public class Caixa44 {
	
	private long [] moedas;
	private static int n=0;

	public void adicionaMoeda (long moeda) {

		assert moeda > 0: "Moeda inválida";
		assert moedaReal (moeda): "Moeda inválida";
		moedas = metodoRetorno(moeda);

	}

	private boolean moedaReal (Long moeda) {

		for (int temp = 0;temp <= 5;temp++) {
			
			if (temp == 1) {
			
				for (int temp2 = 0; temp2 < 10000;temp2++) {
					
					if (moeda == temp * Math.pow(10, temp2)) return true;

				}

			} else if (temp == 2) {

				for (int temp2 = 0; temp2 < 10000;temp2++) {
					
					if (moeda == temp * Math.pow(10, temp2)) return true;

				}

			} else if (temp == 5) {

				for (int temp2 = 0; temp2 < 10000;temp2++) {
					
					if (moeda == temp * Math.pow(10, temp2)) return true;

				}

			}

		}

		return false;

	}

	public long [] metodoRetorno (long moeda) {

		long temp [] = new long [this.n+1];

		for (int i=0;i<this.n;i++) {
			
			temp [i] = this.moedas[i];
		
		}

		temp[this.n++] = moeda;

		return temp;

	}

	public long [] retirarDinheiro (long valorMin) {

		long sum = 0;
		long totalRetirado = 0;
		int l = 0;

		for (int i=0;i<this.n;i++) {
			sum += moedas[i];
		}

		if (sum < valorMin) {
			out.printf("Dinheiro insuficuente!");
			long temp [] = {-1};
			return temp;
		}

		for (int i=0;i<this.n;i++) {
			if (totalRetirado<valorMin){
				totalRetirado += moedas[i];
				l++;
			}
		}

		this.n -= l;

		long temp2 [] = new long [this.n];

		long retirado [] = new long [l];

		totalRetirado = 0;

		for (int i=0;i<this.n+l;i++) {
			
			if (totalRetirado < valorMin) {
			
				totalRetirado += moedas[i];
			
				retirado [i] = moedas [i];
			
			}

			else temp2 [i] = moedas [i]; 

		}

		moedas = temp2;

		return retirado;

	}

	public long [] moedas () {

		return this.moedas;

	}

	public long total () {

		long totalCarteira = 0;

		for (int i = 0;i<this.n;i++) {
			
			totalCarteira += moedas[i];

		}

		return totalCarteira;

	}


}
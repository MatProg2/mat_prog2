import java.util.*;
import java.io.*;
import static java.lang.System.*;
import static java.lang.Integer.*;

class Data32 {

	private int dia, mes, ano;

	public int dia () {

		return dia;
	}

	public int mes () {

		return mes;
	}

	public int ano () {

		return ano;
	}

	public Data32 (int d, int m, int a) {

		dia = d;
		mes = m;
		ano = a;

	}

	public boolean igualA (Data32 a) {

		if (a.dia == dia && a.mes==mes && a.ano==ano) return true;

		else return false;

	}

	public boolean menorDoQue (Data32 a) {

		if (a.ano < ano) return false;

		else if (a.ano > ano) return true;

		else {

			if (a.mes < mes) return false;

			else if (a.mes > mes) return true;

			else {

				if (a.dia < dia) return false;

				else if (a.dia > dia) return true;

				else return false;
			}

		}

	}

	public boolean maiorDoQue (Data32 a) {

		if (a.ano > ano) return false;

		else if (a.ano < ano) return true;

		else {

			if (a.mes < mes) return false;

			else if (a.mes < mes) return true;

			else {

				if (a.dia > dia) return false;

				else if (a.dia < dia) return true;

				else return false;
			}

		}

	}

	public void funcaoEscrever () {

		out.printf("%02d-%02d-%04d\n", dia, mes, ano);
	
	}

}
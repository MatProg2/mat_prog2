/*import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;
import exameP2.*;

public class PhoneCalls{
 
 public static Scanner xxx = new Scanner (System.in);
 
 public static void main(String [] args){
  
  if(args.length==0){
   out.println("Usage: java -ea PhoneCalls <arg1> ...");
   exit(112);
  }
  
  // criar as estruturas de dados (associative array)
  AssociativeArray<String> listaTelefonica = new AssociativeArray<String>();
  AssociativeArray<Node> chamadasFeitas = new AssociativeArray<Node>();
  AssociativeArray<Node> chamadasRecebidas = new AssociativeArray<Node>();
  
  // percorrer cada argumento introduzido
  for(int i=0; i<args.length; i++){
   
   if(args[i].endsWith(".nms")){
    lerNomes(listaTelefonica, args[i]); 
    continue;
   }
   else if(args[i].endsWith(".cls")){
    lerNumeros(listaTelefonica, chamadasFeitas, chamadasRecebidas, args[i]);
    continue;
   }
   else{
    imprimeChamadas(listaTelefonica, chamadasFeitas, chamadasRecebidas, args[i]); 
   } 
  }
  
  exit(1);
 }/// fim da main
 
 //associa um numero de telefone a uma pessoa
 public static void lerNomes(AssociativeArray<String> lista, String ficheiro){
  
  // criar o ficheiro de entrada
  File f = new File (ficheiro);
  if(!f.exists() || f.isDirectory() || !f.canRead())
   out.println("ERROR in file \"" + ficheiro + "\": read failure!");
  
  // fazer do ficheiro de entrada o argumento do scanner (ler o que está no ficheiro)
  try{
   xxx = new Scanner (f);
  }catch(FileNotFoundException e){
   out.println("ERRO: file \"" + f.getName() + "\" not found!");
   exit(0);
  }
  
  // associar cada numero (chave) a um nome (elemento)
  while(xxx.hasNextLine()){
   
   // a funcao trim() retira os espacos em branco que estiverem no fim e no inicio de uma string
   String numero = xxx.next().trim();
   String nome = xxx.nextLine().trim();
   
   if(numero.length() == 0 || nome.length() ==0) //se nao estiver listado um nome ou um numero, a linha e' ignorada
    continue;
   
   //faz a associacao
   lista.set(numero, nome);
  }
  
  xxx.close();// fecha o scanner (e o ficheiro)
 }/// fim lerNomes
 
 //associa quem fez uma chamada e quem a recebeu
 public static void lerNumeros(AssociativeArray<String> lista, AssociativeArray<Node> callsMade, AssociativeArray<Node> callsReceived, String ficheiro){
  
  // criar o ficheiro de entrada
  File f = new File (ficheiro);
  if(!f.exists() || f.isDirectory() || !f.canRead())
   out.println("ERROR in file \"" + ficheiro + "\": read failure!");
  
  // fazer do ficheiro de entrada o argumento do scanner (ler o que está no ficheiro)
  try{
   xxx = new Scanner (f);
  }catch(FileNotFoundException e){
   out.println("ERRO: file \"" + f.getName() + "\" not found!");
   exit(0);
  }
  
  // passar as chamadas para os arrays associativos
  while(xxx.hasNextLine()){
   String [] call = xxx.nextLine().split(" ");
   
   // validar argumentos
    // se nao houver 3 argumentos numa linha...
   if(call.length!=3){
    out.println("ERROR in file \""+ f.getName() +"\": invalid format!");
    exit(10);
   }
   
   String origem, destino;
   int tempo=0;
   
   origem = call[0].trim();  // primeiro argumento da linha
   destino = call[1].trim(); // segundo argumento da linha
   try{
    tempo = Integer.parseInt(call[2].trim()); // converte o terceiro elemento da linha em inteiro
   }catch(NumberFormatException e){
    out.println("ERROR in file \""+ f.getName() +"\": integer conversion failure!");
    exit(11);
   }
   
   // imprime a chamada que está nessa linha, substituindo, se possivel, o numero pelo nome correspondente
   out.printf("%s to %s (%d seconds)\n", ObterNome(lista, origem), ObterNome(lista, destino), tempo);
   
   //atribuir a chamada nessa linha ao array associativo
   Node cMade = new Node(), cReceived = new Node();
   
   cReceived.numero = origem;
   cReceived.duracao = tempo;
   
   cMade.numero = destino;
   cMade.duracao = tempo;
   
   if(callsMade.exists(origem)){
    Node first = callsMade.get(origem);
    cMade.next = first;
   }
   callsMade.set(origem, cMade);
   
   if(callsReceived.exists(destino)){
    Node first = callsReceived.get(destino);
    cReceived.next = first;
   }
   callsReceived.set(destino, cReceived);
  }
  xxx.close();
 }/// fim lerNumeros
 
 //se o numero tiver um nome associado, esta funcao devolve-o
 public static String ObterNome(AssociativeArray<String> list, String num){
  
  if(list.exists(num))
   return list.get(num);
   
  return num;
 }/// fim ObterNome
 
 //imprime as chamadas feitas (e recebidas...)
 public static void imprimeChamadas(AssociativeArray<String> lista, AssociativeArray<Node> callsMade, AssociativeArray<Node> callsReceived, String num){
  
  out.println();
  out.printf("Calls made by %s:\n", ObterNome(lista, num));
  if(callsMade.exists(num)){
   Node n = callsMade.get(num);
   
   while(n!=null){
    out.printf("  - to %s (%d seconds)\n", lista.exists(n.numero)? ObterNome(lista, n.numero) : "phone "+n.numero ,n.duracao);
            // -- >         o que esta acima deste comentario e' um if abreviado      <--
    
    out.print("  - to ");
                if(lista.exists(n.numero)){
                    out.println(ObterNome(lista, n.numero) + " (" + n.duracao + " seconds)");
                }
                else{
     out.println("phone " + n.numero + " (" + n.duracao + " seconds)");
    }
    
    n = n.next;
   }
  }
  out.printf("Calls received by %s:\n", ObterNome(lista, num));
  if(callsReceived.exists(num)){
   Node n = callsReceived.get(num);
   
   while(n!=null){
    out.printf("  - from %s (%d seconds)\n", lista.exists(n.numero)? ObterNome(lista, n.numero) : "phone "+n.numero,n.duracao);
    n = n.next;
   }
  }
 }/// fim imprimeChamadas
  
}/// fim de PhoneCalls
class Node{
 String numero;
 int duracao;
 Node next;
}
*/

import static java.lang.System.*;
import java.io.*;
import p2utils.*;

public class P82
{
   // Apresenta por ordem crescente
   // os numeros inteiros dados na linha de comando
   // Utiliza uma lista ordenada para fazer isto

   public static void main(String[] args) throws IOException
   {
      SortedList<Integer> lst = new SortedList<Integer>();

      for(int i=0; i<args.length; i++) {
         lst.insert(Integer.parseInt(args[i]));
      }
      out.println(lst.isSorted());
      
      while(!lst.isEmpty()) {
         out.println(lst.first());
         lst.removeFirst();
      }
   }
}



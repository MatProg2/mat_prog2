import static java.lang.System.*;                                //n�o consegui fazer o que � pedido, em vez de ao fim de cada jogada 
import java.util.Scanner;                                        //passar ao proximo jogador, est� a passar ao fim de cada jogo
import game.*;

public class P1
{
  private static final Scanner input = new Scanner(in);
  
  public static void main(String[] args)
  {
    /* if (args.length != 0 && args.length != 2)
     {
     err.println("Uso: java -ea P1 [<NUM_LINES> <NUM_COLUMNS>] player1 ... playerN");
     exit(1);
     }*/
    
    int numLines = Integer.parseInt(args[0]);
    int numColumns = Integer.parseInt(args[1]);
    String nome="";
    
    /* if (args.length == 2)
     {
     numLines = Integer.parseInt(args[0]);
     numColumns = Integer.parseInt(args[1]);
     }*/
    
    if(args.length>2)
    {
      for(int i=2; i<args.length; i++)
      {
        nome=args[i];
        jogar(nome, numLines, numColumns);
      }
    }
    
    else
    {
      jogar("PC", numLines, numColumns);
    }
  }
  
  public static void jogar(String player, int  numLines, int numColumns)
  {
    int cont = 1;
    Board game = new Board(player, numLines, numColumns);
    
    while (!game.finished())
    {
      out.println();
      out.println("---------------ROUND "+cont+"--------------");
      game.print();
      out.println();
      out.println("Commands:");
      out.println("   position1 position2 -> attempt to uncover a pair in two positions (ex: A1 A2)");
      out.println("   stop    -> stops game");
      out.println();
      out.print("Command: ");
      
      String word;
      String pos2;
      
      do{
        out.println();
        out.print("1� carta:");
        word=input.nextLine();
        
        if(word.equals("stop"))
        {
          err.println("Game stopped");
          exit(2);
        }
        
        out.print("2� carta");
        pos2=input.nextLine();
        
        if (pos2.equals("stop"))
        {
          err.println("Game stopped!");
          exit(3);
        }
        
        if(word.length()>2 || pos2.length()>2 || !Board.validPosition(word) || !Board.validPosition(pos2) || !Board.validMove(word, pos2))
          out.println("Jogada Inv�lida. Tente outra vez");
        
        
      }while(!Board.validPosition(pos2) || !Board.validPosition(word) || !Board.validMove(word, pos2));
      
      if(game.tryUncover(word, pos2))
      {
        out.println("Success!");
        out.println("Pares encontrados: "+game.pairsFound());
       
        if (game.finished())
        {
          out.println();
          out.println("================ Player's "+player+" game completed! ================");
          out.println();
        }
      }
      
      else
      {
        out.println("Wrong!");
        out.println("Pares encontrados: "+game.pairsFound());
      }
      
      cont++;
    }
    
    out.println();
    out.println("That's all folks!");
  }
}
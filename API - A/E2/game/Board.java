package game;

import static java.lang.System.*;
import java.io.IOException;

public class Board
{
   public final static int pause = 2000; // 2 seconds

   public Board(String player, int numLines, int numColumns)
   {
      assert player != null && player.length() > 0;
      assert numLines > 0 && numLines < 10;
      assert numColumns > 0 && numColumns < 10;

      this.player = player;
      this.numLines = numLines;
      this.numColumns = numColumns;
      numMoves = 0;
      matrix = new char[numLines][numColumns];
      uncovered = new boolean[numLines][numColumns];
      for(int l = 0; l < numLines; l++)
         for(int c = 0; c < numColumns; c++)
         {
            matrix[l][c] = ' ';
            uncovered[l][c] = false;
         }
      createRandomBoard();
   }

   public static  int numLines;
   public static  int numColumns;
   public final String player;

   private static int lin(String pos)
   {
      return (int)Character.toUpperCase(pos.charAt(0))-(int)'A';
   }

   private static int col(String pos)
   {
      return (int)pos.charAt(1)-(int)'1';
   }

   static boolean validPosition(String pos)
   {
      return (pos != null) && (pos.length() == 2) &&
             (lin(pos) >= 0 && lin(pos) < numLines) &&
             (col(pos) >= 0 && col(pos) < numColumns);
   }

   /*
    * Is position pos uncovered?
    *
    * True only when a pair has been found.
    */
   public boolean uncovered(String pos) 
   {
      return uncovered[lin(pos)][col(pos)];
   }
   /*
    * Is uncovering pos1 and pos2 a valid move?
    *
    * True only when both positions are covered and not at the same location.
    */
   public int pairsFound() {
    return pares;
   }

   public boolean validMove(String pos1, String pos2)
   {
      assert validPosition(pos1);
      assert validPosition(pos2);

      return !uncovered[lin(pos1)][col(pos1)] &&
             !uncovered[lin(pos2)][col(pos2)] &&
             (lin(pos1) != lin(pos2) || col(pos1) != col(pos2));
   }

   /*
    * Game finished?
    *
    * True only when all pairs have been fouind.
    */
   public boolean finished()
   {
      boolean result = true;

      int count = 0;
      for(int l = 0; (count <= 1) && l < numLines; l++)
         for(int c = 0; (count <= 1) && c < numColumns; c++)
            if (!uncovered[l][c])
               count++;

      return count <= 1;
   }

   /*
    * Try to uncover pos1 and pos2.
    *
    * It succeeds if a pair is present in both positions.
    */
   private static int pares=0;
   public boolean tryUncover(String pos1, String pos2)
   {
      assert !finished();
      assert validPosition(pos1);
      assert validPosition(pos2);
      assert validMove(pos1, pos2);

      boolean result;

      int l1 = lin(pos1);
      int c1 = col(pos1);
      int l2 = lin(pos2);
      int c2 = col(pos2);

      uncovered[l1][c1] = true;
      uncovered[l2][c2] = true;
      print();
      try { Thread.sleep(pause); } catch(InterruptedException e) {exit(-1);}
      result = (matrix[l1][c1] == matrix[l2][c2]);
      numMoves++;
      if (result) pares++;
      if (!result)
      {
         /* Clears the Console (stdout) */
         out.print("\u001B[2J");

         uncovered[l1][c1] = false;
         uncovered[l2][c2] = false;
         print();
      }
      else if (finished())
      {
         for(int l = 0; l < numLines; l++)
            for(int c = 0; c < numColumns; c++)
               if (!uncovered[l][c])
                  uncovered[l][c] = true;
         print();
      }

      return result;
   }

   private void createRandomBoard()
   {
      int letter = -1;
      for(int n = 0; n < (numLines*numColumns/2)*2; n++)
      {
         if (n % 2 == 0)
            letter++;
         int l,c;
         do
         {
            l = (int)(Math.random()*numLines);
            c = (int)(Math.random()*numColumns);
         }
         while(matrix[l][c] != ' ');
         matrix[l][c] = (char)((int)'A' + letter);
      }
   }

   /*
    * Prints the matrix (no need to waste time trying to understand it!)
    */
   public void print()
   {
      out.println();
      out.println("Player: "+player);
      out.println();
      out.println("Pares encontrados: "+pairsFound());
      out.println();
      out.print("   ");
      for(int c = 0; c < numColumns; c++)
         out.printf(" %2d", (c+1));
      out.println();
      out.print("   +");
      for(int c = 0; c < numColumns; c++)
         out.printf("---");
      out.println("+");
      for(int l = 0; l < numLines; l++)
      {
         out.printf(" %c |", (char)((int)'A'+l));
         for(int c = 0; c < numColumns; c++)
         {
            //if ((c+l)%2==1) out.print("\u001B[46m");
            if (uncovered[l][c])
               out.printf(" %c ", matrix[l][c]);
            else
               out.printf(" ? ");
            //if ((c+l)%2==1) out.print("\u001B[49m");
         }
         out.printf("| %c\n", (char)((int)'A'+l));
      }
      out.print("   +");
      for(int c = 0; c < numColumns; c++)
         out.printf("---");
      out.println("+");
      out.print("   ");
      for(int c = 0; c < numColumns; c++)
         out.printf(" %2d", (c+1));
      out.println();
      out.println();
      out.println("Moves: "+numMoves);
      out.println();
   }

   private int numMoves;
   private  char[][] matrix;
   private boolean[][] uncovered;
}


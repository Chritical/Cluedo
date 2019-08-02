package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class BoardTests {
     public Room[][] roomLocation = new Room[][];

     public void makeRoom(int xStart, int xEnd, int yStart, int yEnd, Room room){
          for(int i = xStart; i!=xEnd;i++){
               for(int j = yStart; j!=yEnd; j++){
                    roomLocation[i][j] = room;
               }
          }
     }

     public Board makeTestBoard() {
          ArrayList<Room> rooms = new ArrayList<Room>();

          Token[][] tokenLocation = new Token[25][25];

          //adds tokens to the board
          tokenLocation[7][24] = new Token("Miss Scarlet", 7, 24);
          tokenLocation[0][17] = new Token("Colonel Mustard", 0, 17);
          tokenLocation[0][9] = new Token("Mrs. White", 0, 9);
          tokenLocation[0][14] = new Token("Mr. Green", 0, 14);


          //makes rooms
          rooms.add(new room("Kitchen"));
          rooms.add(new room ("Ball Room"));
          rooms.add(new room("Conservatory"));
          rooms.add(new room("Billiard Room"));
          rooms.add(new room("Library"));
          rooms.add(new room("Study"));
          rooms.add(new room("Hall"));
          rooms.add(new room("Lounge"));
          rooms.add(new room("Dining Room"));
          rooms.add(new room("Restricted"));

          //adds rooms to the board

          //kitchen
          makeRoom(0, 6, 1,6,rooms.get(0));
          makeRoom(1,6,5, 6, rooms.get(0));
          //ballRoom
          makeRoom(8,16,2,8,rooms.get(1));
          makeRoom(10,13,1, 2,rooms.get(1));
          //Conservatory
          makeRoom(18,24,1,5,rooms.get(2));
          makeRoom(19, 23, 5, 6, rooms.get(2));
          //billiard room
          makeRoom(18,24,8, 13, rooms.get(3));
          //Library
          makeRoom(18,24,13,18, rooms.get(4));
          makeRoom(17,18, 14, 17, rooms.get(4));
          makeRoom(18, 23, 13, 14, rooms.get(4));
          makeRoom(18, 23, 18,19, rooms.get(4) );
          //Study
          makeRoom(17, 24, 21, 24, rooms.get(5));
          makeRoom(18, 24, 24,25, rooms.get(5));

          //Hall
          makeRoom(10, 16, 18,25, rooms.get(6));
          //lounge
          makeRoom(0, 7, 19, 24, rooms.get(7));
          makeRoom(0,6,24,25,rooms.get(7));
          //Dining room
          makeRoom(0, 5, 8, 15, rooms.get(8));
          makeRoom(5, 8, 9, 15, rooms.get(8));
          //Restricted Squares
          makeRoom(0, 9, 0, 1, rooms.get(9));
          makeRoom(10, 14, 0, 1, rooms.get(9));
          makeRoom(7,8,1,2,rooms.get(9));
          makeRoom(17, 18, 1,2,rooms.get(9));
          makeRoom(0, 1, 6,7,rooms.get(9));
          makeRoom(23, 24, 5,6,rooms.get(9));
          makeRoom(0, 1, 8,9,rooms.get(9));
          makeRoom(23, 24, 7,8,rooms.get(9));
          makeRoom(11, 16, 10,17,rooms.get(9));
          makeRoom(23, 24, 13,15,rooms.get(9));
          makeRoom(0, 1, 16,17,rooms.get(9));
          makeRoom(0, 1, 18,19,rooms.get(9));
          makeRoom(17, 18, 18,19,rooms.get(9));
          makeRoom(23, 24, 20,21,rooms.get(9));
          makeRoom(23, 24, 1,2,rooms.get(9));
          makeRoom(6, 7, 24,25,rooms.get(9));
          makeRoom(8, 9, 24,25,rooms.get(9));
          makeRoom(17, 18, 24,25,rooms.get(9));
          makeRoom(15, 16, 24,25,rooms.get(9));


          return new Board(rooms, tokenLocation);
     }



     @Test
     public void tGetRoom() {
          assertEquals(makeTestBoard().getRoom(0,0), new Room("kitchen"));
     }
     @Test
     public void tGetWrongRoom(){
          assertFalse(makeTestBoard().getRoom(18,10), new Room("kitchen"));
     }

     @Test
     public void tGetToken() {
          assertEquals(makeTestBoard().getToken(7,24), new Token("Miss Scarlet", 7, 24));
     }

     @Test
     public void tGetWrongToken() {
          assertFalse(makeTestBoard().getToken(7,24), new Token("Colonel Mustard", 0, 17));
     }

     @Test
     public void tGetMissingToken() {
          assertFalse(makeTestBoard().getToken(18,10), new Token("Miss Scarlet", 7, 24));

     }

     @Test
     public void tDisplay(){

          assertEquals(makeTestBoard().print(), "|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n"+
          "|K|K|K|K|K|K|X| | | |B|B|B|B| | | |X|C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | |C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | | | | | | |\n"+
          "| | | | | | | | |B|B|B|B|B|B|B|B| | | | | | | | |\n"+
          "|X| | | | | | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D| | | | | | | | | | | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D|D| | |X|X|X|X| | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D|D| | |X|X|X|X| | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D|D| | |X|X|X|X| | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D|D| | |X|X|X|X| | | | | | | | |X|\n"+
          "|D|D|D|D|D|D|D|D|D| | |X|X|X|X| | | |L|L|L|L|L|X|\n"+
          "|D|D|D|D|D|D|D|D|D| | |X|X|X|X| | |L|L|L|L|L|L|L|\n"+
          "|X| | | | | | | | | | |X|X|X|X| | |L|L|L|L|L|L|L|\n"+
          "|M| | | | | | | | | | | | | | | | |L|L|L|L|L|L|L|\n"+
          "|X| | | | | | | | | |H|H|H|H|H| | | |L|L|L|L|L|L|\n"+
          "|l|l|l|l|l|l|l|l| | |H|H|H|H|H| | | | | | | | | |\n"+
          "|l|l|l|l|l|l|l|l| | |H|H|H|H|H| | | | | | | | |X|\n"+
          "|l|l|l|l|l|l|l|l| | |H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
          "|l|l|l|l|l|l|l|l| | |H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
          "|l|l|l|l|l|l|l|l| | |H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
          "|l|l|l|l|l|l|l|X|S|X|H|H|H|H|H|X| |X|S|S|S|S|S|S|");
     }

     @Test
     public void tBoardMove() {
          ArrayList<Token> tokens = new ArrayList<Token>();
          Item green = new Token("Mr. Green", 14, 0);
          tokens.add(new Token(green));
          Board board = new Board(tokens);
          board.move(green, new Point(5, 7));
          assertTrue(board.getToken(14,0) == null);
          assertTrue(board.getToken(19,7)==green);
     }



}

package Tests;

import static org.junit.Assert.*;

import GamePackage.Board;
import GamePackage.Item;
import GamePackage.Room;
import GamePackage.Token;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class BoardTests {



     @Test
     public void tGetRoom() {
          assertEquals(new Board().getRoom(new Point(0,0)), new Room("kitchen"));
     }
     @Test
     public void tGetWrongRoom(){
          assertFalse(new Board().getRoom(new Point(18,10)).equals(new Room("kitchen")));
     }

     @Test
     public void tGetNoRoom(){
          assertTrue(new Board().getRoom(new Point(18,10)) == null);
     }

     @Test
     public void tGetToken() {
         Board board = new Board();
         board.addToken(new Token("Miss Scarlet"));
          assertTrue(board.getToken(new Point(7,24)).equals( new Token("Miss Scarlet")) );
     }



     @Test
     public void tGetMissingToken() {
          assertFalse(new Board().getToken(new Point(18,10)).equals(new Token("Miss Scarlet")));

     }
     @Test
     public void tAddAndGetToken(){
          Board board = new Board();
          Token white = new Token("Mrs. White");
          board.addToken(white);
          assertTrue(board.getToken(new Point (9,0)).equals(white));
     }

     @Test
     public void tDisplay(){
          Board board = new Board();
          board.addToken(new Token("Mrs. White"));
          board.addToken(new Token("Mr. Green"));
          board.addToken(new Token("Miss Scarlet"));
          board.addToken(new Token("Colonel Mustard"));
          assertEquals("|X|X|X|X|X|X|X|X|X|W|X|X|X|X|G|X|X|X|X|X|X|X|X|X|\n"+
          "|K|K|K|K|K|K|X| | | |B|B|B|B| | | |X|C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | |C|C|C|C|C|C|\n"+
          "|K|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | |C|C|C|C|X|\n"+
          "|X|K|K|K|K|K| | |B|B|B|B|B|B|B|B| | | | | | | | |\n"+
          "| | | | | | | | |B|B|B|B|B|B|B|B| | | | | | | |X|\n"+
          "|X| | | | | | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D| | | | | | | | | | | | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |b|b|b|b|b|b|\n"+
          "|D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | | | | | | |X|\n"+
          "|D|D|D|D|D|D|D|D| | |X|X|X|X|X| | | |L|L|L|L|L|X|\n"+
          "|D|D|D|D|D|D|D|D| | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
          "|X| | | | | | | | | |X|X|X|X|X| | |L|L|L|L|L|L|L|\n"+
          "|M| | | | | | | | | | | | | | | | |L|L|L|L|L|L|L|\n"+
          "|X| | | | | | | | |H|H|H|H|H|H| | | |L|L|L|L|L|X|\n"+
          "|l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | | |\n"+
          "|l|l|l|l|l|l|l| | |H|H|H|H|H|H| | | | | | | | |X|\n"+
          "|l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
          "|l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
          "|l|l|l|l|l|l|l| | |H|H|H|H|H|H| | |S|S|S|S|S|S|S|\n"+
          "|l|l|l|l|l|l|X|S|X|H|H|H|H|H|H|X| |X|S|S|S|S|S|S|", board.toString());
     }

     @Test
     public void tBoardMove() {
          Token green = new Token("Mr. Green");
          Board board = new Board();
          board.addToken(green);
          board.move(new Point (14, 0), new Point(19, 7));
          assertTrue(board.getToken(new Point(14,0)) == null);
          assertTrue(board.getToken(new Point(19,7))==green);
     }



}

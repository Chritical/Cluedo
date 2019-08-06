package GamePackage;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private Room[][] roomLocation= new Room[24][25];
    private Token[][] tokenLocation = new Token[25][25];

    /**
     * checks if a point on the board is in the same location as the given room
     * @param p
     * @param room
     * @return
     */
    public boolean isInRoom(Point p, Room room) {
        if(room == null){
            return false;
        }
        if(roomLocation[p.x][p.y] == null){
            return false;
        }
        return roomLocation[p.x][p.y].equals(room);

    }

    /**
     * Creates the Cluedo Board that initially stores all room locations
     * and restricted squares.
     */
    public Board(){
        ArrayList<Room> rooms = new ArrayList<Room>();
        //makes rooms
        rooms.add(new Room("Kitchen"));
        rooms.add(new Room("Ball Room"));
        rooms.add(new Room("Conservatory"));
        rooms.add(new Room("Billiard Room"));
        rooms.add(new Room("Library"));
        rooms.add(new Room("Study"));
        rooms.add(new Room("Hall"));
        rooms.add(new Room("Lounge"));
        rooms.add(new Room("Dining Room"));
        rooms.add(new Room("Restricted"));

        //adds rooms to the board

        //kitchen
        makeRoom(0, 6, 1,7,rooms.get(0));
        //ballRoom
        makeRoom(8,16,2,8,rooms.get(1));
        makeRoom(10,14,1, 2,rooms.get(1));
        //Conservatory
        makeRoom(18,24,1,5,rooms.get(2));
        makeRoom(19, 23, 5, 6, rooms.get(2));
        //billiard room
        makeRoom(18,24,8, 13, rooms.get(3));
        //Library
        makeRoom(18,24,15,18, rooms.get(4));
        makeRoom(17,18, 15, 18, rooms.get(4));
        makeRoom(18, 23, 14, 15, rooms.get(4));
        makeRoom(18, 23, 18,19, rooms.get(4) );
        //Study
        makeRoom(17, 24, 21, 24, rooms.get(5));
        makeRoom(18, 24, 24,25, rooms.get(5));

        //Hall
        makeRoom(9, 15, 18,25, rooms.get(6));
        //lounge
        makeRoom(0, 7, 19, 25, rooms.get(7));
        //Dining room
        makeRoom(0, 5, 9, 16, rooms.get(8));
        makeRoom(5, 8, 10, 16, rooms.get(8));
        //Restricted Squares
        makeRoom(0, 9, 0, 1, rooms.get(9));
        makeRoom(10, 14, 0, 1, rooms.get(9));
        makeRoom(15, 24, 0, 1, rooms.get(9));
        makeRoom(6,7,1,2,rooms.get(9));
        makeRoom(17, 18, 1,2,rooms.get(9));
        makeRoom(0, 1, 6,7,rooms.get(9));
        makeRoom(23, 24, 5,6,rooms.get(9));
        makeRoom(0, 1, 8,9,rooms.get(9));
        makeRoom(23, 24, 7,8,rooms.get(9));
        makeRoom(10, 15, 10,17,rooms.get(9));
        makeRoom(23, 24, 13,15,rooms.get(9));
        makeRoom(0, 1, 16,17,rooms.get(9));
        makeRoom(0, 1, 18,19,rooms.get(9));
        makeRoom(23, 24, 18,19,rooms.get(9));
        makeRoom(23, 24, 20,21,rooms.get(9));
        makeRoom(6, 7, 24,25,rooms.get(9));
        makeRoom(8, 9, 24,25,rooms.get(9));
        makeRoom(17, 18, 24,25,rooms.get(9));
        makeRoom(15, 16, 24,25,rooms.get(9));
    }

    private void makeRoom(int xStart, int xEnd, int yStart, int yEnd, Room room){
        for(int i = xStart; i!=xEnd;i++){
            for(int j = yStart; j!=yEnd; j++){
                roomLocation[i][j] = room;
            }
        }
    }

    public Room getRoom(Point p){
        return roomLocation[p.x][p.y];

    }

    public Token getToken(Point p){
        return tokenLocation[p.x][p.y];
    }

    /**
     * adds a token to it's starting location on the board and
     * adds the location on the board to the token
     * @param t
     */
    public void addToken(Token t){
        if(t.getName() == null){
            return;
        }
        else if(t.getName().equals("Professor Plum")){
            tokenLocation[23][19] = t;
            t.setPoint(23, 19);
        }
        else if(t.getName().equals("Miss Scarlett")){
            tokenLocation[7][24] = t;
            t.setPoint(7, 24);
        }
        else if(t.getName().equals("Mrs. White")){
            tokenLocation[9][0] = t;
            t.setPoint(9, 0);
        }
        else if(t.getName().equals("Mrs. Peacock")){
            tokenLocation[23][6] = t;
            t.setPoint(23, 6);
        }
        else if(t.getName().equals("Colonel Mustard")){
            tokenLocation[0][17] = t;
            t.setPoint(0, 17);
        }
        else if(t.getName().equals("Mr. Green")){
            tokenLocation[14][0] = t;
            t.setPoint(14, 0);
        }
        else{
            assert false;
        }
    }

    /**
     * Moves the token at the from location to the to location and sets
     * the from location to null
     * Also updates the location in the token moved
     */
    public void move(Point from,Point to){
        tokenLocation[to.x][to.y] = tokenLocation[from.x][from.y];
        tokenLocation[from.x][from.y] = null;
        if(tokenLocation[to.x][to.y]!=null){
                tokenLocation[to.x][to.y].setPoint(to.x, to.y);
    }
    }

    /**
     * makes a visual representation of the board using ascii characters
     *
     */
    public String toString(){
        char[][] board = new char[24][25];
        StringBuilder boardBuilder = new StringBuilder();
        for(int y = 0; y<25; y++){
            for(int x = 0; x<24; x++){
                Room r = roomLocation[x][y];
                Token t = tokenLocation[x][y];

                if(r==null){
                    board[x][y] = ' ';
                }
                else if(r.getName().equals("Kitchen")){
                    board[x][y] = 'K';
                }
                else if(r.getName().equals("Conservatory")){
                    board[x][y] = 'C';
                }
                else if(r.getName().equals("Ball Room")){
                    board[x][y] = 'B';
                }
                else if(r.getName().equals("Billiard Room")){
                    board[x][y] = 'b';
                }
                else if(r.getName().equals("Library")){
                    board[x][y] = 'L';
                }
                else if(r.getName().equals("Study")){
                    board[x][y] = 'S';
                }
                else if(r.getName().equals("Hall")){
                    board[x][y] = 'H';
                }
                else if(r.getName().equals("Lounge")){
                    board[x][y] = 'l';
                }
                else if(r.getName().equals("Dining Room")){
                    board[x][y] = 'D';
                }
                else if(r.getName().equals("Restricted")){
                    board[x][y] = 'X';
                }
                if(t== null){
                }
                else if(t.getName().equals("Professor Plum")){
                    board[x][y] = 'p';
                }
                else if(t.getName().equals("Miss Scarlett")){
                    board[x][y] = 's';
                }
                else if(t.getName().equals("Mrs. White")){
                    board[x][y] = 'w';
                }
                else if(t.getName().equals("Mrs. Peacock")){
                    board[x][y] = 'e';
                }
                else if(t.getName().equals("Colonel Mustard")){
                    board[x][y] = 'm';
                }
                else if(t.getName().equals("Mr. Green")){
                    board[x][y] = 'g';
                }


            }

            }


            boardBuilder.append("  ");
            for (int i = 0; i < 24; i++) {
                boardBuilder.append(" ");
                boardBuilder.append((char) ('a' + i));
            }
            boardBuilder.append("\n");

            for (int y = 0; y<25;y++){
                boardBuilder.append((char) ('A' + y) + " ");
                for(int x=0; x<24; x++){
                    assert(board[x][y] != 0);
                    boardBuilder.append('|');
                    boardBuilder.append(board[x][y]);

                }
                boardBuilder.append('|');
                boardBuilder.append('\n');

            }
            return boardBuilder.toString() + "\n"+
                    "K = Kitchen, B = Ball Room, C = Conservatory,\n"+
                    "L = Library, S = Study, H = Hall, l = Lounge, \n"+
                    "D = Dining Room, X = Restricted, s = Scarlett, \n"+
                    "e = Peacock, m = Mustard, g = Green, p = Plum, \n"+
                    "w = White";

        }

    /**
     * prints a visual representation of the board using ascii characters
     */
    public void print(){
            System.out.println(toString());
        }
    }


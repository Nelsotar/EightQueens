/*
 * Copyright (C) 2020 Taryn Nelson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package EightQueens;

/**
 * Class for creating a node for CircularLinkedList.
 * @author Taryn Nelson
 * @since 2020-04-06
 */
public class Node {
   /**
    * pane - BoardPane of current node.
    */
   protected BoardPane pane;
   
   /**
    * next - next node in CircularLinkedList.
    */ 
   protected Node next;
   
   /**
    * prev - previous node in CircularLinkedList.
    */
   protected Node prev;
   
   protected int positionNum;
   
   /**
    * Constructor creates a node with provided pane and null next/prev nodes.
    * @param pane BoardPane to be associated with current node.
    * @param positionNum Position number of the Node.
    */
   public Node(BoardPane pane, int positionNum){
       this.pane = pane;
       this.positionNum = positionNum;
       prev = null;
       next = null;
   }
   
}

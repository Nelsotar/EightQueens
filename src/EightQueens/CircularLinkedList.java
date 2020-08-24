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
 *Circular Linked List class.
 * @author Taryn Nelson
 * @since 2020-04-06
 */
public class CircularLinkedList {
    /**
     * head - head node
     */
    Node head;
    
    /**
     * tail - tail node
     */
    Node tail;
    
    /**
     * nodeCount - number of nodes in list
     */
    int nodeCount;
    
    /**
     * prevNode - holds previous node for setting previous node to the node 
     * after it.
     */ 
    Node prevNode;
    
    /**
     * currentNode - node that is currently pointed at by the list.
     */
    Node currentNode;
    
    /**
     * No-arg constructor sets the head and tail to null and nodeCount to zero.
     */
    public CircularLinkedList(){
        head = tail = null;
        nodeCount = 0;
    }
    
    /**
     * Method returns the number of nodes in list.
     * @return number of nodes in list
     */
    public int size(){
        return nodeCount;
    }
    
    /**
     * Method accepts a BoardPane and creates a node + adds it to the list.
     * @param pane BoardPane to be encapsulated in new node
     */
    public void add(BoardPane pane){
        Node newNode = new Node(pane);
        if(nodeCount == 0){
            head = tail = prevNode = newNode;
            currentNode = head;
        }
        else{
            tail.next = newNode;
            prevNode = tail;
            tail = newNode;
            tail.prev = prevNode;
            tail.next = head;
            head.prev = tail;
        }
        
        nodeCount++;
    }
    
    /**
     * Returns the next node's pane and sets current node to next node.
     * @return the next node's BoardPane 
     */
    public BoardPane getNextPane(){
        getNextNode();
        return currentNode.pane;
    }
    
    /**
     * Returns the previous node's pane and sets current node to previous node.
     * @return the previous node's BoardPane
     */
    public BoardPane getPreviousPane(){
        getPreviousNode();
        return currentNode.pane;
    }
    
    /**
     * Returns the current node's pane.
     * @return the current node's BoardPane
     */
    public BoardPane getCurrentPane(){
        return currentNode.pane;
    }
    
    /**
     * Returns the head node.
     * @return head 
     */
    public BoardPane getFirst(){
        currentNode = head;
        return currentNode.pane;
    }
    
    /**
     * Sets current node to next node.
     */
    public void getNextNode(){
        currentNode = currentNode.next;
    }
    
    /**
     * Sets current node to previous node.
     */
    public void getPreviousNode(){
        currentNode = currentNode.prev;
    }
    
    /**
     * Returns the BoardPane that has position number corresponding to argument
     * @param position solution number of the BoardPane
     * @return 
     */
    public BoardPane getPaneAtPosition(int position){
        if(position <= nodeCount && position >= 1){
            BoardPane pane = currentNode.pane;
            while(pane.getPositionNum() != position){
                pane = getNextPane();
            }
            return pane;
        }else{
            return null;
        }
        
    }
    
}

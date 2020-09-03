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
    private Node head;
    
    /**
     * tail - tail node
     */
    private Node tail;
    
    /**
     * nodeCount - number of nodes in list
     */
    private int nodeCount;
    
    /**
     * prevNode - holds previous node for setting previous node to the node 
     * after it.
     */ 
    private Node prevNode;
    
    /**
     * currentNode - node that is currently pointed at by the list.
     */
    private Node currentNode;
    
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
    protected int size(){
        return nodeCount;
    }
    
    /**
     * Method accepts a BoardPane and creates a node + adds it to the list.
     * @param pane BoardPane to be encapsulated in new node
     */
    protected void add(BoardPane pane){
        Node newNode = new Node(pane, nodeCount + 1);
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
    protected BoardPane getNextPane(){
        getNextNode();
        return currentNode.pane;
    }
    
    /**
     * Returns the previous node's pane and sets current node to previous node.
     * @return the previous node's BoardPane
     */
    protected BoardPane getPreviousPane(){
        getPreviousNode();
        return currentNode.pane;
    }
    
    /**
     * Returns the current node's pane.
     * @return the current node's BoardPane
     */
    protected BoardPane getCurrentPane(){
        return currentNode.pane;
    }
    
    /**
     * Returns the head node.
     * @return head 
     */
    protected BoardPane getFirst(){
        currentNode = head;
        return currentNode.pane;
    }
    
    /**
     * Sets current node to next node.
     */
    protected void getNextNode(){
        currentNode = currentNode.next;
    }
    
    /**
     * Sets current node to previous node.
     */
    protected void getPreviousNode(){
        currentNode = currentNode.prev;
    }
    
    /**
     * Returns the BoardPane that has position number corresponding to argument
     * @param position solution number of the BoardPane
     * @return BoardPane of specified Node.
     */
    protected BoardPane getPaneAtPosition(int position){
        if(position <= nodeCount && position >= 1){
            while(currentNode.positionNum != position){
               // System.out.println(currentNode.positionNum);
                getNextNode();
            }
            return currentNode.pane;       
        }else{
            return null;
        }
        
    }
    
    /**
     * Replaces the BoardPane in the specified node with provided BoardPane.
     * @param position - Specifies which node to replace the BoardPane in.
     * @param replacementPane - The BoardPane to replace with.
     */
    protected void replacePaneAtPosition(int position, BoardPane replacementPane){
        if(position <= nodeCount && position >= 1){
            while(currentNode.positionNum != position){
                getNextNode();
            }
            currentNode.pane = replacementPane;       
        } 
    }
    
    /**
     * @return The current position number of the node.
     */
    protected int getCurrentPositionNum(){
        return currentNode.positionNum;
    }
    
}

/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.touringmusician;


import android.graphics.Point;
import android.util.Log;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<Point> {

    private class Node {
        Point point;
        Node prev, next;
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
    }

    Node head;
    Node end;

    public void insertBeginning(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/

        Node node = new Node();
        node.point = p;

        if(head != null){


            node.next = head;
            node.prev = end;

            head.prev = node;
            end.next = node;

            head = node;


            //Log.i("end",end.point.x + " " + end.point.y);
        } else{
            node.next = node;
            node.prev = node;
            head = node;
            end = node;
            //Log.i("end Null",end.point.x + " " + end.point.y);

        }

    }

    private float distanceBetween(Point from, Point to) {
        return (float) Math.sqrt(Math.pow(from.y-to.y, 2) + Math.pow(from.x-to.x, 2));
    }

    public float totalDistance() {
        float total = 0;
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/

        Node iterator = head.next;
        while(iterator != head){
            total = total + distanceBetween(iterator.prev.point,iterator.point);
            iterator = iterator.next;
        }
        return total;
    }

    public void insertNearest(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Node node = new Node();


        if(head != null && head.next != head){

            Node iterator = new Node();
            iterator = head;
            int count = 0;
            int distance = -1;
            boolean endFlag = false;
            Log.i("before loop","before loop");



            while(iterator.next != head && !endFlag){
                Log.i("here",iterator.point.x + " " + iterator.point.y);
                if(distance == -1){
                    distance = Math.round(distanceBetween(iterator.point,iterator.next.point));


                } else if(distanceBetween(iterator.point,iterator.next.point) < distance){
                    distance = Math.round(distanceBetween(iterator.point,iterator.next.point));
                }
                count++;





                if(iterator == end){
                    endFlag = true;

                }
                Log.i("in first loop","in first loop");
            }

            int count2 =0;
            iterator = head;
            Log.i("out of first loop","out of first loop");

            while(iterator.next != head && !endFlag){

                if(count == count2){
                    Node insert = new Node();
                    insert.point = p;
                    insert.next = iterator.next;
                    insert.prev = iterator;
                    iterator.next.prev = insert;
                    iterator.next = insert;

                }
                count2++;





                if(iterator == end){
                    endFlag = true;

                }
                Log.i("in second loop","in second loop");
            }
            Log.i("out of second loop","out of second loop");



        } else if(head != null){
            Node insert = new Node();
            insert.point = p;
            insert.next = head;
            insert.prev = head;
            head.next = insert;
            head.prev = insert;






        }else{
            node.point = p;
            node.next = node;
            node.prev = node;
            head = node;
            end = node;
            Log.i("============","---------------------");
        }


    }

    public void insertSmallest(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Node newNode=new Node();
        newNode.point=p;
        if(head==null)
        {
            head=newNode;
            head.next=head;
            head.prev=head;
        }
        else if(head.next==head)
        {
            newNode.next=head;
            newNode.prev=head.prev;

            head.prev=newNode;
            newNode.prev.next=newNode;
            head=newNode;
        }
        else
        {
            Node stNode=head;
            Node finalNode=null;
            float totalFinalDist=0;
            float distFromPrev=0;
            float distFromNext=0;
            float totalDistIncrease=0;
            float min=99999;
            do
            {
                distFromPrev=distanceBetween(stNode.point,newNode.point);
                distFromNext=distanceBetween(newNode.point,stNode.next.point);
                totalDistIncrease=(distFromNext+distFromPrev)-distanceBetween(stNode.point,stNode.next.point);
                if(totalDistIncrease<min)
                {
                    finalNode=stNode;
                    totalFinalDist=totalDistIncrease;
                    min=totalDistIncrease;
                    Log.println(Log.INFO,"Message","Assigning finalNode insertSmallest()");
                }
                stNode=stNode.next;
                Log.println(Log.INFO,"Message","Check for minimum distance in insertSmallest()");
            }while (stNode!=head);

            newNode.prev=finalNode;
            newNode.next=finalNode.next;
            finalNode.next=newNode;
            finalNode.next.prev=newNode;

        }
    }

    public void reset() {
        head = null;
    }

    private class CircularLinkedListIterator implements Iterator<Point> {

        Node current;

        public CircularLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Point next() {
            Point toReturn = current.point;
            current = current.next;
            if (current == head) {
                current = null;
            }
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new CircularLinkedListIterator();
    }


}

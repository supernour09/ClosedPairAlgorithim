/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithimsassg1;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author darklife
 */
public class AlgorithimsAssg1 {

    /**
     * @param args the command line arguments
     */
    public static Vector<Integer> points = new Vector<Integer>(); 
    public static Vector<Vector<Integer>> s = new Vector<Vector<Integer>>();
    public static int ClosestPair(int s , int e){
        if(e==s)return Integer.MAX_VALUE;
        if(e-s == 1)return points.elementAt(e)- points.elementAt(s);
        int mid = s + (e-s)/2;
        int d0 = points.elementAt(mid+1)- points.elementAt(mid);
        int d1 = ClosestPair(s, mid);
        int d2 = ClosestPair(mid+1, e);
        int d01 = Math.min(d0, d1);
        return Math.min(d01 , d2);
    }
    
    public static double ClosestPairBruteForec(int d){
        double mi = Double.MAX_VALUE;
        for(int i=0;i<s.size();i++){
            for(int j=i+1;j<s.size();j++){
                Double diff = 0.0;
                for(int h=0;h<d;h++){
                    diff+= (s.get(i).get(h) -  s.get(j).get(h)       );
                }
                diff = Math.sqrt(diff);
                mi = Math.min(mi, diff);
            }
        }
        return mi;
    }
    
    

    
    public static void main(String[] args) {
        
        
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt(),x;
        for(int i=0;i<n;i++){
            x = sc.nextInt();
      
            points.add(x);
        }
        Collections.sort(points);
        
        System.out.println(ClosestPair(0, n-1));
        
        
        
        
    }
    
}

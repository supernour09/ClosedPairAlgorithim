/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closest;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author darklife
 */
public class Closest {
    

    public static Point2D sortx[];
    public Closest(Point2D p[]) {
    
    }
    
    public static double stripdo(Point2D stip[],int n , double dis){
        double mi = Double.POSITIVE_INFINITY;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n && (stip[j].getY()-stip[i].getY())<=dis;j++){
                mi = Math.min(mi, stip[i].distance(stip[j]));
            }
        }
        return mi;
    }
    
    public static double closed(Point2D sorty[] , int lo , int hi,int n){
         System.out.println(hi +" "+ lo + " " + (hi-lo) +" " + n );
         
         if(n<=1||lo==hi) return 100000000;
         if(n==2) return sorty[0].distance(sorty[1]);
         int mid = lo + (hi-lo)/2;
         Point2D middlex = sortx[mid];
         
         Point2D pyl[] = new Point2D[n+1];
         Point2D pyr[] = new Point2D[n+1];
         int l=0,r=0;
         
         for(int i=0;i<n;i++){
             if(sorty[i].getX() < middlex.getX()){
                 pyl[l++]=new Point2D(sorty[i].x, sorty[i].y);
            }
             else{
                 pyr[r++]=new Point2D(sorty[i].x, sorty[i].y);
             }
         }
         
         double lefty = closed(pyl,lo,mid,l);
         double righty = closed(pyr,mid+1,hi,r);
         double d = Math.min(lefty, righty);
         
         Point2D stip[] = new Point2D[n];
         int indx=0;
         for(int i=0;i<n;i++){
             if(Math.abs(sorty[i].getX() - middlex.getX() )< d){
                 
                 stip[indx++]=new Point2D(sorty[i].x, sorty[i].y);
             }
         }
         return Math.min(d, stripdo(stip,indx,d));
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        
        //Scanner textfile = new Scanner(new File("C:\\Downloads\\Dataset\\secretinput1.txt"));
        FileInputStream fs = new FileInputStream("C:\\Downloads\\Dataset\\secretinput2.txt");
        InputStreamReader rr = new InputStreamReader(fs);
        BufferedReader br = new BufferedReader(rr);
        
        
        String nn = br.readLine();
        int n = Integer.parseInt(nn);

        Point2D points[] = new Point2D[n];
        for(int i=0;i<n;i++){
            String line = br.readLine();
            String part[] = line.split(" ") ;
            
            points[i] = new Point2D(Integer.parseInt(part[0]),Integer.parseInt(part[1]));

        }
        sortx = new Point2D[n];
        sortx=points.clone();
         Arrays.sort(sortx, new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                if(o1.getX() == o2.getX()){
                    if(o1.getX() < o2.getX())return -1;
                    else return 1;
                }else {
                     if(o1.getY() < o2.getY())return -1;
                    else return 1;
                }
            }
        });
         Point2D sorty[] = new Point2D[n];
        sorty=points.clone();
         Arrays.sort(sorty, new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                return Double.compare((long)o1.getY(), (long)o2.getY());
            }
        });
         double ret = closed(sorty,0,n-1,n-1);
         System.out.println(ret);
    }

    private static class Point2D {
        public double x,y;
        public Point2D(double xx ,double yy) {
            x=xx;
            y=yy;
        }
        public double getY(){
            return y;
        }
        public double getX(){
            return x;
        }
        public double distance(Point2D t){
            return Math.sqrt((x-t.x)*(x-t.x) + (y-t.y)*(y-t.y));
        }
    }
    
}

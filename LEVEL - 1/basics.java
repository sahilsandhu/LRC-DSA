import java.util.*;
public class basic{

// Buy and Sell Stocks  ( 1 trans allowed ) 
    public static void BuyAndSell1(){
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        arr[i] = scn.nextInt();
        
        int ans = 0;
        int prevmin = Integer.MAX_VALUE;
        int tempans = 0;
        for(int i=0;i<n;i++){
            if(arr[i] < prevmin)
            prevmin = arr[i];
            
            tempans = arr[i] - prevmin;
            if(ans < tempans){
                ans = tempans;
            }
        }
        System.out.println(ans);
    }

    // Buy and Sell Stocks ( Multiple Trans allowed )
    // Buy and Sell Stocks with Transaction Fee
    

    public static Scanner scn = new Scanner(System.in);
    
     
    }
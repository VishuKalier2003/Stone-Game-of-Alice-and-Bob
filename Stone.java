/* Alice and Bob take turns playing a game, with Alice starting first... There are n stones in a pile... On each player's turn, they can remove a stone from the pile and receive points based on the stone's value. Alice and Bob may value the stones differently... You are given two integer arrays of length n, alice Values and bob Values... Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value the ith stone... The winner is the person with the most points after all the stones are chosen... If both players have the same amount of points, the game results in a draw. Both players will play optimally... Both players know the other's values... Determine the result of the game, and:
    * If Alice wins, return 1...
    * If Bob wins, return -1...
    * If the game results in a draw, return 0...
    * Eg 1: Alice = [2,4,3]   Bob = [1,6,7]     Output = -1   (Bob Wins)
    * Eg 2: Alice = [1,3]     Bob = [2,1]       Output = 1    (Alice Wins)
    * Eg 3: Alice = [1,2]     Bob = [3,1]       Output = 0    (Draw)                */
import java.util.*;
public class Stone
{
    public int ChanceOfVictory(int alice[], int bob[])
    {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>(alice.length, 1);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i = 0; i < alice.length; i++)
        {
            table.put(Math.abs(alice[i]-bob[i]), i);    // The absolute difference is stored as key and the index where it occurred as its value...
            queue.add(Math.abs(alice[i]-bob[i]));     // The Higher difference value is given more priority...
        }
        int sum1 = 0, sum2 = 0, k = 0;    // Initializing their sums...
        while(k != bob.length)
        {
            int x = queue.poll();     // Removing the maximum difference from the Priority Queue...
            int val = table.get(x);   // Getting the index from the Hashtable...
            table.remove(alice[val]-bob[val]);    // Removing it from the table...
            if(k % 2 == 0)
            {   // For Odd chances we have Alice 's turn...
                sum1 = sum1 + alice[val];
                System.out.println("Alice sum : "+sum1);
            }
            else
            {   // For even chances we have Bob 's turn...
                sum2 = sum2 + bob[val];
                System.out.println("Bob sum : "+sum2);
            }
            k++;
        }
        if(sum1 > sum2)    // If else for return conditions...
            return 1;
        else if(sum2 > sum1)
            return -1;
        return 0;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the number of plays : ");
        x = sc.nextInt();
        int alice[] = new int[x];
        int bob[] = new int[x];
        for(int i = 0; i < alice.length; i++)
        {
            System.out.print("Enter Alice 's value of "+(i+1)+" stone : ");
            alice[i] = sc.nextInt();
        }
        for(int i = 0; i < bob.length; i++)
        {
            System.out.print("Enter Bob 's value of "+(i+1)+" stone : ");
            bob[i] = sc.nextInt();
        }
        System.out.print("The Alice value array : ");
        for(int i = 0; i < alice.length; i++)
            System.out.print(alice[i]+", ");
        System.out.println();
        System.out.print("The Bob value array : ");
        for(int i = 0; i < bob.length; i++)
            System.out.print(bob[i]+", ");
        System.out.println();
        Stone stone = new Stone();      // Object creation...
        System.out.println("The Victory for Alice is : "+stone.ChanceOfVictory(alice, bob));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(n) space...         The Priority Queue and the Hashtable spaces are same...

/* DEDUCTIONS :- 
 * 1. Since we are playing Optimally we will try to remove that stone which increase our score maximum and decrease the opponent score maximum at the same time, the difference mus tbe greatest...
 * 2. To get the Maximum we use Priority Queue and for searching the index of the difference we use Hashtable...
 * 3. We evaluate sum of both players alternatively as the subproblem is solved...
*/
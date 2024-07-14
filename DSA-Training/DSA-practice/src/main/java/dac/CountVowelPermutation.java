package dac;

import java.util.Arrays;


public class CountVowelPermutation {
    int a = 0;
    int e = 1;
    int i = 2;
    int o = 3;
    int u = 4;

    int mod = (int) (1e9 + 7);


    public int countVowelPermutation(int n) {
        if (n <= 0 || n >= 20000) {
            return 0;
        }
        long[] dp = new long[5];
        long[] prevDP = new long[5];
        Arrays.fill(prevDP, 1L);
        while (n-- > 1) {
            dp[a] = (prevDP[e] + prevDP[i] + prevDP[u]) % mod;
            dp[e] = (prevDP[a] + prevDP[i]) % mod;
            dp[i] = (prevDP[e] + prevDP[o]) % mod;
            dp[o] = (prevDP[i]);
            dp[u] = (prevDP[i] + prevDP[o]) % mod;
            long[] tmp = dp;
            dp = prevDP;
            prevDP = tmp;
        }
        return (int) ((prevDP[a] + prevDP[e] + prevDP[i] + prevDP[o] + prevDP[u]) % mod);
    }

    public static int numRollsToTarget(int n, int k, int target) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = j; t <= target; t++) {
                    dp[i][t] = (dp[i][t] + dp[i - 1][t - j]) % MOD;
                }
            }
        }
        for (int [] arr:dp) {
            for (int i:arr ) {
                System.out.print(i+ " ");
            }
            System.out.println();
        }

        return dp[n][target];
    }

    public static void main(String[] args) {
        int dp =numRollsToTarget(1,6,3);
int [][]
[] arr=new int[][][]    {};
for(int [][] rr1:arr){
for(int [] rr2:rr1){
    for (int i:rr2){

    }
}
    }
}
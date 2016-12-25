import java.util.Arrays;

/**
 * Created by amura on 12/25/16.
 */
public class EvenOddMain {
    private static final int[][] INPUT    = {
                                            {2,4,7,6,1,3,5,4},
                                            {1,2,4,7,6,1,3,5,4},
                                            {-11,3,2,4,7,6,1,3,5,4}
                                            };
    private static final int[][] EXPECTED = {
                                            {2,4,6,4,7,1,3,5},
                                            {2,4,6,4,1,7,1,3,5},
                                            {2,4,6,4,-11,3,7,1,3,5}
                                            };

    public static void main(String[] args){
        for(int i=0; i<INPUT.length; i++) {
            int[] result = evenFirst(INPUT[i]);

            if (!Arrays.equals(result, EXPECTED[i]))
                throw new AssertionError("Even numbers need to precede odd numbers");

        }

        System.out.println("Program finished");
    }

    /**
     * Time Complexity O(n) - This method only loop the input array once, but shifting process
     * Space Complexity O(1) - This method uses only 4-5 temporary variables regardless the size of the input array.
     */
    public static final int[] evenFirst(int[] input){
        int[] result = input.clone();
        int len = result.length;
        int evenCursor = 0;
        boolean isOddMode = false;
        for(int i=0; i<len; i++){
            int num = result[i];
            if(isEven(num)){
                if(isOddMode){
                    // Shift all the remainder number to the right by 1 index.
                    System.arraycopy(result, evenCursor, result, evenCursor+1, i-evenCursor);
                    // Move current number to the last even number position
                    result[evenCursor] = num;
                    isOddMode = !isEven(result[i]);
                }
                evenCursor++;
            }else{
                isOddMode = true;
            }
            System.out.printf("DEBUG: %s - i=%d, ev_cur=%d\n", Arrays.toString(result), i, evenCursor);
        }

        System.out.printf("INPUT  : %s\n", Arrays.toString(input));
        System.out.printf("RESULT : %s\n\n", Arrays.toString(result));

        return result;
    }

    /**
     * Check whether an integer is even number,
     * using bitwise operation instead of modulus for faster performance
     */
    public static final boolean isEven(int num) {
        return (num & 1) == 0;
    }
}

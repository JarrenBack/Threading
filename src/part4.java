import java.util.Random;

public class part4 extends Thread{
	static final int numThreads = 8;
	static int[][] matrix = new int[numThreads][10000000];
	static Thread[] arrThreads = new Thread[numThreads];
	static ThreadSum[] arrTS = new ThreadSum[numThreads];
	
	public static void main(String[] args) throws InterruptedException {
		initMatrix();
		
		for (int i = 0; i < numThreads; ++i) {
			ThreadSum ts = new ThreadSum(matrix, i);
			arrTS[i] = ts;
			Thread t = new Thread(ts);
			arrThreads[i] = t;
		}
		
		long startTime = System.nanoTime();
		
		for (int i = 0; i < numThreads; ++i) {
			arrThreads[i].start();
		}
		
		for (int i = 0; i < numThreads; ++i) {
			arrThreads[i].join();
		}
		
		long endTime = System.nanoTime();
	
		int sum = 0;
		for (int i = 0; i < numThreads; ++i) {
			sum += arrTS[i].getSum();
		}
		
		System.out.println(sum);
		System.out.println("Number of threads: " + numThreads);
		System.out.println("Computation took " + ((endTime - startTime) / 1000000) + " milliseconds");

	}
	
	
	static void initMatrix() {
		Random rand = new Random();
		// Initialize matrix with random numbers
		for (int x = 0; x < matrix.length; x++) {
		    for (int y = 0; y < matrix[x].length; y++) {
		        int randomNum = rand.nextInt(200); // 0 - 199
		        matrix[x][y] = randomNum;
		    }
		}
	}
}

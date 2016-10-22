public class ThreadSum implements Runnable {
	private int sum;
	private int[][] array;
	private int threadNum;
	
	public ThreadSum(int[][] array, int threadNum) {
		this.array = array;
		this.threadNum = threadNum;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < array[0].length; ++i) {
			sum += (int) Math.log(array[threadNum][i]);
		}
	}
	
	public int getSum() {
		return sum;
	}
}
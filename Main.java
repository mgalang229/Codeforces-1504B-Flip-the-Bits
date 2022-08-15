import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*

Test case 1:
0111010000 (start)
0100101100 (end)

[0, 1, 1, 1, 0, 1, 0, 0, 0, 0]
[0, 1, 0, 0, 1, 0, 1, 1, 0, 0]

[0, 1, 2, 3, 3, 4, 4, 4, 4, 4]
[1, 1, 1, 1, 2, 2, 3, 4, 5, 6]

take into account the no. of times bits were flipped
just even/odd count is enough 

0111010000
1000101100
0100101100 = possible

Test case 3:
001 (start)
000 (end)

[0, 0, 1]
[1, 2, 2]

[0, 0, 1]
[0, 0, 0]

001 = impossible

Test case 4:
010101010101 (start)
100110011010 (end)

010101010101
101010101010
010101011010
101010011010
010110011010
010110011010
100110011010 = possible

[0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1]
[1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0]

[0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6]
[1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6]

Test case 5:
000111 (start)
110100 (end)

[0, 0, 0, 1, 1, 1]
[1, 1, 0, 1, 0, 0]

[0, 0, 0, 1, 2, 3]
[1, 2, 3, 3, 3, 3]

000111
1110 00 = impossible

 */

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			char[] a = fs.next().toCharArray();
			char[] b = fs.next().toCharArray();
			int[] ones = new int[n];
			int[] zeroes = new int[n];
			Arrays.fill(ones, 0);
			Arrays.fill(zeroes, 0);
			if (a[0] == '1') {
				ones[0]++;
			} else {
				zeroes[0]++;
			}
			for (int i = 1; i < n; i++) {
				if (a[i] == '1') {
					ones[i] = ones[i-1] + 1;
					zeroes[i] = zeroes[i-1];
				} else {
					zeroes[i] = zeroes[i-1] + 1;
					ones[i] = ones[i-1];
				}
			}
			int flip = 0;
			boolean checker = true;
			boolean first = true;
			for (int i = n - 1; i >= 0; i--) {
				if (a[i] != b[i] && first) {
					if (ones[i] != zeroes[i]) {
						checker = false;
						break;
					}
					first = false;
					flip++;
				} else {
					if (flip % 2 == 1) {
						a[i] = (a[i] == '1' ? '0' : '1');
					}
					if (a[i] != b[i]) {
						if (ones[i] != zeroes[i]) {
							checker = false;
							break;
						}
						flip++;
					}
				}
			}
			out.println(checker ? "YES" : "NO");
		}
		out.close();
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}

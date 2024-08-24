public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int cnt = 0;
        while (x < 10) {
            cnt += x;
            x = x + 1;
        }
        System.out.println(cnt);
    }
}
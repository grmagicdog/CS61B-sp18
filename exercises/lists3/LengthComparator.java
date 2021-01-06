public class LengthComparator implements NullSafeStringComparator {

    public int compare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s1 == null) {
            return -1;
        } else if (s2 == null) {
            return 1;
        } else {
            return s1.length() - s2.length();
        }
    }

    public static String max(String[] a, NullSafeStringComparator sc) {
        String maxStr = a[0];
        for (int i = 1; i < a.length; i += 1) {
            if (sc.compare(maxStr, a[i]) < 0) {
                maxStr = a[i];
            }
        }
        return maxStr;
    }

    public static String[][] step(String[][] arr) {
        String[][] stepped = new String[arr.length][arr[0].length];
        for (int i = 1; i < arr.length - 1; i += 1) {
            for (int j = 1; j < arr[0].length - 1; j += 1) {
                String[] temp = new String[9];
                LengthComparator sc = new LengthComparator();
                for (int k = -1; k <= 1; k += 1) {
                    for (int m = -1; m <= 1; m += 1) {
                        temp[(k + 1) * 3 + m + 1] = arr[i + k][j + m];

                    }
                }
                stepped[i][j] = max(temp, sc);
            }
        }
        return stepped;
    }

    public static void main(String[] args) {
        String[][] arr = {
                new String[6],
                {null, "a", "cat", "cat", "dogs", null},
                {null, "a", null, "cat", "a", null},
                {null, "a", "ca", "", "ca", null},
                new String[6]
        };
        String[][] stepped = step(arr);
    }
}

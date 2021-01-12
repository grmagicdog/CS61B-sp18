class IntTree {
    public final int data;
    public IntTree left, right;

    public IntTree (int data, IntTree left, IntTree right) {
        this.data = data; this.left = left; this.right = right;
    }

    public static IntTree mergeRight (IntTree T, IntTree L) {
        T.left.mergeRight(L.left);
        return T;
    }

    private void mergeRight (IntTree L) {
        if (left == null) {
            left = goLeft(L);
        } else {
            left.mergeRight(goLeft(L));
        }
    }

    private IntTree goLeft(IntTree L) {
        if (L == null) {
            return null;
        }
        if (L.data > data) {
            if (right == null) {
                right = L;
            } else {
                right.mergeRight(L);
            }
            return null;
        } else {
            L.right = goLeft(L.right);
            return L;
        }
    }
}

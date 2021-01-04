public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/** Returns the ith item of this IntList. */
	public int get(int i) {
		if (i == 0) {
			return first;
		}
		return rest.get(i - 1);
	}

	/** If 2 numbers in a row are the same, adds them together and
	 *	make one large node. For example:
	 *	1 → 1 → 2 → 3 becomes 2 → 2 → 3 which becomes 4 → 3
	 */
	public void addAdjacent() {
		if (rest == null) {
			return;
		}
		if (first == rest.first) {
			first *= rest.first;
			rest = rest.rest;
			this.addAdjacent();
		} else {
			rest.addAdjacent();
		}
	}

	public void addSquare() {
		if (rest == null) {
			rest = new IntList(first * first, null);
		} else {
			rest.addSquare();
			rest = new IntList(first * first, rest);
		}
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);
		L.addAdjacent();
		L.addSquare();
		System.out.println(L.get(100));
	}
} 
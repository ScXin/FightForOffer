/**
 * @author ScXin
 * @date 6/3/2019 3:38 PM
 */
public class WeightedQuickUnionUF extends UF {

    private static int[] sz;

    public WeightedQuickUnionUF(int N) {
        super(N);
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }
    @Override
    public int find(int k) {
        if (k != id[k]) {
            return id[k];
        }
        return k;
    }
    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}

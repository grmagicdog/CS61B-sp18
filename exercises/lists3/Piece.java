public class Piece {
    int longitude;
    int latitude;

    public Piece(int x, int y) {
        longitude = x;
        latitude = y;
    }

    public Piece[][] groupByLat (Piece[] p) {
        int width = (int) Math.sqrt(p.length);
        Piece[][] latGroup = new Piece[width][p.length];
        for (int i = 0; i < p.length; i++){
            for (int j = 0; j < width; j++){
                if (latGroup[j][0] == null){
                    latGroup[j][0] = p[i];
                    break;
                }
                else if (latGroup[j][0].latitude == p[i].latitude){
                    int counter;
                    for (counter = 0; counter < p.length - 1; counter++){
                        if (latGroup[j][counter] == null){
                            break ;
                        }
                    }
                    latGroup[j][counter] = p[i];
                    break;
                }
            }
        }
        return latGroup ;
    }


    public static void main(String[] args) {

    }
}
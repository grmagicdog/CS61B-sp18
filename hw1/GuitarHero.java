import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(CONCERT_A * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0) {
                    strings[index].pluck();
                }
            }

            /* compute the superposition of samples and
             * advance the simulation of each guitar string by one step */
            double sample = 0;
            for (GuitarString s : strings) {
                sample += s.sample();
                s.tic();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);
        }
    }
}

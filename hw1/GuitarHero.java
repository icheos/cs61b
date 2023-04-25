import synthesizer.GuitarString;

public class GuitarHero {
    private static double[] notes = new double[37];
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        for (int i = 0; i < keyboard.length(); i++) {
            notes[i] = 440 * Math.pow(2, ((double) i - 24) / 12);
        }

        synthesizer.GuitarString[] gs = new GuitarString[37];
        for (int i = 0; i < keyboard.length(); i++) {
            gs[i] = new GuitarString(notes[i]);
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) {
                    gs[keyboard.indexOf(key)].pluck();
                }
            }


            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample += gs[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < keyboard.length(); i++) {
                gs[i].tic();
            }
        }

    }
}

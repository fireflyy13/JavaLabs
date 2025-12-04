/**
 * Клас, що описує джазову музичну композицію.
 */
public class JazzSong extends MusicComposition {

    private boolean hasImprovisation;

    /**
     * @param title назва композиції
     * @param artist виконавець
     * @param durationSeconds тривалість у секундах
     * @param hasImprovisation чи містить композиція імпровізацію
     */
    public JazzSong(String title, String artist, int durationSeconds, boolean hasImprovisation) {
        super(title, artist, durationSeconds, MusicGenre.JAZZ);
        this.hasImprovisation = hasImprovisation;
    }

    @Override
    public void play() {
        System.out.println("Відтворення джазової композиції: " + title +
                (hasImprovisation ? " з імпровізацією." : "."));
    }
}

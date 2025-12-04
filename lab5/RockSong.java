/**
 * Клас, що описує рок-композицію.
 */
public class RockSong extends MusicComposition {

    private boolean hasGuitarSolo;

    /**
     * @param title назва композиції
     * @param artist виконавець
     * @param durationSeconds тривалість у секундах
     * @param hasGuitarSolo чи містить композиція гітарне соло
     */
    public RockSong(String title, String artist, int durationSeconds, boolean hasGuitarSolo) {
        super(title, artist, durationSeconds, MusicGenre.ROCK);
        this.hasGuitarSolo = hasGuitarSolo;
    }

    @Override
    public void play() {
        System.out.println("Відтворення рок-композиції: " + title +
                (hasGuitarSolo ? " (з гітарним соло!)" : ""));
    }
}

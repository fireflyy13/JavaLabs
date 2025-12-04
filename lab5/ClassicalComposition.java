/**
 * Клас, що описує класичну музичну композицію.
 */
public class ClassicalComposition extends MusicComposition {

    private String era;

    /**
     * @param title назва твору
     * @param artist композитор
     * @param durationSeconds тривалість у секундах
     * @param era музична епоха (бароко, романтизм тощо)
     */
    public ClassicalComposition(String title, String artist, int durationSeconds, String era) {
        super(title, artist, durationSeconds, MusicGenre.CLASSICAL);
        this.era = era;
    }

    @Override
    public void play() {
        System.out.println("Виконання класичного твору: " + title +
                " (" + era + " епоха)");
    }
}

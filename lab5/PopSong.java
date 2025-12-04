/**
 * Клас, що описує поп-композицію.
 */
public class PopSong extends MusicComposition {

    private boolean isSingle;

    /**
     * @param title назва композиції
     * @param artist виконавець
     * @param durationSeconds тривалість у секундах
     * @param isSingle чи є композиція синглом
     */
    public PopSong(String title, String artist, int durationSeconds, boolean isSingle) {
        super(title, artist, durationSeconds, MusicGenre.POP);
        this.isSingle = isSingle;
    }

    @Override
    public void play() {
        System.out.println("Відтворення поп-хіта: " + title +
                (isSingle ? " (сингл)" : ""));
    }
}

/**
 * Абстрактний клас, що описує загальні властивості музичної композиції.
 * Є базовим класом для всіх конкретних типів музики.
 */
public abstract class MusicComposition {

    protected String title;
    protected String artist;
    protected int durationSeconds;
    protected MusicGenre style;

    /**
     * Конструктор створює музичну композицію.
     *
     * @param title назва композиції
     * @param artist виконавець
     * @param durationSeconds тривалість у секундах (має бути > 0)
     * @param style музичний стиль
     * @throws InvalidDurationException якщо тривалість <= 0
     */
    public MusicComposition(String title, String artist, int durationSeconds, MusicGenre style) {
        if (durationSeconds <= 0) {
            throw new InvalidDurationException("Тривалість композиції має бути додатною.");
        }

        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.style = style;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public MusicGenre getStyle() {
        return style;
    }

    /**
     * Поліморфний метод відтворення композиції.
     * Кожен підклас реалізує його по-своєму.
     */
    public abstract void play();

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicComposition that = (MusicComposition) o;

        return durationSeconds == that.durationSeconds &&
                title.equals(that.title) &&
                artist.equals(that.artist) &&
                style == that.style;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + durationSeconds;
        result = 31 * result + style.hashCode();
        return result;
    }

    @Override
    public String toString() {
        int min = durationSeconds / 60;
        int sec = durationSeconds % 60;
        return String.format("%s - %s [%s, %d:%02d]", artist, title, style, min, sec);
    }
}

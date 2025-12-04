import java.util.Arrays;

/**
 * Клас, що представляє музичний альбом,
 * який складається з набору композицій.
 */
public class Album {

    private String name;
    private MusicComposition[] tracks;

    /**
     * @param name назва альбому
     * @param tracks масив композицій
     */
    public Album(String name, MusicComposition[] tracks) {
        this.name = name;
        this.tracks = tracks;
    }

    /**
     * Обчислює загальну тривалість альбому.
     *
     * @return сумарна тривалість у секундах
     */
    public int getTotalDuration() {
        int sum = 0;
        for (MusicComposition track : tracks) {
            sum += track.getDurationSeconds();
        }
        return sum;
    }

    /**
     * Сортує композиції альбому за музичним стилем.
     */
    public void sortByStyle() {
        Arrays.sort(tracks,
                (a, b) -> a.getStyle().name().compareTo(b.getStyle().name()));
    }

    /**
     * Пошук композицій за діапазоном тривалості.
     *
     * @param min мінімальна тривалість
     * @param max максимальна тривалість
     * @return масив знайдених композицій
     * @throws TrackNotFoundException якщо композицій не знайдено
     */
    public MusicComposition[] findTracksByLengthRange(int min, int max)
            throws TrackNotFoundException {

        MusicComposition[] found = Arrays.stream(tracks)
                .filter(t -> t.getDurationSeconds() >= min &&
                        t.getDurationSeconds() <= max)
                .toArray(MusicComposition[]::new);

        if (found.length == 0) {
            throw new TrackNotFoundException("Не знайдено композицій у цьому діапазоні.");
        }

        return found;
    }

    /**
     * Виводить список усіх композицій у альбомі.
     */
    public void printTracks() {
        System.out.println("Альбом: " + name);
        for (MusicComposition track : tracks) {
            System.out.println(track);
        }
    }
}

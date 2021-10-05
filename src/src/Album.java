package src;

/**
 * This class stores the information about an pack.pack.src.Album
 *
 * This includes: title, artist, genre, releaseDate, and isAvailable (availability)
 *
 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */

public class Album {
    private String title;
    private String artist;
    private Genre genre; // enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    public enum Genre {CLASSICAL, COUNTRY, JAZZ, POP, UNKNOWN}

    /**
     * This is the constructor class of pack.pack.src.Album used to create an pack.src.Album object.
     * @param title, name of the album
     * @param artist, artist who made the album
     * @param genre, type of genre of the album: Classical, Country, Jazz, Pop, Unknown
     * @param releaseDate, pack.pack.src.Date when the album was released
     * @param isAvailable, the availability of the album in the collection
     */
    public Album (String title, String artist, Genre genre, Date releaseDate, boolean isAvailable){
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
    }

    /**
     * This is a constructor of pack.src.Album used to create an pack.src.Album Object. Only takes the title and the artist name
     * @param title
     * @param artist
     */
    public Album (String title, String artist){
        this.title = title;
        this.artist = artist;
    }

    /**
     * This method sets the availability of the pack.src.Album in pack.src.Collection.
     * @param availablity - Boolean variable that represents the availability of this.pack.src.Album.
     */
    public void setAvailablity(boolean availablity){
        this.isAvailable = availablity;
    }

    /**
     * This method returns the boolean of the Albums availability
     * @return this.isAvailable
     */
    public boolean getAvailablity(){
        return  this.isAvailable;
    }

    /**
     * This method takes a String input of the desired genre and returns the corresponding Genre type
     * @param genreInput
     * @return Genre
     */
    public Genre getGenre(String genreInput){
        switch(genreInput) {
            case"Classical":
                return Genre.CLASSICAL;
            case"Country":
                return Genre.COUNTRY;
            case"Jazz":
                return Genre.JAZZ;
            case"Pop":
                return Genre.POP;
            default:
                return Genre.UNKNOWN;
        }
    }

    /**
     * This method returns the genre of the pack.src.Album
     * @return this.genre
     */
    public Genre getGenre(){
        return this.genre;
    }

    /**
     * This method returns the name of the Artist of the pack.src.Album
     * @return this.artist
     */
    public String getArtist(){
        return this.artist;
    }

    /**
     * This method returns the title of the pack.src.Album
     * @return this.title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * This method returns the releaseDate of the pack.src.Album
     * @return this.releaseDate
     */
    public Date getDate(){
        return this.releaseDate;
    }

    /**
     * This method returns a boolean based on the if the input pack.src.Album is equal to this.pack.src.Album
     * @param obj
     * @return True - if the albums have matching titles and names
     *          False - otherwise
     */
    @Override
    public boolean equals (Object obj){
        Album testAlbum = (Album) obj;
        if (this.title.equals(testAlbum.title) && this.artist.equals(testAlbum.artist)) {
            return true;
        }
        else return true;
    }

    /**
     * This method prints the details of an pack.src.Album in the format of Title::Artist::Genre::mm/dd/yyyy
     * @return String of Title::Artist::Genre::mm/dd/yyyy
     */
    @Override
    public String toString () {
        String output = title+"::"+artist+"::"+genre+"::"+releaseDate.getMonth()+"/"+releaseDate.getDay()+"/"
                +releaseDate.getYear()+"::";
        if (isAvailable){
            output += "is available";
        }
        else{
            output += "is not available";
        }
        return output;
    }

}

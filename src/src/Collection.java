package src;

import src.Album.Genre;

/**
 * This class stores the information about  pack.Collectioin
 *
 * This includes: An array that holds of the albums along with methods to manipulate the collection
 *
 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */

public class Collection {

    private Album[] albums;
    private int numAlbums = 0;
    private static final int NOT_FOUND = -1;

    /**
     * Finds the index of an album in the album array
     * @param album
     * @return integer where album was found or -1 if it was not
     */
    private int find(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (albums[i] != null && albums[i].getTitle().equals(album.getTitle()) && albums[i].getArtist().equals(album.getArtist()) ) {
                return i;
            }
        }
        return (NOT_FOUND);
    }

    /**
     * Makes the array album grow by 4 indexes
     */
    private void grow() {
        Album[] updatedAlbum = new Album[numAlbums + 4];
        for (int i = 0; i < numAlbums; i++) {
            updatedAlbum[i] = albums[i];
        }
        this.albums = updatedAlbum;
    }

    /**
     * The method adds a album into the album array
     * @param album
     * @return boolean for if the album was added
     */
    public boolean add(Album album) {
        if(albums != null){
            if(this.find(album) == -1){
                if(numAlbums < albums.length){
                    albums[numAlbums] = album;
                    numAlbums++;
                    return true;
                }else{
                    this.grow();
                    albums[numAlbums] = album;
                    numAlbums++;
                    return true;
                }
            }else{
                return false;
            }
        }else{
            this.grow();
            albums[numAlbums] = album;
            numAlbums++;
            return true;
        }
    }

    /**
     * The class removes an album from the album array
     * @param album
     * @return boolean if the album was removed
     */
    public boolean remove(Album album) {
        int indexFound = this.find(album);
        if (indexFound == -1){
            return false;
        }
        Album[] updatedAlbum = this.albums.clone();
        updatedAlbum[indexFound] = null;
        numAlbums--;
        if(indexFound != numAlbums) {
            for (int i = indexFound; i < numAlbums; i++) {
                updatedAlbum[i] = updatedAlbum[i + 1];
                updatedAlbum[i+1] = null;
            }
        }
        this.albums = updatedAlbum;
        return true;
    }

    /**
     * This class is used to change the lending status of a album in the album arrray
     * @param album
     * @return boolean if album could be lent or not
     */
    public boolean lendingOut(Album album) {
        int indexFound = this.find(album);
        if(indexFound != -1){
            if (albums[indexFound].getAvailablity()){
                albums[indexFound].setAvailablity(false);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * This class is used to change the return status of a album in the album arrray
     * @param album
     * @return boolean for if the album could be returned
     */
    public boolean returnAlbum(Album album) {

        int indexFound = this.find(album);
        if(indexFound != -1){
            if (!albums[indexFound].getAvailablity()){
                albums[indexFound].setAvailablity(true);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * prints albums in album array
     */
    public void print() {
        if(numAlbums != 0){
            System.out.println("*list of albums in pack.src.Collection.");
            for(Album element: albums){
                if(element!=null)
                    System.out.println(element.toString());
            }
            System.out.println("*end of List.");
        }else{
            System.out.println("The collection is empty!");
        }
    }

    /**
     *
     * @param alb
     * Used to print genre and date sorted album arrays
     */
    private void print(Album[] alb) {
        if(numAlbums != 0){
            for(Album element: alb){
                if(element!=null)
                    System.out.println(element.toString());
            }
            System.out.println("*end of List.");
        }else{
            System.out.println("The collection is empty!");
        }
    }

    /**
     * Creates a list of albums in date acension order to print
     */
    public void printByReleaseDate() {
        if(numAlbums != 0){
            Album[] orderByDate = albums.clone();
            for(int i = 0 ; i < numAlbums; i++ ){
                int smallIndex = i;
                for (int j = i; j < numAlbums; j++){
                    Date smallDate = orderByDate[smallIndex].getDate();
                    Date currIndex = orderByDate[j].getDate();
                    if(smallDate.getYear() > currIndex.getYear()){
                        smallIndex = j;
                    }else{
                        if(smallDate.getYear() == currIndex.getYear() && smallDate.getMonth() > currIndex.getMonth()){
                            smallIndex = j;
                        }else{
                            if(smallDate.getMonth() == currIndex.getMonth() && smallDate.getDay() > currIndex.getDay() && smallDate.getYear() == currIndex.getYear()){
                                smallIndex = j;
                            }
                        }
                    }
                }
                Album atI = orderByDate[i];
                orderByDate[i] = orderByDate[smallIndex];
                orderByDate[smallIndex] = atI;
            }
            System.out.println("*pack.src.Album pack.src.Collection by Release pack.src.Date");
            this.print(orderByDate);
        }else{
            System.out.println("The collection is empty!");
        }
    }

    /**
     * Creates a list of albums in genre order to be printed
     */
    public void printByGenre() {
        if(numAlbums != 0){
            int indexcounter = 0;
            Album[] genreAlbum = new Album[numAlbums];

            Album.Genre[] x = new Album.Genre[5];
            x[0] = Album.Genre.CLASSICAL;
            x[1] = Album.Genre.COUNTRY;
            x[2] = Album.Genre.JAZZ;
            x[3] = Album.Genre.POP;
            x[4] = Album.Genre.UNKNOWN;

            for ( Album.Genre elements: x){
                for (int j = 0; j < albums.length; j++) {
                    if (albums[j] != null && albums[j].getGenre().equals(elements) ) {
                        genreAlbum[indexcounter] = albums[j];
                        indexcounter = indexcounter + 1;
                    }
                }
            }
            System.out.println("*pack.src.Album pack.src.Collection by Genre");
            this.print(genreAlbum);
        }else{
            System.out.println("The collection is empty!");
        }
    }
}

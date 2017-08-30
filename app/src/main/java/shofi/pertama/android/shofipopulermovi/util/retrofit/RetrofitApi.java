package shofi.pertama.android.shofipopulermovi.util.retrofit;

import shofi.pertama.android.shofipopulermovi.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetrofitApi {
    String BASE_URL = "https://api.themoviedb.org/3/";
    String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w185";
    String API_KEY = "156861bcd73d13e0129a612cca99746d";
    String LANG_SOURCE = "en-US";
    String MOVIES_REGION = "US";

    @GET("movie/{type}")
    Call<MovieResponse> getMovies(@Path("type") String type, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page, @Query("region") String region);
}

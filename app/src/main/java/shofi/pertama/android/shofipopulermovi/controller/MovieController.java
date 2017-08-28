package shofi.pertama.android.shofipopulermovi.controller;

import org.greenrobot.eventbus.EventBus;

import shofi.pertama.android.shofipopulermovi.App;
import shofi.pertama.android.shofipopulermovi.event.MovieErrorEvent;
import shofi.pertama.android.shofipopulermovi.event.MovieEvent;
import shofi.pertama.android.shofipopulermovi.model.MovieResponse;
import shofi.pertama.android.shofipopulermovi.util.Constant;
import shofi.pertama.android.shofipopulermovi.util.retrofit.RetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieController {
    private EventBus eventBus = App.getInstance().getEventBus();

    private void getMovies(int type, int page) {
        Call<MovieResponse> movieResponseCall = App.getInstance().getApiService().getMovies(Constant.Data.MOVIE_LIST_TYPE[type], RetrofitApi.API_KEY, RetrofitApi.LANG_SOURCE, page, RetrofitApi.MOVIES_REGION);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.code() == 200) {
                    eventBus.post(new MovieEvent(response.message(), response.body()));
                } else {
                    eventBus.post(new MovieErrorEvent(response.message()));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                eventBus.post(new MovieErrorEvent(t.getMessage()));
            }
        });
    }

    public void getPopularMovies(int page) {
        getMovies(0, page);
    }

    public void getTopRatedMovies(int page) {
        getMovies(1, page);
    }
}
package shofi.pertama.android.shofipopulermovi.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shofi.pertama.android.shofipopulermovi.App;
import shofi.pertama.android.shofipopulermovi.R;
import shofi.pertama.android.shofipopulermovi.model.Movie;
import shofi.pertama.android.shofipopulermovi.util.Constant;
import shofi.pertama.android.shofipopulermovi.util.retrofit.RetrofitApi;
import shofi.pertama.android.shofipopulermovi.view.activity.DetailActivity;

/**
 * Created by alodokter-it on 16/06/17 -- MoviesAdapter.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private ArrayList<Movie> movies;

    public MoviesAdapter() {
        movies = new ArrayList<>();
    }

    public void setData(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_movies, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, int position) {
        Constant.Function.setImageResource(holder.itemView.getContext(), RetrofitApi.BASE_URL_IMAGE + movies.get(position).getPosterPath(), holder.movie_poster);
    }

    private void detailMovie(Context context, int position) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(Constant.Data.MOVIE_INTENT, App.getInstance().getGson().toJson(movies.get(position)));
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_movies_poster)
        ImageView movie_poster;

        public MoviesViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            movie_poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailMovie(itemView.getContext(), getAdapterPosition());
                }
            });
        }
    }
}
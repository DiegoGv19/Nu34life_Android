package Interface;

import Model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRecipe {
    @GET("recipe/{id}")
    Call<Recipe> getRecipeId(@Path("id") Long id );
}

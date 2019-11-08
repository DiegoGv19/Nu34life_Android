package Interface;

import java.util.List;

import Model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRecipe {
    @GET("recipes")
    Call<List<Recipe>> getRecipe();


    @GET("recipes/{id}")
    Call<Recipe> getRecipeId(@Path("id") Long id );

}

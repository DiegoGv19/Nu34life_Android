package Interface;

import java.util.List;

import Model.Ingredient;
import Model.NutrFact;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IIngredient {
    @GET("recipes/{id}/ingredients")
    Call<List<Ingredient>> getIgredients(@Path("id") Long id);
}

package Interface;

import Model.NutrFact;
import Model.Nutritionist;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface INutrFact {

    @GET("recipes/{id}/nutri")
    Call<NutrFact> getNutrFact(@Path("id") Long id);

}

package Interface;

import java.util.List;

import Model.Steps;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPasos {

    @GET("recipes/{id}/steps")
    Call<List<Steps>> getSteps(@Path("id") Long id);
}

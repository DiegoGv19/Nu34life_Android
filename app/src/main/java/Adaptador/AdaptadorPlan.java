package Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.nu34life.R;

import java.util.List;

import Interface.IRecipe;
import Model.ApiClient;
import Model.Plan;
import Model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaptadorPlan extends BaseAdapter {

    private Context context;
    private List<Plan> listPlan;

    public AdaptadorPlan(Context context, List<Plan> listPlan) {
        this.context = context;
        this.listPlan = listPlan;
    }

    @Override
    public int getCount() {
        return listPlan.size();
    }

    @Override
    public Object getItem(int position) {
        return listPlan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPlan.get(position).getRecipeId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.itemdias,null);
        final TextView datos = (TextView) vista.findViewById(R.id.tvdiaMostrar);

        IRecipe iRecipe;
        iRecipe = ApiClient.getRetrofit().create(IRecipe.class);


        Call<Recipe> call = iRecipe.getRecipeId(listPlan.get(position).getRecipeId());
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if(!response.isSuccessful()){

                    return;
                }
                Recipe recipe = response.body();
                datos.setText(recipe.getName().toString());

            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {

            }
        });
        return vista;
    }
}

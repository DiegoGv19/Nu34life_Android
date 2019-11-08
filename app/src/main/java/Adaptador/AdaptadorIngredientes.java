package Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.nu34life.R;

import java.util.List;

import Model.Ingredient;

public class AdaptadorIngredientes  extends BaseAdapter {
    private Context context;
    private List<Ingredient> listIngredients;

    public AdaptadorIngredientes(Context context, List<Ingredient> listIngredients) {
        this.context = context;
        this.listIngredients = listIngredients;
    }

    @Override
    public int getCount() {
        return listIngredients.size();    }

    @Override
    public Object getItem(int position) {
        return listIngredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.itemdias,null);
        TextView datos = (TextView) vista.findViewById(R.id.tvdiaMostrar);
        datos.setText("    "+listIngredients.get(position).getFood()+"               "+listIngredients.get(position).getQuantity()+" "+listIngredients.get(position).getUnit());
        return vista;
    }
}

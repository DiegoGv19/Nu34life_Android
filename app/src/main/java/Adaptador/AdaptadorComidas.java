package Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.nu34life.R;

import java.util.List;

import Model.Recipe;

public class AdaptadorComidas extends BaseAdapter {
    private Context context;
    private List<Recipe> listRecipe;

    public AdaptadorComidas(Context context, List<Recipe> listRecipe) {
        this.context = context;
        this.listRecipe = listRecipe;
    }

    @Override
    public int getCount() {
        return listRecipe.size();
    }

    @Override
    public Object getItem(int position) {
        return listRecipe.get(position);    }

    @Override
    public long getItemId(int position) {
        return listRecipe.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.itemdias,null);
        TextView datos = (TextView) vista.findViewById(R.id.tvdiaMostrar);
        datos.setText(listRecipe.get(position).getName());
        return vista;
    }
}

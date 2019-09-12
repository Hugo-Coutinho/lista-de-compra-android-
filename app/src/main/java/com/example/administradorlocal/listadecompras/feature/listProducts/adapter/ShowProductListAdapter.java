package com.example.administradorlocal.listadecompras.feature.listProducts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.persistence.entity.ShoppingList;
import com.example.administradorlocal.listadecompras.util.imageHelper.ImageManipulate;
import com.example.administradorlocal.listadecompras.util.imageHelper.ImageManipulateImpl;

import java.util.ArrayList;
import java.util.List;

public class ShowProductListAdapter extends BaseAdapter {

    private List<ShoppingList> products;
    private Context ctx;

    private List<ShoppingList> productsChecked;
    private TextView tv_show;
    ShoppingList product;

    public ShowProductListAdapter(List<ShoppingList> products, Context ctx) {
        this.products = products;
        this.ctx = ctx;
        this.productsChecked = new ArrayList<>();
        this.product = new ShoppingList();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        product = products.get(position);
        ImageManipulate imageManipulate = new ImageManipulateImpl();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.show_list_view, null);
        }
        ImageView iv_showImage = convertView.findViewById(R.id.iv_showImage);
        tv_show = convertView.findViewById(R.id.tv_show);

        iv_showImage.setImageURI(imageManipulate.getImagebyUri(ctx, product.getImage()));
        tv_show.setText(product.getName());

        return convertView;

    }
}

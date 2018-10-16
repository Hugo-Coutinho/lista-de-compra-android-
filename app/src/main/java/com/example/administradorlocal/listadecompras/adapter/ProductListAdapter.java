package com.example.administradorlocal.listadecompras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.entity.Product;
import com.example.administradorlocal.listadecompras.util.ImageManipulate;
import com.example.administradorlocal.listadecompras.util.ImageManipulateImpl;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private List<Product> products;
    private Context ctx;

    public ProductListAdapter(List<Product> products, Context ctx) {
        this.products = products;
        this.ctx = ctx;
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

        Product pr = products.get(position);
        ImageManipulate imageManipulate = new ImageManipulateImpl();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view, null);
        }
        ImageView iv_show = (ImageView) convertView.findViewById(R.id.iv_show);
        TextView tv_showName = (TextView) convertView.findViewById(R.id.tv_showName);

        iv_show.setImageURI(imageManipulate.getImagebyUri(ctx, pr.getImage()));
        tv_showName.setText(pr.getName());
        return convertView;
    }

}


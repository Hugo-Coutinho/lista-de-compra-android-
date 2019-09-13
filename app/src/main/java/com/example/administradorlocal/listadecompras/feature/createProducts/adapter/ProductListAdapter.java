package com.example.administradorlocal.listadecompras.feature.createProducts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.persistence.entity.Product;
import com.example.administradorlocal.listadecompras.util.imageHelper.ImageManipulate;
import com.example.administradorlocal.listadecompras.util.imageHelper.ImageManipulateImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private List<Product> products;
    private Context ctx;

    private List<Product> productsChecked;
    private TextView tv_showName;
    Product product;

    public ProductListAdapter(List<Product> products, Context ctx) {
        this.products = products;
        this.ctx = ctx;
        this.productsChecked = new ArrayList<>();
        this.product = new Product();
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
            convertView = inflater.inflate(R.layout.list_view,parent,false);
        }
        ImageView iv_show = convertView.findViewById(R.id.iv_show);
        tv_showName = convertView.findViewById(R.id.tv_showName);
        final CheckBox cb_item = convertView.findViewById(R.id.cb_item);

        cb_item.setTag(product);
        iv_show.setImageURI(imageManipulate.getImagebyUri(ctx, product.getImage()));
        tv_showName.setText(product.getName());

        cb_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cb_item.isChecked()) {
                    Product p = (Product) cb_item.getTag();
                    productsChecked.add(p);
                    Toast.makeText(v.getContext(), p.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    productsChecked.remove(product);
                }
            }
        });
        return convertView;
    }

    public List<Product> getProductsChecked() {
        return productsChecked;
    }

    public void setProductsChecked(List<Product> productsChecked) {
        this.productsChecked = productsChecked;
    }
}

